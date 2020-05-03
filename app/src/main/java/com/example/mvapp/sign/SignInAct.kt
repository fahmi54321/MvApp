package com.example.mvapp.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.mvapp.R
import com.example.mvapp.home.HomeAct
import com.example.mvapp.sign.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.activity_signin.logo
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SignInAct : AppCompatActivity() {

    lateinit var txtUsername:String;
    lateinit var txtPassword:String;
    lateinit var databaseReference: DatabaseReference;
    lateinit var preference:Preferences;

    lateinit var bottom_to_top: Animation;
    lateinit var top_to_bottom: Animation;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        bottom_to_top = AnimationUtils.loadAnimation(this,R.anim.bottom_to_top)
        top_to_bottom = AnimationUtils.loadAnimation(this,R.anim.top_to_bottom)

        logo.startAnimation(top_to_bottom)
        txtSignin.startAnimation(bottom_to_top)
        textUsername.startAnimation(bottom_to_top)
        edtUsername.startAnimation(bottom_to_top)
        textPassword.startAnimation(bottom_to_top)
        edtPassword.startAnimation(bottom_to_top)
        bttnSignin.startAnimation(bottom_to_top)
        bttnSignup.startAnimation(bottom_to_top)

        preference = Preferences(this);
        preference.setValues("onboarding","1");
        if(preference.getValues("status").equals("1"))
        {
            finishAffinity();
            val intent = Intent(this,HomeAct::class.java);
            startActivity(intent);
        }

        bttnSignup.setOnClickListener({
            val intent = Intent(this,SignUpAct::class.java);
            startActivity(intent);
        })

        bttnSignin.setOnClickListener({
            bttnSignin.setText("LOADING...");
            bttnSignin.setEnabled(false);
            txtUsername = edtUsername.text.toString();
            txtPassword = edtPassword.text.toString();

            if(txtUsername.equals(""))
            {
                edtUsername.error="Username Gak Boleh Kosong";
                edtUsername.requestFocus();
                bttnSignin.setText("SIGN IN");
                bttnSignin.setEnabled(true);
            }
            else if(txtPassword.equals(""))
            {
                edtPassword.error="Password Gak Boleh Kosong";
                edtPassword.requestFocus();
                bttnSignin.setText("SIGN IN");
                bttnSignin.setEnabled(true);
            }
            else
            {
                pushLogin(txtUsername,txtPassword);
            }
        })
    }

    private fun pushLogin(txtUsername: String, txtPassword: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference.child(txtUsername).addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

                Toast.makeText(this@SignInAct,"Database Error",Toast.LENGTH_LONG).show();
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java);
                if(user==null)
                {
                    Toast.makeText(this@SignInAct,"User Tidak Ditemukan",Toast.LENGTH_LONG).show();
                    bttnSignin.setText("SIGN IN");
                    bttnSignin.setEnabled(true);
                }
                else
                {
                    if(user.password.equals(txtPassword))
                    {
                        Toast.makeText(this@SignInAct,"Selamat Datang",Toast.LENGTH_LONG).show();

                        // Fungsi Mengubah data Preferences (setValues)
                        // Serta Menggunakan Model "User" yang terdapat pada Class User
                        preference.setValues("nama",user.nama.toString());
                        preference.setValues("username",user.username.toString());
                        preference.setValues("email",user.email.toString());
                        preference.setValues("saldo",user.saldo.toString());
                        preference.setValues("url",user.url.toString());

                        // Sebagai Kunci Supaya User bisa masuk langsung ke Home, tanpa Login
                        preference.setValues("status","1");

                        finishAffinity();
                        val intent = Intent(this@SignInAct,HomeAct::class.java);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(this@SignInAct,"Password Salah",Toast.LENGTH_LONG).show();
                        bttnSignin.setText("SIGN IN");
                        bttnSignin.setEnabled(true);
                    }
                }
            }

        })
    }
}
