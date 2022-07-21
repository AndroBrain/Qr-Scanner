package com.androbrain.qr.scanner.feature.scan

import android.net.wifi.ScanResult
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.androbrain.qr.scanner.feature.scan.camera.QrAnalyzer
import com.androbrain.qr.scanner.util.viewmodel.SingleStateViewModel
import com.androbrain.qr.scanner.util.viewmodel.UiState
import com.google.mlkit.vision.barcode.common.Barcode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val qrAnalyzer: QrAnalyzer,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<ScanUiState>(savedStateHandle, ScanUiState()) {

    fun startAnalyzing() {
        viewModelScope.launch {
            qrAnalyzer.successesFlow().onEach {
                Log.d("BarSuccess", it.rawValue.toString())
            }.launchIn(this)

            qrAnalyzer.failuresFlow().onEach {
                Log.d("BarFailure", it.toString())
            }.launchIn(this)
        }
    }

}

@Parcelize
data class ScanUiState(
    val barcode: String? = null,
) : UiState