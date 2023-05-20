package com.example.simplecourses.homeView.repository

import android.graphics.Bitmap

class ImageRepository {
    private var image: Bitmap? = null

    fun getImage(): Bitmap? {
        return image
    }

    fun setImage(bitmap: Bitmap) {
        image = bitmap
    }
}
