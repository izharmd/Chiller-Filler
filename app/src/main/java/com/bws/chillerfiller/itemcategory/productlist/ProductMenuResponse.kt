package com.bws.chillerfiller.itemcategory.productlist

class ProductMenuResponse : ArrayList<ProductMenuResponseItem>()

data class ProductMenuResponseItem(
    val Category: Any,
    val ChildMenuID: Any,
    val ChildMenuName: Any,
    val ChildMenuUrl: Any,
    val ProductCount: Any,
    val ProductGrouplist: Any,
    val ProductType: Any,
    val ProductTypeChildMenuUrl: Any,
    val Productversion: Any,
    val Productypelist: Any,
    val RestaurantID: Any,
    val TitleGoupName: Any,
    val TitleGrouplist: List<TitleGrouplist>,
    val TitlegroupUrl: Any,
    val TopMenuID: String,
    val TopMenuName: String,
    val TopMenuUrl: String,
    val VaritypopuctCount: Any
)

data class Productypelist(
    val Category: Any,
    val ChildMenuID: String,
    val ChildMenuName: String,
    val ChildMenuUrl: String,
    val ProductCount: String,
    val ProductGrouplist: Any,
    val ProductType: String,
    val ProductTypeChildMenuUrl: String,
    val Productversion: Any,
    val Productypelist: Any,
    val RestaurantID: Any,
    val TitleGoupName: Any,
    val TitleGrouplist: Any,
    val TitlegroupUrl: Any,
    val TopMenuID: Any,
    val TopMenuName: String,
    val TopMenuUrl: Any,
    val VaritypopuctCount: Any
)

data class TitleGrouplist(
    val Category: Any,
    val ChildMenuID: Any,
    val ChildMenuName: Any,
    val ChildMenuUrl: Any,
    val ProductCount: Any,
    val ProductGrouplist: Any,
    val ProductType: Any,
    val ProductTypeChildMenuUrl: Any,
    val Productversion: Any,
    val Productypelist: List<Productypelist>,
    val RestaurantID: Any,
    val TitleGoupName: Any,
    val TitleGrouplist: Any,
    val TitlegroupUrl: Any,
    val TopMenuID: Any,
    val TopMenuName: Any,
    val TopMenuUrl: Any,
    val VaritypopuctCount: Any
)