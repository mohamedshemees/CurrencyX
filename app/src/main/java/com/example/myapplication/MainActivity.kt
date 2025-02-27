package com.example.myapplication


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle

import androidx.annotation.RequiresApi

import androidx.appcompat.app.AppCompatActivity

import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.internal.EdgeToEdgeUtils

class MainActivity : AppCompatActivity() {
     private lateinit var  binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment=CurrencyyFragment()
        val sfragmentmanager=supportFragmentManager
        val transaction=sfragmentmanager.beginTransaction()
        transaction.replace(R.id.fragment_container_view_tag,fragment).commit()

        }

}