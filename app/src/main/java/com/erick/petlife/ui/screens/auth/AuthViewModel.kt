package com.erick.petlife.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _currentUser = MutableStateFlow<FirebaseUser?>(auth.currentUser)
    val currentUser: StateFlow<FirebaseUser?> = _currentUser
    // ...

    private val listener = FirebaseAuth.AuthStateListener { firebaseAuth ->
        _currentUser.value = firebaseAuth.currentUser
    }
    init { auth.addAuthStateListener(listener) }
    override fun onCleared() {
        auth.removeAuthStateListener(listener)
        super.onCleared()
    }

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login(email: String, pass: String) {
        _error.value = null
        _loading.value = true
        auth.signInWithEmailAndPassword(email.trim(), pass)
            .addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    _currentUser.value = auth.currentUser
                } else {
                    _error.value = task.exception?.localizedMessage ?: "Error al iniciar sesión"
                }
            }
    }

    fun register(email: String, pass: String) {
        _error.value = null
        _loading.value = true
        auth.createUserWithEmailAndPassword(email.trim(), pass)
            .addOnCompleteListener { task ->
                _loading.value = false
                if (task.isSuccessful) {
                    _currentUser.value = auth.currentUser
                } else {
                    _error.value = task.exception?.localizedMessage ?: "Error al registrar"
                }
            }
    }

    fun logout() {
        auth.signOut()
        _currentUser.value = null
    }
}
