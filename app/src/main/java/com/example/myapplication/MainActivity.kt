package com.example.myapplication


import android.os.Bundle
import androidx.activity.enableEdgeToEdge

import androidx.appcompat.app.AppCompatActivity

import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        val fragment=CurrencyyFragment()
        val sfragmentmanager=supportFragmentManager
        val transaction=sfragmentmanager.beginTransaction()
        transaction.replace(R.id.fragment_container_view_tag,fragment).commit()


        }


}