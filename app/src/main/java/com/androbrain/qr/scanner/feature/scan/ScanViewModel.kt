package com.androbrain.qr.scanner.feature.scan

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.BarcodeRepository
import com.androbrain.qr.scanner.data.url.UrlModel
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.androbrain.qr.scanner.feature.scan.camera.QrAnalyzer
import com.androbrain.qr.scanner.util.viewmodel.SingleStateViewModel
import com.androbrain.qr.scanner.util.viewmodel.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import org.threeten.bp.LocalDate

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val qrAnalyzer: QrAnalyzer,
    private val barcodeRepository: BarcodeRepository,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ScanUiState>(savedStateHandle, ScanUiState()) {

    init {
        viewModelScope.launch {
            qrAnalyzer.successesFlow().onEach { bar ->
                Log.d("ScanBarSuccess", "${bar.rawValue} ${bar.valueType}")
                bar.url?.let { bookmark ->
                    val urlModel = UrlModel(
                        title = bookmark.title,
                        url = bookmark.url,
                        creationDate = LocalDate.now(),
                        raw = bar.rawValue,
                    )
                    barcodeRepository.insertUrl(urlModel)
                    updateState { state -> state.copy(scannedBarcode = urlModel) }
                }
//                TODO save the bar and navigate to it's individual screen
            }.launchIn(this)

            qrAnalyzer.failuresFlow().onEach { exception ->
                updateState { state -> state.copy(error = R.string.error_camera_unknown) }
                Log.e("ScanError", exception.toString())
            }.launchIn(this)
        }
    }

    fun clearResult() {
        updateState { state -> state.copy(scannedBarcode = null) }
    }

    fun clearError() {
        updateState { state -> state.copy(error = null) }
    }
}

@Parcelize
data class ScanUiState(
    val scannedBarcode: HistoryBarcode? = null,
    @StringRes val error: Int? = null,
) : UiState
