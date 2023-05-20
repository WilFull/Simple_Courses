package com.example.simplecourses.homeView.view

import android.R.attr.data
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecourses.R
import com.example.simplecourses.data.dataViewModel.CoursesViewModel
import com.example.simplecourses.databinding.FragmentHomeScreenBinding
import com.example.simplecourses.homeView.adapter.CoursesAdapter
import com.example.simplecourses.homeView.interfaceHomeView.OnClickListenerRecyclerView
import com.example.simplecourses.homeView.viewmodel.BottomNavigationViewModel
import com.example.simplecourses.homeView.viewmodel.HomeScreenViewModel
import java.io.File


class HomeScreenFragment : Fragment(), OnClickListenerRecyclerView {

    private val bottomNavigationViewModel: BottomNavigationViewModel by activityViewModels()

    private lateinit var coursesViewModel: CoursesViewModel
    private lateinit var context: Context
    private lateinit var recyclerView : RecyclerView
    private lateinit var arrayList : ArrayList<Courses>
    private lateinit var adapter : CoursesAdapter

    private var _binding: FragmentHomeScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeScreenViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val bottomNavigationView = binding.bottomNavigation
        val bottomNavHeight = bottomNavigationView.height

        recyclerView.setPadding(0, 0, 0, 220)
        recyclerView.clipToPadding = true

        val arrayList = ArrayList<Courses>()

        arrayList.add(Courses(R.drawable.green_brown_vibrant,
            "Правильная сортировка мусора", "5 минут"))
        arrayList.add(Courses(R.drawable.blue_yellow_vibrant,
            "Как создать уют", "3 минуты"))
        arrayList.add(Courses(R.drawable.blue_brown_vibrant,
            "Как обсуждать условия на новой работе", "5 минут"))
        arrayList.add(Courses(R.drawable.cream_red_vibrant,
            "Как говорить с детьми про деньги", "5 минут"))
        arrayList.add(Courses(R.drawable.cyan_blue_vibrant,
            "Как защититься от мошенников", "5 минут"))
        arrayList.add(Courses(R.drawable.green_blue_vibrant,
            "Как подготовиться к собеседованию", "7 минут"))
        arrayList.add(Courses(R.drawable.green_indigo_vibrant,
            "Как выбрать и воспитать собаку", "5 минут"))

        adapter = CoursesAdapter(arrayList, this)
        recyclerView.adapter = adapter

        bottomNavigationView.setOnItemSelectedListener { item ->
            bottomNavigationViewModel.selectedItemId = item.itemId
            when (item.itemId) {
                R.id.homeScreenFragment -> {
                    true
                }
                R.id.secondFragment -> {
                    findNavController().navigate(R.id.action_homeScreenFragment_to_secondFragment)
                    true
                }
                R.id.creatorFragment -> {
                    findNavController().navigate(R.id.action_homeScreenFragment_to_creatorFragment)
                    true
                }
                else -> false
            }
        }

        bottomNavigationView.selectedItemId = bottomNavigationViewModel.selectedItemId

        // реализация невыпущенной кнопки в topAppBar
        /*binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.user -> {
                    // TODO: переход на фрагмент профиля
                    true
                }
                else -> false
            }
        }*/

        viewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]
        // TODO: Use the ViewModel
    }


    override fun onClick(course: Courses) {
        // Создание экземпляра целевого фрагмента
        val targetFragment = ContentFragment()

        // Преобразование Drawable в Bitmap
        val drawable = ContextCompat.getDrawable(requireContext(), course.titleImage)

        // Преобразование векторного изображения в Bitmap
        val bitmap = if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else {
            val bitmap = drawable?.let {
                Bitmap.createBitmap(
                    it.intrinsicWidth,
                    drawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
            }
            val canvas = bitmap?.let { Canvas(it) }
            if (canvas != null) {
                drawable.setBounds(0, 0, canvas.width, canvas.height)
            }
            if (drawable != null) {
                if (canvas != null) {
                    drawable.draw(canvas)
                }
            }
            bitmap
        }

        val imageUri = bitmap?.let { bitmapChoice ->
            val imageFile = File(requireContext().cacheDir, "image.jpg")
            imageFile.outputStream().use { outputStream ->
                bitmapChoice.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            }
            imageFile.toURI().toString()
        }

        // Создание Bundle для передачи аргументов
        val args = Bundle().apply {
            putString("imageUri", imageUri)
            putString("titleSubheading", course.title_subheading)
            putString("titleHeading", course.title_heading)
        }

        // Установка аргументов для фрагмента
        targetFragment.arguments = args
        findNavController().navigate(R.id.action_homeScreenFragment_to_contentFragment, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}