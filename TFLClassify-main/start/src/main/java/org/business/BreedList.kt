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
        thread {
            if(database.dogBreedElementDao().getAll().isEmpty()){
                loadDatabase()
            }
        }

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

    private fun onBreedCreated(newItem: DogBreedElement) {
        thread {
            val insertId = database.dogBreedElementDao().insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }
    private fun loadDatabase(){
        onBreedCreated(getBreedElement("golden_retriever", 0, "The Golden Retriever is a Scottish breed of retriever dog of medium size. It is characterised by a gentle and affectionate nature and a striking golden coat. It is commonly kept as a pet and is among the most frequently registered breeds in several Western countries. It is a frequent competitor in dog shows and obedience trials; it is also used as a gundog, and may be trained for use as a guide dog."))
        onBreedCreated(getBreedElement("dachshund", 0, "The dachshund, also known as the wiener dog, badger dog, and sausage dog, is a short-legged, long-bodied, hound-type dog breed. The dog may be smooth-haired, wire-haired, or long-haired, and comes in a variety of colors.The standard-sized dachshund was developed to scent, chase, and flush out badgers and other burrow-dwelling animals. The miniature dachshund was bred to hunt small animals such as rabbits. According to the American Kennel Club, the dachshund was ranked 12th in popularity among dog breeds in the United States in 2018."))
        onBreedCreated(getBreedElement("labrador_retriever", 0, "The Labrador Retriever or simply Labrador is a British breed of retriever gun dog. It was developed in the United Kingdom from fishing dogs imported from the colony of Newfoundland, and was named after the Labrador region of that colony. It is among the most commonly kept dogs in several countries, particularly in the Western world. The Labrador is friendly, energetic, and playful. It was bred as a sporting and hunting dog but is widely kept as a companion dog. It may also be trained as a guide or assistance dog, or for rescue or therapy work."))
        onBreedCreated(getBreedElement("husky", 0, "The Siberian Husky is a medium-sized working sled dog breed. It is recognizable by its thickly furred double coat, erect triangular ears, and distinctive markings, and is smaller than the similar-looking Alaskan Malamute.It is an active, energetic, resilient breed, whose ancestors lived in the extremely cold and harsh environment of the Siberian Arctic. William Goosak, a Russian fur trader, introduced them to Nome, Alaska, during the Nome Gold Rush, initially as sled dogs to work the mining fields and for expeditions.Today, the Siberian Husky is typically kept as a house pet, though they are still frequently used as sled dogs. "))
        onBreedCreated(getBreedElement("shetland_sheepdog", 0, "The Shetland Sheepdog is a breed of herding dog that originated in the Shetland Islands of Scotland. This diligent small dog is clever, vocal, excitable and willing to please. They are incredibly trustworthy to their owners to the point where they are often referred to as 'shadows' due to their attachment to family. Shetland Sheepdog is a hardy but diminutive breed developed to thrive amidst the harsh and meagre conditions of its native islands. While the Sheltie still excels at herding, today it is often raised as a working dog and/or family pet."))
        onBreedCreated(getBreedElement("west_highland_white_terrier", 0, "The West Highland White Terrier, commonly known as the Westie, is a breed of dog from Scotland with a distinctive white harsh coat with a somewhat soft white undercoat.The breed is intelligent, quick to learn, and can be good with children, but does not always tolerate rough handling. The Westie is an active breed, and is social with a high prey drive, as they were once used to hunt rodents. They are a very energetic and boisterous breed, needing regular exercise of around one hour per day."))
        onBreedCreated(getBreedElement("boxer", 0, "The Boxer is a medium to large, short-haired dog breed of mastiff-type, developed in Germany. The coat is smooth and tight-fitting; colors are fawn, brindled, or white, with or without white markings. Boxers are brachycephalic (they have broad, short skulls), have a square muzzle, mandibular prognathism (an underbite), very strong jaws, and a powerful bite ideal for hanging on to large prey. The Boxer was bred from the Old English Bulldog and the now extinct Bullenbeisser, which became extinct by crossbreeding rather than by a decadence of the breed. The Boxer is a member of both The Kennel Club and American Kennel Club (AKC) Working Group."))
        onBreedCreated(getBreedElement("yorkshire_terrier", 0, "The Yorkshire Terrier is one of the smallest dog breeds of the terrier type and indeed of any dog breed. The breed developed during the 19th century in Yorkshire, England. Ideally its maximum size is 7 pounds (3.2 kg).Yorkshire terriers are playful and energetic dogs. They often exhibit separation anxiety when left alone, which is a reason that some owners keep two dogs"))
        onBreedCreated(getBreedElement("border_collie", 0, "The Border Collie is a Scottish breed of herding dog of medium size. Widely considered to be the most intelligent dog breed, they are descended from landrace sheepdogs once found all over the British Isles, but became standardised in the Anglo-Scottish border region. They are now mostly used as working dogs to herd livestock, specifically sheep.Border Collies are extremely energetic, acrobatic, and athletic. They frequently compete with great success in sheepdog trials and a range of dog sports like dog obedience, disc dog, herding and dog agility. Border Collies continue to be employed in their traditional work of herding livestock throughout the world and are kept as pets."))
        onBreedCreated(getBreedElement("chihuahua", 0, "The Chihuahua or Spanish: Chihuahueño is a Mexican breed of toy dog. It is named for the Mexican state of Chihuahua and is among the smallest of all dog breeds. It is usually kept as a companion animal or for showing."))
        onBreedCreated(getBreedElement("pomeranian", 0, "The Pomeranian (often known as a Pom) is a breed of dog of the Spitz type. Classed as a toy dog breed because of its small size, the Pomeranian is descended from larger Spitz-type dogs, specifically the German Spitz. In many countries, they are known as the Zwergspitz ('Dwarf Spitz').The breed has been made popular by a number of royal owners since the 18th century. Queen Victoria owned a particularly small Pomeranian and consequently, the smaller variety became universally popular."))
        onBreedCreated(getBreedElement("bernese_mountain_dog", 0, "The Bernese Mountain Dog (German: Berner Sennenhund) is a large dog breed, one of the four breeds of Sennenhund-type dogs from Bern, Switzerland and the Swiss Alps. These dogs have roots in the Roman mastiffs. The name Sennenhund is derived from the German Senne ('alpine pasture') and Hund (hound/dog), as they accompanied the alpine herders and dairymen called Senn. Berner (or Bernese in English) refers to the area of the breed's origin, in the canton of Bern. This breed was originally kept as a general farm dog. Large Sennenhunde in the past were also used as draft animals, pulling carts. The breed was officially established in 1912."))
        onBreedCreated(getBreedElement("doberman", 0, "The Dobermann, or Doberman Pinscher in the United States and Canada, is a medium-large breed of domestic dog that was originally developed around 1890 by Louis Dobermann, a tax collector from Germany. The Dobermann has a long muzzle. It stands on its pads and is not usually heavy-footed. Ideally, they have an even and graceful gait. Traditionally, the ears are cropped and posted and the tail is docked. However, in some countries, these procedures are now illegal and it is often considered cruel and unnecessary. Dobermanns have markings on the chest, paws/legs, muzzle, above the eyes, and underneath the tail.Dobermanns are known to be intelligent, alert, and tenaciously loyal companions and guard dogs."))
        onBreedCreated(getBreedElement("komondor", 0, "The Komondor, also known as the Hungarian sheepdog, is a large, white-coloured Hungarian breed of livestock guardian dog with a long, corded coat.Sometimes referred to as 'mop dogs', the Komondor is a long-established dog breed commonly employed to guard livestock and other property. The Komondor was brought to Europe by the Cumans and the oldest known mention of it is in a Hungarian codex from 1544. The Komondor breed has been declared one of Hungary’s national treasures, to be preserved and protected from modification."))
        onBreedCreated(getBreedElement("pug", 0, "The Pug is a breed of dog originally from China, with physically distinctive features of a wrinkly, short-muzzled face and curled tail. Pugs were brought from China to Europe in the sixteenth century and were popularized in Western Europe by the House of Orange of the Netherlands, and the House of Stuart. In the United Kingdom, in the nineteenth century, Queen Victoria developed a passion for pugs.Pugs are known for being sociable and gentle companion dogs. The American Kennel Club describes the breed's personality as even-tempered and charming."))
        onBreedCreated(getBreedElement("saint_bernard", 0, "The Saint Bernard or St. Bernard is a breed of very large working dog from the Western Alps in Italy and Switzerland. They were originally bred for rescue work by the hospice of the Great St Bernard Pass on the Italian-Swiss border. The hospice, built by and named after Italian monk Bernard of Menthon, acquired its first dogs between 1660 and 1670. The breed has become famous through tales of Alpine rescues, as well as for its large size, and gentle temperament."))
        onBreedCreated(getBreedElement("rottweiler", 0, "The Rottweiler is a breed of domestic dog, regarded as medium-to-large or large. The dogs were known in German as Rottweiler Metzgerhund, meaning Rottweil butchers' dogs, because their main use was to herd livestock and pull carts laden with butchered meat to market. This continued until the mid-19th century when railways replaced droving. Although still used to herd stock in many parts of the world, Rottweilers are now also used as search and rescue dogs, guard dogs, and police dogs."))
        onBreedCreated(getBreedElement("wire-haired_fox_terrier", 0, "The Wire Fox Terrier is one of many terrier breeds. Two of the wire fox terrier's most distinctive traits are its energy and intelligence. It has a low threshold for boredom and requires stimulation, exercise and attention. The wire fox terrier is a companion animal that requires near-constant attention."))
        onBreedCreated(getBreedElement("beagle", 0, "The beagle is a breed of small scent hound, similar in appearance to the much larger foxhound. The beagle was developed primarily for hunting hare known as beagling. Possessing a great sense of smell and superior tracking instincts, the beagle is the primary breed used as a detection dog for prohibited agricultural imports and foodstuffs in quarantine around the world. The beagle is intelligent and is a popular pet due to its size, good temper, and a lack of inherited health problems."))
        onBreedCreated(getBreedElement("irish_setter", 0, "The Irish Setter is a setter, a breed of gundog, and family dog. They get along well with children and other dogs and will greet visitors enthusiastically. Even though they do well with household pets, small animals may pose a problem for this breed, as they are a hunting breed."))
        onBreedCreated(getBreedElement("kuvasz",0," The kuvasz is one of the oldest herding dogs among the Hungarian dog breeds, perhaps older than the komondor. When shepherding ceased, it became a herding dog of farms and country mansions. The Kuvaz is a guardian and protective herding dog, but in the past it was used as a hunting dog for hunting bears, bison and wildebeests, but today it is used to guard farms and houses. Nowadays, more and more people keep it because of its look that radiates pride and nobility."))
        onBreedCreated(getBreedElement("poodle",0," The Poodle is a breed of water dog. The breed is divided into four varieties based on size, the Standard Poodle, Medium Poodle, Miniature Poodle and Toy Poodle. They have a distinctive thick, curly coat, and come in many colors. While a reasonably healthy breed, they are prone to sebaceous adenitis and Addison's disease among other genetic disorders.The Poodle most likely originated in Germany. The Standard Poodle was originally used by wildfowl hunters to retrieve game from water. They are now one of the most popular dog breeds."))
        onBreedCreated(getBreedElement("basset", 0, "The Basset Hound is a short-legged breed of dog in the hound family. The Basset is a scent hound that was originally bred for the purpose of hunting hare. Their sense of smell and ability to ground-scent is second only to the Bloodhound. They are friendly, outgoing, and playful, tolerant of children and other pets."))
        onBreedCreated(getBreedElement("french_bulldog",0, "The French Bulldog, is a French breed of companion dog or toy dog. It appeared in Paris in the mid-nineteenth century, apparently the result of cross-breeding of Toy Bulldogs imported from England and local Parisian ratters.It is commonly kept as a pet, and is among the most frequently registered dogs in a number of countries including Australia, the United Kingdom and the United States."))
        onBreedCreated(getBreedElement("chow",0,"The Chow Chow is a spitz-type of dog breed originally from northern China. The Chow Chow is a sturdily built dog, square in profile, with a broad skull and small, triangular, erect ears with rounded tips. The breed is known for a very dense double coat that is either smooth or rough. The fur is particularly thick in the neck area, giving it a distinctive ruff or mane appearance. Most commonly kept as pets, Chow Chows tend to display discernment of strangers and can become fiercely protective of their owners and property."))
        onBreedCreated(getBreedElement("vizsla", 0, "The Vizsla is a dog breed from Hungary. The Hungarian or Magyar Vizsla or Smooth-Haired Vizsla are sporting dogs and loyal companions. The Vizsla's medium size is one of the breed's most appealing characteristics.The Vizsla is a natural hunter endowed with an excellent nose and outstanding trainability. Although they are lively, gentle-mannered, demonstrably affectionate and sensitive, they are also fearless and possess a well-developed protective instinct."))
        onBreedCreated(getBreedElement("english_setter", 0, "The English Setter is a medium-size breed of dog. It is part of the setter group, which includes the red Irish Setters, Irish Red and White Setters, and black-and-tan Gordon Setters. A gentle but at times strong-willed, mischievous gun dog, bred for a mix of endurance and athleticism, it is used to hunt for game such as quail, pheasant and grouse. When working, the dog will hunt methodically seeking the airborne scent of its prey. Generally reasonably healthy, they have an average life span of 11 to 12 years. The Kennel Club advises UK breeders to screen for hip dysplasia."))
        onBreedCreated(getBreedElement("whippet", 0, "The Whippet is a dog breed of medium size. It is a sighthound breed that originated in England, descended from the Greyhound. Part of the hound group, Whippets have relatively few health problems other than arrhythmia.They are a popular companion breed frequently used in amateur racing, lure coursing, and dog shows; they have the highest running speed of breeds for their weight: and are possibly the fastest-accelerating dog breed."))
        onBreedCreated(getBreedElement("german_shepherd", 0, "The German Shepherd or Alsatian is a German breed of working dog of medium to large size. The breed was developed by Max von Stephanitz using various traditional German herding dogs from 1899.It was originally bred as a herding dog, for herding sheep. It has since been used in many other types of work, including disability assistance, search-and-rescue, police work, and warfare. It is commonly kept as a companion dog, and according to the Fédération Cynologique Internationale had the second-highest number of annual registrations in 2013."))
        onBreedCreated(getBreedElement("miniature_schnauzer", 0, "The Miniature Schnauzer is a breed of small dog. They may have been developed from the smallest specimens of the Standard Schnauzer, or crosses between the standard and one or more smaller breeds, as farmers bred a small dog that was an efficient ratting dog. They are described as 'spunky' but aloof dogs, with good guarding tendencies without some guard dogs' predisposition to bite.It is the most popular Schnauzer breed, and remains one of the most popular worldwide, primarily for its temperament and relatively small size."))
        onBreedCreated(getBreedElement("bloodhound", 0, "The bloodhound is a large scent hound, originally bred for hunting deer, wild boar and, since the Middle Ages, for tracking people. Believed to be descended from hounds once kept at the Abbey of Saint-Hubert, Belgium, in French it is called, le chien de Saint-Hubert. This breed is famed for its ability to discern human scent over great distances, even days later. Its extraordinarily keen sense of smell is combined with a strong and tenacious tracking instinct, producing the ideal scent hound, and it is used by police and law enforcement all over the world to track escaped prisoners, missing people, and lost pets."))
    }
    private fun getBreedElement(_name: String, _detected: Int, _description: String) = DogBreedElement(
        name = _name,
        detected = _detected == 1,
        description = _description

    )
}
