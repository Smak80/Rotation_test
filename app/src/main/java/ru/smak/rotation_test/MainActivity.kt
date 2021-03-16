package ru.smak.rotation_test

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
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
                ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
        pressedText = findViewById(R.id.pressedView)

        viewModel.observe(this) {
            pressedText.text = if (it>0) getString(R.string.lastPressed, it) else ""
        }

        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
    }

    override fun onStop() {
        val prefs = getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(Constants.BTN_NUMBER,
                if (viewModel.btnPressed) viewModel.btnNumber else 0).apply()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        val prefs = getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE)
        viewModel.btnNumber = prefs.getInt(Constants.BTN_NUMBER, 0)
    }

    override fun onClick(v: View?) {
        viewModel.btnNumber = when (v?.id){
            R.id.button1 -> 1
            R.id.button2 -> 2
            R.id.button3 -> 3
            else -> 0
        }
        viewModel.btnPressed = true
    }
}