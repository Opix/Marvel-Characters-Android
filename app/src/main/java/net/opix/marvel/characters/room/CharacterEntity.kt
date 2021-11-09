package net.opix.marvel.characters.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "timestamp") val timestamp: String,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "locationline1") val locationLine1: String?,
    @ColumnInfo(name = "locationline2") val locationLine2: String?)