package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {
    private lateinit var loginField: EditText
    private lateinit var passwordField: EditText
    private lateinit var loginWrapper: TextInputLayout
    private lateinit var passwordWrapper: TextInputLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button: Button = findViewById(R.id.loginButton)

        loginField = findViewById(R.id.login)
        passwordField = findViewById(R.id.password)
        loginWrapper = findViewById(R.id.login_wrapper)
        passwordWrapper = findViewById(R.id.password_wrapper)
        button.setBackgroundColor(resources.getColor(R.color.colorAccent)) //Fix for material library xml property bug
        button.setOnClickListener(this::clickListener)

    }



    private fun clickListener(v: View) {
        val code:Int = User.auth(loginField?.text.toString(), passwordField?.text.toString(), this)
        when (code) {
            1->{

                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)

            }
            0->{
                passwordWrapper?.error = resources.getString(R.string.error_password)
                loginWrapper?.error = null
            }
            -1->{
                loginWrapper?.error = resources.getString(R.string.error_login)
                passwordWrapper?.error = null

            }
        }
    }



}




