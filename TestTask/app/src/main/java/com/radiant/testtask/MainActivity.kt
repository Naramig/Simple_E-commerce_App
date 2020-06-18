package com.radiant.testtask

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.InputStream
import java.nio.charset.StandardCharsets.UTF_8
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView
    lateinit var fromEditText: EditText
    lateinit var toEditText: EditText
    lateinit var spinner: Spinner

    val EXTRA_MESSAGE = "com.radiant.testTask.ITEM"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fromEditText = findViewById(R.id.from_editText)
        toEditText = findViewById(R.id.to_editText)
        spinner = findViewById(R.id.spinner)
        listView = findViewById(R.id.listView)

        spinner.adapter = ScrollAdapter(this, get_data())

        updateTextView()

    }
    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
    fun get_data(): JSONArray {
        val json: String
        val jsonArray: JSONArray

        val inSt: InputStream = assets.open("MOCK_DATA.json")
        val size = inSt.available()
        val buffer: ByteArray = ByteArray(size)
        inSt.read(buffer)
        inSt.close()

        json = buffer.toString(UTF_8)
        jsonArray = JSONArray(json)
        return jsonArray
    }

    fun updateTextView(){
        var from: Int = 0
        if(fromEditText.text.toString() != "") {
            from = fromEditText.text.toString().toInt()
        }

        var to: Int = 0
        if(toEditText.text.toString() != ""){
            to = toEditText.text.toString().toInt()
        }

        val chosenCategory: String = spinner.selectedItem.toString()
        listView.adapter = ItemsAdapter(this, get_data(), from, to, chosenCategory)

        listView.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            val item =  parent.adapter.getItem(position) as Items
            val intent = Intent(this,Detail::class.java)
            intent.putExtra(EXTRA_MESSAGE, item as Serializable)
            startActivity(intent)
        })
    }
    fun updateListViewListener(view: View) {
        updateTextView()
        view.hideKeyboard()
    }

}

