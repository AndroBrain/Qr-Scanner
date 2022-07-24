package com.androbrain.qr.scanner.feature.history

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
import androidx.recyclerview.widget.GridLayoutManager
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentHistoryBinding
import com.androbrain.qr.scanner.feature.history.controller.HistoryController
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

    private fun setupObservers() = with(binding) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .onEach { state ->
                    Log.d("HistoryState", state.toString())
                    controller.barcodes = state.barcodes
                }.launchIn(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}
