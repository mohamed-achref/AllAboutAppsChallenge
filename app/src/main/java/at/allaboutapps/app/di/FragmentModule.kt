package at.allaboutapps.app.di

import at.allaboutapps.app.features.main.ClubDetailFragment
import at.allaboutapps.app.features.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    fun provideMainFragment(): MainFragment

    @PerFragment
    @ContributesAndroidInjector
    fun provideClubDetailFragment(): ClubDetailFragment
}
