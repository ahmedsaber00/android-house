package com.atef.clubhouse.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.atef.clubhouse.AuthGraphDirections
import com.atef.clubhouse.R
import com.atef.clubhouse.base.extension.viewBinding
import com.atef.clubhouse.databinding.FragmentHomeBinding
import com.atef.clubhouse.domain.entity.auth.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private val binding: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            observeNavigation(viewLifecycleOwner, ::handleNavigation)
            user.observe(viewLifecycleOwner, ::handleUserData)
        }
        binding.logoutBtn.setOnClickListener { viewModel.navigateToLogout() }
        viewModel.getUser()
    }

    private fun handleNavigation(navigation: HomeNavigation) {
        if (navigation is HomeNavigation.Logout)
            findNavController().navigate(AuthGraphDirections.actionGlobalAuthGraph())
    }

    private fun handleUserData(user: User?) {
        binding.welcomeTxt.text = getString(R.string.welcome_message, user?.name ?: "")
    }

}
