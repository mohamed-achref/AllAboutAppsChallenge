package at.allaboutapps.app.application

import at.allaboutapps.app.BuildConfig
import at.allaboutapps.app.di.AppInjector
import at.allaboutapps.app.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()

        initLogging()

        SplashScreenHelper.register(this)
        AppInjector.init(this)
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
