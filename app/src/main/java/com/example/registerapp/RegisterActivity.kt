package com.example.registerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.example.registerapp.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.backImageViewButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonNext.setOnClickListener {
            intent = Intent(this, RegisterActivityUsername::class.java)
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Snackbar.make(binding.root, "ველები ცარიელია, შეავსეთ!!", Snackbar.LENGTH_SHORT)
                    .show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Snackbar.make(binding.root, "Email შეიყვანეთ სწორად!!!", Snackbar.LENGTH_SHORT)
                    .show()
            } else if (password.length < 6) {
                Snackbar.make(
                    binding.root, "პაროლი არ უნდა იყოს 6 სიმბოლოზე ნაკლები!!", Snackbar.LENGTH_SHORT
                ).show()
            } else {
                intent.putExtra("EMAIL", email)
                intent.putExtra("PASSWORD", password)
                startActivity(intent)
            }


        }

    }
}