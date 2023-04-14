package com.example.aes

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var plainText: String = ""
    private var plainTextBinary: String = ""
    private var keyText: String = ""
    private var keyTextBinary: String = ""
    private var keyLength : Int = 0
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etPlainText = findViewById<EditText>(R.id.etPlainText)
        val etKeyText = findViewById<EditText>(R.id.etKeyText)
        val btnCal = findViewById<Button>(R.id.btnCal)
        val aesSpinner = findViewById<Spinner>(R.id.AesTypesSpinner)


        val adapter =ArrayAdapter.createFromResource(this,R.array.AESType,android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        aesSpinner.adapter = adapter

        aesSpinner.onItemSelectedListener = object : OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> {keyLength= 128}
                    1 -> {Toast.makeText(this@MainActivity,"Working on it",Toast.LENGTH_SHORT).show()}
                    2 -> {Toast.makeText(this@MainActivity,"Working on it",Toast.LENGTH_SHORT).show()}
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

//        plainText = etPlainText.text.toString()
//        cipherText = etKeyText.text.toString()



        val textView = findViewById<TextView>(R.id.textView)


        etPlainText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                plainText = s.toString()
                plainTextBinary = convertToBinary(s.toString())

                // you can call or do what you want with your EditText here
                // yourEditText...
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })

        etKeyText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                keyText = p0.toString()
                keyTextBinary = convertToBinary(p0.toString())
            }

        })

        btnCal.setOnClickListener {
            if (etPlainText.text.toString() == "" || etKeyText.text.toString() == ""){
                textView.text = "Plz Enter Something :("
            }
            else{
                val listofHex = covertToHex(plainText)
                textView.text = listofHex.toString()
            }
        }
    }
}

fun covertToHex(n:String): List<String>{
    val arrayOfChar=n.toCharArray()
    val listOfHex = mutableListOf<String>()
    for (char in arrayOfChar)
    {
        val acci=char.code
        val hex= Integer.toHexString(acci)
        listOfHex.add(hex)
    }
    return listOfHex
}

fun convertToBinary(n: String) : String{
    val arrayOfChar = n.toCharArray()
    var covertedToString: String = ""
    for (char in arrayOfChar){
        val acii = char.code
        val binary = Integer.toBinaryString(acii)
        covertedToString = covertedToString.plus(binary)
    }
    return covertedToString
}
