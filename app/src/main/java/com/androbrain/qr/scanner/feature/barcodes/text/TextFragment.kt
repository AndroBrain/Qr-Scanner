package com.androbrain.qr.scanner.feature.barcodes.text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.data.text.TextModel
import com.androbrain.qr.scanner.databinding.FragmentTextBinding
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.model.info.BarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil
import com.androbrain.qr.scanner.util.context.copyToClipboard
import com.androbrain.qr.scanner.util.context.openUrlInBrowser
import com.androbrain.qr.scanner.util.context.shareText

class TextFragment : Fragment() {
    private var _binding: FragmentTextBinding? = null
    private val binding get() = _binding!!
    private val args: TextFragmentArgs by navArgs()
    private val controller by lazy { BarcodeController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextBinding.inflate(inflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        recycler.setController(controller)
        controller.info = createControllerInput(args.textModel)
    }

    private fun createControllerInput(textModel: TextModel) = listOfNotNull(
        BarcodeInfo(
            title = R.string.barcodes_scan_date,
            content = textModel.scanDate.toString()
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_display,
            content = textModel.display
        ),
        BarcodesUtil.getBarcodeCardInputOrNull(
            title = R.string.barcodes_raw,
            content = textModel.raw
        ),
    )

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        val textModel = args.textModel
        toolbar.menu.findItem(R.id.item_share).apply {
            isVisible = !textModel.raw.isNullOrBlank()
            if (textModel.raw != null && textModel.raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    requireContext().shareText(
                        subject = textModel.display,
                        text = textModel.raw
                    )
                    true
                }
            }
        }

        buttonCopyText.isVisible = !textModel.raw.isNullOrBlank()
        buttonSearchText.isVisible = !textModel.raw.isNullOrBlank()
        if (textModel.raw != null && textModel.raw.isNotBlank()) {
            buttonCopyText.setOnClickListener { view ->
                view.context.copyToClipboard(
                    text = textModel.raw,
                    label = textModel.display,
                )
            }
            buttonSearchText.setOnClickListener { view ->
                view.context.openUrlInBrowser(textModel.raw)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
