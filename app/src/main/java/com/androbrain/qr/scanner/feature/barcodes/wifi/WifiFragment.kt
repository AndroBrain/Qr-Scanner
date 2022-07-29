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
import com.androbrain.qr.scanner.data.wifi.WifiModel
import com.androbrain.qr.scanner.databinding.FragmentWifiBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil
import com.androbrain.qr.scanner.util.share.shareRaw
import com.google.mlkit.vision.barcode.common.Barcode

class WifiFragment : Fragment() {
    private var _binding: FragmentWifiBinding? = null
    private val binding get() = _binding!!
    private val args: WifiFragmentArgs by navArgs()
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
        val wifiModel = args.wifiModel
        textTitle.text = wifiModel.display ?: getString(R.string.screen_wifi)
        recycler.setController(controller)
        controller.info = createControllerInput(wifiModel)
    }

    private fun createControllerInput(wifiModel: WifiModel) = listOfNotNull(
        BarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = wifiModel.scanDate.toString()
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.wifi_encryption_type,
            content = when (wifiModel.encryptionType) {
                Barcode.WiFi.TYPE_OPEN -> getString(R.string.wifi_ssid_open)
                Barcode.WiFi.TYPE_WEP -> getString(R.string.wifi_ssid_wep)
                Barcode.WiFi.TYPE_WPA -> getString(R.string.wifi_ssid_wpa)
                else -> null
            }
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.wifi_ssid,
            content = wifiModel.ssid
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.wifi_password,
            content = wifiModel.password
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_display,
            content = wifiModel.display
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_raw,
            content = wifiModel.raw
        ),
    )

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.menu.findItem(R.id.item_share).apply {
            val wifiModel = args.wifiModel
            isVisible = !wifiModel.raw.isNullOrBlank()
            if (wifiModel.raw != null && wifiModel.raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    requireContext().shareRaw(
                        subject = wifiModel.display ?: wifiModel.ssid,
                        raw = wifiModel.raw
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
