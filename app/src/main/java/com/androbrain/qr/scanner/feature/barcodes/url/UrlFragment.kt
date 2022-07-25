package com.androbrain.qr.scanner.feature.barcodes.url

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentUrlBinding
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

        chipShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_SUBJECT,
                args.urlModel.title ?: args.urlModel.url.orEmpty()
            )
            shareIntent.putExtra(Intent.EXTRA_TEXT, args.urlModel.url.orEmpty())
            startActivity(Intent.createChooser(shareIntent, args.urlModel.url.orEmpty()))
        }

        buttonOpen.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(args.urlModel.url))
            startActivity(browserIntent)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
