package com.androbrain.qr.scanner.feature.barcodes.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.phone.PhoneModel
import com.androbrain.qr.scanner.databinding.FragmentPhoneBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil
import com.androbrain.qr.scanner.util.share.shareRaw
import com.google.mlkit.vision.barcode.common.Barcode.Phone.TYPE_FAX
import com.google.mlkit.vision.barcode.common.Barcode.Phone.TYPE_HOME
import com.google.mlkit.vision.barcode.common.Barcode.Phone.TYPE_MOBILE
import com.google.mlkit.vision.barcode.common.Barcode.Phone.TYPE_UNKNOWN
import com.google.mlkit.vision.barcode.common.Barcode.Phone.TYPE_WORK

class PhoneFragment : Fragment() {
    private var _binding: FragmentPhoneBinding? = null
    private val binding get() = _binding!!
    private val args: PhoneFragmentArgs by navArgs()
    private val controller by lazy { BarcodeController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneBinding.inflate(inflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        val phoneModel = args.phoneModel
        textTitle.text = phoneModel.number ?: getString(R.string.screen_phone)
        recycler.setController(controller)
        controller.info = createControllerInput(phoneModel)
    }

    private fun createControllerInput(phoneModel: PhoneModel) = listOfNotNull(
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_scan_date,
            content = phoneModel.scanDate.toString(),
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.phone_type,
            content = when (phoneModel.type) {
                TYPE_UNKNOWN -> getString(R.string.phone_unknown)
                TYPE_WORK -> getString(R.string.phone_work)
                TYPE_HOME -> getString(R.string.phone_home)
                TYPE_FAX -> getString(R.string.phone_fax)
                TYPE_MOBILE -> getString(R.string.phone_mobile)
                else -> null
            }
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.phone_number,
            content = phoneModel.number,
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_display,
            content = phoneModel.display,
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_raw,
            content = phoneModel.raw,
        ),
    )

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.menu.findItem(R.id.item_share).apply {
            val phoneModel = args.phoneModel
            isVisible = !phoneModel.raw.isNullOrBlank()
            if (phoneModel.raw != null && phoneModel.raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    requireContext().shareRaw(
                        subject = phoneModel.display ?: phoneModel.number,
                        raw = phoneModel.raw
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
