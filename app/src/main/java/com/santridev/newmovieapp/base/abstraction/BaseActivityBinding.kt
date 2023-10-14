package com.santridev.newmovieapp.base.abstraction

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivityBinding<B : ViewBinding> : AppCompatActivity() {
    private var _binding: B? = null
    protected val binding: B
        get() = requireNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (_binding == null) {
            _binding = getViewBinding(layoutInflater)
        }
        setContentView(_binding?.root)
    }

    abstract val getViewBinding: (LayoutInflater) -> B

    fun alertDialog(
        title: String,
        message: String,
        positiveButtonText: String = "OK",
        onPositiveButtonPressed: () -> Unit = {},
        negativeButtonText: String = "Cancel",
        onNegativeButtonPressed: () -> Unit = {}
    ) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveButtonText) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
                onPositiveButtonPressed()
            }
            .setNegativeButton(negativeButtonText) { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()
                onNegativeButtonPressed()
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}