package com.androbrain.qr.scanner.feature.navigation

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.androbrain.qr.scanner.R
import com.androbrain.qr.scanner.databinding.FragmentNavigationBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter

@AndroidEntryPoint
class NavigationFragment : Fragment() {
    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        binding.root.applyInsetter {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                type(navigationBars = true) {
                    margin()
                }
            }
        }
        setupBottomNav()
        return binding.root
    }

    private fun setupBottomNav() = with(binding) {
        val navFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navFragment.navController
        NavigationUI.setupWithNavController(navBottomBar, navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
