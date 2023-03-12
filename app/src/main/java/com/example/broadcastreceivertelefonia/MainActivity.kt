package com.example.broadcastreceivertelefonia

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtPhoneNumber: TextView
    private lateinit var txtTargetNumber: TextView
    private lateinit var txtMsg: TextView


    private val incomingCallReceiver = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context?, intent: Intent?) {
            val incomingNumber = intent?.getStringExtra("incoming_number")
            txtPhoneNumber.text = "Llamada entrante de $incomingNumber"
            if (incomingNumber == txtTargetNumber.text.toString()) {
                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(
                    txtTargetNumber.text.toString(), null, txtMsg.text.toString(), null, null
                )
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtPhoneNumber = findViewById(R.id.Telefono)
        txtTargetNumber = findViewById(R.id.txtTelefono)
        txtMsg = findViewById(R.id.txtMensaje)

        val filter = IntentFilter("com.example.myapp.NEW_INCOMING_CALL")
        registerReceiver(incomingCallReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(incomingCallReceiver)
    }
}
