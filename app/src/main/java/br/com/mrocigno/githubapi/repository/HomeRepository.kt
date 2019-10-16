package br.com.mrocigno.githubapi.repository

import br.com.mrocigno.githubapi.model.ApiBase
import br.com.mrocigno.githubapi.model.Github
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeRepository {

    interface Api {
        @GET("search/repositories?q=language:kotlin&sort=stars&per_page=10")
        fun getRepositories(@Query("page") page : Int) : Call<ApiBase>
    }

    interface Remote {

        fun getRepositories(page : Int, callback: Callback<ApiBase>)

    }

    interface Local {

    }

}