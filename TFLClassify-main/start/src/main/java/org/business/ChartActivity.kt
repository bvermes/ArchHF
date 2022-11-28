package org.business

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import org.business.databinding.ActivityChartBinding
import org.data.database.DogBreedListDatabase
import org.data.entities.DogBreedElement
import kotlin.concurrent.thread

class ChartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChartBinding
    private lateinit var database: DogBreedListDatabase

    private var breed1Value : Float = 0.0f
    private var breed2Value : Float = 0.0f
    private var breed3Value : Float = 0.0f
    private var breedrestValue : Float = 1.0f

    private lateinit var breed1Name : String
    private lateinit var breed2Name : String
    private lateinit var breed3Name : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = DogBreedListDatabase.getDatabase(applicationContext)


        var intent: Intent = intent
        val hashMap = intent.getSerializableExtra("map") as HashMap<String, Float>

        breed1Name = hashMap.keys.elementAt(0) // Get key by index.
        breed1Value = hashMap.getValue(breed1Name) // Get value.
        breed2Name = hashMap.keys.elementAt(1) // Get key by index.
        breed2Value = hashMap.getValue(breed2Name) // Get value.
        breed3Name = hashMap.keys.elementAt(2) // Get key by index.
        breed3Value = hashMap.getValue(breed3Name) // Get value.
        breedrestValue = 1 - breed1Value - breed2Value - breed3Value

        Log.e("Balazs", breed1Name)
        Log.e("Balazs2", breed2Name)
        Log.e("Balazs3", breed3Name)
        breed1Name = breed1Name.toLowerCase()
        thread {
            if (breed1Name != null) {
                val items = database.dogBreedElementDao().getAll()
                val corritems = items.toMutableList()
                for (i in corritems) {
                    if (i.name == breed1Name) {
                        i.detected = true
                        onItemChanged(i)
                    }
                }
            }
        }

        Log.v("HashMapTest", hashMap["key"].toString())
        //var best: String = intent.getStringExtra(org.business.EXTRA_TEXT)

        loadBreeds()

        binding.btnDog1.setOnClickListener{
            val dog1Intent = Intent(this, EvaluationActivity::class.java)
            dog1Intent.putExtra("key", breed1Name.toString())
            startActivity(dog1Intent)
        }
        binding.btnDog2.setOnClickListener{
            val dog2Intent = Intent(this, EvaluationActivity::class.java)
            dog2Intent.putExtra("key", breed2Name.toString())
            startActivity(dog2Intent)
        }
        binding.btnDog3.setOnClickListener{
            val dog3Intent = Intent(this, EvaluationActivity::class.java)
            dog3Intent.putExtra("key", breed3Name.toString())
            startActivity(dog3Intent)
        }

    }

    private fun loadBreeds(){
        val entries = listOf(
            PieEntry(breed1Value, breed1Name),
            PieEntry(breed2Value, breed2Name),
            PieEntry(breed3Value, breed3Name),
            PieEntry(breedrestValue, "Other")
        )
        val dataSet = PieDataSet(entries, "Dog Breeds")
        dataSet.setColors(Color.parseColor("#B340C4FF"), Color.parseColor("#FFC39A70"), Color.parseColor("#E6FFEA00"), Color.parseColor("#FFB2FF59"))

        val data = PieData(dataSet)
        binding.chartBreeds.setUsePercentValues(true)
        binding.chartBreeds.data = data
        binding.chartBreeds.data.setValueTextSize(12.0f)
        binding.chartBreeds.data.setValueTextColor(Color.parseColor("#FF515151"))
        binding.chartBreeds.setEntryLabelColor(Color.parseColor("#FF515151"))
        binding.chartBreeds.setEntryLabelTextSize(10.0f)
        binding.chartBreeds.description.isEnabled = false
        binding.chartBreeds.getLegend().setEnabled(false)
        binding.chartBreeds.invalidate()
    }

    fun onItemChanged(item: DogBreedElement) {
        thread {
            database.dogBreedElementDao().update(item)
            Log.d("BreedList", "DogBreeds update was successful")
        }
    }
}