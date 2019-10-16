package br.com.mrocigno.githubapi.repository


import br.com.mrocigno.githubapi.abstract.NetworkConfig.retrofit
import br.com.mrocigno.githubapi.model.ApiBase
import retrofit2.Callback

class HomeRepositoryImpl : HomeRepository.Local, HomeRepository.Remote {

    override fun getRepositories(page: Int, callback: Callback<ApiBase>) {
        retrofit.create(HomeRepository.Api::class.java).getRepositories(page).enqueue(callback)
    }

}