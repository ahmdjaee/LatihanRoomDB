package com.example.latihanroomdb

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.latihanroomdb.data.User

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: UserListAdapter.MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.tvId).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvFirstName).text = currentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.tvLastname).text = currentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.tvAge).text = currentItem.age.toString()

        holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
        if (position % 2 == 0){
            holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setBackgroundColor(Color.YELLOW)
        }else {
            holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setBackgroundColor(Color.GREEN)
        }
    }



    override fun getItemCount(): Int {
        return userList.size
        Log.d("Size of User", userList.size.toString())
    }

    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }


}