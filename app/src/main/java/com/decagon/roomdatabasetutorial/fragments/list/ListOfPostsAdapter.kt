package com.decagon.roomdatabasetutorial.fragments.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.decagon.roomdatabasetutorial.R
import com.decagon.roomdatabasetutorial.model.Post

class ListOfPostsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var postList = emptyList<Post>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_of_posts_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val post = postList[position]

        holder.itemView.findViewById<TextView>(R.id.user_id).text = "User ID: ${post.userId}"
        holder.itemView.findViewById<TextView>(R.id.id).text = "Post ID: ${post.id.toString()}"
        holder.itemView.findViewById<TextView>(R.id.title).text = "Title: ${post.title?.
        split(" ")?.map{ it -> it.capitalize() }?.joinToString(" ")}"
        holder.itemView.findViewById<TextView>(R.id.body).text = post.body?.capitalize()

        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailsFragment(post)

            //"ListFragmentDirections" is a class that is automatically generated for our list fragment
            //because it has action(s) originating from it which can go in different directions that we specify.
            //A class is created for each destination where an action originates, ListFragmentDirections is one
            //of such class. This class has a method (e.g. actionListFragmentToDetailsFragment()) for each
            //action defined in the originating destination (ListFragment)

            //Note also that, a class is created for the receiving destination(DetailsFragment).
            //The name of this class is the name of the destination, appended with the word "Args", i.e
            //"DetailsFragmentArgs". Use this class's fromBundle() method to retrieve the arguments.

            holder.itemView.findNavController().navigate(action)
        }
    }


    fun setData(list: List<Post>) {
        this.postList = list
        notifyDataSetChanged()
    }

}
