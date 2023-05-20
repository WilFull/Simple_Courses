package com.example.simplecourses.homeView.view

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.simplecourses.R
import com.example.simplecourses.data.dataViewModel.CoursesViewModel
import com.example.simplecourses.data.room.CoursesDatabase
import com.example.simplecourses.databinding.FragmentContentBinding
import com.example.simplecourses.databinding.FragmentHomeScreenBinding
import com.example.simplecourses.homeView.repository.ImageRepository
import java.io.Serializable

class ContentFragment : Fragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!

    private lateinit var coursesViewModel: CoursesViewModel
    private lateinit var database: CoursesDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        coursesViewModel = ViewModelProvider(this).get(CoursesViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получение аргументов фрагмента
        val args = arguments
        if (args != null) {
            // val titleImage = arguments?.getString("titleImage")
            val titleHeading = arguments?.getString("titleHeading")
            val titleSubheading = arguments?.getString("titleSubheading")

            val imageUriString = args.getString("imageUri")
            val imageUri = Uri.parse(imageUriString)

            binding.titleImage.setImageURI(imageUri)

            titleHeading?.let {
                binding.titleHeader.text = titleHeading
            }

            titleSubheading?.let {
                binding.titleSubheading.text = titleSubheading
            }

            titleHeading?.let {
                coursesViewModel.getContentByTitle(requireContext(), it)?.observe(viewLifecycleOwner) { coursesEntity ->
                    binding.content.text = coursesEntity?.content
                }
            }
        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}