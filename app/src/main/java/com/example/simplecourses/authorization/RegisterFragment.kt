package com.example.simplecourses.authorization

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.navigation.fragment.findNavController
import com.example.simplecourses.R
import com.example.simplecourses.databinding.FragmentRegisterBinding
import com.example.simplecourses.registration.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Реализация через локальную БД
        /*context = this@RegisterFragment.requireContext()
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]*/

        val email = binding.editTextRegistrationEmailAddress
        val username = binding.editTextRegistrationName
        val password = binding.editTextRegistrationPassword

        val inputUsername = binding.editTextRegistrationName.editText.toString()
        val inputEmail = binding.editTextRegistrationEmailAddress.editText.toString()
        val inputPassword = binding.editTextRegistrationPassword.editText.toString()

        binding.imageViewBackToAutorization.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_authorizationFragment)
        }

        binding.btnRegistrationUser.setOnClickListener {
            // val regex: Regex = "^[a-zA-Z0-9._%+-]+@example\\.com$".toRegex()
            if (username.counterMaxLength > 20) {
                username.error = getString(R.string.errorMaxLimitName)
            }
            else if (username.isEmpty()) {
                username.error = getString(R.string.errorEmptyName)
            }
            else if (email.isEmpty()) {
                email.error = getString(R.string.errorEmptyEmail)
            }
            else if (password.isEmpty()) {
                password.error = getString(R.string.errorEmptyPassword)
            }
            else {


                // Реализация через локальную БД
                /*registerViewModel.insertData(context, username = binding.editTextRegistrationName.editText?.text.toString(),
                    email = binding.editTextRegistrationEmailAddress.editText?.text.toString(),
                    password = binding.editTextRegistrationPassword.editText?.text.toString())
                    Log.e("Првиет", "$id, $inputEmail, $inputUsername, $inputPassword")*/
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}