package com.example.simplecourses.data.dataViewModel

import android.content.Context
import androidx.lifecycle.*
import com.example.simplecourses.data.room.CoursesEntity
import com.example.simplecourses.data.repository.CoursesRepository

class CoursesViewModel() : ViewModel() {

    var liveDataCourses: LiveData<CoursesEntity>? = null

    fun insertData(context: Context, header: String, subheading: String, content: String) {
        CoursesRepository.insertData(context, header, subheading, content)
    }

    fun getCoursesHeader(context: Context, header: String) : LiveData<CoursesEntity>? {
        liveDataCourses = CoursesRepository.getCoursesHeader(context, header)
        return liveDataCourses
    }

    fun getCoursesSubheading(context: Context, subheading: String) : LiveData<CoursesEntity>? {
        liveDataCourses = CoursesRepository.getCoursesSubheading(context, subheading)
        return liveDataCourses
    }

    fun getCoursesContent(context: Context, content: String) : LiveData<CoursesEntity>? {
        liveDataCourses = CoursesRepository.getCoursesContent(context, content)
        return liveDataCourses
    }
}