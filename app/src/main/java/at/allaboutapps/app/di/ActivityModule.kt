package at.allaboutapps.app.di

import at.allaboutapps.app.features.start.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @PerActivity
    @ContributesAndroidInjector
    fun provideMainActivity(): MainActivity
}
