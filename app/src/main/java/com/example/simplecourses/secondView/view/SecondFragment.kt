package com.example.simplecourses.secondView.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.simplecourses.R
import com.example.simplecourses.databinding.FragmentHomeScreenBinding
import com.example.simplecourses.databinding.FragmentSecondBinding
import com.example.simplecourses.homeView.viewmodel.BottomNavigationViewModel
import com.example.simplecourses.secondView.viewModel.SecondViewModel
import com.google.android.material.navigation.NavigationBarView

class SecondFragment : Fragment() {

    private val bottomNavigationViewModel: BottomNavigationViewModel by activityViewModels()
    private lateinit var viewModel: SecondViewModel

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener { item ->
            bottomNavigationViewModel.selectedItemId = item.itemId
            when (item.itemId) {
                R.id.homeScreenFragment -> {
                    findNavController().navigate(R.id.action_secondFragment_to_homeScreenFragment)
                    true
                }
                R.id.secondFragment -> {

                    true
                }
                R.id.creatorFragment -> {
                    findNavController().navigate(R.id.action_secondFragment_to_creatorFragment)
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = bottomNavigationViewModel.selectedItemId


        viewModel = ViewModelProvider(this)[SecondViewModel::class.java]
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}