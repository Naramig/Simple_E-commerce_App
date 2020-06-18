package com.radiant.testtask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.json.JSONArray

class ItemsAdapter (context: Context, jsonArray: JSONArray, from: Int, to: Int, chosenCategory: String): BaseAdapter() {
    private val mContext: Context

    var arrayOfItems: MutableList<Items> = getArrayOfItem(jsonArray)
    var finalArrayOfItems: List<Items> = getFinalArrayOfItems(arrayOfItems, from, to, chosenCategory)

    private fun getFinalArrayOfItems(arrayOfItems: MutableList<Items>, from: Int, to: Int, chosenCategory: String): List<Items> {
        val finalArrayOfItems: MutableList<Items> = mutableListOf()
        for(i in 0..arrayOfItems.size-1){
            if(arrayOfItems[i].price >= from && to >= arrayOfItems[i].price){
                if(chosenCategory == "Any" || arrayOfItems[i].category.equals(chosenCategory)) {
                    finalArrayOfItems.add(arrayOfItems[i])
                }
            }
        }
        return finalArrayOfItems
    }

    init {
        mContext = context
    }

    fun getArrayOfItem(jsonArray: JSONArray): MutableList<Items>{
        val arrayOfItems: MutableList<Items> = mutableListOf()
        for (i in 0..jsonArray.length() - 1){
            val json = jsonArray.getJSONObject(i).toString()// your json
            val topic = Gson().fromJson(json, Items::class.java)
            val temp: Items = Items(topic.name, topic.price, topic.desc, topic.company, topic.category, topic.id, topic.img)
            arrayOfItems.add(temp)
        }
        return arrayOfItems
    }

    override fun getCount(): Int {
       return finalArrayOfItems.size
    }

    override fun getItem(position: Int): Any {
        return finalArrayOfItems[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val lauoutInflater = LayoutInflater.from(mContext)
        val rowMain = lauoutInflater.inflate(R.layout.list_item, parent, false)
        val name: TextView = rowMain.findViewById(R.id.name_textView)
        val price: TextView = rowMain.findViewById(R.id.price_textView)
        val image: ImageView = rowMain.findViewById(R.id.poster_imageView)

        name.text = finalArrayOfItems[position].name
        price.text = "Price : " + finalArrayOfItems[position].price.toString()
        val url = finalArrayOfItems[position].img

        Picasso.get().load(url).into(image)

        return rowMain
    }
}