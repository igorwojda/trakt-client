package com.igorwojda.traktclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.igorwojda.traktclient.feature.trendingmovielist.TrendingMovieListController

class MainActivity : AppCompatActivity() {

	private lateinit var router: Router

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val container = findViewById(R.id.activity_main_controller_container) as ViewGroup

		router = Conductor.attachRouter(this, container, savedInstanceState)

		if (!router.hasRootController()) {
			router.setRoot(RouterTransaction.with(TrendingMovieListController()))
		}
	}

	override fun onBackPressed() {
		if (!router.handleBack()) {
			super.onBackPressed()
		}
	}
}
