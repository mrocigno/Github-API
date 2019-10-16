package br.com.mrocigno.githubapi.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessRecyclerView(context: Context, attributeSet: AttributeSet?) : RecyclerView(context, attributeSet) {

    var loading = false

    fun setOnScrollEnd(callback : Callback){
        addOnScrollListener(object : OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    layoutManager?.let {
                        val visibleItemCount = it.childCount
                        val totalItemCount = it.itemCount
                        val pastVisibleItems = (it as LinearLayoutManager).findFirstVisibleItemPosition()

                        if (!loading){
                            if ((visibleItemCount + pastVisibleItems) >= totalItemCount){
                                loading = true
                                callback.onScrollEnd()
                            }
                        }
                    }
                }
            }
        })
    }

    abstract class Callback{

        abstract fun onScrollEnd()

    }

}