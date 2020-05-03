package com.example.mvapp.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mvapp.R
import com.example.mvapp.home.HomeAct
import com.example.mvapp.sign.utils.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.bttnSignup
import kotlinx.android.synthetic.main.activity_sign_up.edtPassword
import kotlinx.android.synthetic.main.activity_sign_up.edtUsername
import kotlinx.android.synthetic.main.activity_signin.*

class SignUpAct : AppCompatActivity() {

    lateinit var txtUsername:String;
    lateinit var txtPassword:String;
    lateinit var txtNama:String;
    lateinit var txtEmail:String

    lateinit var reference: DatabaseReference;
    lateinit var preference: Preferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        preference = Preferences(this);
        preference.setValues("onboarding","1");
        if(preference.getValues("status").equals("1"))
        {
            finishAffinity();
            val intent = Intent(this, HomeAct::class.java);
            startActivity(intent);
        }
        bttnBack.setOnClickListener({
            finishAffinity();
            val intent = Intent(this,SignInAct::class.java);
            startActivity(intent);
        })

        bttnSignup.setOnClickListener({
            bttnSignup.setText("LOADING...");
            bttnSignup.setEnabled(false);
            txtUsername = edtUsername.text.toString();
            txtPassword = edtPassword.text.toString();
            txtNama = edtNama.text.toString();
            txtEmail = edtEmail.text.toString();

            if(txtUsername.equals(""))
            {
                edtUsername.error="Username Gak Boleh Kosong";
                edtUsername.requestFocus();
                bttnSignup.setText("SIGN UP");
                bttnSignup.setEnabled(true);
            }
            else if(txtPassword.equals(""))
            {
                edtPassword.error="Password Gak Boleh Kosong";
                edtPassword.requestFocus();
                bttnSignup.setText("SIGN UP");
                bttnSignup.setEnabled(true);
            }
            else if(txtNama.equals(""))
            {
                edtNama.error="Nama Gak Boleh Kosong";
                edtNama.requestFocus();
                bttnSignup.setText("SIGN UP");
                bttnSignup.setEnabled(true);
            }
            else if(txtEmail.equals(""))
            {
                edtEmail.error="Email Gak Boleh Kosong";
                edtEmail.requestFocus();
                bttnSignup.setText("SIGN UP");
                bttnSignup.setEnabled(true);
            }
            else
            {
                saveUser(txtUsername,txtPassword,txtNama,txtEmail);
            }
        })
    }

    private fun saveUser(txtUsername: String, txtPassword: String, txtNama: String, txtEmail: String) {

        val user = User();
        user.username=txtUsername;
        user.password=txtPassword;
        user.nama=txtNama;
        user.email=txtEmail;

        if(txtUsername!=null)
        {
            checkingUsername(txtUsername,user);
        }

    }

    private fun checkingUsername(textUsername: String, data: User) {
        reference = FirebaseDatabase.getInstance().getReference().child("User").child(textUsername);
        reference.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@SignUpAct, "Database Error",Toast.LENGTH_LONG).show();
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user=dataSnapshot.getValue(User::class.java);
                if(user==null)
                {
                    reference.setValue(data);

                    preference.setValues("nama",data.nama.toString());
                    preference.setValues("username",data.username.toString());
                    preference.setValues("email",data.email.toString());
                    preference.setValues("saldo",data.saldo.toString());
                    preference.setValues("url",data.url.toString());
                    preference.setValues("status","1");

                    finishAffinity();
                    val intent = Intent(this@SignUpAct,UploadPhotoAct::class.java).putExtra("nama",data.nama);
                    startActivity(intent);
                }
                else
                {
                    edtUsername.error="Username Sudah Digunakan";
                    edtUsername.requestFocus();
                    bttnSignup.setText("SIGN UP");
                    bttnSignup.setEnabled(true);
                }
            }

        })

    }
}
