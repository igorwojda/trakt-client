package com.igorwojda.traktclient.feature.movie.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.igorwojda.traktclient.R
import com.igorwojda.traktclient.core.controllers.base.BaseController
import kotlinx.android.synthetic.main.controller_movie.view.*

/**
 * Created by Panel on 14.01.2017
 */
class MovieController(var detail: String = "") : BaseController() {

	private lateinit var titleTextView: TextView

	init {
		title = detail
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
		val view = inflater.inflate(R.layout.controller_movie, container, false)
		titleTextView = view.title
		titleTextView.text = detail

		view.setBackgroundColor(0xCCFFCC)
		return view
	}
}