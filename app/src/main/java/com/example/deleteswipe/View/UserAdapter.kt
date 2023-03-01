package com.example.deleteswipe.View

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.deleteswipe.R
import com.example.deleteswipe.model.UserData


class UserAdapter(val context:Context,val userList:ArrayList<UserData>):RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{



    inner class UserViewHolder(val view: View):RecyclerView.ViewHolder(view){
        var name:TextView
        var mbNum:TextView
        var mMenus:ImageView

        init {
            name = view.findViewById<TextView>(R.id.mTitle)
            mbNum = view.findViewById<TextView>(R.id.mSubTitle)
            mMenus = view.findViewById(R.id.mMenus)
            mMenus.setOnClickListener { popupMenus(it) }
        }

        @SuppressLint("NotifyDataSetChanged", "DiscouragedPrivateApi")
        private fun popupMenus(view:View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(context,view)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editText->{
                        val v = LayoutInflater.from(context).inflate(R.layout.add_item,null)
                        val name = v.findViewById<EditText>(R.id.userName)
                        val number = v.findViewById<EditText>(R.id.userNo)
                        AlertDialog.Builder(context)
                            .setView(v)
                            .setPositiveButton("Ok"){
                                    dialog,_->
                                position.userName = name.text.toString()
                                position.userMb = number.text.toString()
                                notifyDataSetChanged()
                                Toast.makeText(context,"User Information is Edited",Toast.LENGTH_SHORT).show()
                                dialog.dismiss()

                            }
                            .setNegativeButton("Cancel"){
                                    dialog,_->
                                dialog.dismiss()

                            }
                            .create()
                            .show()

                        true
                    }
                    R.id.delete->{
                        /**set delete*/
                        AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_warning)
                            .setMessage("Are you sure delete this Information")
                            .setPositiveButton("Yes"){
                                    dialog,_->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(context,"Deleted this Information",Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                    dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()

                        true
                    }
                    else-> true
                }

            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon",Boolean::class.java)
                .invoke(menu,true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(R.layout.list_item,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.mbNum.text = newList.userMb
    }

    override fun getItemCount(): Int {
        return  userList.size
    }
}