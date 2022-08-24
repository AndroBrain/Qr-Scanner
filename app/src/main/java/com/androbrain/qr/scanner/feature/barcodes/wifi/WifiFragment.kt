package com.androbrain.qr.scanner.feature.barcodes.wifi

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiNetworkSpecifier
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentWifiBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare
import com.androbrain.qr.scanner.feature.barcodes.wifi.WifiMappers.toBarcodeInfo
import com.androbrain.qr.scanner.util.view.canOpen
import com.androbrain.qr.scanner.util.view.setupCopyButton
import com.google.mlkit.vision.barcode.common.Barcode

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

        buttonJoinWifi.setOnClickListener {
            val wifiNetworkBuilder = WifiNetworkSpecifier.Builder()
                .setSsid(wifiModel.ssid.orEmpty())

            when (wifiModel.encryptionType) {
                Barcode.WiFi.TYPE_OPEN -> Unit
                Barcode.WiFi.TYPE_WEP -> wifiNetworkBuilder.setWpa2Passphrase(wifiModel.password.orEmpty())
                Barcode.WiFi.TYPE_WPA -> wifiNetworkBuilder.setWpa2Passphrase(wifiModel.password.orEmpty())
                else -> wifiNetworkBuilder.setWpa2Passphrase(wifiModel.password.orEmpty())
            }

            val networkRequest = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .setNetworkSpecifier(wifiNetworkBuilder.build())
                .build()

            val connectivityManager =
                requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.requestNetwork(
                networkRequest,
                object : ConnectivityManager.NetworkCallback() {
                    override fun onUnavailable() {
                        super.onUnavailable()
                        context?.let {
                            Toast.makeText(it, R.string.wifi_unavailable, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
        buttonCopyPassword.setupCopyButton(wifiModel.password)
        val wifiSettingsIntent = Intent(Settings.ACTION_WIFI_SETTINGS)
        val canOpenWifiSettings = wifiSettingsIntent.canOpen(requireContext())
        buttonOpenSettings.isVisible = canOpenWifiSettings
        if (canOpenWifiSettings) {
            buttonOpenSettings.setOnClickListener {
                startActivity(wifiSettingsIntent)
                Toast.makeText(requireContext(), R.string.wifi_join_message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        buttonCopy.setupCopyButton(wifiModel.raw)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
