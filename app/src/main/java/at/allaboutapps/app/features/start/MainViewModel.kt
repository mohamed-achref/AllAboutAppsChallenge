package at.allaboutapps.app.features.start

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.allaboutapps.app.networking.model.Club
import at.allaboutapps.app.networking.services.ApiService
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: ApiService) : ViewModel() {

    val clubList = MutableLiveData<List<Club>>()
    val errorMessage = MutableLiveData<String>()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getAllClubs() {
        compositeDisposable.add(
            repository.getAllClubs()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ list: List<Club> ->
                    if (clubList.value == null || clubList.value?.equals(list) == false) {
                        clubList.postValue(list)
                    }
                }, { error: Throwable ->
                    errorMessage.postValue(error.message)
                })
        )
    }
}