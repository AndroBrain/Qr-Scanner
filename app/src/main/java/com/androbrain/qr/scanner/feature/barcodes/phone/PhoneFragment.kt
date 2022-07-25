package com.androbrain.qr.scanner.feature.barcodes.phone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.databinding.FragmentPhoneBinding

class PhoneFragment : Fragment() {
    private var _binding: FragmentPhoneBinding? = null
    private val binding get() = _binding!!
    private val args: PhoneFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneBinding.inflate(inflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        val phoneModel = args.phoneModel
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
