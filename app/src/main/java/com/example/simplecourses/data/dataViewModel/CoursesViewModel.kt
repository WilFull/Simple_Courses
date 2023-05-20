package com.example.simplecourses.data.dataViewModel

import android.content.Context
import androidx.lifecycle.*
import com.example.simplecourses.data.room.CoursesEntity
import com.example.simplecourses.data.repository.CoursesRepository

class CoursesViewModel() : ViewModel() {

    var liveDataCourses: LiveData<CoursesEntity>? = null

    fun getCoursesById(context: Context, courseId: Int) : LiveData<CoursesEntity>? {
        liveDataCourses = CoursesRepository.getCourseById(context, courseId)
        return liveDataCourses
    }

    fun getContentByTitle(context: Context, title: String): LiveData<CoursesEntity>? {
        return CoursesRepository.getContentByTitle(context, title)
    }


    /*fun insertData(context: Context, header: String, subheading: String, content: String) {
        CoursesRepository.insertData(context, header, subheading, content)
    }*/
}