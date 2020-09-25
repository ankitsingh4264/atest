package com.example.test.model

import android.media.MediaDescription
import com.google.firebase.firestore.DocumentId

data class thought(
    var title:String="",
   var description: String="",
    var documentId: String=""
)