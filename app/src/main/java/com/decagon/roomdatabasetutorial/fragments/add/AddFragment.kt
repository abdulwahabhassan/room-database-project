package com.decagon.roomdatabasetutorial.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.decagon.roomdatabasetutorial.R
import com.decagon.roomdatabasetutorial.model.Post
import com.decagon.roomdatabasetutorial.utils.toast
import com.decagon.roomdatabasetutorial.viewModel.PostViewModel
import com.google.android.material.textfield.TextInputEditText

class AddFragment : Fragment() {

   private lateinit var mPostViewModel: PostViewModel

//    var binding: FragmentAddBinding? = null

    private lateinit var postButton: Button
    private lateinit var inputTextUserId: TextInputEditText
    private lateinit var inputTextBody: TextInputEditText
    private lateinit var inputTextTitle: TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mPostViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        postButton = view.findViewById(R.id.postButton)
        inputTextBody = view.findViewById(R.id.textBody)
        inputTextTitle = view.findViewById(R.id.textTitle)
        inputTextUserId = view.findViewById(R.id.textUserID)


        postButton.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val userId = inputTextUserId.text.toString()
        val title = inputTextTitle.text.toString()
        val body = inputTextBody.text.toString()

        if (inputCheck(userId, title, body)) {
            val post = Post(userId = Integer.parseInt(userId), title = title, body = body, id = null)
            mPostViewModel.addPost(post)
            toast("Successfully added!")
            Navigation.findNavController(requireView()).navigateUp()
        } else {
            toast("Please fill out all fields.")
        }
    }

    private fun inputCheck(userId: String, title: String, body: String): Boolean {
        return !(userId.isEmpty() && TextUtils.isEmpty(title) && TextUtils.isEmpty(body))
    }


}