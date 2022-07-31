package com.androbrain.qr.scanner.feature.barcodes.email

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentEmailBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.email.EmailMappers.toBarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare

class EmailFragment : Fragment() {
    private var _binding: FragmentEmailBinding? = null
    private val binding get() = _binding!!
    private val args: EmailFragmentArgs by navArgs()
    private val emailModel get() = args.emailModel
    private val controller by lazy { BarcodeController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmailBinding.inflate(inflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textTitle.text = if (emailModel.subject.isNullOrBlank()) {
            getString(R.string.screen_email)
        } else {
            emailModel.subject
        }
        recycler.setController(controller)
        controller.info = emailModel.toBarcodeInfo()
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.setupShare(
            context = requireContext(),
            raw = emailModel.raw,
            subject = emailModel.subject ?: emailModel.display
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
