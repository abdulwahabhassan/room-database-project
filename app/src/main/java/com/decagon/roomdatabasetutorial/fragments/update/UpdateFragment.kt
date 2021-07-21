package com.decagon.roomdatabasetutorial.fragments.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.decagon.roomdatabasetutorial.databinding.FragmentUpdateBinding
import com.decagon.roomdatabasetutorial.model.Post
import com.decagon.roomdatabasetutorial.utils.toast
import com.decagon.roomdatabasetutorial.viewModel.PostViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>() //UpdateFragmentArgs is a class that is
    // automatically generated when we create arguments for our UpdateFragment in the navigation graph
    private lateinit var mPostViewModel: PostViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        binding.textUpdateUserID.setText(args.currentPost.userId.toString())
        binding.textUpdateTitle.setText(args.currentPost.title)
        binding.textUpdateBody.setText(args.currentPost.body)

        binding.updateButton.setOnClickListener {
            updateItem()
        }

        return binding.root
    }

    private fun updateItem() = with(binding) {

        val userId = textUpdateUserID.text.toString().trim()
        val title = textUpdateTitle.text.toString().trim()
        val body = textUpdateBody.text.toString().trim()

        if (inputCheck(userId, title, body)) {
            val updatedPost = Post(args.currentPost.id, userId.toInt(), title, body)
            mPostViewModel.updatePost(updatedPost)

            setFragmentResult("updatedPostRequestKey", bundleOf("bundleKey" to updatedPost))

            view?.findNavController()?.navigateUp()
            toast("Successfully updated!")
        } else {
            toast("Please fill out all fields.")
        }
    }


    private fun inputCheck(userId: String, title: String, body: String): Boolean {
        return !(userId.isEmpty() && TextUtils.isEmpty(title) && TextUtils.isEmpty(body))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}