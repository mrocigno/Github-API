package br.com.mrocigno.githubapi.model

data class ApiBase(
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<Github>
)