package com.example.mvapp.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvapp.R
import com.example.mvapp.home.model.Plays

class PlayAdapter (private var data:List<Plays>,
                   private val listener:(Plays)->Unit)
    : RecyclerView.Adapter<PlayAdapter.LeagueViewHolder>()
{

    lateinit var ContextAdapter :Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder
    {
        val layoutInflater= LayoutInflater.from(parent.context)
        ContextAdapter=parent.context
        val inflatedView:View=layoutInflater.inflate(R.layout.row_item_play,parent,false)

        return LeagueViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(data[position],listener,ContextAdapter,position)
    }

    class LeagueViewHolder(view: View):RecyclerView.ViewHolder(view) {

        private val txtJudul:TextView = view.findViewById(R.id.txt_Judul)
        private val imgPoster:ImageView = view.findViewById(R.id.imgposterimage)

        fun bindItem(data: Plays, listener: (Plays) -> Unit, context: Context, position: Int) {

            txtJudul.text= data.nama
            Glide.with(context)
                .load(data.url)
                .apply(RequestOptions.circleCropTransform())
                .into(imgPoster)

            itemView.setOnClickListener({
                listener(data)
            })

        }



    }

}

