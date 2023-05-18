package com.example.simplecourses.homeView.view

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.simplecourses.R
import com.example.simplecourses.data.room.CoursesDatabase
import com.example.simplecourses.databinding.FragmentContentBinding
import com.example.simplecourses.databinding.FragmentHomeScreenBinding
import java.io.Serializable

class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получение аргументов фрагмента
        val args = arguments
        if (args != null) {
            val titleImage = arguments?.getString("titleImage")
            val titleHeading = arguments?.getString("titleHeading")
            val titleSubheading = arguments?.getString("titleSubheading")

            titleImage?.let {
                // val imageUri = Uri.parse(it)
                /*binding.titleImage.setImageURI(imageUri)*/

                /*Glide.with(binding.content)
                    .load(titleImage)
                    .into(binding.titleImage)*/

                val bitmap = BitmapFactory.decodeFile(titleImage)
                binding.titleImage.setImageBitmap(bitmap)
            }

            titleHeading?.let {
                binding.titleHeader.text = titleHeading
            }

            titleSubheading?.let {
                binding.titleSubheading.text = titleSubheading
            }
        }


        val database =
            context?.let { Room.databaseBuilder(it, CoursesDatabase::class.java, "app-database").build() }
        val coursesDao = database?.coursesDatabaseDao()

        // Получение курса по ID
        /*val courseId = 1
        val course = coursesDatabaseDao.getCourseById(courseId)
        val header = course.header
        val subheading = course.subheading
        val content = course.content*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}