package com.radiant.testtask

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.gson.Gson
import org.json.JSONArray

class ScrollAdapter (context: Context, jsonArray: JSONArray): BaseAdapter() {
    private val mContext: Context

    init {
        mContext = context
    }
    var listOfCategory: List<String> = getCategory(jsonArray)

    private fun getCategory(jsonArray: JSONArray): List<String> {
        val listOfCategory: MutableList<String> = mutableListOf()
        listOfCategory.add("Any")
        for (i in 0..jsonArray.length() - 1){
            val json = jsonArray.getJSONObject(i).toString()// your json
            val item = Gson().fromJson(json, Items::class.java)
            listOfCategory.add(item.category)
        }
        return listOfCategory.distinct()
    }

    override fun getCount(): Int {
        return listOfCategory.size
    }

    override fun getItem(position: Int): Any {
        return listOfCategory[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView = TextView(mContext)
        textView.text = listOfCategory[position]
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20.0f)

        return textView
    }
}