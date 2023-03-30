package com.example.simplecourses.registration.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RegisterEntity::class], version = 1, exportSchema = false)
abstract class RegisterDatabase : RoomDatabase() {

    abstract fun registerDatabaseDao(): RegisterDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: RegisterDatabase? = null

        /*getInstance() - это статический метод, используемый для получения экземпляра объекта.
        Он обычно используется в паттерне Singleton, когда нужно гарантировать,
        что только один экземпляр объекта существует в приложении.*/
        fun getInstance(context: Context): RegisterDatabase {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context.applicationContext,
                        RegisterDatabase::class.java, "REGISTER_USER_DATABASE.db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }
    }
}