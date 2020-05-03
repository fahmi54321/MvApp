package com.example.mvapp.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvapp.R
import com.example.mvapp.checkout.CheckoutAct
import com.example.mvapp.checkout.model.Checkout
import com.example.mvapp.home.model.Film
import kotlinx.android.synthetic.main.activity_pilih_bangku.*

class PilihBangkuAct : AppCompatActivity() {

    var statusA3:Boolean = false;
    var statusA4:Boolean = false;

    var statusB1:Boolean = false;
    var statusB2:Boolean = false;
    var statusB3:Boolean = false;
    var statusB4:Boolean = false;

    var statusC1:Boolean = false;
    var statusC2:Boolean = false;

    var statusD1:Boolean = false;
    var statusD2:Boolean = false;
    var statusD3:Boolean = false;
    var statusD4:Boolean = false;
    var total:Int=0;

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_bangku)

        bttnSignin.visibility = View.INVISIBLE

        val data = intent.getParcelableExtra<Film>("data")
        txtJudul.text = data.judul;

        a3.setOnClickListener({
            if(statusA3)
            {
                a3.setImageResource(R.drawable.bg_bangku_kosong)
                statusA3=false
                total-=1
                beliTiket(total)

                //delete data
                dataList.remove(Checkout("A3","90000"))
            }
            else
            {
                a3.setImageResource((R.drawable.bg_bangku_pilih))
                statusA3=true
                total+=1
                beliTiket(total)

                val data = Checkout("A3","90000")
                dataList.add(data)
            }
        })

        a4.setOnClickListener({
            if(statusA4)
            {
                a4.setImageResource(R.drawable.bg_bangku_kosong)
                statusA4=false
                total-=1
                beliTiket(total)

                //delete data
                dataList.remove(Checkout("A4","90000"))
            }
            else
            {
                a4.setImageResource(R.drawable.bg_bangku_pilih)
                statusA4=true
                total+=1
                beliTiket(total)

                val data = Checkout("A4","90000")
                dataList.add(data)
            }
        })

        b1.setOnClickListener({
            if(statusB1)
            {
                b1.setImageResource(R.drawable.bg_bangku_kosong)
                statusB1=false
                total-=1
                beliTiket(total)

                //delete data
                dataList.remove(Checkout("B1","80000"))
            }
            else
            {
                b1.setImageResource((R.drawable.bg_bangku_pilih))
                statusB1=true
                total+=1
                beliTiket(total)

                val data = Checkout("B1","80000")
                dataList.add(data)
            }
        })

        b2.setOnClickListener({
            if(statusB2)
            {
                b2.setImageResource(R.drawable.bg_bangku_kosong)
                statusB2=false
                total-=1
                beliTiket(total)

                //delete data
                dataList.remove(Checkout("B2","80000"))
            }
            else
            {
                b2.setImageResource(R.drawable.bg_bangku_pilih)
                statusB2=true
                total+=1
                beliTiket(total)

                val data = Checkout("B2","80000")
                dataList.add(data)
            }
        })

        b3.setOnClickListener({
            if(statusB3)
            {
                b3.setImageResource(R.drawable.bg_bangku_kosong)
                statusB3=false
                total-=1
                beliTiket((total))

                //delete data
                dataList.remove(Checkout("B3","80000"))
            }
            else
            {
                b3.setImageResource(R.drawable.bg_bangku_pilih)
                statusB3=true
                total+=1
                beliTiket(total)

                val data = Checkout("B3","80000")
                dataList.add(data)
            }
        })

        b4.setOnClickListener({
            if(statusB4)
            {
                b4.setImageResource(R.drawable.bg_bangku_kosong)
                statusB4=false
                total-=1
                beliTiket((total))

                //delete data
                dataList.remove(Checkout("B4","80000"))
            }
            else
            {
                b4.setImageResource(R.drawable.bg_bangku_pilih)
                statusB4=true
                total+=1
                beliTiket(total)

                val data = Checkout("B4","80000")
                dataList.add(data)
            }
        })

        c1.setOnClickListener({
            if(statusC1)
            {
                c1.setImageResource(R.drawable.bg_bangku_kosong)
                statusC1=false
                total-=1
                beliTiket(total)

                //delete data
                dataList.remove(Checkout("C1","70000"))
            }
            else
            {
                c1.setImageResource((R.drawable.bg_bangku_pilih))
                statusC1=true
                total+=1
                beliTiket(total)

                val data = Checkout("C1","70000")
                dataList.add(data)
            }
        })

        c2.setOnClickListener({
            if(statusC2)
            {
                c2.setImageResource(R.drawable.bg_bangku_kosong)
                statusC2=false
                total-=1
                beliTiket(total)

                //delete data
                dataList.remove(Checkout("C2","70000"))
            }
            else
            {
                c2.setImageResource(R.drawable.bg_bangku_pilih)
                statusC2=true
                total+=1
                beliTiket(total)

                val data = Checkout("C2","70000")
                dataList.add(data)
            }
        })

        d1.setOnClickListener({
            if(statusD1)
            {
                d1.setImageResource(R.drawable.bg_bangku_kosong)
                statusD1=false
                total-=1
                beliTiket(total)

                //delete data
                dataList.remove(Checkout("D1","60000"))
            }
            else
            {
                d1.setImageResource((R.drawable.bg_bangku_pilih))
                statusD1=true
                total+=1
                beliTiket(total)

                val data = Checkout("D1","60000")
                dataList.add(data)
            }
        })

        d2.setOnClickListener({
            if(statusD2)
            {
                d2.setImageResource(R.drawable.bg_bangku_kosong)
                statusD2=false
                total-=1
                beliTiket(total)

                //delete data
                dataList.remove(Checkout("D2","60000"))
            }
            else
            {
                d2.setImageResource(R.drawable.bg_bangku_pilih)
                statusD2=true
                total+=1
                beliTiket(total)

                val data = Checkout("D2","60000")
                dataList.add(data)
            }
        })

        d3.setOnClickListener({
            if(statusD3)
            {
                d3.setImageResource(R.drawable.bg_bangku_kosong)
                statusD3=false
                total-=1
                beliTiket((total))

                //delete data
                dataList.remove(Checkout("D3","60000"))
            }
            else
            {
                d3.setImageResource(R.drawable.bg_bangku_pilih)
                statusD3=true
                total+=1
                beliTiket(total)

                val data = Checkout("D3","60000")
                dataList.add(data)
            }
        })

        d4.setOnClickListener({
            if(statusD4)
            {
                d4.setImageResource(R.drawable.bg_bangku_kosong)
                statusD4=false
                total-=1
                beliTiket((total))

                //delete data
                dataList.remove(Checkout("D4","60000"))
            }
            else
            {
                d4.setImageResource(R.drawable.bg_bangku_pilih)
                statusD4=true
                total+=1
                beliTiket(total)

                val data = Checkout("D4","60000")
                dataList.add(data)
            }
        })

        bttnSignin.setOnClickListener({
            val intent = Intent(this, CheckoutAct::class.java).putExtra("data",dataList)
            startActivity(intent)
        })


    }

    private fun beliTiket(total: Int) {
        if(total==0)
        {
            bttnSignin.setText("Beli Tiket")
            bttnSignin.visibility = View.INVISIBLE
        }
        else
        {
            bttnSignin.setText("Beli Tiket("+total+")")
            bttnSignin.visibility = View.VISIBLE
        }
    }
}
