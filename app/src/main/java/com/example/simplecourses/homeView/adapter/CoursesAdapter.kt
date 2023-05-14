package com.example.simplecourses.homeView.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecourses.R
import com.example.simplecourses.homeView.view.Courses
import com.google.android.material.imageview.ShapeableImageView

class CoursesAdapter(private val coursesList : ArrayList<Courses>) :
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
        val currentItem = coursesList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.titleHeader.text = currentItem.title_heading
        holder.titleSubheading.text = currentItem.title_subheading
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titleImage : ShapeableImageView = itemView.findViewById(R.id.title_image)
        val titleHeader : TextView = itemView.findViewById(R.id.title_header)
        val titleSubheading : TextView = itemView.findViewById(R.id.title_subheading)
    }
}