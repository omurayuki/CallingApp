package com.example.callingapp.di

import com.example.callingapp.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        ConfigModule::class,
        DataModule::class,
        DataApiComponentModule::class,
        DbComponentModule::class,
        RepositoryComponentModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    /**
     * `@Component.Factory`がついたクラスは
     * そのComponentに必要なインスタンスを受け取ってComponentを作成する。
     *
     * `DaggerAppComponent.factory()`
     */
    @Component.Factory
    interface Factory : AndroidInjector.Factory<App>

    companion object {
        /**
         * Shorthand for DaggerAppComponent.factory()
         */
        fun factory(): Factory = DaggerAppComponent.factory()
    }
}
