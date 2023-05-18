package com.example.simplecourses.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.simplecourses.data.room.CoursesDatabase
import com.example.simplecourses.data.room.CoursesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CoursesRepository {

    companion object {
        var coursesDatabase: CoursesDatabase? = null
        var coursesEntityHeader: LiveData<CoursesEntity>? = null
        var coursesEntitySubheading: LiveData<CoursesEntity>? = null
        var coursesEntityContent: LiveData<CoursesEntity>? = null

        private fun initializeDB(context: Context) : CoursesDatabase {
            return CoursesDatabase.getInstance(context)
        }

        fun getCourseById(context: Context, courseId: Int): LiveData<CoursesEntity> {
            coursesDatabase = initializeDB(context)
            return coursesDatabase!!.coursesDatabaseDao().getCourseById(courseId)
        }

        /*fun getCourses(context: Context): List<CoursesEntity> {
            coursesDatabase = initializeDB(context)
            return coursesDatabase!!.coursesDatabaseDao().getCourses()
        }*/

        /*fun insertData(context: Context, header: String, subheading: String, content: String) {
            coursesDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val registerDetails = CoursesEntity(header = header, subheading = subheading,
                    content = content)
                coursesDatabase!!.coursesDatabaseDao().insert(registerDetails)
            }
        }*/
    }
}