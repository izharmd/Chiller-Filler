package com.bws.chillerfiller.itemcategory.productlist

import android.app.Dialog
import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bws.chillerfiller.R
import com.bws.chillerfiller.common.Constant.Companion.addDataToCart
import com.bws.chillerfiller.itemcategory.cartlist.CartListModel
import com.bws.chillerfiller.itemcategory.productlist.addquentity.QuentityAdapater
import com.bws.chillerfiller.itemcategory.productlist.addquentity.QuentityModel
import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration


class ProductListAdapter(val mList: List<ProductListModel>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    var context: Context? = null
    var myInt: Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_product_list, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemProduct = mList[position]
        holder.txtBrandName.text = itemProduct.brandName
        holder.txtDeliveryTime.text = itemProduct.deliveryTime
        holder.txtProductName.text = itemProduct.productName
        holder.txtPrice.text = "£ " + itemProduct.productPrice
        holder.txtQuantity.text = itemProduct.quentity
        holder.txtDiscountPrice.text = itemProduct.productDiscountPrice
        holder.txtOffer.text = itemProduct.offer + " OFF"
        holder.txtRatingPoint.text = itemProduct.ratingPoint
        holder.txtRating.text = itemProduct.ratingStr
        //  holder.txtDiscountPrice.text = itemProduct.productDiscountPrice
        // holder.txtDiscountPrice.text = itemProduct.productDiscountPrice
        // holder.imvProduct.setImageResource(itemProduct.productImage)


        var offer = itemProduct.offer

        if (offer.equals("", true)) {
            holder.txtOffer.visibility = View.GONE
            holder.txtDiscountPrice.visibility = View.GONE
        }


        var strOutOfStock = itemProduct.outOfStock

        if (strOutOfStock.equals("YES", true)) {
            holder.txtOutOfStock.visibility = View.GONE
            holder.txtNotiFyme.visibility = View.GONE
            holder.txtAdd.visibility = View.VISIBLE
        } else {
            holder.txtOutOfStock.visibility = View.VISIBLE
            holder.txtNotiFyme.visibility = View.VISIBLE
            holder.txtAdd.visibility = View.GONE
        }

        holder.txtDiscountPrice.setPaintFlags(holder.txtDiscountPrice.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        holder.txtAdd.setOnClickListener() {
            myInt = 1
            val itm = mList[position]
            holder.llIncrementDecrement.visibility = View.VISIBLE
            holder.txtAdd.visibility = View.GONE

            addDataToCart.add(
                CartListModel(
                    itm.productImage,
                    itm.productName,
                    itm.quentity,
                    itm.productPrice,
                    itm.productDiscountPrice,
                    itm.offer
                )
            )
        }

        holder.txtQuantity.setOnClickListener() {
            val pName = itemProduct.productName
            // dialogViewProduct(pName)
        }

        holder.txtDecrement.setOnClickListener() {
            myInt--
            holder.txtTotalQuentity.text = myInt.toString()
            addDataToCart.removeAt(position)
        }

        holder.txtInrement.setOnClickListener() {
            myInt++
            holder.txtTotalQuentity.text = myInt.toString()
            val itm = mList[position]
            addDataToCart.add(
                CartListModel(
                    itm.productImage,
                    itm.productName,
                    itm.quentity,
                    itm.productPrice,
                    itm.productDiscountPrice,
                    itm.offer
                )
            )
        }



        Glide.with(context!!)
            .load("http://bigbazaar.uk/Content/images/" + itemProduct.productImage)
            .centerCrop()
            .apply(RequestOptions().override(320, 250))
            .into(holder.imvProduct)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val txtBrandName: TextView = itemView.findViewById(R.id.txtBrandName)
        val txtDeliveryTime: TextView = itemView.findViewById(R.id.txtDeliveryTime)
        val txtProductName: TextView = itemView.findViewById(R.id.txtProductName)
        val txtQuantity: TextView = itemView.findViewById(R.id.txtQuantity)
        val txtPrice: TextView = itemView.findViewById(R.id.txtPrice)
        val txtDiscountPrice: TextView = itemView.findViewById(R.id.txtDiscountPrice)
        val txtAdd: TextView = itemView.findViewById(R.id.txtAdd)
        val txtDecrement: TextView = itemView.findViewById(R.id.txtDecrement)
        val txtInrement: TextView = itemView.findViewById(R.id.txtInrement)
        val txtTotalQuentity: TextView = itemView.findViewById(R.id.txtTotalQuentity)
        val txtOffer: TextView = itemView.findViewById(R.id.txtOffer)
        val txtRatingPoint: TextView = itemView.findViewById(R.id.txtRatingPoint)
        val txtRating: TextView = itemView.findViewById(R.id.txtRating)
        val txtOutOfStock: TextView = itemView.findViewById(R.id.txtOutOfStock)
        val txtNotiFyme: TextView = itemView.findViewById(R.id.txtNotiFyme)

        val imvProduct: ImageView = itemView.findViewById(R.id.imvProduct)
        val llIncrementDecrement: LinearLayout = itemView.findViewById(R.id.llIncrementDecrement)

    }

    fun dialogViewProduct(pName: String) {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_quentity)
        dialog.getWindow()
            ?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val recyQuentity: RecyclerView = dialog.findViewById(R.id.recyQuentity)
        val imv_cross: ImageView = dialog.findViewById(R.id.imv_cross)
        val txtProductName: TextView = dialog.findViewById(R.id.txtProductName)
        txtProductName.text = pName

        recyQuentity.layoutManager = LinearLayoutManager(context)
        val quentityModel = ArrayList<QuentityModel>()


        quentityModel.add(QuentityModel("1 Kg - ", "£ 110.50", "£ 90.00"))
        quentityModel.add(QuentityModel("5 Kg - ", "£ 320.50", "£ 240.00"))

        val dividerDrawable =
            ContextCompat.getDrawable(context!!, R.drawable.line_divider)
        recyQuentity.addItemDecoration(DividerItemDecoration(dividerDrawable))


        val adapterTop = QuentityAdapater(quentityModel)
        recyQuentity.adapter = adapterTop
        adapterTop.notifyDataSetChanged()

        imv_cross.setOnClickListener() {
            dialog.dismiss()
        }

        dialog.show()
    }

}