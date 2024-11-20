package com.glinyanov.childstars.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.glinyanov.childstars.R
import com.glinyanov.childstars.api.Api
import com.glinyanov.childstars.api.enums.OperationStatus
import com.glinyanov.childstars.databinding.ActivityMainBinding
import com.glinyanov.childstars.databinding.ActivityTasksBinding
import com.glinyanov.childstars.extensions.createDefaultExceptionHandler
import com.glinyanov.childstars.extensions.getToken
import com.glinyanov.childstars.extensions.hideProgressBar
import com.glinyanov.childstars.extensions.setToken
import com.glinyanov.childstars.extensions.showCommonAlert
import com.glinyanov.childstars.extensions.showProgressBar
import com.glinyanov.childstars.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TasksActivity : AppCompatActivity(R.layout.activity_tasks), CoroutineScope by MainScope() {

    @Inject
    lateinit var api: Api

    private lateinit var binding: ActivityTasksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTasksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.main
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.addChild.setOnClickListener {
            addChild()
        }
    }

    private fun addChild() {
        launch(createDefaultExceptionHandler(binding.progressBar)){
            showProgressBar(binding.progressBar)
            val response = getToken()?.let {
                api.getOtp(it.toInt())
            }
            if(response?.operationStatus == OperationStatus.SUCCESS)
            {
                hideProgressBar(binding.progressBar)
            } else {
                showCommonAlert(R.string.alert_error_otp, progressBar = binding.progressBar)
            }

        }
    }
}