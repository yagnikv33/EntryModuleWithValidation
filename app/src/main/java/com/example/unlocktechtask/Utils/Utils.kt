package com.example.unlocktechtask.Utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.unlocktechtask.databinding.SuccessDialogBinding

fun showSuccessDialog(context: Context) {
    val inflater = LayoutInflater.from(context)
    val binding = SuccessDialogBinding.inflate(inflater)

    val dialog = AlertDialog.Builder(context)
        .setView(binding.root)
        .setCancelable(false)
        .create()

    binding.btnOk.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}