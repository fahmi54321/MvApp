package com.example.mvapp.home.dashboard


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.example.mvapp.R
import com.example.mvapp.home.DetailAct
import com.example.mvapp.home.model.Film
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private lateinit var preferences: com.example.mvapp.sign.utils.Preferences;
    lateinit var databaseReference: DatabaseReference;

    private var dataList = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences=com.example.mvapp.sign.utils.Preferences(activity!!.applicationContext)
        databaseReference = FirebaseDatabase.getInstance().getReference("Film")

        txtNama.setText(preferences.getValues("nama"))
        if(!preferences.getValues("saldo").equals(""))
        {
            currecy(preferences.getValues("saldo")!!.toDouble(),txtSaldo)
        }

        Glide.with(this)
            .load(preferences.getValues("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(imgPhoto)

        rvNowPlaying.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        rvComingSoon.layoutManager = LinearLayoutManager(context!!.applicationContext)
        getData()
    }

    private fun currecy(harga: Double, textView: TextView) {

        val localeID = Locale("in","ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))

    }

    private fun getData() {
        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context, "Error Database",Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for(getdataSnapshot in dataSnapshot.getChildren())
                {
                    var film = getdataSnapshot.getValue(Film::class.java!!)
                    dataList.add(film!!)
                }

                rvNowPlaying.adapter = NowPlayingAdapter(dataList)
                {
                    var intent = Intent(context, DetailAct::class.java).putExtra("data",it)
                    startActivity(intent)
                }
                rvComingSoon.adapter = ComingSoonAdapter(dataList)
                {
                    var intent = Intent(context, DetailAct::class.java).putExtra("data",it)
                    startActivity(intent)
                }
            }

        })
    }
}
