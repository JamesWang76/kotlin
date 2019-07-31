package com.example.kotlin_everywhere_to_do_list

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity

class activity_choosephoto : AppCompatActivity() {
    val ALBUM_CODE = 100;
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            ALBUM_CODE -> {
                if (resultCode == Activity.RESULT_OK && data != null) {

                }
            }


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intentAlbum = Intent(Intent.ACTION_PICK)
        intentAlbum.type = "image/*"

    }

}