package com.androbrain.qr.scanner.feature.barcodes.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentPhoneBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.phone.PhoneMappers.toBarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare
import com.androbrain.qr.scanner.util.context.addContact
import com.androbrain.qr.scanner.util.context.dialNumber
import com.androbrain.qr.scanner.util.view.addContact
import com.androbrain.qr.scanner.util.view.dialNumber
import com.androbrain.qr.scanner.util.view.setupCopyButton

class PhoneFragment : Fragment() {
    private var _binding: FragmentPhoneBinding? = null
    private val binding get() = _binding!!
    private val args: PhoneFragmentArgs by navArgs()
    private val phoneModel get() = args.phoneModel
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
        textTitle.text = if (phoneModel.number.isNullOrBlank()) {
            getString(R.string.screen_phone)
        } else {
            phoneModel.number
        }
        recycler.setController(controller)
        controller.info = phoneModel.toBarcodeInfo(requireContext())
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.setupShare(
            context = requireContext(),
            raw = phoneModel.raw,
            subject = phoneModel.number ?: phoneModel.display
        )
        buttonCall.dialNumber(phoneModel.number)
        buttonAddContact.addContact(
            name = phoneModel.display,
            phone = phoneModel.number,
        )
        buttonCopy.setupCopyButton(phoneModel.raw)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
