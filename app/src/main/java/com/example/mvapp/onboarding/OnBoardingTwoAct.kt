package com.example.mvapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.mvapp.R
import com.example.mvapp.sign.SignInAct
import kotlinx.android.synthetic.main.activity_on_boarding_one.*
import kotlinx.android.synthetic.main.activity_on_boarding_two.*
import kotlinx.android.synthetic.main.activity_on_boarding_two.bttnSignin
import kotlinx.android.synthetic.main.activity_on_boarding_two.bttnSignup
import kotlinx.android.synthetic.main.activity_on_boarding_two.imageView
import kotlinx.android.synthetic.main.activity_on_boarding_two.textView
import kotlinx.android.synthetic.main.activity_on_boarding_two.textView2

class OnBoardingTwoAct : AppCompatActivity() {

    lateinit var left_to_right: Animation;
    lateinit var right_to_left: Animation;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_two)

        left_to_right = AnimationUtils.loadAnimation(this,R.anim.left_to_right)
        right_to_left = AnimationUtils.loadAnimation(this,R.anim.right_to_left)

        imageView.startAnimation(right_to_left);
        textView.startAnimation(right_to_left);
        textView2.startAnimation(right_to_left);

        bttnSignin.startAnimation(left_to_right);
        bttnSignup.startAnimation(left_to_right);

        bttnSignin.setOnClickListener({
            val intent = Intent(this,OnBoardingThree::class.java);
            startActivity(intent);
        })

        bttnSignup.setOnClickListener({
            finishAffinity()
            val intent = Intent(this,SignInAct::class.java);
            startActivity(intent);
        })
    }
}
