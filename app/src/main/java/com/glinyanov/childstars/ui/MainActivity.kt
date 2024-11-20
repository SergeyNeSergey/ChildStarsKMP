package com.glinyanov.childstars.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.glinyanov.childstars.R
import com.glinyanov.childstars.api.enums.OperationStatus
import com.glinyanov.childstars.api.enums.Role
import com.glinyanov.childstars.databinding.ActivityMainBinding
import com.glinyanov.childstars.extensions.createDefaultExceptionHandler
import com.glinyanov.childstars.extensions.getToken
import com.glinyanov.childstars.extensions.hideProgressBar
import com.glinyanov.childstars.extensions.setToken
import com.glinyanov.childstars.extensions.showCommonAlert
import com.glinyanov.childstars.extensions.showProgressBar
import com.glinyanov.childstars.ui.RegistrationActivity.Companion.ARGS_ROLE
import com.glinyanov.childstars.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main), CoroutineScope by MainScope() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onStart() {
        if(getToken() != null) {
            startActivity(Intent(this@MainActivity, ChildsActivity::class.java))
        }
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.main
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnLogin.setOnClickListener {
            if (binding.editTextEmail.text.toString().isNotEmpty()) {
                if (binding.editTextPassword.text.toString().isNotEmpty()) {
                    login(
                        binding.editTextEmail.text.toString(),
                        binding.editTextPassword.text.toString()
                    )
                } else {
                    binding.editTextPassword.error = getString(R.string.registration_passError)
                }
            } else {
                binding.editTextEmail.error = getString(R.string.registration_titleError)
            }
        }

        binding.registration.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            intent.putExtra(ARGS_ROLE, Role.PARENT.primitiveValue)
            startActivity(intent)
        }

        binding.addChild.setOnClickListener {
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            intent.putExtra(ARGS_ROLE, Role.CHILD.primitiveValue)
            startActivity(intent)

        }
    }

    private fun login(email: String, password: String) {
        launch(createDefaultExceptionHandler(binding.progressBar)){
            showProgressBar(binding.progressBar)
            val response = mainViewModel.login(email, password)
            if(response.operationStatus == OperationStatus.SUCCESS)
            {
                setToken(response.responseData?.id.toString())
                hideProgressBar(binding.progressBar)
            } else {
                showCommonAlert(R.string.alert_error_unknown_user, progressBar = binding.progressBar)
            }

        }
    }

//    fun registration(email: String, password: String) {
//        launch(){
//            binding.progressBar.isVisible = true
//            val response = mainViewModel.addUser(email, password)
//            if(response.operationStatus == OperationStatus.SUCCESS)
//            {
//                TODO()
//                // into app
//            } else {
//                showCommonAlert(null)
//            }
//            binding.progressBar.isVisible = false
//        }
//    }



}