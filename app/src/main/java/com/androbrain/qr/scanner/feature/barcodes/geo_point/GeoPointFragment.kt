package com.androbrain.qr.scanner.feature.barcodes.geo_point

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.databinding.FragmentGeoPointBinding

class GeoPointFragment : Fragment() {
    private var _binding: FragmentGeoPointBinding? = null
    private val binding get() = _binding!!
    private val args: GeoPointFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeoPointBinding.inflate(inflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        val geoPointModel = args.geoPointModel
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
