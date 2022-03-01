package com.example.githubuser

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.model.xmlresponse.User
import com.example.githubuser.view.home.Userhomerecadapter
import com.example.githubuser.databinding.ActivityMainBinding


@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {
//    private lateinit var adapter : Userhomerecadapter
    private lateinit var binding : ActivityMainBinding

    private var data = ArrayList<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        adapter = Userhomerecadapter()
//        val recView = binding.recviewUser
//        recView.adapter = adapter
//
//        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
//            recView.layoutManager = GridLayoutManager(this,2)
//        }else{
//            recView.layoutManager = LinearLayoutManager(this)
//        }

        window.statusBarColor = ContextCompat.getColor(this,R.color.maincolor)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.barmenu,menu)
//
//        return true
//    }



//    fun Inituser(){
//        val Name = resources.getStringArray(R.array.name)
//        val Photo = resources.obtainTypedArray(R.array.avatar)
//        for (i in Name.indices){
//            val dataUser = User(
//                resources.getStringArray(R.array.name)[i],
//                resources.getStringArray(R.array.username)[i],
//                resources.getStringArray(R.array.location)[i],
//                resources.getStringArray(R.array.repository)[i],
//                resources.getStringArray(R.array.company)[i],
//                resources.getStringArray(R.array.followers)[i],
//                resources.getStringArray(R.array.following)[i],
//                Photo.getResourceId(i,-1)
//            )
//
//            data.add(dataUser)
//            adapter.setdata(data)
//        }
//    }



}

