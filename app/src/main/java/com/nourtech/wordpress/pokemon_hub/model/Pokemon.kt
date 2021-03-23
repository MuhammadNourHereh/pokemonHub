package com.nourtech.wordpress.pokemon_hub.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_fev")
data class Pokemon(

    val name: String,
    var url: String
) {

    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
