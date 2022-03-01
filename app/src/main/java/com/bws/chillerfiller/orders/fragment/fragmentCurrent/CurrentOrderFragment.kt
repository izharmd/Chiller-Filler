package com.bws.chillerfiller.orders.fragment.fragmentCurrent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bws.chillerfiller.R

class CurrentOrderFragment : Fragment() {
   override fun onCreateView(
   inflater: LayoutInflater, container: ViewGroup?,
   savedInstanceState: Bundle?
   ): View? {

      val view: View = inflater!!.inflate(R.layout.fragment_football, container, false)

      /* view.recyCurrentOrder.layoutManager = LinearLayoutManager(context)
       val data = ArrayList<CurrentOrderModel>()


       data.add(CurrentOrderModel("20/12/2021","11004344","£100.00","Not Shipped","Track"))
       data.add(CurrentOrderModel("25/12/2021","15610044","£150.60","Not Shipped","Track"))
       data.add(CurrentOrderModel("26/12/2021","17610044","£50.20","Order Confirm","Track"))
       data.add(CurrentOrderModel("01/01/2022","11004984","£70.00","Not Shipped","Track"))
       data.add(CurrentOrderModel("10/01/2022","11054044","£20.80","Out for Delivery","Track"))

       val adapter = CurrentOrderAdapter(data)
       view.recyCurrentOrder.adapter = adapter
       adapter.notifyDataSetChanged()
*/

      return view
   }
}


