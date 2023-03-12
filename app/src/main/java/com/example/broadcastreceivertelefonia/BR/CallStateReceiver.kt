package com.example.broadcastreceivertelefonia.BR

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager

class CallStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val state = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
        val incomingNumber = intent?.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
        if (state == TelephonyManager.EXTRA_STATE_RINGING) {
            val i = Intent("com.example.myapp.NEW_INCOMING_CALL")
            i.putExtra("incoming_number", incomingNumber)
            context?.sendBroadcast(i)
        }
    }
}
