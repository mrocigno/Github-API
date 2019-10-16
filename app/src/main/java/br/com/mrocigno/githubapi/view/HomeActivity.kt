package br.com.mrocigno.githubapi.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mrocigno.githubapi.R
import br.com.mrocigno.githubapi.abstract.AbstractActivity
import br.com.mrocigno.githubapi.adapter.RepositoriesAdapter
import br.com.mrocigno.githubapi.model.Github
import br.com.mrocigno.githubapi.presenter.HomePresenter
import br.com.mrocigno.githubapi.presenter.HomePresenterImpl
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AbstractActivity<HomePresenter>(), HomeView {

    override val presenter: HomePresenter
        get() = HomePresenterImpl(this)

    val adapter = RepositoriesAdapter(activity, ArrayList())
    var page = 1

    override fun initVars() {

    }

    override fun initActions() {
        rcyHome.setOnScrollEnd(object : EndlessRecyclerView.Callback(){

            override fun onScrollEnd() {
                adapter.addPlaceholder(3)
                presenter.load(++page)
            }

        })

        swipeHome.setOnRefreshListener {
            page = 1
            adapter.removeAll()
            adapter.addPlaceholder(3)
            presenter.load(page)
        }
    }

    override fun contentView(): Int {
        return R.layout.activity_home
    }

    override fun customCreate(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)

        val manager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rcyHome.layoutManager = manager
        rcyHome.adapter = adapter

        savedInstanceState?.let {bundle ->
            bundle.getParcelableArrayList<Github>(LIST)?.let {
                addOnRecycler(it)
                manager.scrollToPosition(bundle.getInt(SCROLL_POSITION))
                page = bundle.getInt(PAGE)
            }
        }

        presenter.init()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(LIST, adapter.list as ArrayList<Github>)
        outState.putInt(SCROLL_POSITION, (rcyHome.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition())
        outState.putInt(PAGE, page)
    }


    // View implements
    override fun addOnRecycler(list: List<Github>) {
        rcyHome.loading = false
        swipeHome.isRefreshing = false
        adapter.addData(list)
    }

    override fun addPlaceholder(numItems: Int) {
        adapter.addPlaceholder(numItems)
    }

    override fun removePlaceholders() {
        adapter.removePlaceholders()
    }

    override fun onApiError() {
        swipeHome.isRefreshing = false
        adapter.showInfoCard()
    }

    companion object{
        const val SCROLL_POSITION = "scrollPosition"
        const val LIST = "list"
        const val PAGE = "page"

    }
}
