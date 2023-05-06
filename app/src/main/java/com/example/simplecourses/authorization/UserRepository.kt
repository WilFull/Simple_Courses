package com.example.simplecourses.authorization

import com.example.simplecourses.authorization.UserData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository {
    companion object {
        val instance = UserRepository()
    }

    suspend fun createUser(user: UserData) {
        val collection = FirebaseFirestore.getInstance().collection("users")
        val document = collection.document(user.uid)
        document.set(user).await()
    }

}