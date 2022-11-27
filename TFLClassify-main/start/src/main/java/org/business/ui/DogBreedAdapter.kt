package org.business.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import org.business.R
import org.business.databinding.ElementDogBreedListBinding
import org.data.entities.DogBreedElement


class DogBreedAdapter(private val listener: DogBreedElementClickListener) :
    RecyclerView.Adapter<DogBreedAdapter.DogBreedViewHolder>() {

    private val items = mutableListOf<DogBreedElement>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DogBreedViewHolder(
        ElementDogBreedListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: DogBreedViewHolder, position: Int) {
        val dogbreedElement = items[position]

        holder.binding.ivIcon.setImageResource(getImageResource(dogbreedElement.name))
        holder.binding.tvName.text = dogbreedElement.detected.toString()
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
    fun addItem(item: DogBreedElement) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(dogbreedElements: List<DogBreedElement>) {
        items.clear()
        items.addAll(dogbreedElements)
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = items.size

    interface DogBreedElementClickListener {
        fun onItemChanged(item: DogBreedElement)
    }


    inner class DogBreedViewHolder(val binding: ElementDogBreedListBinding) : RecyclerView.ViewHolder(binding.root)
}