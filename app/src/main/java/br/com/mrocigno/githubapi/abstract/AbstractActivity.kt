package br.com.mrocigno.githubapi.abstract

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment


abstract class AbstractActivity<T> : AppCompatActivity(), AbstractView {

    protected val activity = this
    protected abstract val presenter : T


    protected abstract fun initVars()
    protected abstract fun initActions()
    protected abstract fun contentView() : Int
    protected abstract fun customCreate(savedInstanceState: Bundle?)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())

        initVars()
        initActions()
        customCreate(savedInstanceState)
    }

    override fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    protected fun switchFragment(fragment : Fragment, frameId : Int, tag : String){
        hideAllFragments()
        if(supportFragmentManager.findFragmentByTag(tag) != null){
            showFragment(tag)
        } else {
            addFragment(fragment, frameId, tag)
        }
    }

    private fun addFragment(fragment : Fragment, frameId : Int, tag : String){
        if (!fragment.isAdded) {
            val transaction = activity.supportFragmentManager.beginTransaction()
            transaction.add(frameId, fragment, tag)
            transaction.commit()
        }
    }

    private fun showFragment(tag : String) {
        val transaction = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentByTag(tag)?.let {
            transaction.show(it)
        }


        transaction.commit()
    }

    private fun hideAllFragments(){
        val transaction = supportFragmentManager.beginTransaction()

        for (frag in supportFragmentManager.fragments){
            transaction.hide(frag)
        }
        transaction.commit()
    }

    protected fun removeAllFragments() {
        val transaction = supportFragmentManager.beginTransaction()

        for (fragment in supportFragmentManager.fragments){
            transaction.remove(fragment)
        }

        transaction.commit()
    }

}