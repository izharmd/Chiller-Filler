package com.bws.chillerfiller.itemcategory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bws.chillerfiller.R
import com.bws.chillerfiller.common.Constant.Companion.pos
import com.bws.chillerfiller.itemcategory.productlist.ProductListActivity
import com.mikhaellopez.circularimageview.CircularImageView

class ItemCategoryAdapter(val list: List<ItemCategoryModel>) :
    RecyclerView.Adapter<ItemCategoryAdapter.ViewHolder>() {

    private var context: Context? = null

    var row_index: Int = 0;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemCategory = list[position]
        holder.txtCategory.text = itemCategory.category
        holder.imvCatogoryImage.setImageResource(itemCategory.image)

        holder.itemView.setOnClickListener() {
            pos = position
            row_index = position;
            notifyDataSetChanged();
            if (context is ProductListActivity) {
                 (context as ProductListActivity).topCategory()
            }

        }

        if (row_index == position) {
            holder.imvCatogoryImage.apply {
                holder.imvCatogoryImage.borderColor = resources.getColor(R.color.yellow)
            }
        } else {
            holder.imvCatogoryImage.apply {
                holder.imvCatogoryImage.borderColor = resources.getColor(R.color.black)
            }

        }
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val txtCategory: TextView = itemView.findViewById(R.id.txtCategory)

        // val imvCatogoryImage: ImageView = itemView.findViewById(R.id.imvCatogoryImage)
        // val imvCatogoryImage: RoundedImageView = itemView.findViewById(R.id.imvCatogoryImage)
        val imvCatogoryImage: CircularImageView = itemView.findViewById(R.id.imvCatogoryImage)
    }
}