package com.mahshook.shopee

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel:ViewModel() {
    val textFieldState=MutableLiveData<String>("")
       fun updateData(newData:String)  {
           textFieldState.value=newData
       }
}