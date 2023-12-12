package com.johabfreitas.projetonetflixapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.johabfreitas.projetonetflixapi.api.RetrofitService
import com.johabfreitas.projetonetflixapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "info_filme"
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private val filmeAPI by lazy {
        RetrofitService.filmeAPI
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )



    }


}