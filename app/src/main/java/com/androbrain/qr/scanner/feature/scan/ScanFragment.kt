package com.androbrain.qr.scanner.feature.scan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.androbrain.qr.scanner.databinding.FragmentScanBinding
import com.androbrain.qr.scanner.feature.scan.camera.CameraPreview
import com.androbrain.qr.scanner.util.navigation.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

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
        setupPreview()
        setupObservers()
        viewModel.startAnalyzing()
        return binding.root
    }

    private fun setupPreview() = with(binding) {
        cameraPreview.setupPreview(viewLifecycleOwner, previewView.surfaceProvider)
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .onEach { state ->
                    state.urlModel?.let { urlModel ->
                        findNavController().safeNavigate(
                            ScanFragmentDirections.actionScanFragmentToUrlFragment(
                                urlModel = urlModel
                            )
                        )
                        viewModel.clearResult()
                    }
                }.launchIn(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
