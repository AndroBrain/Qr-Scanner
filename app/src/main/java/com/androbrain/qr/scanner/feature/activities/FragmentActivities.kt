package com.androbrain.qr.scanner.feature.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.androbrain.qr.scanner.BuildConfig
import com.androbrain.qr.scanner.databinding.FragmentActivitiesBinding
import com.androbrain.qr.scanner.util.context.ActivitiesContextUtils
import com.androbrain.qr.scanner.util.view.canOpen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentActivities : Fragment() {

    private var _binding: FragmentActivitiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AppInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitiesBinding.inflate(layoutInflater)
        setupViews()
        setupActions()
        setupObservers()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textAppVersion.text = BuildConfig.VERSION_NAME
    }

    private fun setupActions() {
        setupShareAction()
        setupReviewAction()
    }

    private fun setupReviewAction() = with(binding) {
        val reviewAppIntent = ActivitiesContextUtils.createReviewAppIntent(requireContext())
        if (reviewAppIntent.canOpen(requireContext())) {
            actionReviewApp.setOnClickListener {
                startActivity(reviewAppIntent)
            }
        } else {
            actionReviewApp.isVisible = false
        }
    }

    private fun setupShareAction() = with(binding) {
        val shareAppIntent = ActivitiesContextUtils.createShareAppLinkIntent(requireContext())
        if (shareAppIntent.canOpen(requireContext())) {
            actionShareApp.setOnClickListener {
                startActivity(shareAppIntent)
            }
        } else {
            actionShareApp.isVisible = false
        }
    }

    private fun setupObservers() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).onEach { state ->
                textScannedQrsNumber.text = state.numberOfBarcodes.toString()
            }.launchIn(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
