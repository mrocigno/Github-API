package br.com.mrocigno.githubapi.abstract

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.mrocigno.githubapi.R

abstract class AbstractFragment<T> : Fragment(), AbstractView {

    protected abstract val presenter : T

    protected abstract fun initVars(view : View)
    protected abstract fun initActions(view : View)
    protected abstract fun contentView() : Int
    protected abstract fun customCreate(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, view : View) : View?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        val view = inflater.inflate(contentView(), container, false)

        initVars(view)
        initActions(view)
        return customCreate(layoutInflater, container, savedInstanceState, view)
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }
}