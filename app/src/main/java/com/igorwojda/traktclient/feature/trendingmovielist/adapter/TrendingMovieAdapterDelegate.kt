package com.igorwojda.traktclient.feature.trendingmovielist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import kotlinx.android.synthetic.main.trending_movie_item.view.*

/**
 * Created by Panel on 22.01.2017
 */

class TrendingMovieAdapterDelegate(
		private val inflater: LayoutInflater,
		private val clickListener: (TrendingMovie) -> Unit) : AdapterDelegate<List<TrendingMovie>>() {

	override fun onBindViewHolder(items: List<TrendingMovie>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
		(holder as TrendingMovieViewHolder).let { it.bind(items[position]) }
	}

	override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder
		= TrendingMovieViewHolder(inflater.inflate(R.layout.trending_movie_item, parent, false))

	override fun isForViewType(items: List<TrendingMovie>, position: Int)
		= items[position] is TrendingMovie

	inner class TrendingMovieViewHolder(v: View) : RecyclerView.ViewHolder(v) {

		lateinit var item: TrendingMovie

		init {
			v.setOnClickListener { clickListener(item) }
		}

		fun bind(item: TrendingMovie) {
			this.item = item

			val movie: Movie? = item.movie ?: return
			val context = inflater.context
			val resources = inflater.context.resources

			item.watchers?.let {
				itemView.watchers.text = resources?.getString(R.string.watchers, it)
			}

			movie?.let {
				//ToDo use with?
				it.title?.let { itemView.title.text = it }

				it.released?.let {
					itemView.releaseDate.text = resources?.getString(R.string.release, it)
				}

				it.imageUrl?.let {
					com.bumptech.glide.Glide
							.with(context)
							.load(it)
							.diskCacheStrategy(DiskCacheStrategy.SOURCE)
							.into(itemView.image)
				}
			}
		}
	}

}