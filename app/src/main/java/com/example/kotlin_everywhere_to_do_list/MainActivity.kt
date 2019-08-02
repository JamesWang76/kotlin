package com.example.kotlin_everywhere_to_do_list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {


    private val myAdapter = MyAdapter()
    private var holderList = mutableListOf<Holder.Datas>()
    val noteList = mutableListOf<Holder.Datas>()

    val TXT_CODE = 1
    val ALBUM_CODE = 2

    override fun onPause() {
        super.onPause()
        Log.d("activity", "pause")

    }


    override fun onResume() {
        super.onResume()
        Log.d("sizeResume", myAdapter.itemCount.toString())
        Log.d("activity", "resume")
        myAdapter.update(noteList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = myAdapter
        loadTxtEvent()
    }

    private fun addEvent(){
        val intent = Intent(this, EditActivity::class.java)
        startActivityForResult(intent, TXT_CODE)
    }

    private fun choosePhotoEvent(){
        val intentAlbum = Intent(Intent.ACTION_PICK)
        intentAlbum.type = "image/*"
        intentAlbum.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intentAlbum,ALBUM_CODE)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("data: " , data.toString())

        when(requestCode) {
            TXT_CODE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val intent = intent
//                    loadTxtEvent()
                }
            }
            ALBUM_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val resolver = this.contentResolver
                    val bitmap = MediaStore.Images.Media.getBitmap(resolver, data?.data)
                    noteList.add(Holder.Datas(bitmap, "hi"))
                    Log.d("sizeActicity", myAdapter.itemCount.toString())
                    myAdapter.update(noteList)
                    // glide
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        var add = menu?.findItem(R.id.menu_item_add)
        var addImage = menu?.findItem(R.id.menu_Image_add)
        add?.setOnMenuItemClickListener {
            addEvent()
            true
        }
        addImage?.setOnMenuItemClickListener {
            choosePhotoEvent()
            true
        }


        return true
        //return super.onCreateOptionsMenu(menu)
    }
    private fun loadTxtEvent(){
        val loader = getSharedPreferences("MySP", Context.MODE_PRIVATE)
        val keyList = loader.all.keys.sorted()
        for (key in keyList){

            noteList.add(Holder.Datas(null, loader.getString(key, "")!!))
        }
        myAdapter.update(noteList)
    }
    private fun addTxtEvent(){
        myAdapter.update(noteList)
    }
    private fun deletePrefData(){
        val loader = getSharedPreferences("MySP", Context.MODE_PRIVATE)
        val keyList = loader.all.keys.sorted()
        for (key in keyList){
            noteList.add(Holder.Datas(null, loader.getString(key, "")!!))
        }
        myAdapter.update(noteList)
    }
}

