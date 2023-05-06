package com.example.simplecourses.authorization

import com.example.simplecourses.authorization.UserData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import com.example.simplecourses.authorization.Result
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest

class FirebaseAuthHelper {

    companion object {
        val instance = FirebaseAuthHelper()
    }

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    val currentUser: FirebaseUser?
        get() = auth.currentUser

    val isAuthorized: Boolean
        get() = auth.currentUser != null

    fun check(hasAuth: (Boolean) -> Unit) {
        auth.addAuthStateListener(object : FirebaseAuth.AuthStateListener {
            override fun onAuthStateChanged(fauth: FirebaseAuth) {
                hasAuth(fauth.currentUser != null)
            }
        })
    }

    fun getUser(): UserData? {
        val user = auth.currentUser
        if (user != null) {
            return UserData(user.uid, user.displayName.orEmpty(), user.email.orEmpty())
        }
        return null
    }

    suspend fun signInWithEmail(email: String, password: String): Result<FirebaseUser?> {
        try {
            val response = auth.signInWithEmailAndPassword(email, password).await()
            return Result.Success(auth.currentUser)
        } catch (e: Exception) {
            return Result.Error(e)

        }
    }

    suspend fun register(name: String, email: String, password: String): Result<UserData?> {
        val createResponse = createUser(email, password)
        when (createResponse) {
            is Result.Success<*> -> signInWithEmail(email, password).also { signinResult ->
                when (signinResult) {
                    is Result.Success<*> -> {
                        val currentUser = signinResult.data
                        if (currentUser != null && currentUser is FirebaseUser) {
                            changeProfile(currentUser, name)
                            val newUser = UserData(currentUser.uid, name = name, email = email)
                            try {
                                UserRepository.instance.createUser(newUser)
                                return Result.Success(newUser)
                            } catch (e: java.lang.Exception) {
                                return Result.Error(e)
                            }
                        } else {
                            return Result.Success(null)
                        }

                    }

                    is Result.Error -> {
                        return Result.Error(signinResult.exception)
                    }

                    is Result.Canceled -> {
                        return Result.Canceled(signinResult.exception)
                    }
                }
            }

            is Result.Error -> {
                return Result.Error(createResponse.exception)
            }

            is Result.Canceled -> {
                return Result.Canceled(createResponse.exception)
            }
        }
    }


    suspend fun changeProfile(currentUser: FirebaseUser, name: String) {
        val request = userProfileChangeRequest {
            displayName = name
        }
        currentUser.updateProfile(request).await()
    }

    suspend fun createUser(email: String, password: String): Result<AuthResult?> {
        return try {
            val data = auth
                .createUserWithEmailAndPassword(email, password)
                .await()
            return Result.Success(data)
        } catch (e: Exception) {
            return Result.Error(e)
        }
    }

    fun logout() {
        auth.signOut()
    }

    suspend fun loginUser(email: String, password: String): Result<FirebaseUser?> {
        try {
            val response = auth.signInWithEmailAndPassword(email, password).await()
            return Result.Success(auth.currentUser)
        } catch (e: Exception) {
            return Result.Error(e)


        }
    }
}