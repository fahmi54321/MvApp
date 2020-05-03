package com.example.mvapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.mvapp.R
import com.example.mvapp.home.dashboard.DashboardFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //Menampilkan Fragment Ke Activity Home
        val fragmentHome= DashboardFragment()
        setFragment(fragmentHome)

//        val fragmentSettings = SettingFragment()
//        setFragment(fragmentSettings)

        changeIcon(menu_home,R.drawable.home_active);
        changeIcon(menu_ticket,R.drawable.person_tidak_active);
        changeIcon(menu_settings,R.drawable.person_tidak_active);

        //Menu Dalam Home
        menu_home.setOnClickListener({
            setFragment(fragmentHome)
            changeIcon(menu_home,R.drawable.home_active);
            changeIcon(menu_ticket,R.drawable.person_tidak_active);
            changeIcon(menu_settings,R.drawable.person_tidak_active);
        })

        menu_ticket.setOnClickListener({
            val fragmentTicket = TicketFragment()
            setFragment(fragmentTicket)
            changeIcon(menu_home,R.drawable.home_tidak_active);
            changeIcon(menu_ticket,R.drawable.person_active);
            changeIcon(menu_settings,R.drawable.person_tidak_active);
        })

        menu_settings.setOnClickListener({
            val fragmentSettings = SettingFragment()
            setFragment(fragmentSettings)
            changeIcon(menu_home,R.drawable.home_tidak_active);
            changeIcon(menu_ticket,R.drawable.person_tidak_active);
            changeIcon(menu_settings,R.drawable.person_active);
        })
    }

    private fun changeIcon(imageView: ImageView, int: Int) {

        imageView.setImageResource(int);

    }

    protected fun setFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }
}
