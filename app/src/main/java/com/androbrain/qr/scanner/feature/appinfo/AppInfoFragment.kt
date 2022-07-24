package com.androbrain.qr.scanner.feature.appinfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.androbrain.qr.scanner.BuildConfig
import com.androbrain.qr.scanner.databinding.FragmentAppInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AppInfoFragment : Fragment() {

    private var _binding: FragmentAppInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AppInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppInfoBinding.inflate(layoutInflater)
        setupViews()
        setupObservers()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textAppVersion.text = BuildConfig.VERSION_NAME
    }

    private fun setupObservers() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).onEach { state ->
                textScannedQrsNumber.text = state.numberOfBarcodes.toString()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
