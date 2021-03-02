package ru.smak.rotation_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var pressedText: TextView

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory(application)
        ).get(MainViewModel::class.java)

        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
        pressedText = findViewById(R.id.pressedView)

        viewModel.observe(this){
            pressedText.text = getString(R.string.lastPressed, it)
        }

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        viewModel.btnNumber = when (v?.id){
            R.id.button1 -> 1
            R.id.button2 -> 2
            R.id.button3 -> 3
            else -> 0
        }
    }
}