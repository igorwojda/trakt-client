package com.igorwojda.traktclient.feature.trendingmovielist.controller

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.bumptech.glide.Glide
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.api.trakt.entities.Movie
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie
import com.igorwojda.traktclient.core.controllers.base.BaseController
import com.igorwojda.traktclient.feature.movie.controller.MovieController
import com.igorwojda.traktclient.feature.trendingmovielist.model.TrendingMovieListModel
import kotlinx.android.synthetic.main.trending_movie_row_item.view.*
import net.vrallev.android.cat.Cat
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by Panel on 14.01.2017
 */
class TrendingMovieListController(args: Bundle? = null) : BaseController(args)  {

	init {
		title = "Trending Movies"
	}

	private lateinit var recyclerView: RecyclerView
	private var detailContainer: ViewGroup? = null
	private val model = TrendingMovieListModel()

	private var selectedIndex: Int = 0

	private val twoPaneView:Boolean
		get() = detailContainer != null


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		val view = inflater.inflate(R.layout.controller_trending_movie_list, container, false)

		recyclerView = view.findViewById(R.id.controller_trending_movie_list_recycler_view) as RecyclerView
		recyclerView.setHasFixedSize(true)
		recyclerView.layoutManager = LinearLayoutManager(view.context)
		recyclerView.adapter = TrendingMovieAdapter(LayoutInflater.from(view.context), view.context)

		detailContainer = view.findViewById(R.id.controller_trending_movie_list_detail_container) as ViewGroup?

		if (twoPaneView)
			onRowSelected(selectedIndex)

		loadTrendingMovies()

		return view
	}

	private fun loadTrendingMovies() {
		model.trending()
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						{
							(recyclerView.adapter as TrendingMovieAdapter).items = it
						},
						{
							Log.e("Error", it.message)
						}
				)
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)

		outState.putInt(KEY_SELECTED_INDEX, selectedIndex)
	}

	override fun onRestoreInstanceState(savedInstanceState: Bundle) {
		super.onRestoreInstanceState(savedInstanceState)

		selectedIndex = savedInstanceState.getInt(KEY_SELECTED_INDEX)
	}

	internal fun onRowSelected(index: Int) {
		selectedIndex = index

		val trendingMovieAdapter = recyclerView.adapter as TrendingMovieAdapter
		val trendingMovie = trendingMovieAdapter.items[index]
		val movieTraktId = trendingMovie.movie?.ids?.trakt ?: return

		Cat.d("index: $index Open Title: ${trendingMovie.movie?.title}, traktId: $movieTraktId")

		val controller = MovieController(movieTraktId)

		if (twoPaneView) {
			detailContainer?.let {
				getChildRouter(it).setRoot(RouterTransaction.with(controller))
			}
		} else {
			router.pushController(RouterTransaction.with(controller)
					.pushChangeHandler(HorizontalChangeHandler())
					.popChangeHandler(HorizontalChangeHandler()))
		}
	}

	internal inner class TrendingMovieAdapter(private val inflater: LayoutInflater, private val context:Context) : RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder>() {

		var items: List<TrendingMovie> = emptyList()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(inflater.inflate(R.layout.trending_movie_row_item, parent, false))
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			holder.bind(items[position], position)
		}

		override fun getItemCount(): Int = items.size

		internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
			private var localPosition: Int = 0

			init {

				itemView.setOnClickListener {
					onRowSelected(localPosition)
					notifyDataSetChanged()
				}
			}

			fun bind(trendingMovie: TrendingMovie, position:Int) {
				localPosition = position

				val movie: Movie? = trendingMovie.movie ?: return

				movie?.title.let { itemView.trending_movie_row_item_title.text = it}

				movie?.released?.year.let {
					val releaseLabel = resources?.getText(R.string.release)
					itemView.trending_movie_row_item_releaseDate.text = "$releaseLabel: $it"
				}

				trendingMovie.watchers?.let {
					val watchLabel = resources?.getText(R.string.release)
					itemView.trending_movie_row_item_watchers.text = "$watchLabel: $it"
				}

				movie?.image?.let {
					Glide
							.with( context )
							.load( it )
							.into( itemView.trending_movie_row_item_image )
				}

//				localPosition = position

//				if (twoPaneView && localPosition == selectedIndex) {
//					root.setBackgroundColor(0x78909C)
//				} else {
//					root.setBackgroundColor(ContextCompat.getColor(root.context, android.R.color.transparent))
//				}
			}
		}
	}

	companion object {

		private val KEY_SELECTED_INDEX = "MasterDetailListController.selectedIndex"
	}

}
