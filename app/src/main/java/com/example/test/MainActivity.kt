package com.example.test

//import com.example.test.MainActivity.mm.mMainActivityViewModel

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test.model.thought
import com.example.test.viewmodels.mainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var mMainActivityViewModel: mainActivityViewModel


    private lateinit var mAdapter: rvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainActivityViewModel= ViewModelProviders.of(this).get(mainActivityViewModel::class.java)
        mMainActivityViewModel.init()
        val datalist= arrayListOf<thought>()
        mAdapter= rvAdapter(this, datalist)
        rvinit()

        mMainActivityViewModel.getData().observe(this, {
            Log.i("info", "$it")
            mAdapter.setData(it)

        })

        btnAdd.setOnClickListener {
            val title:String= edttitle.text.toString()
            val desc=edtdesc.text.toString()


              mMainActivityViewModel.addData(thought(title, desc))
            Toast.makeText(this@MainActivity, "Note Added", Toast.LENGTH_SHORT).show()

        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: ViewHolder,
                target: ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: ViewHolder, direction: Int) {
                mMainActivityViewModel.deleteData(mAdapter.getNoteAt(viewHolder.adapterPosition)!!.documentId)


                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerview)
    }

    private fun rvinit() {



        recyclerview.apply {

            layoutManager=LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter=mAdapter
        }
    }


}
