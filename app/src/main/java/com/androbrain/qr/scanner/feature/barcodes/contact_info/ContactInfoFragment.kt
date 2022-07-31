package com.androbrain.qr.scanner.feature.barcodes.contact_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentContactInfoBinding
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare

class ContactInfoFragment : Fragment() {
    private var _binding: FragmentContactInfoBinding? = null
    private val binding get() = _binding!!
    private val args: ContactInfoFragmentArgs by navArgs()
    private val contactInfoModel get() = args.contactInfoModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactInfoBinding.inflate(inflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textTitle.text = if (contactInfoModel.title.isNullOrBlank()) {
            getString(R.string.screen_driver_license)
        } else {
            contactInfoModel.title
        }
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.setupShare(
            context = requireContext(),
            raw = contactInfoModel.raw,
            subject = contactInfoModel.title ?: contactInfoModel.display,
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
