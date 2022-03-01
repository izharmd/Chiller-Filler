package com.bws.chillerfiller.itemcategory.productlist.categorytop

import com.bws.chillerfiller.itemcategory.productlist.Productypelist
import com.bws.chillerfiller.itemcategory.productlist.TitleGrouplist

data class TopCategoryModel(
    val topImage: Int,
    val categoryName: String,
    val arrChicken: List<Productypelist>,
   // val arrChickenImg: ArrayList<Int>
)
