package at.allaboutapps.app.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.allaboutapps.app.di.Injectable
import at.allaboutapps.app.di.viewmodel.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Base class to use for this application
 */
abstract class BaseActivity : AppCompatActivity(), Injectable, HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    /**
     * Request a ViewModel from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> viewModel() = ViewModelProvider(this, viewModelFactory).get(T::class.java)
}
