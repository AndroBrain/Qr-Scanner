package com.androbrain.qr.scanner.feature.barcodes.wifi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.databinding.FragmentWifiBinding

class WifiFragment : Fragment() {
    private var _binding: FragmentWifiBinding? = null
    private val binding get() = _binding!!
    private val args: WifiFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWifiBinding.inflate(inflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        val wifiModel = args.wifiModel
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
