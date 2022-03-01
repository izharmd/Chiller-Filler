package com.bws.chillerfiller.itemcategory.subcategory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bws.chillerfiller.R

class SubCategoryActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subcategory)

        val name = intent.getStringArrayListExtra("Data")

        val str = name?.get(0)
        System.out.println("rrrrrrrrr=="+str)
    }
}