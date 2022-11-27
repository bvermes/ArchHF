package org.business

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import org.business.databinding.ActivityBreedListBinding
import org.business.ui.DogBreedAdapter
import org.data.database.DogBreedListDatabase
import org.data.entities.DogBreedElement
import kotlin.concurrent.thread

class BreedList : AppCompatActivity(), DogBreedAdapter.DogBreedElementClickListener {
    private lateinit var binding: ActivityBreedListBinding

    private lateinit var database: DogBreedListDatabase
    private lateinit var adapter: DogBreedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBreedListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = DogBreedListDatabase.getDatabase(applicationContext)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = DogBreedAdapter(this)
        binding.rvBreed.layoutManager = LinearLayoutManager(this)
        binding.rvBreed.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.dogBreedElementDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(item: DogBreedElement) {
        thread {
            database.dogBreedElementDao().update(item)
            Log.d("BreedList", "DogBreeds update was successful")
        }
    }
}