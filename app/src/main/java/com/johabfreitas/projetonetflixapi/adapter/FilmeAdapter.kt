package com.johabfreitas.projetonetflixapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.johabfreitas.projetonetflixapi.api.RetrofitService
import com.johabfreitas.projetonetflixapi.databinding.ItemFilmeBinding
import com.johabfreitas.projetonetflixapi.model.Filme
import com.squareup.picasso.Picasso

class FilmeAdapter() : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    private var listaFilmes: List<Filme> = emptyList()

    fun adicionarLista(lista: List<Filme>){
        this.listaFilmes = lista
        notifyDataSetChanged()
    }

    inner class FilmeViewHolder(val itemFilmeBinding: ItemFilmeBinding)
        : RecyclerView.ViewHolder(itemFilmeBinding.root) {

        //Essa função faz uma conexão com o layout
            fun bind(filme: Filme){
                val nomeFilme = filme.backdrop_path
                val tamanhoFilme = "w780"
                val urlBase = RetrofitService.BASE_URL_IMAGE
                val urlFilme = urlBase + tamanhoFilme + nomeFilme

            Picasso.get()
                .load(urlFilme)
                .into(itemFilmeBinding.imgItemFilme)

            itemFilmeBinding.textTitulo.text = filme.title

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmeBinding.inflate(
            layoutInflater, parent, false
        )

        return FilmeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val filme = listaFilmes[position]
        holder.bind(filme)
    }

    override fun getItemCount(): Int {
        return listaFilmes.size
    }


}