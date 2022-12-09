package com.androbrain.qr.scanner.feature.activities

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.BarcodeRepository
import com.androbrain.qr.scanner.feature.activities.scan.QrInputAnalyzer
import com.androbrain.qr.scanner.feature.history.HistoryBarcode
import com.androbrain.qr.scanner.util.viewmodel.SingleStateViewModel
import com.androbrain.qr.scanner.util.viewmodel.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@HiltViewModel
class AppInfoViewModel @Inject constructor(
    private val qrInputAnalyzer: QrInputAnalyzer,
    private val barcodeRepository: BarcodeRepository,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ActivitiesState>(savedStateHandle, ActivitiesState()) {

    init {
        viewModelScope.launch {
            barcodeRepository.getHistory().onEach {
                updateState { state -> state.copy(numberOfBarcodes = it.size) }
            }.launchIn(this)

            qrInputAnalyzer.successesFlow().onEach { barcode ->
                val scannedBarcode = barcodeRepository.insertBarcode(barcode)
                updateState { state -> state.copy(scannedBarcode = scannedBarcode) }
            }.launchIn(this)

            qrInputAnalyzer.failuresFlow().onEach {
                updateState { state -> state.copy(errorMsg = R.string.error_camera_scan) }
            }.launchIn(this)
        }
    }

    fun clearBarcode() {
        updateState { state -> state.copy(scannedBarcode = null) }
    }

    fun clearErrorMsg() {
        updateState { state -> state.copy(errorMsg = null) }
    }
}

@Parcelize
data class ActivitiesState(
    val numberOfBarcodes: Int = 0,
    val scannedBarcode: HistoryBarcode? = null,
    @StringRes val errorMsg: Int? = null,
) : UiState
