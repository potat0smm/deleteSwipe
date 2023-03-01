package com.example.deleteswipe.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.deleteswipe.R
import com.example.deleteswipe.model.UserData

class UserAdapter(val context: Context,val userList: ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val view: View):RecyclerView.ViewHolder(view){

        var name:TextView
        var xzNum:TextView
        var Menu: ImageView
        init {
            name = view.findViewById<TextView>(R.id.title)
            xzNum = view.findViewById<TextView>(R.id.subTitle)
            Menu = view.findViewById<ImageView>(R.id.Menu)
            Menu.setOnClickListener { popupMenus(it) }
        }

        private fun popupMenus(view: View) {
            val popupMenus = PopupMenu(context,view)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText->{
                        Toast.makeText(context,"Edit Button is Clicked",Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.delete->{
                        Toast.makeText(context,"Delete Button is Clicked", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("Popup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }


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