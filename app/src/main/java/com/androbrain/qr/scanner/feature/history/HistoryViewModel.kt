package com.androbrain.qr.scanner.feature.history

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.androbrain.qr.scanner.data.BarcodeRepository
import com.androbrain.qr.scanner.util.viewmodel.SingleStateViewModel
import com.androbrain.qr.scanner.util.viewmodel.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val barcodeRepository: BarcodeRepository,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<HistoryState>(savedStateHandle, HistoryState()) {

    init {
        viewModelScope.launch {
            barcodeRepository.getHistory().onEach { historyBarcodes ->
                updateState { state ->
                    val allBarcodes = state.barcodes + historyBarcodes
                    state.copy(barcodes = allBarcodes.distinct())
                }
            }.launchIn(this)
        }
    }
}

@Parcelize
data class HistoryState(
    val barcodes: List<HistoryBarcode> = emptyList()
) : UiState
