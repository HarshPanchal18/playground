package com.example.playground.activities

import android.os.Bundle
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.playground.R
import kotlinx.android.synthetic.main.activity_parse_data.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class ParseData : AppCompatActivity() {
    private var contactList=ArrayList<HashMap<String,String>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parse_data)

        jsonbtn.setOnClickListener {
            contactList.clear()
            jsonParsing()
        }

        xmlbtn.setOnClickListener {
            contactList.clear()
            xmlParsing()
        }
    }

    private fun xmlParsing() {
        val inputStream=assets.open("empdata.xml")

        val parserFactory=XmlPullParserFactory.newInstance()
        val parser=parserFactory.newPullParser()

        // setting the namespaces feature to false
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false)

        // setting the input to the parser
        parser.setInput(inputStream,null)

        // creating a user string hashmap
        var emp= HashMap<String,String>()
        var tag:String?=""
        var text:String?=""
        var event=parser.eventType

        while (event!=XmlPullParser.END_DOCUMENT){
            tag=parser.name
            when(event){
                XmlPullParser.START_TAG->if(tag=="rootNode") emp= HashMap()
                XmlPullParser.TEXT->text=parser.text
                XmlPullParser.END_TAG-> when(tag){
                    "Id"->emp["Id"]=text.toString()
                    "Name"->emp["Name"]=text.toString()
                    "Age"->emp["Age"]=text.toString()
                    "rootNode"->contactList.add(emp)
                }
            }
            event=parser.next()
        }
        val personList: ListView =findViewById(R.id.parselist)
        val adapter:ListAdapter=SimpleAdapter(
            this,
            contactList,
            R.layout.list_item,
            arrayOf("Id", "Name", "Age"),
            intArrayOf(R.id.dataId, R.id.nameId, R.id.ageId)
        )
        personList.adapter=adapter
    }

    private fun jsonParsing() {
        try {
            // read from JSON like String
            val strJson:String="{\n" +
                    "  \"rootNode\": [\n" +
                    "    {\n" +
                    "      \"Id\": 1,\n" +
                    "      \"Name\": \"Arjun\",\n" +
                    "      \"Age\": 25\n" +
                    "    },{\n" +
                    "      \"Id\": 2,\n" +
                    "      \"Name\": \"Arjun2\",\n" +
                    "      \"Age\": 35\n" +
                    "    },{\n" +
                    "      \"Id\": 3,\n" +
                    "      \"Name\": \"Arjun3\",\n" +
                    "      \"Age\": 45\n" +
                    "    },{\n" +
                    "      \"Id\": 4,\n" +
                    "      \"Name\": \"Arjun4\",\n" +
                    "      \"Age\": 55\n" +
                    "    },{\n" +
                    "      \"Id\": 5,\n" +
                    "      \"Name\": \"Arjun5\",\n" +
                    "      \"Age\": 65\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}\n"
            //val reader=JSONObject(strJson)

            val reader=JSONObject(loadJSONFromAsset()!!)
            val jArr = reader.optJSONArray("rootNode") as JSONArray
            for (i in 0 until jArr.length()){
                try {
                    val obj: JSONObject = jArr.getJSONObject(i)
                    val id: Int = obj.getInt("Id")
                    val name: String = obj.getString("Name")
                    val age: Int = obj.getInt("Age")
                    addToHash(id, name, age)
                } catch (e:JSONException){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show() }
            }
            val personList: ListView =findViewById(R.id.parselist)
            val adapter:ListAdapter=SimpleAdapter(
                this,
                contactList,
                R.layout.list_item,
                arrayOf("Id", "Name", "Age"),
                intArrayOf(R.id.dataId, R.id.nameId, R.id.ageId)
            )
            personList.adapter=adapter
        } catch (e:JSONException){ Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show() }
    }

    private fun addToHash(id: Int, name: String, age: Int) {
        val contact=HashMap<String,String>()
        contact["Id"] = id.toString()
        contact["Name"] = name
        contact["Age"] = age.toString()
        contactList.add(contact)
    }

    private fun loadJSONFromAsset(): String? {
        val json: String?
        try {
            val inputStream: InputStream = assets.open("data.json")
            json=inputStream.bufferedReader().use { it.readText() }
            /*val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)*/
        } catch (e: IOException) {
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
            return null
        }
        return json
    }
}
