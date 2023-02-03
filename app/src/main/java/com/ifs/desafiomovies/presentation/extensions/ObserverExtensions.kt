package com.ifs.desafiomovies.presentation.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.lifecycle(stateFlow: StateFlow<T>, action: (t: T) -> Unit){
    lifecycleScope.launchWhenCreated{
        stateFlow.collect { t -> action(t) }
    }
}
