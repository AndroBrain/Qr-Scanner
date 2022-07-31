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
import com.androbrain.qr.scanner.util.context.openUrlInBrowser
import com.androbrain.qr.scanner.util.context.shareText
import com.androbrain.qr.scanner.util.date.DateFormattingUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UrlFragment : Fragment() {
    private var _binding: FragmentUrlBinding? = null
    private val binding get() = _binding!!
    private val args: UrlFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUrlBinding.inflate(layoutInflater)
        setupViews()
        setupClicks()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        val urlModel = args.urlModel
        toolbar.subtitle = DateFormattingUtils.formatToDayMonthYear(urlModel.scanDate)

        textTitle.text = if (urlModel.title.isNullOrBlank()) {
            getString(R.string.screen_url)
        } else {
            urlModel.title
        }
        textLink.text = urlModel.url

        textRaw.text = getString(R.string.url_raw, urlModel.raw.orEmpty())
    }

    private fun setupClicks() = with(binding) {
        toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        val urlModel = args.urlModel
        chipShare.setOnClickListener {
            requireContext().shareText(
                subject = urlModel.title ?: urlModel.url.orEmpty(),
                text = urlModel.url.orEmpty(),
                title = urlModel.url.orEmpty(),
            )
        }

        buttonOpen.isVisible = urlModel.url != null
        if (urlModel.url != null) {
            buttonOpen.setOnClickListener {
                requireContext().openUrlInBrowser(urlModel.url)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
