package com.androbrain.qr.scanner.feature.barcodes.calendar_event

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentCalendarEventBinding
import com.androbrain.qr.scanner.feature.barcodes.calendar_event.CalendarEventMappers.toBarcodeInfo
import com.androbrain.qr.scanner.feature.barcodes.controller.BarcodeController
import com.androbrain.qr.scanner.feature.barcodes.util.BarcodesUtil.setupShare
import com.androbrain.qr.scanner.util.view.addCalendarEvent
import com.androbrain.qr.scanner.util.view.setupCopyButton

class CalendarEventFragment : Fragment() {
    private var _binding: FragmentCalendarEventBinding? = null
    private val binding get() = _binding!!
    private val args: CalendarEventFragmentArgs by navArgs()
    private val calendarEventModel get() = args.calendarEventModel
    private val controller by lazy { BarcodeController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarEventBinding.inflate(inflater)
        setupViews()
        setupActions()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        textTitle.text = if (calendarEventModel.display.isNullOrBlank()) {
            getString(R.string.screen_calendar_event)
        } else {
            calendarEventModel.display
        }
        recycler.setController(controller)
        controller.info = calendarEventModel.toBarcodeInfo(requireContext())
    }

    private fun setupActions() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        toolbar.setupShare(
            context = requireContext(),
            raw = calendarEventModel.raw,
            subject = calendarEventModel.summary ?: calendarEventModel.display
        )
        buttonAddToCalendar.addCalendarEvent(calendarEventModel)
        buttonCopy.setupCopyButton(calendarEventModel.raw)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
