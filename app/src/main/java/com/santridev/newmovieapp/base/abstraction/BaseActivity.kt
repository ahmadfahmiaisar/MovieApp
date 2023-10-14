package com.santridev.newmovieapp.base.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding, V : ViewModel> : AppCompatActivity() {

    private var _binding: B? = null
    protected val binding: B
        get() = requireNotNull(_binding)

    private lateinit var mViewModel: V
    val viewModel: V
        get() = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (_binding == null) {
            _binding = getViewBinding(layoutInflater)
        }
        setContentView(_binding?.root)

        mViewModel = ViewModelProvider(this).get(getViewModelClass)

    }

    abstract val getViewBinding: (LayoutInflater) -> B
    abstract val getViewModelClass: Class<V>

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}