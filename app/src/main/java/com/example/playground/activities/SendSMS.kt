package com.example.playground.activities

import android.os.Bundle
import android.telephony.SmsManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_send_sms.*

class SendSMS : AppCompatActivity() {

        var SEND_SMS_PERMISSION_REQUEST_CODE:Int =1
        //private var numbertext:EditText?=null
        //private var  messagetext: EditText?=null
        //private var sendbtn: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        val numbertext:EditText?=findViewById(R.id.inputnumber)
        val messagetext:EditText?=findViewById(R.id.inputmessage)
        //sendbtn =findViewById(R.id.sendbtn)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_sms)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //sendbtn?.isEnabled=false
        /*if(checkPermission(android.Manifest.permission.SEND_SMS))
            sendbtn?.isEnabled=true
        else*/
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.SEND_SMS),SEND_SMS_PERMISSION_REQUEST_CODE)

        val phoneNumber=numbertext?.text.toString()
        val message=messagetext?.text.toString()

        /*if(phoneNumber.isEmpty() || message.isEmpty())
            return*/

        sendbtn.setOnClickListener {
            try {
                val obj = SmsManager.getDefault()
                obj.sendTextMessage("9824638130",null,"Hello, I'm Harsh, testing my texting skills here",
                    null, null)
            }
            catch (e:Exception){
                Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        /*
        destination - Target number
        source address - sender phone number (maybe it is set to null)
        text - Your Message for destination
        sentIntent,destIntent - Notify on sent/received
        */

    }

    /*fun onSend(view: View) {
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
    }*/
}
