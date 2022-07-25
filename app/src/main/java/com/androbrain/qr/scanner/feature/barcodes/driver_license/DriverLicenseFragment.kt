package com.androbrain.qr.scanner.feature.barcodes.driver_license

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.databinding.FragmentDriverLicenseBinding

class DriverLicenseFragment : Fragment() {
    private var _binding: FragmentDriverLicenseBinding? = null
    private val binding get() = _binding!!
    private val args: DriverLicenseFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriverLicenseBinding.inflate(inflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        val driverLicenseModel = args.driverLicenseModel

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
