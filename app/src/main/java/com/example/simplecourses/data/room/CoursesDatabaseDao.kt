package com.example.simplecourses.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CoursesDatabaseDao {

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coursesEntity: CoursesEntity)*/

    @Query("SELECT * FROM Courses WHERE id = :courseId")
    fun getCourseById(courseId: Int): LiveData<CoursesEntity>

    /*@Query("SELECT * FROM Courses")
    fun getCourses(): List<CoursesEntity>*/

}