package com.example.latihanroomdb

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.latihanroomdb.data.User
import com.example.latihanroomdb.data.UserViewModel
import com.example.latihanroomdb.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    //MENDEKLARASIKAN VARIABLE ARGS
    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.etFirstname2.setText(args.currentUser.firstName)
        binding.etLastname2.setText(args.currentUser.lastName)
        binding.etAge2.setText(args.currentUser.age.toString())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnUpdate.setOnClickListener {
            val id = args.currentUser.id
            updateDataToDatabase(id)
            findNavController().navigate(R.id.action_updateFragment_to_ListFragment)
        }
        binding.btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Yakin ingin menghapus data ini?").setCancelable(false).setPositiveButton("Ya"){dialog, id -> deleteUser()
            }.setNegativeButton("Tidak"){dialog, id -> }
            val alert = builder.create()
            alert.show()
        }
        return view
    }

    private fun deleteUser(){
        val user = User(args.currentUser.id,args.currentUser.firstName,args.currentUser.lastName,args.currentUser.age)
        mUserViewModel.deleteUser(user)
        findNavController().navigate(R.id.action_updateFragment_to_ListFragment)
    }

        private fun deleteUser2(){
        val firstName = args.currentUser.firstName
        val lastName = args.currentUser.lastName
        val age = args.currentUser.age
        val id = args.currentUser.id

        val user = User (id, firstName, lastName, age)
        mUserViewModel.deleteUser(user)
    }


    private fun updateDataToDatabase(id : Int){
        val firstName = binding.etFirstname2.text.toString()
        val lastName = binding.etLastname2.text.toString()
        val age = Integer.parseInt(binding.etAge2.text.toString())
        args.currentUser.id
        val user = User (id, firstName, lastName, age)
        mUserViewModel.updateUser(user)
    }



}