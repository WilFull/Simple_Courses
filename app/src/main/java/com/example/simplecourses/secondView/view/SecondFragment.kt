package com.example.simplecourses.secondView.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.simplecourses.R
import com.example.simplecourses.databinding.FragmentHomeScreenBinding
import com.example.simplecourses.databinding.FragmentSecondBinding
import com.example.simplecourses.homeView.viewmodel.BottomNavigationViewModel
import com.example.simplecourses.secondView.viewModel.SecondViewModel
import com.google.android.material.navigation.NavigationBarView
import com.google.firebase.auth.FirebaseAuth

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

        // Получение текущего пользователя
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val email = currentUser.email
            binding.emailUser.text = email
        } else {
            binding.emailUser.text = "Пользователь не авторизован"
        }

        binding.btnExitUser.setOnClickListener {
            // Выход из аккаунта
            FirebaseAuth.getInstance().signOut()

            // Переход на экран авторизации с очисткой бэкстека
            findNavController().popBackStack(R.id.authorizationFragment, false)
        }

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