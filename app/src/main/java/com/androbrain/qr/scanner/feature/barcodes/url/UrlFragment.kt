package com.androbrain.qr.scanner.feature.barcodes.url

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentUrlBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.url.UrlMappers.toBarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare
import com.androbrain.qr.scanner.util.context.openUrlInBrowser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UrlFragment : Fragment() {
    private var _binding: FragmentUrlBinding? = null
    private val binding get() = _binding!!
    private val args: UrlFragmentArgs by navArgs()
    private val urlModel get() = args.urlModel
    private val controller by lazy { BarcodeController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUrlBinding.inflate(layoutInflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textTitle.text = if (urlModel.title.isNullOrBlank()) {
            getString(R.string.screen_url)
        } else {
            urlModel.title
        }
        recycler.setController(controller)
        controller.info = urlModel.toBarcodeInfo()
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.setupShare(
            context = requireContext(),
            raw = urlModel.url,
            subject = urlModel.title ?: urlModel.display,
        )

        val url = urlModel.url
        buttonOpen.isVisible = url != null
        if (url != null) {
            buttonOpen.setOnClickListener {
                requireContext().openUrlInBrowser(url)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
