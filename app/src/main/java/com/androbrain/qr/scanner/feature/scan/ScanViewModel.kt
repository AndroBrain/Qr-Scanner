package com.androbrain.qr.scanner.feature.scan

import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.BarcodeRepository
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

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val qrAnalyzer: QrAnalyzer,
    private val barcodeRepository: BarcodeRepository,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ScanUiState>(savedStateHandle, ScanUiState()) {

    init {
        viewModelScope.launch {
            qrAnalyzer.successesFlow().onEach { barcode ->
                Log.d("ScanBarSuccess", "${barcode.rawValue} ${barcode.valueType}")
                val scannedBarcode = barcodeRepository.insertBarcode(barcode)
                updateState { state -> state.copy(scannedBarcode = scannedBarcode) }
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
