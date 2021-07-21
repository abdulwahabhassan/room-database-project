package com.decagon.roomdatabasetutorial.fragments.list

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.decagon.roomdatabasetutorial.R
import com.decagon.roomdatabasetutorial.model.Post
import com.decagon.roomdatabasetutorial.utils.toast
import com.decagon.roomdatabasetutorial.viewModel.PostViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {

    private lateinit var mPostViewModel: PostViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListOfPostsAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.postRecyclerView)
        recyclerView.adapter = adapter

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        mPostViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        view.findViewById<FloatingActionButton>(R.id.addPostButton).setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_all) {

            deleteAllPosts()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllPosts() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {
                _, _ -> mPostViewModel.deleteAllPost()
            toast("Successfully removed all posts")
        }
        builder.setNegativeButton("No") {
                _,_ ->
        }
        builder.setTitle("Delete all posts?")
        builder.setMessage("Are you sure you want to delete all posts")
        builder.create().show()
    }

}