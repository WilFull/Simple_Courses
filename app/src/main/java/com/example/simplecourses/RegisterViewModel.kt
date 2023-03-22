package com.example.simplecourses

import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    // объявление переменных для хранения данных регистрации
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    var password: String = ""

    // метод для обработки логики регистрации
    fun register() {

    }

    fun validateData(): Boolean {
        // Ваш код для проверки введенных данных пользователя
        return true
    }
}