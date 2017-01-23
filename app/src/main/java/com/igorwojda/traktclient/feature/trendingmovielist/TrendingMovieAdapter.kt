package com.hannesdorfmann.mosby.conductor.sample.tasks

import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import com.igorwojda.traktclient.core.api.trakt.entities.TrendingMovie

/**
 * Responsible to display a lis of [TaskListItem] by using AdapterDelegats library
 * https://github.com/sockeqwe/AdapterDelegates
 *
 * @author Hannes Dorfmann
 */
class TrendingMovieAdapter(manager: AdapterDelegatesManager<List<TrendingMovie>>) : ListDelegationAdapter<List<TrendingMovie>>(
    manager) {

  init {
    setHasStableIds(true)
  }

//  override fun getItemId(position: Int): Long = items[position].id

}
