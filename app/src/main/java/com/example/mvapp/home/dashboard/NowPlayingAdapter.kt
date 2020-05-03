package com.example.mvapp.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvapp.R
import com.example.mvapp.home.model.Film

class NowPlayingAdapter (private var data:List<Film>,
                         private val listener : (Film)->Unit)
    :RecyclerView.Adapter<NowPlayingAdapter.LeagueViewHolder>()
{

    lateinit var ContextAdapter:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ContextAdapter=parent.context
        val inflatedView:View= layoutInflater.inflate(R.layout.row_item_now_playing,parent,false)

        return LeagueViewHolder(inflatedView)
    }

    class LeagueViewHolder(view: View):RecyclerView.ViewHolder(view) {

        private val txtTitle : TextView = view.findViewById(R.id.txt_Judul)
        private val txtGenre : TextView = view.findViewById(R.id.tv_genre)
        private val txtRate : TextView = view.findViewById(R.id.tv_rate)

        private val txtImage : ImageView = view.findViewById(R.id.imgposterimage)

        fun bindItem(data: Film, listener: (Film) -> Unit, context: Context, position: Int) {

            txtTitle.text=data.judul
            txtGenre.text=data.genre
            txtRate.text=data.rating

            Glide.with(context)
                .load(data.poster)
                .into(txtImage);

            itemView.setOnClickListener({
                listener(data)
            })

        }
    }


    override fun getItemCount(): Int=data.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {

        holder.bindItem(data[position],listener,ContextAdapter,position)

    }

}