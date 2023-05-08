package com.example.simplecourses.firebase.auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.simplecourses.R
import com.example.simplecourses.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputEmail = binding.editTextRegistrationEmailAddress.editText
        val inputPassword = binding.editTextRegistrationPassword.editText

        binding.imageViewBackToAutorization.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_authorizationFragment)
        }

        binding.btnRegistrationUser.setOnClickListener {
            createAccount(inputEmail!!.text.toString(), inputPassword!!.text.toString())
        }

        auth = Firebase.auth
    }

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")

        if (!validateForm()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Проверьте правильность введённых данных",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun reload() {
        auth.currentUser!!.reload().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateUI(auth.currentUser)
                Toast.makeText(context, "Reload successful!", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "reload", task.exception)
                Toast.makeText(context, "Failed to reload user.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = binding.editTextRegistrationEmailAddress.editText?.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.editTextRegistrationEmailAddress.error = "Заполните поле"
            valid = false
        } else {
            binding.editTextRegistrationEmailAddress.error = null
        }

        val password = binding.editTextRegistrationPassword.editText?.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.editTextRegistrationPassword.error = "Заполните поле"
            valid = false
        } else {
            binding.editTextRegistrationPassword.error = null
        }

        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            findNavController().navigate(R.id.action_registerFragment_to_authorizationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}