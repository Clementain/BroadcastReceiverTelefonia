package com.example.broadcastreceivertelefonia

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtPhoneNumber: TextView

    private val incomingCallReceiver = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context?, intent: Intent?) {
            val incomingNumber = intent?.getStringExtra("incoming_number")
            txtPhoneNumber.text = "Llamada entrante de $incomingNumber"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtPhoneNumber = findViewById(R.id.Telefono)

        val filter = IntentFilter("com.example.myapp.NEW_INCOMING_CALL")
        registerReceiver(incomingCallReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(incomingCallReceiver)
    }
}
