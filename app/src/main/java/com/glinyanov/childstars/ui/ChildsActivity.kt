package com.glinyanov.childstars.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.glinyanov.childstars.R
import com.glinyanov.childstars.api.Api
import com.glinyanov.childstars.api.enums.OperationStatus
import com.glinyanov.childstars.api.request.ExposedUser
import com.glinyanov.childstars.databinding.ActivityChildsBinding
import com.glinyanov.childstars.extensions.createDefaultExceptionHandler
import com.glinyanov.childstars.extensions.getToken
import com.glinyanov.childstars.extensions.hideProgressBar
import com.glinyanov.childstars.extensions.showCommonAlert
import com.glinyanov.childstars.extensions.showProgressBar
import com.glinyanov.childstars.ui.adapters.ChildRVAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ChildsActivity : AppCompatActivity(R.layout.activity_childs), CoroutineScope by MainScope() {

    private var childList: List<ExposedUser>? = null
    private val childsAdapter = ChildRVAdapter()

    @Inject
    lateinit var api: Api

    private lateinit var binding: ActivityChildsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityChildsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.main
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        with(binding.resultsRv) {
            layoutManager = LinearLayoutManager(context)
            adapter = childsAdapter
        }
        getChilds(false)
        childsAdapter.onChildClicked = {
//                val zap = listSystem?.find { it.isEdit }
//                zap?.name = getString(R.string.zap_city, it.name)
//                zap?.url = getString(R.string.chosen_city_url, it.slug)
//                putArrayList(App.KEY_LIST_SYSTEM, listSystem)
                startActivity(Intent(this@ChildsActivity, TasksActivity::class.java))
        }

        binding.btnAddChild.setOnClickListener {
            addChild()
        }

        binding.btnCheckAddChild.setOnClickListener {
            getChilds()
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
                binding.codeLayout.isVisible = true
                binding.childCode.text = response.responseData?.otp
                hideProgressBar(binding.progressBar)
            } else {
                showCommonAlert(R.string.alert_error_otp, progressBar = binding.progressBar)
            }

        }
    }

    private fun getChilds(isNotFirst: Boolean = true) {
        launch(createDefaultExceptionHandler(binding.progressBar)){
            showProgressBar(binding.progressBar)
            val response = getToken()?.let {
                api.getChilds(it.toInt())
            }
            if(response?.operationStatus == OperationStatus.SUCCESS)
            {
                childList = response.responseData?.childList
                if (!childList.isNullOrEmpty()) {
                    childsAdapter.setNewData(childList!!)
                }

                hideProgressBar(binding.progressBar)
            } else if(isNotFirst){
                showCommonAlert(R.string.alertCommon, progressBar = binding.progressBar)
            } else {
                hideProgressBar(binding.progressBar)
            }
        }
    }

}