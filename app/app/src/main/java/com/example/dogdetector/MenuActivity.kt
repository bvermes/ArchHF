package com.example.dogdetector

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogdetector.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btndetector.setOnClickListener {
            val detectorIntent = Intent(this, DetectorActivity::class.java)
            startActivity(detectorIntent)
        }

        binding.btnbreed.setOnClickListener {
            val breedIntent = Intent(this, BreedCheckActivity::class.java)
            startActivity(breedIntent)
        }
    }


}