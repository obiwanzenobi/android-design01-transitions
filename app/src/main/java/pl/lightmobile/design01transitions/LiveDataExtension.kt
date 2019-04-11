package pl.lightmobile.design01transitions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner, Observer { observer(it!!) })
}

fun LiveData<*>.isEmpty() = this.value == null

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> {
    val mediatorLiveData: MediatorLiveData<T> = MediatorLiveData()
    var latestValue: T? = null
    mediatorLiveData.addSource(this) { newValue ->
        if (latestValue != newValue) {
            mediatorLiveData.value = newValue
            latestValue = newValue
        }
    }
    return mediatorLiveData
}

