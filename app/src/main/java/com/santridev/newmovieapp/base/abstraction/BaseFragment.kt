package com.santridev.newmovieapp.base.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<out B : ViewBinding, V : ViewModel> : Fragment() {
    private var _binding: B? = null
    protected val binding: B
        get() = requireNotNull(_binding)

    lateinit var viewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(getViewModelClass)
        if (_binding == null) {
            _binding = getViewBinding(inflater, container, false)
        }
        return requireNotNull(_binding).root
    }

    abstract val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> B
    abstract val getViewModelClass: Class<V>

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}