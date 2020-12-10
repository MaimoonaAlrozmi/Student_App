package com.maimoona.exam

import android.app.Application

class StudentIntentApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        StudentRepository.initialize(this)
    }
}