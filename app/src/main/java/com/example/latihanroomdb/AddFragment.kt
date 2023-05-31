package com.example.latihanroomdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.latihanroomdb.data.User
import com.example.latihanroomdb.data.UserViewModel
import com.example.latihanroomdb.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel:UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding.root
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnSave.setOnClickListener {
            insertDataToDatabase()
            findNavController().navigate(R.id.action_AddFragment_to_ListFragment)
        }

//        binding.btnSave.setOnClickListener {
//            findNavController().navigate(R.id.action_AddFragment_to_ListFragment)
//        }
        return view
//        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    private fun insertDataToDatabase(){
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = Integer.parseInt(binding.etAge.text.toString())

        val user = User (0, firstName, lastName, age)
        mUserViewModel.addUser(user)
    }

}