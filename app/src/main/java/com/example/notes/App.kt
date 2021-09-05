package com.example.notes

import android.app.Application
import com.example.notes.data.Repository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class App: Application() {
    @DelicateCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch { Repository.init(context = this@App) }
    }
}