package br.com.mrocigno.githubapi.view

import br.com.mrocigno.githubapi.abstract.AbstractView
import br.com.mrocigno.githubapi.model.Github

interface HomeView : AbstractView {

    fun addOnRecycler(list : List<Github>)

    fun addPlaceholder(numItems : Int)

    fun removePlaceholders()

    fun onApiError()

}