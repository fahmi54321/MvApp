package com.example.mvapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.mvapp.onboarding.OnBoardingOneAct
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashScreenAct : AppCompatActivity() {

    lateinit var bottom_to_top: Animation;
    lateinit var top_to_bottom: Animation;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)

        logo.startAnimation(top_to_bottom)
        nama_aplikasi.startAnimation(bottom_to_top)

        var handler = Handler();
        handler.postDelayed({
            val intent = Intent(this,OnBoardingOneAct::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}
