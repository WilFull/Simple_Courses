package com.example.simplecourses.firebase.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthorizationViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String, password: String): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                result.value = task.isSuccessful
            }

        return result
    }

}