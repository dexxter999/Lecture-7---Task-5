package com.example.registerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.registerapp.databinding.ActivityRegisterUsernameBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegisterActivityUsername : AppCompatActivity() {

    private val binding by lazy { ActivityRegisterUsernameBinding.inflate(layoutInflater) }
    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backImageViewButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        binding.buttonSignUpUsername.setOnClickListener {
            val email = intent.getStringExtra("EMAIL")
            val password = intent.getStringExtra("PASSWORD")

            auth.createUserWithEmailAndPassword(email!!, password!!)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "თქვენი ანგარიში შექმნილია.", Toast.LENGTH_SHORT)
                            .show()
                        val intentSuccess = Intent(this, MainActivity::class.java)
                        startActivity(intentSuccess)
                    } else {
                        Snackbar.make(binding.root, "დაფიქსირდა შეცდომა", Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
        }

    }
}