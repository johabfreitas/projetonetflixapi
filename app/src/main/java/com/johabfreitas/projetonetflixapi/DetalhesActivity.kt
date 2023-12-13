package com.johabfreitas.projetonetflixapi

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.johabfreitas.projetonetflixapi.api.RetrofitService
import com.johabfreitas.projetonetflixapi.databinding.ActivityDetalhesBinding
import com.johabfreitas.projetonetflixapi.model.Filme
import com.squareup.picasso.Picasso

class DetalhesActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras

        val filme = bundle?.getParcelable("filme", Filme::class.java)

        if(filme != null){
            binding.textFilmeTitulo.text = filme.title

            val nomeFilme = filme.backdrop_path
            val tamanhoFilme = "w780"
            val urlBase = RetrofitService.BASE_URL_IMAGE
            val urlFilme = urlBase + tamanhoFilme + nomeFilme

            Picasso.get()
                .load(urlFilme)
                .into(binding.imgPoster)
        }

    }

}

