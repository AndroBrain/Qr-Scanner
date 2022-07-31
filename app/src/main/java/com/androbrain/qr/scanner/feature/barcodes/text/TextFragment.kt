package com.androbrain.qr.scanner.feature.barcodes.text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentTextBinding
import com.androbrain.qr.scanner.util.share.shareRaw

class TextFragment : Fragment() {
    private var _binding: FragmentTextBinding? = null
    private val binding get() = _binding!!
    private val args: TextFragmentArgs by navArgs()

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

    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.menu.findItem(R.id.item_share).apply {
            val textModel = args.textModel
            isVisible = !textModel.raw.isNullOrBlank()
            if (textModel.raw != null && textModel.raw.isNotBlank()) {
                setOnMenuItemClickListener {
                    requireContext().shareRaw(
                        subject = textModel.display,
                        raw = textModel.raw
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
