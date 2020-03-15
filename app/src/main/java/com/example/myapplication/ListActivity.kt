package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson


class ListActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecordListAdapter
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        this.title = "WebList@"+User.Login

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)


        viewManager = LinearLayoutManager(this)
        viewAdapter = RecordListAdapter(getData(), this){ it->

            builder.setTitle("Открыть в:").setItems(arrayOf("Приложении", "Браузере")) { dialog, which->
                when(which){
                    0-> openInApp(it.Url, it.Name)
                    1-> openInBrowser(it.Url)
                }
            }
            var dialog: Dialog = builder.create()
            dialog.show()

        }

        list = findViewById(R.id.list)
        list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    private fun openInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    private fun openInApp(url: String, name: String) {
        val intent = Intent(this, WebviewActivity::class.java).apply {
            putExtra("name", name)
            putExtra("url", url)
        }
        startActivity(intent)
    }

    private fun getData(): ArrayList<Record> {

        val str:String = this.resources.openRawResource(R.raw.records).bufferedReader().use { it.readText() }
        val records = Gson().fromJson<Array<Record>>(str, Array<Record>::class.java)
        return records.toCollection(ArrayList())
    }


}
