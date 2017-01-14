package com.igorwojda.traktclient.core.api.trakt.entities

import org.joda.time.DateTime

data class Movie (
	var title: String? = null,
	var year: Int? = null,
	var ids: MovieIds? = null,
	var certification: String? = null,
	var tagline: String? = null,
	var released: DateTime? = null,
	var runtime: Int? = null,
	var trailer: String? = null,
	var homepage: String? = null,
	var language: String? = null,
	var genres: List<String>? = null,
	var image: String? = null
)
