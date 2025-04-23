package com.example.unlocktechtask.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.unlocktechtask.R
import com.example.unlocktechtask.Utils.showSuccessDialog
import com.example.unlocktechtask.databinding.ActivityMainBinding
import com.example.unlocktechtask.server.model.LoginRequest
import com.example.unlocktechtask.server.model.VerifyOtpRequest
import com.example.unlocktechtask.ui.vm.EntryModuleVm

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: EntryModuleVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = vm
        binding.lifecycleOwner = this

        registerObservers()

        binding.btnSubmit.setOnClickListener {
            val emailOrMobile = binding.edtEmail.text.toString()
            val otp = binding.edtOtp.text.toString()

            when {
                emailOrMobile.isBlank() || otp.isBlank() -> {
                    Toast.makeText(
                        this,
                        getString(R.string.validation_empty_fields),
                        Toast.LENGTH_LONG
                    ).show()
                }

                emailOrMobile.contains("@") -> {
                    if (isValidEmail() && isOtpValid()) {
                        doLogin()
                    }
//                    else {
//                        Toast.makeText(this, getString(R.string.validation_mobile_no_or_email,"Email"), Toast.LENGTH_LONG).show()
//                    }
                }

                else -> {
                    if (isValidMobileNo() && isOtpValid()) {
                        doLogin()
                    }
//                    else {
//                        Toast.makeText(this,  getString(R.string.validation_mobile_no_or_email,"Mobile no"), Toast.LENGTH_LONG).show()
//                    }
                }
            }
        }

        //Error
        vm.error.observe(this@MainActivity) { errorText ->
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }

    private fun doLogin() {
        vm.login(
            LoginRequest(
                deviceIMEI = "NA",
                deviceSubscriptionId = "NA",
                username = binding.edtEmail.text.toString(),
                password = binding.edtOtp.text.toString(),
                version = 12
            )
        )
    }

    private fun registerObservers() {
        handelLoginApi()
        handelGenerateOtpApi()
        handelOtpVerifyApi()
    }

    //Login API
    private fun handelLoginApi() {
        vm.loginResult.observe(this) { response ->
            vm.generateOtp(
                LoginRequest(
                    deviceIMEI = "NA",
                    deviceSubscriptionId = "NA",
                    username = response.Mobile,
                    password = binding.edtOtp.text.toString(),
                    version = 12
                )
            )
        }
    }

    //OTP Generate API
    private fun handelGenerateOtpApi() {
        vm.otpResult.observe(this@MainActivity) { otpRes ->
            vm.verifyOtp(
                VerifyOtpRequest(
                    deviceIMEI = "NA",
                    deviceSubscriptionId = "NA",
                    authToken = vm.loginResult.value?.authToken,
                    otpToken = otpRes?.otpToken,
                    password = binding.edtOtp.text.toString(),
                    username = binding.edtEmail.text?.toString(),
                    version = 12,
                    otp = "168941"
                )
            )
        }
    }

    //Verify API
    private fun handelOtpVerifyApi() {
        vm.verifyOtpResult.observe(this) { verifyOtpRes ->
            //Show dialog
            if (verifyOtpRes != null)
                showSuccessDialog(this)
        }
    }

    //Validations
    private fun isValidEmail(): Boolean {
        val email = binding.edtEmail.text.toString().trim()
        if (!vm.isValidEmail(email)) {
            binding.edtEmail.error = getString(R.string.validation_mobile_no_or_email,"Email")
            return false
        } else {
            return true
        }
    }

    private fun isValidMobileNo(): Boolean {
        val mobile = binding.edtEmail.text.toString().trim()
        if (!vm.isValidMobile(mobile)) {
            binding.edtEmail.error = getString(R.string.validation_mobile_no_or_email,"Mobile no")
            return false
        } else {
            return true
        }
    }

    private fun isOtpValid(): Boolean {
        val otp = binding.edtOtp.text.toString().trim()
        if (!vm.isValidOtp(otp)) {
            binding.edtOtp.error = getString(R.string.validation_mobile_no_or_email,"OTP")
            return false
        } else {
            return true
        }
    }
}