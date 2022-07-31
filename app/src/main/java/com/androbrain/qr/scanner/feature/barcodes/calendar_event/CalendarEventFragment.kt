package com.androbrain.qr.scanner.feature.barcodes.calendar_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.databinding.FragmentCalendarEventBinding

class CalendarEventFragment : Fragment() {
    private var _binding: FragmentCalendarEventBinding? = null
    private val binding get() = _binding!!
    private val args: CalendarEventFragmentArgs by navArgs()
    private val calendarEventModel = args.calendarEventModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarEventBinding.inflate(inflater)
        setupViews()
        return binding.root
    }

    private fun setupViews() = with(binding) {
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
