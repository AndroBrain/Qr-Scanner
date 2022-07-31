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
import com.androbrain.qr.scanner.util.context.shareText

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
        textTitle.text = phoneModel.number ?: getString(R.string.screen_phone)
        recycler.setController(controller)
        controller.info = phoneModel.toBarcodeInfo(requireContext())
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.menu.findItem(R.id.item_share).apply {
            val raw = phoneModel.raw
            isVisible = raw.isNullOrBlank()
            if (raw != null && raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    requireContext().shareText(
                        subject = phoneModel.display ?: phoneModel.number,
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
