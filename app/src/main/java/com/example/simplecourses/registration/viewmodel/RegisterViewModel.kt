package com.example.simplecourses.registration.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.simplecourses.registration.room.RegisterEntity
import com.example.simplecourses.registration.repository.RegisterRepository

class RegisterViewModel() : ViewModel() {

    var liveDataRegister: LiveData<RegisterEntity>? = null

    fun insertData(context: Context, username: String, email: String, password: String) {
        RegisterRepository.insertData(context, username, email, password)
    }

    fun getRegisterUsername(context: Context, username: String) : LiveData<RegisterEntity>? {
        liveDataRegister = RegisterRepository.getRegisterUsername(context, username)
        return liveDataRegister
    }

    fun getRegisterEmail(context: Context, email: String) : LiveData<RegisterEntity>? {
        liveDataRegister = RegisterRepository.getRegisterEmail(context, email)
        return liveDataRegister
    }
}