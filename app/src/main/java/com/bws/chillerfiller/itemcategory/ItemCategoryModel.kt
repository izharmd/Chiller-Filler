package com.bws.chillerfiller.itemcategory

import com.bws.chillerfiller.itemcategory.productlist.TitleGrouplist

data class ItemCategoryModel(
    val image: Int,
    val category: String,
    val arrSubCategory: List<TitleGrouplist>
)

