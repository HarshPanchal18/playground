package com.example.playground.activities

import android.Manifest.permission.SEND_SMS
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.playground.R

class SendSMS : AppCompatActivity() {

        var SEND_SMS_PERMISSION_REQUEST_CODE:Int =1
        private var numbertext:EditText?=null
        private var  messagetext:EditText?=null
        private var sendbtn: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        numbertext=findViewById(R.id.inputnumber)
        messagetext=findViewById(R.id.inputmessage)
        sendbtn =findViewById(R.id.sendbtn)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_sms)

        sendbtn?.isEnabled=false
        if(checkPermission(android.Manifest.permission.SEND_SMS))
            sendbtn?.isEnabled=true
        else
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.SEND_SMS),SEND_SMS_PERMISSION_REQUEST_CODE)

        /*sendbtn.setOnClickListener {
            val obj=SmsManager.getDefault()
            obj.sendTextMessage("+919824638130","+919909106338","Hello, I'm Harsh, testing my texting skills here",
                null,null)
        }*/
        /*
        destination - Target number
        source address - sender phone number (maybe it is set to null)
        text - Your Message for destination
        sentIntent,destIntent - Notify on sent/received
        */

    }

    fun onSend(view: View) {
        val phoneNumber:String=numbertext?.text.toString()
        val message:String=messagetext?.text.toString()

        if(phoneNumber.isEmpty() || message.isEmpty())
            return

        if(checkPermission(SEND_SMS)) {
            try{
                val smsManager=SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNumber,null,"Hello (Testing)",null,null)
                Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show()
            }
            catch (e:Exception){
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        else
            Toast.makeText(this,"Allow Permission",Toast.LENGTH_SHORT).show()
    }

    private fun checkPermission(permission:String?): Boolean {
        val check:Int=ContextCompat.checkSelfPermission(this, permission.toString())
        return (check==PackageManager.PERMISSION_GRANTED)
    }
}
