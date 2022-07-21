package com.androbrain.qr.scanner.feature.scan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.androbrain.qr.scanner.databinding.FragmentScanBinding
import com.androbrain.qr.scanner.feature.scan.camera.CameraPreview
import dagger.hilt.android.AndroidEntryPoint
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
//        TODO ask for camera permission and display special screen if the user didn't give permission
        _binding = FragmentScanBinding.inflate(inflater)
        setupPreview()
        viewModel.startAnalyzing()
        return binding.root
    }

    private fun setupPreview() = with(binding) {
        cameraPreview.setupPreview(viewLifecycleOwner, previewView.surfaceProvider)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
