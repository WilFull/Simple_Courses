package com.example.simplecourses

import android.R
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bumptech.glide.manager.Lifecycle
import com.google.firebase.auth.FirebaseAuth
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class testApp {
    @Test
    fun testSignInSuccess() {
        val email = "aleksandr.v.ur@gmail.com"
        val password = "123qwe"

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                assertTrue(task.isSuccessful)
                assertNotNull(FirebaseAuth.getInstance().currentUser)
            }
    }

    @Test
    fun testSignInFailure() {
        val email = "invalid@example.com"
        val password = "invalidpassword"

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                assertFalse(task.isSuccessful)
                assertNull(FirebaseAuth.getInstance().currentUser)
            }
    }

    @Test
    fun testSignUpSuccess() {
        val email = "newuser@example.com"
        val password = "newpassword"

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                assertTrue(task.isSuccessful)
                assertNotNull(FirebaseAuth.getInstance().currentUser)
            }
    }

    @Test
    fun testSignUpFailure() {
        val email = "aleksandr.v.ur@gmail.com"
        val password = "123qwe"

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                assertFalse(task.isSuccessful)
                assertNull(FirebaseAuth.getInstance().currentUser)
            }
    }
}