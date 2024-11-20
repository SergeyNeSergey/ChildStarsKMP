package com.glinyanov.childstars.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import com.glinyanov.childstars.R
import com.glinyanov.childstars.api.enums.OperationStatus
import kotlinx.coroutines.CoroutineExceptionHandler
import java.text.SimpleDateFormat
import java.util.*

const val APP = "app"
const val token = "token"


fun AppCompatActivity.getToken(): String?{
    return getSharedPreferences(APP, AppCompatActivity.MODE_PRIVATE).getString(token, null)
}

fun AppCompatActivity.setToken(userToken: String?){
    getSharedPreferences(APP, AppCompatActivity.MODE_PRIVATE).edit().putString(
        token,
        userToken
    ).apply()
}

fun Date.toFormattedDisplay(): String {
    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy hh:mm", Locale("ru"))
    return simpleDateFormat.format(this)
}

@SuppressLint("CutPasteId")
fun AppCompatActivity.showCommonAlert(message: Int?, titleId: Int? = null, progressBar: ConstraintLayout? = null) {
    val mDialogView = LayoutInflater.from(this).inflate(R.layout.alert_common, null)
    if (message != null) {
        mDialogView.findViewById<TextView>(R.id.messageAlert).text = getString(message)
    }
    if (titleId != null) {
        mDialogView.findViewById<TextView>(R.id.messageAlert).visibility = View.VISIBLE
        mDialogView.findViewById<TextView>(R.id.messageAlert).text = getString(titleId)
    }
    val mBuilder = AlertDialog.Builder(this)
        .setView(mDialogView)
    val  mAlertDialog = mBuilder.show()
    mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    mDialogView.findViewById<TextView>(R.id.btnAlertPositive).setOnClickListener {
        mAlertDialog.dismiss()
        if (progressBar != null) {
            hideProgressBar(progressBar)
        }
    }
}

@SuppressLint("CutPasteId")
fun AppCompatActivity.showAlert(message: String? = null, titleId: Int? = null, progressBar: ConstraintLayout? = null) {
    val mDialogView = LayoutInflater.from(this).inflate(R.layout.alert_common, null)
    if (message != null) {
        mDialogView.findViewById<TextView>(R.id.messageAlert).text = message
    }
    if (titleId != null) {
        mDialogView.findViewById<TextView>(R.id.messageAlert).visibility = VISIBLE
        mDialogView.findViewById<TextView>(R.id.messageAlert).text = getString(titleId)
    }
    val mBuilder = AlertDialog.Builder(this)
        .setView(mDialogView)
    val  mAlertDialog = mBuilder.show()
    mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    mDialogView.findViewById<TextView>(R.id.btnAlertPositive).setOnClickListener {
        mAlertDialog.dismiss()
        if (progressBar != null) {
            hideProgressBar(progressBar)
        }
    }
}

fun AppCompatActivity.createDefaultExceptionHandler(progressBar: ConstraintLayout? = null): CoroutineExceptionHandler {
    return CoroutineExceptionHandler { context, throwable ->
        showCommonAlert(R.string.alertCommon, progressBar = progressBar)
        Log.e(this::class.simpleName, "Unhandled exception", throwable)
    }
}

fun AppCompatActivity.getDefaultErrorMessage(status: OperationStatus?): String? =
    when (status) {
        OperationStatus.SUCCESS -> null
        OperationStatus.FORBIDDEN -> getString(R.string.alert_error_forbidden_message)
        OperationStatus.INVALID_REQUEST -> getString(R.string.alert_error_invalid_message)
        OperationStatus.UNSUPPORTED_API_VERSION -> getString(R.string.alert_error_unsupported_api_message)
        OperationStatus.MAINTENANCE -> getString(R.string.alert_error_maintenance_message)
        OperationStatus.FAILED -> getString(R.string.alert_error_failed_message)
        null -> getString(R.string.alert_error_unknown_message)
    }


fun AppCompatActivity.showProgressBar(progressBar: ConstraintLayout) {
    hideKeyboard()
    progressBar.isVisible = true
    progressBar.isClickable = true
    //backgroundBottomNavigation.isClickable = true
}

fun AppCompatActivity.hideProgressBar(progressBar: ConstraintLayout) {
    progressBar.isVisible = false
    progressBar.isClickable = false
    //backgroundBottomNavigation.isClickable = false
}

fun AppCompatActivity.hideKeyboard() {
    currentFocus?.let {
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(it.windowToken, 0)
        it.clearFocus()
    }
}

fun AppCompatActivity.alertEmptyField(view: TextView?){
    view?.background = ContextCompat.getDrawable(this, R.drawable.bg_input_field_alert)
    view?.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (s.isNotEmpty()) {
                view.background = ContextCompat.getDrawable(
                    this@alertEmptyField,
                    R.drawable.bg_input_field
                )
            }
        }
    })
}



