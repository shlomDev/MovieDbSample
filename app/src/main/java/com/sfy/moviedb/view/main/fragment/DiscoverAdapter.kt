package com.sfy.moviedb.view.main.fragment

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sfy.moviedb.data.Movie
import io.reactivex.subjects.PublishSubject
import com.sfy.moviedb.R
import com.sfy.moviedb.app.api.APIConfiguration


class DiscoverAdapter : RecyclerView.Adapter<DiscoverAdapter.ViewHolder>() {

    private var list = ArrayList<Movie>()
    val onClickMovieItem = PublishSubject.create<Movie>()

    fun loadMovieslist(movielist: ArrayList<Movie>){
        list = movielist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.discover_item_row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = list.size


    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        private val poster = itemview.findViewById<ImageView>(R.id.poster)

        init {
            poster.setOnClickListener{ onClickMovieItem.onNext(list[adapterPosition]) }
        }

        fun bind() {
            val item = list[adapterPosition]
            poster.context?.apply{
                Glide.with(this).load(APIConfiguration.API_IMAGE_URL+item.posterPath).into(poster)
            }

        }
    }
}