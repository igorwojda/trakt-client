package com.igorwojda.traktclient.feature.trendingmovielist.controller

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.controllers.base.BaseController
import com.igorwojda.traktclient.feature.movie.controller.MovieController
import net.vrallev.android.cat.Cat

/**
 * Created by Panel on 14.01.2017
 */
class TrendingMovieListController : BaseController() {

	init {
		title = "Trending Movies"
	}

	enum class DetailItemModel constructor(internal var title: String, internal var detail: String, internal var backgroundColor: Int) {
		ONE("Item 1", "First item", 0x7986cb),
		TWO("Item 2", "This is another item.", 0x4dd0e1),
		THREE("Item 3", "Wow, a 3rd item!", 0x9575cd)
	}

	private lateinit var recyclerView: RecyclerView
	private var detailContainer: ViewGroup? = null

	private var selectedIndex: Int = 0

	private val twoPaneView:Boolean
		get() = detailContainer != null


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		val view = inflater.inflate(R.layout.controller_trending_movie_list, container, false)

		recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
		recyclerView.setHasFixedSize(true)
		recyclerView.layoutManager = LinearLayoutManager(view.context)
		recyclerView.adapter = TrendingMovieAdapter(LayoutInflater.from(view.context), DetailItemModel.values())

		detailContainer = view.findViewById(R.id.detail_container) as ViewGroup?

		Cat.d("twoPaneView $twoPaneView")
		if (twoPaneView) {
			onRowSelected(selectedIndex)
		}

		return view
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
//		val trendingMovieAdapter = recyclerView.adapter as TrendingMovieAdapter
		selectedIndex = index

		val model = DetailItemModel.values()[index]
		val controller = MovieController(model.detail)

		Cat.d("twoPaneView2 $twoPaneView")

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

	internal inner class TrendingMovieAdapter(private val inflater: LayoutInflater, private var items: Array<DetailItemModel>) : RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder>() {

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
			return ViewHolder(inflater.inflate(R.layout.row_detail_item, parent, false))
		}

		override fun onBindViewHolder(holder: ViewHolder, position: Int) {
			holder.bind(items[position], position)
		}

		override fun getItemCount(): Int = items.size

		internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

			private var root: View = itemView.findViewById(R.id.row_root)
			private var tvTitle = itemView.findViewById(R.id.tv_title) as TextView

			private var localPosition: Int = 0

			init {

				root.setOnClickListener {
					onRowSelected(localPosition)
					notifyDataSetChanged()
				}
			}

			fun bind(item: DetailItemModel, position: Int) {
				tvTitle.text = item.title
				localPosition = position

				if (twoPaneView && localPosition == selectedIndex) {
					root.setBackgroundColor(0x78909C)
				} else {
					root.setBackgroundColor(ContextCompat.getColor(root.context, android.R.color.transparent))
				}
			}
		}
	}

	companion object {

		private val KEY_SELECTED_INDEX = "MasterDetailListController.selectedIndex"
	}

}
