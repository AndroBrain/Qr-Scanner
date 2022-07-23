package com.androbrain.qr.scanner.feature.scan

import android.net.wifi.ScanResult
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.androbrain.qr.scanner.data.BarcodeRepository
import com.androbrain.qr.scanner.data.url.UrlModel
import com.androbrain.qr.scanner.feature.scan.camera.QrAnalyzer
import com.androbrain.qr.scanner.util.viewmodel.SingleStateViewModel
import com.androbrain.qr.scanner.util.viewmodel.UiState
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val qrAnalyzer: QrAnalyzer,
    private val barcodeRepository: BarcodeRepository,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ScanUiState>(savedStateHandle, ScanUiState()) {

    private var analyzeJob: Job? = null

    fun startAnalyzing() {
        if (analyzeJob != null) {
            return
        }
        analyzeJob = viewModelScope.launch {
            qrAnalyzer.successesFlow().onEach { bar ->
                Log.d("BarSuccess", "${bar.rawValue} ${bar.valueType}")
                bar.url?.let { bookmark ->
                    val urlModel = UrlModel(
                        title = bookmark.title,
                        url = bookmark.url,
                        creationDate = LocalDate.now(),
                        raw = bar.rawValue,
                    )
                    barcodeRepository.insertUrl(urlModel)
                    updateState { state -> state.copy(urlModel = urlModel) }
                }
//                TODO save the bar and navigate to it's individual screen
            }.launchIn(this)

            qrAnalyzer.failuresFlow().onEach { exception ->
//                TODO handle given excpetion
                Log.d("BarFailure", exception.toString())
            }.launchIn(this)
        }
    }

    fun clearResult() {
        updateState { state -> state.copy(urlModel = null) }
    }

}

@Parcelize
data class ScanUiState(
    val urlModel: UrlModel? = null,
) : UiState