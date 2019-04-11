package pl.lightmobile.design01transitions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import pl.lightmobile.design01transitions.SimpleStateViewModel.SimpleState.Error
import pl.lightmobile.design01transitions.SimpleStateViewModel.SimpleState.Loading
import java.util.concurrent.TimeUnit.SECONDS

class SimpleStateViewModel : ViewModel() {

    private val mutableViewState = MutableLiveData<SimpleState>().apply { value = Loading }
    val viewState: LiveData<SimpleState> = mutableViewState

    fun loadData() {
        mutableViewState.value = Loading
        Observable.just(1)
            .delay(2, SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { mutableViewState.value = Error },
                onError = { mutableViewState.value = Error }
            )
    }

    sealed class SimpleState {
        object Loading : SimpleState()
        object Error : SimpleState()
    }
}
