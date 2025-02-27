package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

import androidx.appcompat.app.AlertDialog


class introActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intro)
        val mainActivity= Intent(this,MainActivity::class.java)

        if (isNetworkAvailable(this)) {
            startActivity(mainActivity)
            finish()
        } else {
            showNetworkDialog()
        }
        }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

     private fun showNetworkDialog() {
        AlertDialog.Builder(this)
            .setTitle("No Internet")
            .setMessage("Check your connection and try again.")
            .setPositiveButton("Retry") { _, _ ->
                if (isNetworkAvailable(this)) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    showNetworkDialog()
                }
            }
            .setNegativeButton("Exit") { _, _ -> finish() }
            .show()
    }


    }

