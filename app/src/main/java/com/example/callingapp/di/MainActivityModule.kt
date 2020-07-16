package com.example.callingapp.di

import androidx.appcompat.app.AppCompatActivity
import com.example.callingapp.MainActivity
import com.example.callingapp.ui.admin.login.LoginFragment
import com.example.callingapp.ui.admin.setting.SettingFragment
import com.example.callingapp.ui.admin.top.TopFragment
import com.example.callingapp.ui.main.MainFragment
import com.example.callingapp.ui.service.calling.CallingFragment
import com.example.callingapp.ui.service.done.DoneFragment
import com.example.callingapp.ui.service.reception.ReceptionFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {

    @Binds
    fun bindAppCompatActivity(activity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector
    fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    fun contributeReceptionFragment(): ReceptionFragment

    @ContributesAndroidInjector
    fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    fun contributeTopFragment(): TopFragment

    @ContributesAndroidInjector
    fun contributeSettingFragment(): SettingFragment

    @ContributesAndroidInjector
    fun contributeDoneFragment(): DoneFragment

    @ContributesAndroidInjector
    fun contributeCallingFragment(): CallingFragment
}
