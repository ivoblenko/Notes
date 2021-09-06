package com.example.notes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(
    val id: Long = 0,
    val title: String = "",
    val text: String = ""
) : Parcelable