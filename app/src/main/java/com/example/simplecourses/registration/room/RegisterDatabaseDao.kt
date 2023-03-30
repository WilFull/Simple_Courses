package com.example.simplecourses.registration.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RegisterDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(registerEntity: RegisterEntity)

    @Query("SELECT * FROM Register_user WHERE username = :username")
    fun getUserByUsername(username: String?) : LiveData<RegisterEntity>

    @Query("SELECT * FROM Register_user WHERE email = :email")
    fun getUserByEmail(email: String?): LiveData<RegisterEntity>

    @Query("DELETE FROM Register_user WHERE username = :username")
    fun deleteUserByUsername(username: String)

    @Query("DELETE FROM Register_user WHERE email = :email")
    fun deleteUserByEmail(email: String)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: RegisterEntity)

    @Query("SELECT * FROM Register_user")
    fun getUsers(): List<RegisterEntity>
}