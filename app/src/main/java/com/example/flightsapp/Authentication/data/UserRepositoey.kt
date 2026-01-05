package com.example.flightsapp.Authentication.data

import android.util.Log
import com.example.flightsapp.Authentication.model.User
import com.example.flightsapp.utils.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UserRepository(private val auth: FirebaseAuth, private val firestore: FirebaseFirestore) {

    suspend fun signUp(email: String, password: String, username: String): Result<Boolean> =
        try {
            Result.Loading
            if (isUsernameTaken(username)) {
                Result.Error(Exception("Username Already taken"))
            } else {
                auth.createUserWithEmailAndPassword(email, password).await()
                val user = User(username = username, email = email)
                saveUsersToFirestore(user)
                Result.Success(true)
            }
        } catch (e: Exception) {
            Log.e("UserRepository", "Signup failed", e)
            Result.Error(e)
        }

    private suspend fun isUsernameTaken(username: String): Boolean {
        val querySnapshot = firestore
            .collection("users")
            .whereEqualTo("username", username)
            .get()
            .await()

        return !querySnapshot.isEmpty
    }

    suspend fun saveUsersToFirestore(user: User) {
        firestore.collection("users").document(user.email).set(user).await()
    }

    suspend fun login(email : String, password: String) : Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        }catch (e : Exception){
            Result.Error(e)
        }

}