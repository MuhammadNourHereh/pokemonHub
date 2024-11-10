 # pokemonHub

This is a pokemon hub app

This app display 20 pokemon cards that contain the pokemon name and an image from "https://pokeapi.co/api/v2/"
Also, favorite pokemon can be saved in SQLite DB

Features:

	* MVVM architecture: contains layout layer, VM layer, repository layer, DB by room layer, and networking layer
	* retrofit: to got pokemon from pokeapi
	* rooms: to save the pokemon
	* dagger-hilt: for dependency injection
	* glide: to display pokemon image
	* recycler view: to display the cards
