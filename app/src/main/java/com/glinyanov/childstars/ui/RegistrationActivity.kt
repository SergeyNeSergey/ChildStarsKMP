package com.glinyanov.childstars.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.glinyanov.childstars.R
import com.glinyanov.childstars.api.Api
import com.glinyanov.childstars.api.enums.OperationStatus
import com.glinyanov.childstars.api.enums.Role
import com.glinyanov.childstars.databinding.ActivityRegistrationBinding
import com.glinyanov.childstars.extensions.createDefaultExceptionHandler
import com.glinyanov.childstars.extensions.getDefaultErrorMessage
import com.glinyanov.childstars.extensions.hideProgressBar
import com.glinyanov.childstars.extensions.setToken
import com.glinyanov.childstars.extensions.showAlert
import com.glinyanov.childstars.extensions.showProgressBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationActivity : AppCompatActivity(R.layout.activity_registration), CoroutineScope by MainScope() {

    companion object {
        const val ARGS_ROLE = "ARGS_ROLE"
    }

    @Inject
    lateinit var api: Api

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.main
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        val role = intent.getIntExtra(ARGS_ROLE, Role.EMPTY.primitiveValue)
        when(role) {
            Role.PARENT.primitiveValue -> {}
            Role.CHILD.primitiveValue -> {
                with(binding) {
                    name.isVisible = false
                    editTextEmail.isVisible = false
                    editTextPassword.isVisible = false
                    btnRegistration.isVisible = false
                    childName.isVisible = true
                    addChildCode.isVisible = true
                    btnChildRegistration.isVisible = true
                }
            }
            Role.EMPTY.primitiveValue -> {}
        }
        binding.btnRegistration.setOnClickListener {
            if (binding.editTextEmail.text.toString().isNotEmpty()) {
                if (binding.editTextPassword.text.toString().isNotEmpty()) {
                    if (binding.name.text.toString().isNotEmpty()) {
                        registrationParent(
                            binding.name.text.toString(),
                            binding.editTextEmail.text.toString(),
                            binding.editTextPassword.text.toString()
                        )
                    } else {
                        binding.name.error = getString(R.string.registration_name)
                    }
                } else {
                    binding.editTextPassword.error = getString(R.string.registration_passError)
                }
            } else {
                binding.editTextEmail.error = getString(R.string.registration_titleError)
            }
        }
        binding.btnChildRegistration.setOnClickListener {
            if (binding.childName.text.toString().isNotEmpty()) {
                if (binding.addChildCode.text.toString().isNotEmpty()) {
                    registrationChild(
                            binding.childName.text.toString(),
                            binding.addChildCode.text.toString()
                        )
                    } else {
                        binding.childName.error = getString(R.string.registration_name)
                    }
                } else {
                    binding.addChildCode.error = getString(R.string.registration_addChildCode)
                }
            }
    }

    private fun registrationParent(name: String, email: String, password: String) {
        launch(createDefaultExceptionHandler(binding.progressBar)){
            showProgressBar(binding.progressBar)
            val response = api.registration(name, email, password, Role.PARENT.primitiveValue)
            if(response.operationStatus == OperationStatus.SUCCESS)
            {
                setToken(response.responseData?.id.toString())
                hideProgressBar(binding.progressBar)
                // Переход в приложение родителя TODO
                startActivity(Intent(this@RegistrationActivity, TasksActivity::class.java))
            } else {
                showAlert(getDefaultErrorMessage(response.operationStatus), progressBar = binding.progressBar)
            }
            binding.progressBar.isVisible = false
        }
    }

    private fun registrationChild(name: String, otp: String) {
        launch(createDefaultExceptionHandler(binding.progressBar)){
            showProgressBar(binding.progressBar)
            val response = api.registrationChild(name, otp, Role.CHILD.primitiveValue)
            if(response.operationStatus == OperationStatus.SUCCESS)
            {
                setToken(response.responseData?.id.toString())
                hideProgressBar(binding.progressBar)
                // Переход в приложение ребёнка TODO
                startActivity(Intent(this@RegistrationActivity, TasksActivity::class.java))
            } else {
                showAlert(getDefaultErrorMessage(response.operationStatus), progressBar = binding.progressBar)
            }
            binding.progressBar.isVisible = false
        }
    }



}