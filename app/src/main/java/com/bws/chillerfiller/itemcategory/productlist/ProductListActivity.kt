package com.bws.chillerfiller.itemcategory.productlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bws.chillerfiller.MyProfileActivity
import com.bws.chillerfiller.R
import com.bws.chillerfiller.common.Constant.Companion.pos
import com.bws.chillerfiller.enotes.EnotesActivity
import com.bws.chillerfiller.itemcategory.ItemCategoryAdapter
import com.bws.chillerfiller.itemcategory.ItemCategoryModel
import com.bws.chillerfiller.itemcategory.basket.BasketsActivity
import com.bws.chillerfiller.itemcategory.cartlist.CartListActivity
import com.bws.chillerfiller.itemcategory.productlist.categorytop.TopCategoryAdapter
import com.bws.chillerfiller.itemcategory.productlist.categorytop.TopCategoryModel
import com.bws.chillerfiller.login.LoginFactory
import com.bws.chillerfiller.login.LoginRepository
import com.bws.chillerfiller.network.ProductListBody
import com.bws.chillerfiller.network.RequestBodies
import com.bws.chillerfiller.orders.SearchOrderActivity
import com.bws.chillerfiller.util.LoadingDialog
import com.bws.chillerfiller.util.Resources2
import com.google.gson.Gson
import com.volcaniccoder.bottomify.BottomifyNavigationView
import com.volcaniccoder.bottomify.OnNavigationItemChangeListener
import kotlinx.android.synthetic.main.activity_productlist.*
import kotlinx.android.synthetic.main.tool_bar_search_view.*

class ProductListActivity : AppCompatActivity() {
    val dataMainCtegory = ArrayList<ItemCategoryModel>()
    var dataTopCategory = ArrayList<TopCategoryModel>()
    val data = ArrayList<ProductListModel>()

    lateinit var populateProductDetailViewModel: PopulateProductDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productlist)
        supportActionBar?.hide()

        productMenu()

        imvSearch.setOnClickListener() {
            //txtSearchProduct.visibility = View.GONE
            searchView.visibility = View.VISIBLE
        }

        imvCart.setOnClickListener() {
            startActivity(Intent(this@ProductListActivity, CartListActivity::class.java))
        }

        bottomify.setOnNavigationItemChangedListener(object : OnNavigationItemChangeListener {
            override fun onNavigationItemChanged(navigationItem: BottomifyNavigationView.NavigationItem) {
                val pos = navigationItem.position
                if (pos == 0) {

                } else if (pos == 1) {
                    startActivity(Intent(this@ProductListActivity, SearchOrderActivity::class.java))
                } else if (pos == 2) {
                    startActivity(Intent(this@ProductListActivity, BasketsActivity::class.java))
                } else if (pos == 3) {
                    startActivity(Intent(this@ProductListActivity, EnotesActivity::class.java))
                } else {
                    startActivity(Intent(this@ProductListActivity, MyProfileActivity::class.java))
                }
            }
        })
    }

    private fun productMenu() {
        val repository = LoginRepository()
        val factory = LoginFactory(repository, this)
        val productListVM =
            ViewModelProvider(this, factory).get(ProductListViewModel::class.java)

        val loadingDialog = LoadingDialog.progressDialog(this)

        productListVM.httpProductMenu(ProductListBody())

        productListVM.result.observe(this, Observer {
            when (it) {
                is Resources2.NoInternet -> {
                    loadingDialog.hide()
                }
                is Resources2.Loading -> {
                    loadingDialog.show()
                }
                is Resources2.Success -> {
                    loadingDialog.hide()
                    for (i in 0..it.data?.size!! - 1) {
                        val pName = it?.data.get(i)
                        val titleGroupList = it?.data.get(i).TitleGrouplist
                        dataMainCtegory.add(
                            ItemCategoryModel(
                                R.drawable.meat,
                                pName.TopMenuName,
                                titleGroupList
                            )
                        )
                        for (j in 0..it?.data.get(i).TitleGrouplist.size - 1) {
                            val glist = it?.data.get(i).TitleGrouplist.get(j).Productypelist
                            for (k in 0..glist.size - 1) {
                                val itm = glist.get(k).ChildMenuName
                                if (i == 0) {
                                    dataTopCategory!!.add(
                                        TopCategoryModel(
                                            R.drawable.premium_cheken,
                                            itm, glist
                                        )
                                    )
                                }
                            }

                        }
                    }

                    recyMainCategory.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    val adapter = ItemCategoryAdapter(dataMainCtegory)
                    recyMainCategory.adapter = adapter
                    adapter.notifyDataSetChanged()


                    recyTopCategory.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                    val adapterTop = TopCategoryAdapter(dataTopCategory!!)
                    recyTopCategory.adapter = adapterTop
                    adapterTop.notifyDataSetChanged()


// USE FOR POPULATE PRODUCT DETAILS IN HOME SCREEN
                    populateProductDetails()
                }
                is Resources2.Error -> {
                    loadingDialog.hide()
                    Toast.makeText(this, "rrrrr", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun populateProductDetails() {

        val repoPopulateProductDetails = LoginRepository()
        val factory = LoginFactory(repoPopulateProductDetails, this)
        populateProductDetailViewModel =
            ViewModelProvider(this, factory).get(PopulateProductDetailViewModel::class.java)
        val loadingDialog = LoadingDialog.progressDialog(this)
        val populateProductDetailsPram =
            RequestBodies.PopulateProductDetailsBody(
                "1",
                "Groceries",
                "Fresh Fruit, Vegetable & Herbs",
                "Fruits",
                "10000000",
                "100000",
                "ProductName",
                "DESC"
            )
        populateProductDetailViewModel.populateProductDetails(populateProductDetailsPram)
        populateProductDetailViewModel.resultPopulateProductDetails.observe(this, Observer {

            when (it) {
                is Resources2.NoInternet -> {
                    loadingDialog.hide()

                }
                is Resources2.Loading -> {
                    loadingDialog.show()
                }
                is Resources2.Success -> {
                    loadingDialog.hide()
                    val productList = it.data?.Productlist
                    for (i in 0..productList?.size!! - 1) {

                        val product = productList.get(i)
                        data.add(
                            ProductListModel(
                                product.ProductImageName, product.ProductName,
                                product.ProductPrice,
                                product.DiscountAmount,
                                "4 hrs",
                                "FRESHO",
                                "5 kg",
                                "",
                                product.AvgRating,
                                "1003 Ratings",
                                "YES"
                            )
                        )
                    }
                    recyProductList.layoutManager = LinearLayoutManager(this)
                    val adapter1 = ProductListAdapter(data)
                    recyProductList.adapter = adapter1
                    adapter1.notifyDataSetChanged()


                    // Toast.makeText(this,userDetails,Toast.LENGTH_LONG).show()
                    // System.out.println("USER DETAILS==" + "111111111111111")
                }
                is Resources2.Error -> {
                    loadingDialog.hide()
                    Toast.makeText(this, "rrrrr", Toast.LENGTH_LONG).show()
                }
            }

        })

    }


    fun topCategory() {


        val categoryItem = dataMainCtegory.get(pos).arrSubCategory
        // val productType = categoryItem


        for (i in 0..categoryItem.size - 1) {
            val cat = categoryItem.get(i).Productypelist
            println("CATE======" + cat)

        }

    }

}