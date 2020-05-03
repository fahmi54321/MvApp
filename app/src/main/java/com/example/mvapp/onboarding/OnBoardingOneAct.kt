package com.example.mvapp.onboarding

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.mvapp.R
import com.example.mvapp.sign.SignInAct
import com.example.mvapp.sign.utils.Preferences
import kotlinx.android.synthetic.main.activity_on_boarding_one.*

class OnBoardingOneAct : AppCompatActivity() {

    lateinit var preferences: Preferences;
    lateinit var left_to_right: Animation;
    lateinit var right_to_left: Animation;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_one)

        preferences = Preferences(this);
        left_to_right =AnimationUtils.loadAnimation(this,R.anim.left_to_right)
        right_to_left =AnimationUtils.loadAnimation(this,R.anim.right_to_left)

        imageView.startAnimation(left_to_right);
        textView.startAnimation(left_to_right);
        textView2.startAnimation(left_to_right);

        bttnSignin.startAnimation(right_to_left);
        bttnSignup.startAnimation(right_to_left);

        if(preferences.getValues("onboarding").equals("1"))
        {
            finishAffinity();

            val intent = Intent(this,SignInAct::class.java);
            startActivity(intent);
        }

        bttnSignin.setOnClickListener({
            val intent = Intent(this,OnBoardingTwoAct::class.java);
            startActivity(intent);
        })

        bttnSignup.setOnClickListener({
            finish()
            val intent = Intent(this,SignInAct::class.java);
            startActivity(intent);
        })
    }
}
