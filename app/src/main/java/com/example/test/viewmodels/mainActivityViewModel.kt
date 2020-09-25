package com.example.test.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.thought
import com.example.test.repository.thoughtsRepo
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.doAsync

class mainActivityViewModel :ViewModel(){

   private  var mthought: MutableLiveData<MutableList<thought>> = MutableLiveData()
    private  var mthoughtsRepository:thoughtsRepo=thoughtsRepo().getinstance()
    private var isadded:MutableLiveData<Boolean> = MutableLiveData()

    fun init(){
        mthought.value= arrayListOf()
    }



    fun getData(): MutableLiveData<MutableList<thought>> {
        Log.i("info","getting data")


        mthoughtsRepository.getThoughts().addOnSuccessListener { result ->
            val list = arrayListOf<thought>()
            for (document: DocumentSnapshot in result) {

                var temp = document.toObject(thought::class.java)
                list.add(temp!!)
            }
            mthought.value=list;


        }

        Log.i("info", "${mthought.value!!.size}")

        return mthought
    }
    fun addData(thought: thought){
        mthoughtsRepository.addThought(thought)
         getData()
    }

    fun deleteData(documentId: String){
        mthoughtsRepository.deleteThought(documentId)
        getData()
    }


}