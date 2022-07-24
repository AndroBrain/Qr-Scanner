package com.androbrain.qr.scanner.feature.scan.permission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.androbrain.qr.scanner.databinding.FragmentCameraPermissionBinding
import com.androbrain.qr.scanner.util.navigation.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraPermissionFragment : Fragment() {
    private var _binding: FragmentCameraPermissionBinding? = null
    private val binding get() = _binding!!

    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                navigateToScanFragment()
            } else {
                Toast.makeText(requireContext(), "Camera access denied", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        val cameraPermission =
            ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            navigateToScanFragment()
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraPermissionBinding.inflate(inflater)
        setupClicks()
        return binding.root
    }

    private fun setupClicks() = with(binding) {
        buttonAllowCamera.setOnClickListener {
            val cameraPermission = Manifest.permission.CAMERA
            cameraPermissionLauncher.launch(cameraPermission)
        }
    }

    private fun navigateToScanFragment() {
        findNavController().safeNavigate(
            CameraPermissionFragmentDirections.actionCameraPermissionFragmentToScanFragment()
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
