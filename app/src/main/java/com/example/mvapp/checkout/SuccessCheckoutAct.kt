package com.example.mvapp.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvapp.R
import com.example.mvapp.home.HomeAct
import kotlinx.android.synthetic.main.activity_success_checkout.*

class SuccessCheckoutAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_checkout)

        bttn_signin.setOnClickListener({

        })

        bttn_signup.setOnClickListener({
            finishAffinity()
            val intent = Intent(this,HomeAct::class.java)
            startActivity(intent)
        })
    }
}
