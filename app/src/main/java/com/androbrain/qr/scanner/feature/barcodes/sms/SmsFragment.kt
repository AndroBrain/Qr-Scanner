package com.androbrain.qr.scanner.feature.barcodes.sms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.sms.SmsModel
import com.androbrain.qr.scanner.databinding.FragmentSmsBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil
import com.androbrain.qr.scanner.util.context.shareText

class SmsFragment : Fragment() {
    private var _binding: FragmentSmsBinding? = null
    private val binding get() = _binding!!
    private val args: SmsFragmentArgs by navArgs()
    private val controller by lazy { BarcodeController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSmsBinding.inflate(inflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        val smsModel = args.smsModel
        textTitle.text = smsModel.display ?: getString(R.string.screen_sms)
        recycler.setController(controller)
        controller.info = createControllerInput(smsModel)
    }

    private fun createControllerInput(smsModel: SmsModel) = listOfNotNull(
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_scan_date,
            content = smsModel.scanDate.toString(),
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.sms_message,
            content = smsModel.message,
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.sms_phone,
            content = smsModel.phoneNumber,
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_display,
            content = smsModel.display,
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_raw,
            content = smsModel.raw,
        ),
    )

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.menu.findItem(R.id.item_share).apply {
            val smsModel = args.smsModel
            isVisible = !smsModel.raw.isNullOrBlank()
            if (smsModel.raw != null && smsModel.raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    requireContext().shareText(
                        subject = smsModel.display ?: smsModel.phoneNumber,
                        text = smsModel.raw
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
