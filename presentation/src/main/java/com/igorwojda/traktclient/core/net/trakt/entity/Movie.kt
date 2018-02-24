package com.igorwojda.traktclient.core.net.trakt.entity

class Movie {
	var title: String? = null
	var overview: String? = null
	var rating: Double? = null
	var votes: Int? = null
	var updated_at: String? = null
	var available_translations: List<String>? = null
	var year: Int? = null
	var ids: MovieIds? = null
	var certification: String? = null
	var tagline: String? = null
	var released: String? = null
	var runtime: Int? = null
	var trailer: String? = null
	var homepage: String? = null
	var language: String? = null
	var genres: List<String>? = null
	var imageUrl: String? = null
}
