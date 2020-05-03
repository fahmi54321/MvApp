package com.example.mvapp.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mvapp.R
import com.example.mvapp.home.dashboard.PlayAdapter
import com.example.mvapp.home.model.Film
import com.example.mvapp.home.model.Plays
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail.*

class DetailAct : AppCompatActivity() {

    lateinit var database:DatabaseReference;
    private var dataList=ArrayList<Plays>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Film>("data")

        database = FirebaseDatabase.getInstance().getReference("Film")
            .child(data.judul.toString())
            .child("play")

        txtJudul.text = data.judul
        txtGenre.text = data.genre
        txtDesc.text = data.desc
        txtRating.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(imgPoster)

        //Tombol Pilih Bangku
        bttnSignin.setOnClickListener({
            val intent = Intent(this,PilihBangkuAct::class.java).putExtra("data",data)
            startActivity(intent)
        })

        rvWhosPlayed.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        getData();
    }

    private fun getData() {
        database.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(dataSnapshot: DatabaseError) {
                Toast.makeText(this@DetailAct,"Database Error",Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for(getDataSnapshot in dataSnapshot.getChildren())
                {
                    var film = getDataSnapshot.getValue(Plays::class.java!!)
                    dataList.add(film!!)
                }
                rvWhosPlayed.adapter = PlayAdapter(dataList){

                }
            }

        })
    }
}
