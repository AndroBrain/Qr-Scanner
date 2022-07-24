package com.androbrain.qr.scanner.feature.appinfo

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
class AppInfoViewModel @Inject constructor(
    private val barcodeRepository: BarcodeRepository,
    savedStateHandle: SavedStateHandle,
) : SingleStateViewModel<AppInfoState>(savedStateHandle, AppInfoState()) {
    init {
        viewModelScope.launch {
            barcodeRepository.getHistory().onEach {
                updateState { state -> state.copy(numberOfBarcodes = it.size) }
            }.launchIn(this)
        }
    }
}

@Parcelize
data class AppInfoState(
    val numberOfBarcodes: Int = 0,
) : UiState
