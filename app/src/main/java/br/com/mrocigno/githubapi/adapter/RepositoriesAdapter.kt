package br.com.mrocigno.githubapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mrocigno.githubapi.R
import br.com.mrocigno.githubapi.model.Github
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_repository.view.*
import kotlin.collections.ArrayList

class RepositoriesAdapter(
    private val context : Context,
    var list : List<Github>
) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = when(viewType){
            0 -> {
                LayoutInflater.from(context).inflate(R.layout.card_placeholder, parent, false)
            }
            1 -> {
                LayoutInflater.from(context).inflate(R.layout.card_info, parent, false)
            }
            else -> {
                LayoutInflater.from(context).inflate(R.layout.card_repository, parent, false)
            }
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val github = list[position]
        if(!github.placeholder && !github.infoCard){
            holder.setData(github)
        }
    }

    override fun getItemViewType(position: Int): Int =
        if(list[position].placeholder){
            0
        } else if (list[position].infoCard) {
            1
        } else {
            2
        }

    fun addData(list : List<Github>){
        if(this.list.isNotEmpty()){
            (this.list as ArrayList).addAll(list)
        } else {
            this.list = list
        }

        notifyDataSetChanged()
    }

    fun addPlaceholder(numItems : Int){
        val merge = list as ArrayList
        for (i in 1..numItems){
            merge.add(Github(true))
        }

        notifyDataSetChanged()
    }

    fun removePlaceholders(){
        list = list.dropLastWhile { github ->
            github.placeholder
        }
        notifyDataSetChanged()
    }

    fun removeAll(){
        list = ArrayList()
        notifyDataSetChanged()
    }

    fun showInfoCard(){
        removePlaceholders()

        if (list.isNotEmpty() && !list.last().infoCard) {
            val merge = list as ArrayList
            merge.add(Github(placeholder = false, infoCard = true))
        } else {
            list = listOf(Github(placeholder = false, infoCard = true))
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        fun setData(github: Github){
            view.txtRepositoryName.text = github.full_name
            view.txtDescription.text = github.description
            view.txtForks.text = github.forks_count.toString()
            view.txtStars.text = github.stargazers_count.toString()
            github.owner?.let {
                Picasso.get().load(it.avatar_url).into(view.imgAuthorPic)
                view.txtAuthorName.text = it.login
            }

        }

    }

}