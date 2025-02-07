package com.example.stockcontrol.viewModel

import android.content.Intent
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockcontrol.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val gAtuh : FirebaseAuth = Firebase.auth

    fun signUp(email: String, password: String, siguiente: () -> Unit, onError: (String) ->Unit){
        viewModelScope.launch{
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    siguiente()
                }else{
                    onError(task.exception?.message?: " ERROR DESCONOCIDO")
                }
            }
        }
    }
    fun logIn(email: String, password: String, siguiente: () -> Unit, onError: (String) ->Unit) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    siguiente()
                } else {
                    onError(task.exception?.message ?: " ERROR DESCONOCIDO")
                }
            }
        }
    }
    fun signInWithGoogleCredential(credential: AuthCredential, home:() -> Unit){
     viewModelScope.launch {
         try {
             auth.signInWithCredential(credential).addOnCompleteListener { task ->
                 if (task.isSuccessful) {
                     home()
                 }
             }
                 .addOnCompleteListener {
                 }
         } catch (e: Exception) {
             Log.e("google", "Error google view model $e")
         }
     }
    }

}