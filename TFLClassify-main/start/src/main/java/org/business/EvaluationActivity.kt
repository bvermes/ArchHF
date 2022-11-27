package org.business

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EvaluationActivity : AppCompatActivity() {
    private val BestGuessText by lazy{
        findViewById<TextView>(R.id.bestguessText)
    }
    private val BestGuessImageView by lazy{
        findViewById<ImageView>(R.id.bestGuessImageView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluation)

        var intent: Intent = intent
        var best: String = intent.getStringExtra(org.business.EXTRA_TEXT)
        BestGuessText.text = best
        when(best){
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
}