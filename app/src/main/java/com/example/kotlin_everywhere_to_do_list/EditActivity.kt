package com.example.kotlin_everywhere_to_do_list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        title = "編輯模式"

        if (!intent.getStringExtra("event").isNullOrEmpty()) edtText_edit.setText(intent.getStringExtra("event"))

        btn_confirm.setOnClickListener {
            if (edtText_edit.text.isNullOrEmpty()) Toast.makeText(this, "請輸入待辦事項", Toast.LENGTH_SHORT).show()
            else confirm()
        }

        btn_cancel.setOnClickListener {
            this.finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        var deleteBtn = menu?.findItem(R.id.menu_delete)
        deleteBtn?.setOnMenuItemClickListener {
            deleteEvent()
            true
        }
        return true
    }
    private fun confirm(){
        val newEvent = edtText_edit.text.toString()
        saveEvent(newEvent)
        setResult(Activity.RESULT_OK)
        finish()
    }
    private fun saveEvent(note :String){
        val preference = getSharedPreferences("MySP", Context.MODE_PRIVATE)
        val editor = preference.edit()
        var index = 0
        while (!preference.getString("myNote-$index", "").isNullOrEmpty()) index++
        editor.putString("myNote-$index",note).apply()
        val intent = Intent(this, EditActivity::class.java);
        intent.putExtra("editword", note)
    }
    private fun deleteEvent(){
        Log.d("data", "hi")
        val preference = getSharedPreferences("MySP", Context.MODE_PRIVATE)
        val editor = preference.edit()
        editor.clear().commit()
    }
}
