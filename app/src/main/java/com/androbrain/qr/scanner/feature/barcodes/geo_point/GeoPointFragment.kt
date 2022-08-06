package com.androbrain.qr.scanner.feature.barcodes.geo_point

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentGeoPointBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.geo_point.GeoPointMappers.toBarcodesInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare
import com.androbrain.qr.scanner.util.context.openUrlInBrowser
import com.androbrain.qr.scanner.util.view.setupCopyButton
import java.util.Locale

private const val GOOGLE_MAPS_QUERY_FORMATTER =
    "https://www.google.com/maps/search/?api=1&query=%f, %f"

class GeoPointFragment : Fragment() {
    private var _binding: FragmentGeoPointBinding? = null
    private val binding get() = _binding!!
    private val args: GeoPointFragmentArgs by navArgs()
    private val geoPointModel get() = args.geoPointModel
    private val controller by lazy { BarcodeController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeoPointBinding.inflate(inflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textTitle.text = if (geoPointModel.display.isNullOrBlank()) {
            getString(R.string.screen_geo_point)
        } else {
            geoPointModel.display
        }
        recycler.setController(controller)
        controller.info = geoPointModel.toBarcodesInfo(requireContext())
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.setupShare(
            context = requireContext(),
            raw = geoPointModel.raw,
            subject = geoPointModel.display ?: geoPointModel.display,
        )
        buttonOpenInMap.setOnClickListener {
//            TODO if latitude or longitude are null disable button
            requireContext().openUrlInBrowser(
                String.format(
                    Locale.US,
                    GOOGLE_MAPS_QUERY_FORMATTER,
                    geoPointModel.latitude,
                    geoPointModel.longitude
                )
            )
        }
        buttonCopy.setupCopyButton(geoPointModel.raw)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
