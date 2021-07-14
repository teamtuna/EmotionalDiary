package com.teamtuna.emotionaldiary.authentication

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

fun signInAnonymously(actor: (AuthResult) -> Unit) {
    Firebase.auth.signInAnonymously()
        .continueWith {
            if (it.isSuccessful)
                actor(it.result!!)
        }
}