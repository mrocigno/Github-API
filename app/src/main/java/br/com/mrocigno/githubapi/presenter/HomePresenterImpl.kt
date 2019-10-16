package br.com.mrocigno.githubapi.presenter

import br.com.mrocigno.githubapi.model.ApiBase
import br.com.mrocigno.githubapi.model.Github
import br.com.mrocigno.githubapi.repository.HomeRepository
import br.com.mrocigno.githubapi.repository.HomeRepositoryImpl
import br.com.mrocigno.githubapi.view.HomeView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenterImpl(val view: HomeView) : HomePresenter {

    private val init = HomeRepositoryImpl()
    private val remoteHomeRepository: HomeRepository.Remote = init
    private val localHomeRepository: HomeRepository.Local = init

    private val callback : Callback<ApiBase> = object : Callback<ApiBase>{

        override fun onResponse(call: Call<ApiBase>, response: Response<ApiBase>) {
            if(response.code() == 200) {
                response.body()?.let {
                    if(valid(it.items)){
                        view.removePlaceholders()
                        view.addOnRecycler(it.items)
                    } else {
                        view.onApiError()
                    }
                }
            } else {
                view.onApiError()
            }

        }

        override fun onFailure(call: Call<ApiBase>, t: Throwable) {
            view.onApiError()
        }

    }

    override fun init() {
        view.addPlaceholder(3)
        load(1)
    }

    override fun load(page: Int) {
        remoteHomeRepository.getRepositories(page, callback)
    }

    fun valid(list: List<Github>) : Boolean {

        for (github in list){
            if(github.infoCard ||
                github.placeholder ||
                github.forks_count == 0 ||
                github.full_name == "" ||
                github.owner == null ||
                github.stargazers_count == 0){
                return false
            }
        }

        return true

    }
}