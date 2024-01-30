package com.example.videosdkapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.jitsi.meet.sdk.JitsiMeet
import org.jitsi.meet.sdk.JitsiMeetActivity
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var editCode : EditText
    lateinit var btnCreate : Button
    lateinit var share : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editCode = findViewById(R.id.edt_code)
        btnCreate = findViewById(R.id.btnCreate)
        share = findViewById(R.id.share)


        initializeJitsiMeet()

        btnCreate.setOnClickListener {
            val roomCode = editCode.text.toString().trim()
            if (roomCode.isNotEmpty()) {
                startJitsiMeet(roomCode)
            }
        }

        share.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Join my Jitsi Meet with code: ${editCode.text}")
            startActivity(shareIntent)
        }
    }
    private fun initializeJitsiMeet() {
        val serverURL: URL
        try {
            serverURL = URL("https://meet.jit.si")
            val defaultOptions = JitsiMeetConferenceOptions.Builder()
                .setServerURL(serverURL)
                .build()
            JitsiMeet.setDefaultConferenceOptions(defaultOptions)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
    }

    private fun startJitsiMeet(roomCode: String) {
        val options = JitsiMeetConferenceOptions.Builder()
            .setRoom(roomCode)
            .setFeatureFlag("invite.enabled", false)
            .build()

        JitsiMeetActivity.launch(this, options)
    }
//        val serverUrl: URL
//        try {
//            serverUrl = URL("https://meet.jit.si")
//            val defaultOption: JitsiMeetConferenceOptions = JitsiMeetConferenceOptions.Builder()
//                .setServerURL(serverUrl)
//                .build()
//            JitsiMeet.setDefaultConferenceOptions(defaultOption)
//        } catch (e: MalformedURLException) {
//            e.printStackTrace()
//        }
//
//        btnCreate.setOnClickListener {
//            val text = editCode.text.toString()
//            if (text.length > 0) {
//                val options: JitsiMeetConferenceOptions = JitsiMeetConferenceOptions.Builder()
//                    .setRoom(text)
//                    .setFeatureFlag("invite.enabled", false)
//                    .build()
//                JitsiMeetActivity.launch(this@MainActivity, options)
//            }
//        }
//
//        share.setOnClickListener {
//            val shareIntent = Intent()
//            shareIntent.action = Intent.ACTION_SEND
//            shareIntent.putExtra(
//                Intent.EXTRA_TEXT,
//                "Enter the room code to join the meeting: " + editCode.text.toString()
//            )
//            shareIntent.type = "text/plain"
//            startActivity(shareIntent)
//        }
//    }
    }
