package com.example.playground.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_co_routine.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoRoutineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_co_routine)

        btnclick.setOnClickListener {
            setNewText("Clicked")
            CoroutineScope(IO).launch {
                fakeApiRequest()
            }
        }
    }

    private suspend fun fakeApiRequest() {
        logThread("fakeApiRequest")
        val res1=getResult1FromApi()
        if(res1 == "Result 1"){
            setTextOnMainThread("Got $res1")

            val res2=getResult2FromApi()
            if (res2 == "Result 2")
                setTextOnMainThread("Got $res2") // wait until job is done
            else
                setTextOnMainThread("Couldn't get Result #2")

        } else {
            setTextOnMainThread("Couldn't get Result #1")
        }
    }

    private suspend fun setTextOnMainThread(input: String) {
        withContext(Main){
            setNewText(input)
        }
    }

    private suspend fun getResult1FromApi(): String {
        logThread("getResultFromApi")
        delay(1000) // Does not block thread. Just suspends the coroutine inside the thread
        return "Result 1"
    }

    private suspend fun getResult2FromApi(): String {
        logThread("getResultFromApi")
        delay(1000) // Does not block thread. Just suspends the coroutine inside the thread
        return "Result 2"
    }

    private fun logThread(method: String) {
        println("debug: $method : ${Thread.currentThread().name}")
    }

    private fun setNewText(str: String) {
        val newText = text.text.toString() + "\n$str"
        text.text = newText
    }
}
