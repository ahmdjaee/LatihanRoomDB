package com.example.latihanroomdb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanroomdb.data.User
import com.example.latihanroomdb.data.UserViewModel
import com.example.latihanroomdb.databinding.FragmentListBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {


//    lateinit var binding: Fragment1Binding
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//         Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        //Menyiapkan adapter
        val adapter = UserListAdapter()
        val rvUser = binding.rvUser
        rvUser.adapter = adapter

        //Menambahkan adapter ke recyclerView
        rvUser.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            user -> adapter.setData(user)
            Log.d("User", user.toString())
        })
        //Membuat action btnADD
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_ListFragment_to_AddFragment)
        }
        return view
//        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}