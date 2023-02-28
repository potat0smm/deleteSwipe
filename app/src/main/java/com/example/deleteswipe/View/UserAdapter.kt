package com.example.deleteswipe.View

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deleteswipe.R
import com.example.deleteswipe.model.UserData

class UserAdapter(val context: Context,val userList: ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.title)
        val xzNum = view.findViewById<TextView>(R.id.subTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item,parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.xzNum.text = newList.userXz
    }


}