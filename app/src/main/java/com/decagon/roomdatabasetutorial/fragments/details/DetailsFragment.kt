package com.decagon.roomdatabasetutorial.fragments.details

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.decagon.roomdatabasetutorial.R
import com.decagon.roomdatabasetutorial.databinding.FragmentDetailsBinding
import com.decagon.roomdatabasetutorial.model.Post
import com.decagon.roomdatabasetutorial.utils.toast
import com.decagon.roomdatabasetutorial.viewModel.PostViewModel


class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mPostViewModel: PostViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("updatedPostRequestKey") {
            requestKey, bundle -> val result = bundle.getParcelable<Post>("bundleKey")

            args.postDetails.title = result?.title?.split(" ")?.joinToString(" ") { it -> it.capitalize() } as String
            args.postDetails.body = result.body.capitalize()
            args.postDetails.id = result.id
            args.postDetails.userId = result.userId
           bindData()
        }
       bindData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.delete_menu ->  {
            deletePost()
            true
        }
        R.id.edit_menu -> {
            editPost()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun bindData () = with(binding) {
        title.text = args.postDetails.title.split(" ")?.joinToString(" ") { it -> it.capitalize() }
        post.text = args.postDetails.body.capitalize()
        postId.text = "${getString(R.string.post_id_text).capitalize()} ${args.postDetails.id}"
        userId.text = "Posted By: User ${args.postDetails.userId}"
    }

    private fun editPost() = with (binding) {

        val postId = postId.text?.toString()?.filter{char -> char.isDigit()}?.toInt()
        val userId = userId.text?.toString()?.filter{char -> char.isDigit()}?.toInt() as Int
        val title = title.text?.toString() as String
        val body = post.text.toString()

        val postDetails = Post(postId, userId, title, body)
        val action = DetailsFragmentDirections.actionDetailsFragmentToUpdateFragment(postDetails)
        findNavController().navigate(action)
    }

    private fun deletePost() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") {
            _, _ -> mPostViewModel.deletePost(args.postDetails)
            toast("Successfully removed post ${args.postDetails.id}")
            view?.findNavController()?.navigateUp()
        }
        builder.setNegativeButton("No") {
            _,_ ->
        }
        builder.setTitle("Delete ${args.postDetails.title}?")
        builder.setMessage("Are you sure you want to delete post ${args.postDetails.id}")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}