package com.example.callingapp.di

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.callingapp.App
import com.example.callingapp.lifecycle.ActivityLifecycleCallbacks
import com.example.callingapp.lifecycle.FragmentLifecycleCallbacks
import dagger.android.AndroidInjection
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection

/**
 * Inject into Activities and Fragments
 */
object AppInjector {

    fun init(app: App) {
        AppComponent.factory().create(app).inject(app)
        app.registerActivityLifecycleCallbacks(
            ActivityLifecycleCallbacks(
                onCreated = { activity, _ -> handleActivity(activity) }
            )
        )
    }

    private fun handleActivity(activity: Activity) {
        if (activity is HasAndroidInjector) {
            AndroidInjection.inject(activity)
        }

        if (activity is FragmentActivity) {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                FragmentLifecycleCallbacks(
                    onPreAttached = { _, fragment ->
                        if (fragment is Injected) {
                            try {
                                AndroidSupportInjection.inject(fragment)
                            } catch (e: IllegalArgumentException) {
                                throw IllegalArgumentException(
                                    "${activity.javaClass} does not have injector for ${fragment.javaClass}. Did you forget to declare it in Activity's Module?",
                                    e
                                )
                            }
                        }
                    }
                ),
                true)
        }
    }
}
