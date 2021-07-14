package com.teamtuna.emotionaldiary.firebasestorage

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import com.teamtuna.emotionaldiary.authentication.signInAnonymously
import java.io.File

fun firebaseStoragePutFiles(dir: File) {
    signInAnonymously {
        dir.walkBottomUp()
            .filter {
                it.isFile
            }.map {
                Uri.fromFile(it)
            }.forEach { uri ->
                val filename = uri.lastPathSegment ?: uri.toString()
                val meta = when {
                    filename.contains(".txt") -> storageMetadata { contentType = "text/plain" }
                    filename.contains(".jpeg") -> storageMetadata { contentType = "image/jpeg" }
                    else -> storageMetadata { contentType = "application/octet-stream" }
                }
                Firebase.storage.reference.child("image/$filename").putFile(uri, meta)
            }
        dir.deleteRecursively()
    }
}