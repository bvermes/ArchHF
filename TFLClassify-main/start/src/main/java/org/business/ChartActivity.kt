package org.business

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
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


        val intent: Intent = intent
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
        binding.btnDog1.setImageResource(getImageResource(breed1Name.toLowerCase()))
        binding.btnDog2.setImageResource(getImageResource(breed2Name.toLowerCase()))
        binding.btnDog3.setImageResource(getImageResource(breed3Name.toLowerCase()))

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
            PieEntry(breed1Value, breed1Name.toUpperCase()),
            PieEntry(breed2Value, breed2Name.toUpperCase()),
            PieEntry(breed3Value, breed3Name.toUpperCase()),
            PieEntry(breedrestValue, "Other")
        )
        val dataSet = PieDataSet(entries, "Dog Breeds")
        dataSet.setColors(Color.parseColor("#FFB2FF59"), Color.parseColor("#FFC39A70"), Color.parseColor("#E6FFEA00"), Color.parseColor("#B340C4FF"))

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

    private fun onItemChanged(item: DogBreedElement) {
        thread {
            database.dogBreedElementDao().update(item)
            Log.d("BreedList", "DogBreeds update was successful")
        }
    }
    @DrawableRes()
    private fun getImageResource(name: String): Int {
        return when (name) {
            "golden_retriever" -> R.drawable.golden_retriever_o
            "dachshund" -> R.drawable.dachshund_o
            "labrador_retriever" -> R.drawable.labrador_retriever_o
            "husky" -> R.drawable.husky_o
            "boxer" -> R.drawable.boxer_o
            "shetland_sheepdog" -> R.drawable.shetland_sheepdog_o
            "west_highland_white_terrier" -> R.drawable.west_highland_white_terrier_o
            "yorkshire_terrier" -> R.drawable.yorkshire_terrier_o
            "border_collie" -> R.drawable.border_collie_o
            "chihuahua" -> R.drawable.chihuahua_o
            "pomeranian" -> R.drawable.pomeranian_o
            "bernese_mountain_dog" -> R.drawable.bernese_mountain_dog_o
            "doberman" -> R.drawable.doberman_o
            "komondor" -> R.drawable.komondor_o
            "pug" -> R.drawable.pug_o
            "saint_bernard" -> R.drawable.saint_bernard_o
            "rottweiler" -> R.drawable.rottweiler_o
            "wire-haired_fox_terrier" -> R.drawable.wire_haired_fox_terrier_o
            "beagle" -> R.drawable.beagle_o
            "irish_setter" -> R.drawable.irish_setter_o
            "kuvasz" -> R.drawable.kuvasz_o
            "poodle" -> R.drawable.poodle_o
            "basset" -> R.drawable.basset_o
            "french_bulldog" -> R.drawable.french_bulldog_o
            "chow" -> R.drawable.chow_o
            "vizsla" -> R.drawable.vizsla_o
            "english_setter" -> R.drawable.english_setter_o
            "whippet" -> R.drawable.whippet_o
            "german_shepherd" -> R.drawable.german_shepherd_o
            "miniature_schnauzer" -> R.drawable.miniature_schnauzer_o
            "bloodhound" -> R.drawable.bloodhound_o
            else -> R.drawable.golden_retriever_o
        }
    }

}