package com.androbrain.qr.scanner.feature.barcodes.geo_point

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.geo_point.GeoPointModel
import com.androbrain.qr.scanner.databinding.FragmentGeoPointBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil
import com.androbrain.qr.scanner.util.context.shareText

class GeoPointFragment : Fragment() {
    private var _binding: FragmentGeoPointBinding? = null
    private val binding get() = _binding!!
    private val args: GeoPointFragmentArgs by navArgs()
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
        val geoPointModel = args.geoPointModel
        textTitle.text = geoPointModel.display ?: getString(R.string.screen_geo_point)
        recycler.setController(controller)
        controller.info = createControllerInput(geoPointModel)
    }

    private fun createControllerInput(geoPointModel: GeoPointModel) = listOfNotNull(
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_scan_date,
            content = geoPointModel.scanDate.toString(),
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.geo_point_latitude,
            content = geoPointModel.latitude.toString(),
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.geo_point_longitude,
            content = geoPointModel.longitude.toString(),
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_display,
            content = geoPointModel.display,
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_raw,
            content = geoPointModel.raw,
        ),
    )

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.menu.findItem(R.id.item_share).apply {
            val geoPointModel = args.geoPointModel
            isVisible = !geoPointModel.raw.isNullOrBlank()
            if (geoPointModel.raw != null && geoPointModel.raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    requireContext().shareText(
                        subject = geoPointModel.display ?: geoPointModel.display,
                        text = geoPointModel.raw
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
