package com.example.playground.activities

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_validation.*

class ValidationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()

        submitbtn.setOnClickListener { submitForm() }
    }

    private fun emailFocusListener() {
        emailBox.setOnFocusChangeListener{ _,focuesd->
            if(!focuesd)
                emailContainer.helperText=validEmail()
        }
    }

    private fun validEmail(): String? {
        val emailText=emailBox.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
            return "Invalid E-Mail"
        return null
    }

    private fun passwordFocusListener() {
        passwordBox.setOnFocusChangeListener{ _,focuesd->
            if(!focuesd)
                passwordContainer.helperText=validPassword()
        }
    }

    private fun validPassword(): String? {
        val passText=passwordBox.text.toString()
        if(passText.length <8)
            return "Minimum 8 characters of Password is required"

        if(!passText.matches(".*[A-Z].*".toRegex()))
            return "Password must contain a Uppercase character"

        if(!passText.matches(".*[a-z].*".toRegex()))
            return "Password must contain a Lowercase character"

        if(!passText.matches(".*[0-9].*".toRegex()))
            return "Password must contain a Digit"

        if(!passText.matches(".*[@#\$%^&=+].*".toRegex()))
            return "Password must contain a Special character(@#\$%^&=+)"

        return null
    }

    private fun phoneFocusListener() {
        phoneBox.setOnFocusChangeListener{ _,focused->
            if(!focused)
                passwordContainer.helperText=validPhone()
        }
    }

    private fun validPhone(): String? {
        val phoneText=passwordBox.text.toString()

        if(phoneText.length<10 || phoneText.length>10)
            return "Phone Number must be 10 digits long only"

        if(!phoneText.matches(".*[0-9].*".toRegex()))
            return "Phone Number must contain Digits"

        return null
    }

    private fun submitForm() {
        emailContainer.helperText=validEmail()
        passwordContainer.helperText=validPassword()
        phoneContainer.helperText=validPhone()

        val validMail=emailContainer.helperText==null
        val validPass=passwordContainer.helperText==null
        val validPhone=phoneContainer.helperText==null

        if(validMail && validPass && validPhone)
            resetForm()
        else
            invalidForm()
    }

    private fun resetForm() {
        var message="Email: "+emailBox.text
        message+="\nPassword: "+passwordBox.text
        message+="\nPhone: "+phoneBox.text

        AlertDialog.Builder(this)
            .setTitle("Form Submitted")
            .setMessage(message)
            .setPositiveButton("Okay"){_,_ ->
                emailBox.text=null
                passwordBox.text=null
                phoneBox.text=null

                emailContainer.helperText="Required"
                passwordContainer.helperText="Required"
                phoneContainer.helperText="Required"
            }.show()
    }

    private fun invalidForm() {
        var message=""
        if(emailContainer.helperText!=null)
            message+="\n\nEmail: "+emailContainer.helperText

        if(passwordContainer.helperText!=null)
            message+="\n\nPassword: "+passwordContainer.helperText

        if(phoneContainer.helperText!=null)
            message+="\n\nPhone: "+phoneContainer.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay"){_,_ ->}.show()
    }
}
