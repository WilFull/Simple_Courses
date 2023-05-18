package com.example.simplecourses.homeView.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecourses.R
import com.example.simplecourses.databinding.ListItemBinding
import com.example.simplecourses.homeView.interfaceHomeView.OnClickListenerRecyclerView
import com.example.simplecourses.homeView.view.Courses

class CoursesAdapter(private val coursesList : ArrayList<Courses>,
                     private val listener: OnClickListenerRecyclerView
) :
    RecyclerView.Adapter<CoursesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(coursesList[position], listener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ListItemBinding.bind(itemView)
        fun bind (course: Courses, listener: OnClickListenerRecyclerView) = with(binding) {
            titleImage.setImageResource(course.titleImage)
            titleHeader.text = course.title_heading
            titleSubheading.text = course.title_subheading
            itemView.setOnClickListener {
                listener.onClick(course)
            }
        }
    }
}