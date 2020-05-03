package com.example.mvapp.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvapp.R
import com.example.mvapp.checkout.model.Checkout
import java.lang.NumberFormatException
import java.text.NumberFormat
import java.util.*

class CheckoutAdapter (private var data:List<Checkout>, private val listener:(Checkout)->Unit)
    :RecyclerView.Adapter<CheckoutAdapter.LeagueViewHolder>()
{
    lateinit var ContextAdapter:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        ContextAdapter = parent.context
        val inflatedView:View = layoutInflater.inflate(R.layout.row_item_checkout,parent,false)

        return LeagueViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position],listener,ContextAdapter,position)
    }

    class LeagueViewHolder(view: View):RecyclerView.ViewHolder(view)
    {

        private val txtKursi:TextView = view.findViewById(R.id.txt_kursi)
        private val txtHarga:TextView = view.findViewById(R.id.txt_harga)

        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, context: Context, position: Int) {

            val localeID = Locale("in","ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            txtHarga.setText(formatRupiah.format(data.harga!!.toDouble()))

            if (data.kursi!!.startsWith("Total"))
            {
                txtKursi.text = data.kursi
                txtKursi.setCompoundDrawables(null,null,null,null)
            }
            else
            {
                txtKursi.text="Seat No. "+data.kursi
            }

            itemView.setOnClickListener({
                listener(data)
            })

        }

    }

}