package com.radiant.testtask

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class Detail : AppCompatActivity() {
    val EXTRA_MESSAGE = "com.radiant.testTask.ITEM"

    lateinit var priceTextView: TextView
    lateinit var descTextView: TextView
    lateinit var nameTextView: TextView
    lateinit var imageView: ImageView
    lateinit var categoryTextView: TextView
    lateinit var idOfItems: TextView
    lateinit var companyTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        nameTextView = findViewById(R.id.name_textView)
        priceTextView = findViewById(R.id.price_textView)
        descTextView = findViewById(R.id.desc_textView)
        categoryTextView = findViewById(R.id.category_textView)
        idOfItems = findViewById(R.id.itemId_textView)
        companyTextView = findViewById(R.id.company_textView)
        imageView = findViewById(R.id.image_imageView)

        val items = intent.getSerializableExtra(EXTRA_MESSAGE) as Items

        nameTextView.text = items.name
        descTextView.text = items.desc
        priceTextView.text = items.price.toString() + " $"
        idOfItems.text = items.id
        companyTextView.text = items.company
        categoryTextView.text = items.category

        var url: String = items.img

        Picasso.get().load(url).into(imageView)

        //Add back button to actionbar
        val actionbar = supportActionBar
        actionbar!!.title = "Details About Item"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
