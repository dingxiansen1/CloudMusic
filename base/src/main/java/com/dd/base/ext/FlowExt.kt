package com.dd.base.ext

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow


fun <T> ViewModel.observerFlow(data: Flow<T>, action: suspend (value: T) -> Unit) {
    launch {
        data.collect {
            action.invoke(it)
        }
    }
}
