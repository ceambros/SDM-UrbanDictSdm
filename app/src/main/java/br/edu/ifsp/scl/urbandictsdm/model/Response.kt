package br.edu.ifsp.scl.urbandictsdm.model

/* Este arquivo foi gerado com base num objeto JSON, exemplo de resposta de acesso ao Web Service,
da API. Para isso, foi usado o plugin do Android Studio chamado JSON to Kotlin Class (recomendo).
Utilizar uma ferramenta como Postman ou Insomnia para fazer a requisição genérica pode ajudar
quando a API não disponibiliza uma página Web para teste. */

data class Response(
    val list: List<Meaning>
)

data class Meaning(
    val author: String,
    val current_vote: String,
    val defid: Int,
    val definition: String,
    val example: String,
    val permalink: String,
    val sound_urls: List<String>,
    val thumbs_down: Int,
    val thumbs_up: Int,
    val word: String,
    val written_on: String
)