package org.business

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import org.business.databinding.ActivityChartBinding

class ChartActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChartBinding
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

        var intent: Intent = intent
        val hashMap = intent.getSerializableExtra("map") as HashMap<String, Float>

        breed1Name = hashMap.keys.elementAt(0) // Get key by index.
        breed1Value = hashMap.getValue(breed1Name) // Get value.
        breed2Name = hashMap.keys.elementAt(1) // Get key by index.
        breed2Value = hashMap.getValue(breed2Name) // Get value.
        breed3Name = hashMap.keys.elementAt(2) // Get key by index.
        breed3Value = hashMap.getValue(breed3Name) // Get value.
        breedrestValue = 1 - breed1Value - breed2Value - breed3Value

        Log.v("HashMapTest", hashMap["key"].toString())
        //var best: String = intent.getStringExtra(org.business.EXTRA_TEXT)

        loadBreeds()
    }
    private fun loadBreeds(){
        val entries = listOf(
            PieEntry(breed1Value, breed1Name),
            PieEntry(breed2Value, breed2Name),
            PieEntry(breed3Value, breed3Name),
            PieEntry(breedrestValue, "Rest")
        )
        val dataSet = PieDataSet(entries, "Dog Breeds")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()

        val data = PieData(dataSet)
        binding.chartBreeds.data = data
        binding.chartBreeds.invalidate()
    }

}