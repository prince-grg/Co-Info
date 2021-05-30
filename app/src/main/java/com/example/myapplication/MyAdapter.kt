package com.example.myapplication


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color.red
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import retrofit2.Callback

class MyAdapter(var context: Context, var sessionslits:List<Session>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        val center: TextView
        val fee:TextView
        val vaccine:TextView
        val available:TextView
        val mini:TextView
      init {
          center=view.findViewById(R.id.txt_centerName);
          fee=view.findViewById(R.id.txt_feeTpye);
          vaccine=view.findViewById(R.id.txt_Vaccine);
          available=view.findViewById(R.id.txt_available);
          mini=view.findViewById(R.id.txt_minAge);
      }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.center.text="Center :" + sessionslits[position].name
        holder.available.text="Avialable: "+sessionslits[position].available_capacity.toString()
        holder.fee.text=  "Fee :"+sessionslits[position].fee
        holder.vaccine.text="Vaccine :"+sessionslits[position].vaccine
        holder.mini.text="Min Age :"+sessionslits[position].min_age_limit.toString()

        if(sessionslits[position].available_capacity>0)
        {
            holder.itemView.setBackgroundColor(R.color.green)

        }
        else {
            holder.itemView.setBackgroundColor(R.color.red)
        }


    }

    override fun getItemCount(): Int {
          return sessionslits.size
    }


}
