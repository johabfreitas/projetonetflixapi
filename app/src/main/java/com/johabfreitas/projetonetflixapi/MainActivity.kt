package com.johabfreitas.projetonetflixapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.johabfreitas.projetonetflixapi.api.RetrofitService
import com.johabfreitas.projetonetflixapi.databinding.ActivityMainBinding
import com.johabfreitas.projetonetflixapi.model.FilmeRecente
import com.johabfreitas.projetonetflixapi.model.FilmeResposta
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "info_filme"
    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private val filmeAPI by lazy {
        RetrofitService.filmeAPI
    }

    var jobFilmeRecente: Job? = null
    var jobFilmePopulares: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( binding.root )
    }

    override fun onStart() {
        super.onStart()
        recuperarFilmeRecente()
        recuperarFilmesPopulares()
    }

    private fun recuperarFilmesPopulares() {
        jobFilmePopulares = CoroutineScope(Dispatchers.IO).launch {
            var resposta: Response<FilmeResposta>? = null

            try {
                resposta = filmeAPI.recuperarFilmesPopulares()
            }catch (e: Exception){
                exibirMensagem("Erro ao fazer a requisição")
            }

            if(resposta != null){

                if(resposta.isSuccessful){

                    val filmeResposta = resposta.body()
                    val listaFilmes = filmeResposta?.filmes
                    if(listaFilmes != null && listaFilmes.isNotEmpty()){
                        Log.i("filmes_api", "Lista Filmes: ")
                        listaFilmes.forEach{filme ->
                            Log.i("filmes_api", "Titulo: ${filme.title} ")

                        }

                    }


                } else {
                    exibirMensagem("Problema ao fazer a requisição CODIGO: ${resposta.code()}")
                }

            } else {
                exibirMensagem("Não foi possível fazer a requisição")
            }
        }
    }

    private fun recuperarFilmeRecente() {
       jobFilmeRecente = CoroutineScope(Dispatchers.IO).launch {
            var resposta: Response<FilmeRecente>? = null

           try {
               resposta = filmeAPI.recuperarFilmeRecente()
           }catch (e: Exception){
                exibirMensagem("Erro ao fazer a requisição")
           }

           if(resposta != null){

               if(resposta.isSuccessful){
                   val filmeRecente = resposta.body()
                   val nomeImagem = filmeRecente?.poster_path
                   val urlImagem = RetrofitService.BASE_URL_IMAGE + "w780" + nomeImagem

                   withContext(Dispatchers.Main){
                       Picasso.get()
                           .load(urlImagem)
                           .error(R.drawable.capa)
                           .into(binding.imgCapa)
                   }

               } else {
                   exibirMensagem("Problema ao fazer a requisição CODIGO: ${resposta.code()}")
               }

           } else {
               exibirMensagem("Não foi possível fazer a requisição")
           }
        }
    }

    private fun exibirMensagem(mensagem: String) {
        Toast.makeText(
            applicationContext,
            mensagem,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onStop() {
        super.onStop()
        jobFilmeRecente?.cancel()
        jobFilmePopulares?.cancel()
    }


}