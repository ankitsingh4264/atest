package com.example.test.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.test.model.thought
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

class thoughtsRepo {
    private var instance: thoughtsRepo? = null
    val db=FirebaseFirestore.getInstance()


    private var dataSet: ArrayList<thought> = ArrayList()


    fun getinstance():thoughtsRepo{
        if (instance==null){
            instance = thoughtsRepo()
        }
        return instance as thoughtsRepo
    }

    fun addThought(newthought: thought):Boolean{
        var added=false
       var docref= db.collection("thoughts").document()
               var id=docref.id
                newthought.documentId=id
            docref.set(newthought).addOnSuccessListener {
                added=true;
            }

        return added;
    }

    fun getThoughts(): Task<QuerySnapshot> {
        val docRef=db.collection("thoughts")
        return docRef.get()

    }

    fun deleteThought(docid:String){
      var docref=  db.collection("thoughts").document(docid)
        docref.delete()
    }



}