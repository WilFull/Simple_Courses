package com.example.simplecourses.creatorView.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.simplecourses.R
import com.example.simplecourses.databinding.FragmentCreatorBinding
import com.example.simplecourses.databinding.FragmentHomeScreenBinding
import com.example.simplecourses.homeView.viewmodel.BottomNavigationViewModel
import com.google.android.material.navigation.NavigationBarView

class CreatorFragment : Fragment() {

    private val bottomNavigationViewModel: BottomNavigationViewModel by activityViewModels()

    private var _binding: FragmentCreatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener { item ->
            bottomNavigationViewModel.selectedItemId = item.itemId
            when (item.itemId) {
                R.id.homeScreenFragment -> {
                    findNavController().navigate(R.id.action_creatorFragment_to_homeScreenFragment)
                    true
                }
                R.id.secondFragment -> {
                    findNavController().navigate(R.id.action_creatorFragment_to_secondFragment)
                    true
                }
                R.id.creatorFragment -> {
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = bottomNavigationViewModel.selectedItemId

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}