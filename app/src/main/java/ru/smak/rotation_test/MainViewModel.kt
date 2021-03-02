package ru.smak.rotation_test

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

    private val savedData = MutableLiveData<Int>()

    var btnNumber: Int
        get() = savedData.value ?: 0
        set(value) {
            savedData.value = value
        }

    fun observe(owner: LifecycleOwner, f: (Int)->Unit){
        savedData.observe(owner, f)
    }
}