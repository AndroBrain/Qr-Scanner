package com.androbrain.qr.scanner.feature.history

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.androbrain.qr.scanner.NavGraphDirections
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentHistoryBinding
import com.androbrain.qr.scanner.feature.history.controller.HistoryController
import com.androbrain.qr.scanner.util.navigation.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val controller by lazy { HistoryController() }
    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater)
        setupViews()
        setupActions()
        setupObservers()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        recycler.layoutManager = GridLayoutManager(
            requireContext(),
            requireContext().resources.getInteger(R.integer.history_columns_count),
            GridLayoutManager.VERTICAL,
            false
        )
        recycler.setController(controller)
    }

    private fun setupActions() = with(binding) {
        layoutHistoryEmpty.buttonScan.setOnClickListener {
            findNavController().safeNavigate(NavGraphDirections.actionGlobalToScanFragment())
        }
    }

    private fun setupObservers() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .onEach { state ->
                    controller.barcodes = state.barcodes
                    layoutHistoryEmpty.root.isVisible = state.barcodes.isEmpty()
                }.launchIn(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
