package com.androbrain.qr.scanner.feature.barcodes.sms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentSmsBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.sms.SmsMappers.toBarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare
import com.androbrain.qr.scanner.util.context.sendSms
import com.androbrain.qr.scanner.util.view.setupCopyButton

class SmsFragment : Fragment() {
    private var _binding: FragmentSmsBinding? = null
    private val binding get() = _binding!!
    private val args: SmsFragmentArgs by navArgs()
    private val smsModel get() = args.smsModel
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
        textTitle.text = if (smsModel.display.isNullOrBlank()) {
            getString(R.string.screen_sms)
        } else {
            smsModel.display
        }
        recycler.setController(controller)
        controller.info = smsModel.toBarcodeInfo()
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.setupShare(
            context = requireContext(),
            raw = smsModel.raw,
            subject = smsModel.display ?: smsModel.phoneNumber,
        )
        buttonWriteBack.setOnClickListener {
            requireContext().sendSms(smsModel.phoneNumber)
        }
        buttonCopy.setupCopyButton(smsModel.raw)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
