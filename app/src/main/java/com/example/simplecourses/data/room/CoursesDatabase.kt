package com.example.simplecourses.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CoursesEntity::class], version = 1, exportSchema = false)
abstract class CoursesDatabase : RoomDatabase() {

    abstract fun coursesDatabaseDao(): CoursesDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: CoursesDatabase? = null

        /*getInstance() - это статический метод, используемый для получения экземпляра объекта.
        Он обычно используется в паттерне Singleton, когда нужно гарантировать,
        что только один экземпляр объекта существует в приложении.*/
        fun getInstance(context: Context): CoursesDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context.applicationContext,
                        CoursesDatabase::class.java, "COURSES_USER_DATABASE.db")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }
    }
}