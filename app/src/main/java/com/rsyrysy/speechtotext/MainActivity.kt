package com.rsyrysy.speechtotext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration
import com.rsyrysy.speechtotext.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val mresultCode = 1001
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var t1: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.fab.setOnClickListener { view ->
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, mresultCode)
            } else {
                Toast.makeText(this, "Device Don't Support Speech Input", Toast.LENGTH_SHORT)
                    .show()
            }

        }


        /*  t1 = TextToSpeech(
              applicationContext
          ) { status ->
              if (status != TextToSpeech.ERROR) {
                  t1!!.language = Locale.UK
              }
          }*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            mresultCode -> if (resultCode == RESULT_OK && data != null) {
                val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                Toast.makeText(this, "$result", Toast.LENGTH_SHORT)
                    .show()

                /*    var toSpeak = result?.get(0)?.toString()
                    t1?.speak("Ram", TextToSpeech.QUEUE_FLUSH, null);*/
                binding.tvshow.text = result?.get(0)?.toString()
            }
        }
    }

    /*override fun onPause() {
        if (t1 != null) {
            t1!!.stop()
            t1!!.shutdown()
        }
        super.onPause()
    }*/

}