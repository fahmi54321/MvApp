package com.example.mvapp.sign.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences (val context:Context)
{
    companion object{
        const val JOB_PREF="USER_PREF";
    }

    // Membuat Objek Preferences

    val sharePref = context.getSharedPreferences(JOB_PREF,0);

    // Fungsi Mengubah Isi Preferences
    fun setValues(key : String,value:String)
    {
        val editor:SharedPreferences.Editor = sharePref.edit();
        editor.putString(key,value);
        editor.apply();
    }

    // Fungsi Mengakses Data Dari SharedPreferences
    fun getValues(key: String):String?
    {
        return sharePref.getString(key,"");
    }
}