package com.santridev.newmovieapp.base.abstraction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragmentBinding<out B : ViewBinding> : Fragment() {
    private var _binding: B? = null
    protected val binding: B
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = getViewBinding(inflater, container, false)
        }
        return requireNotNull(_binding).root
    }

    abstract val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> B

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}