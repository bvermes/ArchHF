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
            "BEAR"-> BestGuessImageView.setImageResource(R.drawable.bear)
            "BUTTERFLY"-> BestGuessImageView.setImageResource(R.drawable.butterfly)
            "CAT" -> BestGuessImageView.setImageResource(R.drawable.cat)
            "CHEETAH"->BestGuessImageView.setImageResource(R.drawable.cheetah)
            "CHICKEN"->BestGuessImageView.setImageResource(R.drawable.chicken)
            "COW"->BestGuessImageView.setImageResource(R.drawable.cow)
            "CROCODILE"->BestGuessImageView.setImageResource(R.drawable.crocodile)
            "DEER"->BestGuessImageView.setImageResource(R.drawable.deer)
            "DOG"->BestGuessImageView.setImageResource(R.drawable.dog)
            "ELEPHANT"->BestGuessImageView.setImageResource(R.drawable.elephant)
            "GIRAFFE"->BestGuessImageView.setImageResource(R.drawable.giraffe)
            "GOAT"->BestGuessImageView.setImageResource(R.drawable.goat)
            "HIPPOPOTAMUS"->BestGuessImageView.setImageResource(R.drawable.hippopotamus)
            "HORSE"->BestGuessImageView.setImageResource(R.drawable.horse)
            "KANGAROO"->BestGuessImageView.setImageResource(R.drawable.kangaroo)
            "LION"->BestGuessImageView.setImageResource(R.drawable.lion)
            "MEERKAT"->BestGuessImageView.setImageResource(R.drawable.meerkat)
            "MONKEY"->BestGuessImageView.setImageResource(R.drawable.monkey)
            "MOOSE"->BestGuessImageView.setImageResource(R.drawable.moose)
            "OSTRICH"->BestGuessImageView.setImageResource(R.drawable.ostrich)
            "PANDA"->BestGuessImageView.setImageResource(R.drawable.panda)
            "PENGUIN"->BestGuessImageView.setImageResource(R.drawable.penguin)
            "PORCUPINE"->BestGuessImageView.setImageResource(R.drawable.porcupine)
            "RABBIT"->BestGuessImageView.setImageResource(R.drawable.rabbit)
            "RHINO"->BestGuessImageView.setImageResource(R.drawable.rhino)
            "SHEEP"->BestGuessImageView.setImageResource(R.drawable.sheep)
            "SNAKE"->BestGuessImageView.setImageResource(R.drawable.snake)
            "SPIDER"->BestGuessImageView.setImageResource(R.drawable.spider)
            "SQUIRREL"->BestGuessImageView.setImageResource(R.drawable.squirrel)
            "TIGER"->BestGuessImageView.setImageResource(R.drawable.tiger)
            "TORTOISE"->BestGuessImageView.setImageResource(R.drawable.tortoise)
            "WALRUS"->BestGuessImageView.setImageResource(R.drawable.walrus)
            "WOLF"->BestGuessImageView.setImageResource(R.drawable.wolf)
            "ZEBRA"->BestGuessImageView.setImageResource(R.drawable.zebra)
        }
    }
}