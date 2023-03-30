package com.example.simplecourses.registration.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.simplecourses.registration.room.RegisterDatabase
import com.example.simplecourses.registration.room.RegisterEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RegisterRepository {

    companion object {
        var registerDatabase: RegisterDatabase? = null

        var registerEntityUsername: LiveData<RegisterEntity>? = null
        var registerEntityEmail: LiveData<RegisterEntity>? = null

        fun initializeDB(context: Context) : RegisterDatabase {
            return RegisterDatabase.getInstance(context)
        }

        fun insertData(context: Context, username: String, email: String, password: String) {
            registerDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val registerDetails = RegisterEntity(username = username, email = email,
                    password = password)
                registerDatabase!!.registerDatabaseDao().insert(registerDetails)
            }
        }

        fun getRegisterUsername(context: Context, username: String) : LiveData<RegisterEntity>? {

            registerDatabase = initializeDB(context)
            registerEntityUsername = registerDatabase!!.registerDatabaseDao()
                .getUserByUsername(username)

            return registerEntityEmail
        }

        fun getRegisterEmail(context: Context, email: String) : LiveData<RegisterEntity>? {

            registerDatabase = initializeDB(context)
            registerEntityUsername = registerDatabase!!.registerDatabaseDao()
                .getUserByEmail(email)

            return registerEntityEmail
        }


    }
}