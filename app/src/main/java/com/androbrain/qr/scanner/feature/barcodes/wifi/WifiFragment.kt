package com.androbrain.qr.scanner.feature.barcodes.wifi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentWifiBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare
import com.androbrain.qr.scanner.feature.barcodes.wifi.WifiMappers.toBarcodeInfo
import com.androbrain.qr.scanner.util.context.shareText
import com.androbrain.qr.scanner.util.view.setupCopyButton

class WifiFragment : Fragment() {
    private var _binding: FragmentWifiBinding? = null
    private val binding get() = _binding!!
    private val args: WifiFragmentArgs by navArgs()
    private val wifiModel get() = args.wifiModel
    private val controller by lazy { BarcodeController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWifiBinding.inflate(inflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textTitle.text = if (wifiModel.display.isNullOrBlank()) {
            getString(R.string.screen_wifi)
        } else {
            wifiModel.display
        }
        recycler.setController(controller)
        controller.info = wifiModel.toBarcodeInfo(requireContext())
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.setupShare(
            context = requireContext(),
            raw = wifiModel.raw,
            subject = wifiModel.ssid ?: wifiModel.display,
        )

        buttonCopy.setupCopyButton(wifiModel.raw)
        buttonJoinWifi.setOnClickListener {
            Toast.makeText(requireContext(), "TODO join wifi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
