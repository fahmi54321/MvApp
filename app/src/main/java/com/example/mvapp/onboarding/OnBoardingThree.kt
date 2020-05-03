package com.example.mvapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.mvapp.R
import com.example.mvapp.sign.SignInAct
import kotlinx.android.synthetic.main.activity_on_boarding_three.*

class OnBoardingThree : AppCompatActivity() {

    lateinit var top_to_bottom: Animation;
    lateinit var bottom_to_top: Animation;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_three)

        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)
        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)

        imageView.startAnimation(top_to_bottom)
        textView.startAnimation(bottom_to_top)
        textView2.startAnimation(bottom_to_top)

        bttnSignin.startAnimation(bottom_to_top)

        bttnSignin.setOnClickListener({
            finishAffinity();
            val intent = Intent(this,SignInAct::class.java);
            startActivity(intent);
        })
    }
}
