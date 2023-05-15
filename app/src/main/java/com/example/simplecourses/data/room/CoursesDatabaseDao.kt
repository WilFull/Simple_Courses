package com.example.simplecourses.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CoursesDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coursesEntity: CoursesEntity)

    @Query("SELECT * FROM Courses WHERE header = :header")
    fun getCoursesByHeader(header: String?) : LiveData<CoursesEntity>

    @Query("SELECT * FROM Courses WHERE Subheading = :subheading")
    fun getCoursesBySubheading(subheading: String?): LiveData<CoursesEntity>

    @Query("SELECT * FROM Courses WHERE Content = :content")
    fun getCoursesByContent(content: String?): LiveData<CoursesEntity>

    /*@Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUser(user: CoursesEntity)*/

    @Query("SELECT * FROM Courses")
    fun getCourses(): List<CoursesEntity>
}