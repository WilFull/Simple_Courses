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

        fun insertData(context: Context, header: String, subheading: String, content: String) {
            coursesDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val registerDetails = CoursesEntity(header = header, subheading = subheading,
                    content = content)
                coursesDatabase!!.coursesDatabaseDao().insert(registerDetails)
            }
        }

        fun getCoursesHeader(context: Context, header: String) : LiveData<CoursesEntity>? {
            coursesDatabase = initializeDB(context)
            coursesEntityHeader = coursesDatabase!!.coursesDatabaseDao()
                .getCoursesByHeader(header)

            return coursesEntityHeader
        }

        fun getCoursesSubheading(context: Context, subheading: String) : LiveData<CoursesEntity>? {
            coursesDatabase = initializeDB(context)
            coursesEntitySubheading = coursesDatabase!!.coursesDatabaseDao()
                .getCoursesBySubheading(subheading)

            return coursesEntitySubheading
        }

        fun getCoursesContent(context: Context, content: String) : LiveData<CoursesEntity>? {
            coursesDatabase = initializeDB(context)
            coursesEntityContent = coursesDatabase!!.coursesDatabaseDao()
                .getCoursesByContent(content)

            return coursesEntityContent
        }

    }
}