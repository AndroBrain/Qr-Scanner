package com.androbrain.qr.scanner.feature.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.androbrain.qr.scanner.databinding.FragmentScanBinding
import com.androbrain.qr.scanner.feature.scan.camera.CameraPreview
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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
        setupPreview()
        setupObservers()
        return binding.root
    }

    private fun setupPreview() = with(binding) {
        previewView.doOnLayout {
            cameraPreview.setupPreview(viewLifecycleOwner, previewView.surfaceProvider)
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .onEach { state ->
                    state.scannedBarcode?.let { scannedBarcode ->
                        scannedBarcode.navigateFromScan(findNavController())
                        viewModel.clearResult()
                    }

                    state.error?.let { error ->
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                        viewModel.clearError()
                    }
                }.launchIn(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
