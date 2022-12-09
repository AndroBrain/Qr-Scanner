package com.androbrain.qr.scanner.feature.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.androbrain.qr.scanner.BuildConfig
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentActivitiesBinding
import com.androbrain.qr.scanner.feature.activities.scan.QrInputAnalyzer
import com.androbrain.qr.scanner.util.context.ActivitiesContextUtils
import com.androbrain.qr.scanner.util.view.canOpen
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentActivities : Fragment() {

    private var _binding: FragmentActivitiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AppInfoViewModel by viewModels()

    @Inject
    lateinit var qrInputAnalyzer: QrInputAnalyzer

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { data ->
                    qrInputAnalyzer.analyze(InputImage.fromFilePath(requireContext(), data))
                }
            }
        }

    private val requestStoragePermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                scanFromGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.activities_read_storage_required),
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitiesBinding.inflate(layoutInflater)
        setupViews()
        setupActions()
        setupObservers()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textAppVersion.text = BuildConfig.VERSION_NAME
    }

    private fun setupActions() = with(binding) {
        setupShareAction()
        setupReviewAction()
        setupScanFromGalleryAction()
    }

    private fun setupReviewAction() = with(binding) {
        val reviewAppIntent = ActivitiesContextUtils.createReviewAppIntent(requireContext())
        if (reviewAppIntent.canOpen(requireContext())) {
            actionReviewApp.setOnClickListener {
                startActivity(reviewAppIntent)
            }
        } else {
            actionReviewApp.isVisible = false
        }
    }

    private fun setupShareAction() = with(binding) {
        val shareAppIntent = ActivitiesContextUtils.createShareAppLinkIntent(requireContext())
        if (shareAppIntent.canOpen(requireContext())) {
            actionShareApp.setOnClickListener {
                startActivity(shareAppIntent)
            }
        } else {
            actionShareApp.isVisible = false
        }
    }

    private fun setupScanFromGalleryAction() = with(binding) {
        actionScanFromGallery.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                scanFromGallery()
            } else {
                requestStoragePermissionLauncher.launch(
                    if (Build.VERSION.SDK_INT < 33) {
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    } else {
                        Manifest.permission.READ_MEDIA_IMAGES
                    }
                )
            }
        }
    }

    private fun scanFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        galleryLauncher.launch(intent)
    }

    private fun setupObservers() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).onEach { state ->
                textScannedQrsNumber.text = state.numberOfBarcodes.toString()

                state.scannedBarcode?.let { scannedBarcode ->
                    scannedBarcode.navigateToScreen(findNavController())
                    viewModel.clearBarcode()
                }

                state.errorMsg?.let { error ->
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                    viewModel.clearErrorMsg()
                }
            }.launchIn(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
