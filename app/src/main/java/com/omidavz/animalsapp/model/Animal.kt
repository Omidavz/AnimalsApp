package com.omidavz.animalsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Animal(
    val characteristics: Characteristics,
    val locations: List<String>? = arrayListOf("No Information"),
    val name: String? = "No Information",
//    val taxonomy: Taxonomy
) : Parcelable

@Parcelize
data class Characteristics(
    val lifespan: String? = "No Information",
    val diet: String? = "No Information",
    val location: String? = "No Information",
//    val age_of_molting: String? = "",
//    val age_of_sexual_maturity: String? = "",
//    val age_of_weaning: String? = "",
//    val aggression: String? = "",
//    val average_clutch_size: String? = "",
//    val average_litter_size: String? = "",
//    val biggest_threat: String? = "",
//    val color: String? = "",
//    val common_name: String? = "",
//    val distinctive_feature: String? = "",
//    val estimated_population_size: String? = "",
//    val favorite_food: String? = "",
//    val gestation_period: String? = "",
//    val group: String? = "",
//    val group_behavior: String? = "",
//    val habitat: String? = "",
//    val height: String? = "",
//    val incubation_period: String? = "",
//    val length: String? = "",
//    val lifestyle: String? = "",
//    val litter_size: String? = "",
//    val main_prey: String? = "",
//    val migratory: String? = "",
//    val most_distinctive_feature: String? = "",
//    val name_of_young: String? = "",
//    val nesting_location: String? = "",
//    val number_of_species: String? = "",
//    val optimum_ph_level: String? = "",
//    val other_name : String? = "",
//    val predators: String? = "",
//    val prey: String? = "",
//    val skin_type: String? = "",
//    val slogan: String? = "",
//    val temperament: String? = "",
//    val top_speed: String? = "",
//    val training: String? = "",
//    val type: String? = "",
//    val venomous: String? = "",
//    val water_type: String? = "",
//    val weight: String? = "",
//    val wingspan: String? = ""
) : Parcelable

//@Parcelize
//data class Taxonomy(
//    val `class`: String? = "",
//    val family: String? = "",
//    val genus: String? = "",
//    val kingdom: String? = "",
//    val order: String? = "",
//    val phylum: String? = "",
//    val scientific_name: String? = ""
//) : Parcelable