package com.example.deleteswipe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deleteswipe.View.UserAdapter
import com.example.deleteswipe.databinding.ActivityMainBinding
import com.example.deleteswipe.model.UserData
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var addsBtn:FloatingActionButton
    private lateinit var recycler:RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set List
        userList = ArrayList()
        //set find id
        addsBtn = findViewById(R.id.addingBtn)
        recycler = findViewById(R.id.mRecycler)
        //set Adapter
        userAdapter = UserAdapter(this,userList)
        //ser viewAdapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = userAdapter
        //set Dialog
        addsBtn.setOnClickListener { addInfo()}
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.add_item,null)
        //set view
        val userName = view.findViewById<EditText>(R.id.userName)
        val userNo = view.findViewById<EditText>(R.id.userNo)

        val addDialog = AlertDialog.Builder(this)


        addDialog.setView(view)
        addDialog.setPositiveButton("Ok"){
            dialog,_->
            val names = userName.text.toString()
            val number = userNo.text.toString()
            userList.add(UserData("Name: $names","Mobile No: $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this,"Adding Us Inf Success",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("cancel"){
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"cancel",Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}