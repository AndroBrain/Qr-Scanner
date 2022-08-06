package com.androbrain.qr.scanner.feature.barcodes.driver_license

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentDriverLicenseBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.driver_license.DriverLicenseMappers.toBarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare
import com.androbrain.qr.scanner.util.view.setupCopyButton

class DriverLicenseFragment : Fragment() {
    private var _binding: FragmentDriverLicenseBinding? = null
    private val binding get() = _binding!!
    private val args: DriverLicenseFragmentArgs by navArgs()
    private val driverLicenseModel get() = args.driverLicenseModel
    private val controller by lazy { BarcodeController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriverLicenseBinding.inflate(inflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textTitle.text = if (driverLicenseModel.licenseNumber.isNullOrBlank()) {
            getString(R.string.screen_driver_license)
        } else {
            driverLicenseModel.licenseNumber
        }
        recycler.setController(controller)
        controller.info = driverLicenseModel.toBarcodeInfo(requireContext())
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.setupShare(
            context = requireContext(),
            raw = driverLicenseModel.raw,
            subject = driverLicenseModel.licenseNumber ?: driverLicenseModel.display
        )
        buttonCopy.setupCopyButton(driverLicenseModel.raw)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
