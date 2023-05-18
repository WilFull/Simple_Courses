package com.example.simplecourses.homeView.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecourses.R
import com.example.simplecourses.data.dataViewModel.CoursesViewModel
import com.example.simplecourses.databinding.FragmentHomeScreenBinding
import com.example.simplecourses.homeView.interfaceHomeView.OnClickListenerRecyclerView
import com.example.simplecourses.homeView.adapter.CoursesAdapter
import com.example.simplecourses.homeView.viewmodel.BottomNavigationViewModel
import com.example.simplecourses.homeView.viewmodel.HomeScreenViewModel

class HomeScreenFragment : Fragment(), OnClickListenerRecyclerView {

    private val bottomNavigationViewModel: BottomNavigationViewModel by activityViewModels()

    private lateinit var coursesViewModel: CoursesViewModel
    private lateinit var context: Context
    private lateinit var recyclerView : RecyclerView
    private lateinit var arrayList : ArrayList<Courses>
    private lateinit var adapter : CoursesAdapter
    lateinit var imageId : ArrayList<Int>
    lateinit var titleHeader : ArrayList<String>
    lateinit var titleSubheading : ArrayList<String>

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

        recyclerView.setPadding(0, 0, 0, bottomNavHeight)

        val arrayList = ArrayList<Courses>()

        arrayList.add(Courses(R.drawable.blue_brown_subtle,
            "Курс №1", "Примерно на 30 минут"))
        arrayList.add(Courses(R.drawable.blue_yellow_vibrant,
            "Курс №2", "Примерно на 20 минут"))
        arrayList.add(Courses(R.drawable.blue_brown_vibrant,
            "Курс №3", "Примерно на 50 минут"))
        arrayList.add(Courses(R.drawable.cream_red_vibrant,
            "Курс №4", "Примерно на 30 минут"))
        arrayList.add(Courses(R.drawable.cyan_blue_vibrant,
            "Курс №5", "Примерно на 30 минут"))
        arrayList.add(Courses(R.drawable.green_blue_vibrant,
            "Курс №6", "Примерно на 30 минут"))
        arrayList.add(Courses(R.drawable.green_indigo_vibrant,
            "Курс №7", "Примерно на 30 минут"))
        arrayList.add(Courses(R.drawable.leaf_green_subtle,
            "Курс №8", "Примерно на 30 минут"))
        arrayList.add(Courses(R.drawable.red_green_subtle,
            "Курс №9", "Примерно на 30 минут"))

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

        binding.topAppBar.setNavigationOnClickListener {
            // TODO: Handle navigation icon press
        }

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.user -> {
                    // Тестирование БД
                    /*val header = "Заголовок"
                    val subheading = "Подзаголовок"
                    val content = "Содержимое"
                    val coursesViewModel = CoursesViewModel()
                    coursesViewModel.insertData(context, header, subheading, content)*/
                    // TODO: переход на фрагмент профиля
                    true
                }
                else -> false
            }
        }

        viewModel = ViewModelProvider(this)[HomeScreenViewModel::class.java]
        // TODO: Use the ViewModel
    }


    override fun onClick(course: Courses) {
        // Создание экземпляра целевого фрагмента
        val targetFragment = ContentFragment()

        // Создание Bundle для передачи аргументов
        val args = Bundle().apply {
            putString("titleImage", course.titleImage.toString())
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