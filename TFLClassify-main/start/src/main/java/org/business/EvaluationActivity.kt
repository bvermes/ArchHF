package org.business

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.data.database.DogBreedListDatabase
import org.data.entities.DogBreedElement
import kotlin.concurrent.thread

class EvaluationActivity : AppCompatActivity() {

    private lateinit var database: DogBreedListDatabase
    private lateinit var best: String
    //private lateinit var bestBreedElement: DogBreedElement
    private val BestGuessText by lazy{
        findViewById<TextView>(R.id.bestguessText)
    }
    private val BestGuessImageView by lazy{
        findViewById<ImageView>(R.id.bestGuessImageView)
    }
    private val DescriptionText by lazy{
        findViewById<TextView>(R.id.descriptionText)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)

        val intent: Intent = intent
        //var best: String? = intent.getStringExtra(org.business.EXTRA_TEXT.toString())
        best = intent.getStringExtra("key") as String
        BestGuessText.text = best.toUpperCase()
        best = best.toLowerCase()
        database = DogBreedListDatabase.getDatabase(applicationContext)

        thread {
            if (best != null) {
                val items = database.dogBreedElementDao().getAll()
                val corritems = items.toMutableList()
                setBest(corritems)

            }
        }

        when(best.toUpperCase()){
            "GOLDEN_RETRIEVER"-> BestGuessImageView.setImageResource(R.drawable.golden_retriever)
            "DACHSHUND"-> BestGuessImageView.setImageResource(R.drawable.dachshund)
            "LABRADOR_RETRIEVER"-> BestGuessImageView.setImageResource(R.drawable.labrador_retriever)
            "HUSKY"-> BestGuessImageView.setImageResource(R.drawable.husky)
            "BOXER"-> BestGuessImageView.setImageResource(R.drawable.boxer)
            "SHETLAND_SHEEPDOG"-> BestGuessImageView.setImageResource(R.drawable.shetland_sheepdog)
            "WEST_HIGHLAND_WHITE_TERRIER"-> BestGuessImageView.setImageResource(R.drawable.west_highland_white_terrier)
            "YORKSHIRE_TERRIER"-> BestGuessImageView.setImageResource(R.drawable.yorkshire_terrier)
            "BORDER_COLLIE"-> BestGuessImageView.setImageResource(R.drawable.border_collie)
            "CHIHUAHUA"-> BestGuessImageView.setImageResource(R.drawable.chihuahua)
            "POMERANIAN"-> BestGuessImageView.setImageResource(R.drawable.pomeranian)
            "BERNESE_MOUNTAIN_DOG"-> BestGuessImageView.setImageResource(R.drawable.bernese_mountain_dog)
            "DOBERMAN"-> BestGuessImageView.setImageResource(R.drawable.doberman)
            "KOMONDOR"-> BestGuessImageView.setImageResource(R.drawable.komondor)
            "PUG"-> BestGuessImageView.setImageResource(R.drawable.pug)
            "SAINT_BERNARD"-> BestGuessImageView.setImageResource(R.drawable.saint_bernard)
            "ROTTWEILER"-> BestGuessImageView.setImageResource(R.drawable.rottweiler)
            "WIRE-HAIRED_FOX_TERRIER"-> BestGuessImageView.setImageResource(R.drawable.wire_haired_fox_terrier)
            "BEAGLE"-> BestGuessImageView.setImageResource(R.drawable.beagle)
            "IRISH_SETTER"-> BestGuessImageView.setImageResource(R.drawable.irish_setter)
            "KUVASZ"-> BestGuessImageView.setImageResource(R.drawable.kuvasz)
            "POODLE"-> BestGuessImageView.setImageResource(R.drawable.poodle)
            "BASSET"-> BestGuessImageView.setImageResource(R.drawable.basset)
            "FRENCH_BULLDOG"-> BestGuessImageView.setImageResource(R.drawable.french_bulldog)
            "CHOW"-> BestGuessImageView.setImageResource(R.drawable.chow)
            "VIZSLA"-> BestGuessImageView.setImageResource(R.drawable.vizsla)
            "ENGLISH_SETTER"-> BestGuessImageView.setImageResource(R.drawable.english_setter)
            "WHIPPET"-> BestGuessImageView.setImageResource(R.drawable.whippet)
            "GERMAN_SHEPHERD"-> BestGuessImageView.setImageResource(R.drawable.german_shepherd)
            "MINIATURE_SCHNAUZER"-> BestGuessImageView.setImageResource(R.drawable.miniature_schnauzer)
            "BLOODHOUND"-> BestGuessImageView.setImageResource(R.drawable.bloodhound)
        }

    }

    private fun setBest(corritems: MutableList<DogBreedElement>) {
        for (i in corritems){
            if (i.name == best){
                DescriptionText.text = i.description
            }
        }

    }


}