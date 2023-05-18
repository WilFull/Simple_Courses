package com.example.simplecourses.firebase.auth

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.simplecourses.R
import com.example.simplecourses.databinding.FragmentAuthorizationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthorizationFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewToRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment_to_registerFragment)
        }

        binding.buttonContinueAuthorization.setOnClickListener {
            val email = binding.editTextEmailAddress.editText
            val password = binding.editTextPassword.editText
            signIn(email!!.text.toString(), password!!.text.toString())
        }

        auth = Firebase.auth
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        context,
                        "Проверьте корректность",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }

                /*if (!task.isSuccessful) {
                    binding.status.setText(R.string.auth_failed)
                }*/
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            findNavController().navigate(R.id.action_authorizationFragment_to_homeScreenFragment)
        }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = binding.editTextEmailAddress.editText?.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.editTextEmailAddress.error = "Заполните поле"
            valid = false
        } else {
            binding.editTextEmailAddress.error = null
        }

        val password = binding.editTextPassword.editText?.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.editTextPassword.error = "Заполните поле"
            valid = false
        } else {
            binding.editTextPassword.error = null
        }

        return valid
    }

    override fun onStart() {
        super.onStart()
        // Проверьте, вошел ли пользователь в систему (ненулевое значение),
        // и соответствующим образом обновите пользовательский интерфейс.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        auth.currentUser!!.reload().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                updateUI(auth.currentUser)
                Toast.makeText(context, "Вход выполнен!", Toast.LENGTH_SHORT).show()
            } else {
                Log.e(TAG, "reload", task.exception)
                Toast.makeText(context, "Вход не выполнен!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}