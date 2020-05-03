package com.example.mvapp.checkout

import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvapp.R
import com.example.mvapp.checkout.model.Checkout
import com.example.mvapp.home.HomeAct
import kotlinx.android.synthetic.main.activity_checkout.*
import java.text.NumberFormat
import java.util.*
import java.util.prefs.Preferences
import kotlin.collections.ArrayList

class CheckoutAct : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total:Int=0

    private lateinit var preferences: com.example.mvapp.sign.utils.Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = com.example.mvapp.sign.utils.Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        for (x in dataList.indices)
        {
            total += dataList[x].harga!!.toInt()
        }

        dataList.add(Checkout("Total Harus Bayar ",total.toString()))

        rvSeats.layoutManager = LinearLayoutManager(this)
        rvSeats.adapter = CheckoutAdapter(dataList){

        }
        val localeID = Locale("in","ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        txt_balance.setText(formatRupiah.format(preferences.getValues("saldo")!!.toDouble()))

        bttnSignin.setOnClickListener({
            finishAffinity()
            val intent = Intent(this,SuccessCheckoutAct::class.java)
            startActivity(intent)
        })

        bttnSignup.setOnClickListener({
            finishAffinity()
            val intent = Intent(this,HomeAct::class.java)
            startActivity(intent)
        })


    }
}
