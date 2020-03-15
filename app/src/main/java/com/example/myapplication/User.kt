package com.example.myapplication

import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.lang.Exception

object User {
    var Login: String? = null
    var Logged:Boolean = false
    fun auth(name:String, password:String, context: Context): Int {
        val str:String = context.resources.openRawResource(R.raw.users).bufferedReader().use { it.readText() }
        Log.i("Name", name)
        Log.i("Pass", password)
        try {

            if (JSONObject(str).getString(name.toLowerCase()) == password){
                Logged = true
                Login = name.toLowerCase()
                return 1
            }
            Login = null
            Logged = false
            return  0
        }
        catch (e: Exception)
        {
            Login = null
            Logged = false
            return -1
        }


    }
}