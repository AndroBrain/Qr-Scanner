package com.androbrain.qr.scanner.feature.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentScanBinding
import com.androbrain.qr.scanner.feature.scan.camera.CameraPreview
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val LINE_ANIM_DURATION = 500L

@AndroidEntryPoint
class ScanFragment : Fragment() {
    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ScanViewModel by viewModels()

    @Inject
    lateinit var cameraPreview: CameraPreview

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScanBinding.inflate(inflater)
        viewModel.clearResult()
        setupViews()
        setupPreview()
        setupObservers()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        scaleUpAndDownScanLine()
    }

    private fun scaleUpAndDownScanLine() {
        _binding?.viewScanLine?.animate()
            ?.alpha(0f)
            ?.setDuration(LINE_ANIM_DURATION)
            ?.withEndAction {
                _binding?.viewScanLine?.animate()
                    ?.alpha(1f)
                    ?.setDuration(LINE_ANIM_DURATION)
                    ?.withEndAction { scaleUpAndDownScanLine() }
            }
    }

    private fun setupPreview() = with(binding) {
        previewView.doOnLayout {
            cameraPreview.setupPreview(viewLifecycleOwner, previewView.surfaceProvider)
        }
    }

    private fun setupObservers() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .onEach { state ->
                    state.scannedBarcode?.let { scannedBarcode ->
                        scannedBarcode.navigateToScreen(findNavController())
                        viewModel.clearResult()
                    }

                    state.error?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        viewModel.clearError()
                    }

                }.launchIn(this)

            cameraPreview.supportTorchMode().onEach { supports ->
                buttonTorch.isVisible = supports
            }.launchIn(this)

            cameraPreview.torchModes().onEach { isTorchOn ->
                if (isTorchOn) {
                    buttonTorch.setImageResource(R.drawable.ic_flash_on)
                } else {
                    buttonTorch.setImageResource(R.drawable.ic_flash_off)
                }
            }.launchIn(this)
        }

    }

    private fun setupActions() = with(binding) {
        buttonTorch.setOnClickListener {
            cameraPreview.changeTorchState()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
