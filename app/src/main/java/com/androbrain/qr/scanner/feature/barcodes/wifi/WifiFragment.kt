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
import com.androbrain.qr.scanner.feature.barcodes.wifi.WifiMappers.toBarcodeInfo
import com.androbrain.qr.scanner.util.context.shareText

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
        textTitle.text = wifiModel.display ?: getString(R.string.screen_wifi)
        recycler.setController(controller)
        controller.info = wifiModel.toBarcodeInfo(requireContext())
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.menu.findItem(R.id.item_share).apply {
            isVisible = !wifiModel.raw.isNullOrBlank()
            val raw = wifiModel.raw
            if (raw != null && raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    requireContext().shareText(
                        subject = wifiModel.display ?: wifiModel.ssid,
                        text = raw
                    )
                    true
                }
            }
        }

        buttonJoinWifi.setOnClickListener {
            Toast.makeText(requireContext(), "TODO join wifi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
