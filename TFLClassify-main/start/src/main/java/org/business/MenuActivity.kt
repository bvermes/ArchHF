package org.business

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.business.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btndetector.setOnClickListener {
            val detectorIntent = Intent(this, MainActivity::class.java)
            startActivity(detectorIntent)
        }

        binding.btnbreed.setOnClickListener {
            val breedIntent = Intent(this, MainActivity::class.java)
            startActivity(breedIntent)
        }
    }


}