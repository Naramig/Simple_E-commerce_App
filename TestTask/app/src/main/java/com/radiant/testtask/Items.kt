package com.radiant.testtask

import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Items(var name: String, var price: Int, var desc: String, var company: String,var category: String, var id: String, var img: String) : Serializable