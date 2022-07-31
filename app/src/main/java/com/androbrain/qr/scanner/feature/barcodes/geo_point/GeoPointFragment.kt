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
import com.androbrain.qr.scanner.util.context.shareText

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
        textTitle.text = geoPointModel.display ?: getString(R.string.screen_geo_point)
        recycler.setController(controller)
        controller.info = geoPointModel.toBarcodesInfo()
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.menu.findItem(R.id.item_share).apply {
            val raw = geoPointModel.raw
            isVisible = !raw.isNullOrBlank()
            if (raw != null && raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    requireContext().shareText(
                        subject = geoPointModel.display ?: geoPointModel.display,
                        text = raw
                    )
                    true
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
