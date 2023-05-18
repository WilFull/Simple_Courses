package com.example.simplecourses.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Courses")
data class CoursesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "header") val header: String,
    @ColumnInfo(name = "subheading") val subheading: String,
    @ColumnInfo(name = "content") val content: String
)
