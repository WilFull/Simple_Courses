package com.example.simplecourses.registration.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.navigation.fragment.findNavController
import com.example.simplecourses.R
import com.example.simplecourses.databinding.FragmentRegisterBinding
import com.example.simplecourses.registration.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    /*private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!*/
    private  lateinit var binding: FragmentRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var context: Context
   /* lateinit var strUsername: String
    lateinit var strEmail: String
    lateinit var strPassword: String*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root*/
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context = this@RegisterFragment.requireContext()

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

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
                registerViewModel.insertData(context, username = binding.editTextRegistrationName.editText?.text.toString(),
                    email = binding.editTextRegistrationEmailAddress.editText?.text.toString(),
                    password = binding.editTextRegistrationPassword.editText?.text.toString())
                // Toast.makeText(requireContext(), "$inputUsername, $inputEmail, $inputPassword",
                    // Toast.LENGTH_LONG).show()
                Log.e("Првиет", "$id, $inputEmail, $inputUsername, $inputPassword")
            }
            /*when {
                username.counterMaxLength > 20 ->
                    username.error = getString(R.string.errorMaxLimitName)

                username.isEmpty() -> username.error = getString(R.string.errorEmptyName)
            }

            when {
                email.isEmpty() -> email.error = getString(R.string.errorEmptyEmail)
                // TODO: Выполнить проверку на постфикс
            }

            when {
                password.isEmpty() -> password.error = getString(R.string.errorEmptyPassword)
            }*/
        }
    }

    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/
}