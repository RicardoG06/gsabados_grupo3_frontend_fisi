package com.example.dogscloud.activities


import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.dogscloud.R

class MyToolbar {
    fun show(activities : AppCompatActivity, title:String, upButton: Boolean){
        activities.setSupportActionBar((activities.findViewById(R.id.toolbar)))
        activities.supportActionBar?.title = title
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}