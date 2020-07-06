package com.example.callingapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.callingapp.ui.admin.login.LoginViewModel
import com.example.callingapp.ui.admin.setting.SettingViewModel
import com.example.callingapp.ui.admin.top.TopViewModel
import com.example.callingapp.ui.main.MainViewModel
import com.example.callingapp.ui.service.calling.CallingViewModel
import com.example.callingapp.ui.service.done.DoneViewModel
import com.example.callingapp.ui.service.meeting.MeetingCallViewModel
import com.example.callingapp.ui.service.meeting.MeetingViewModel
import com.example.callingapp.ui.service.reception.ReceptionViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MeetingViewModel::class)
    fun bindMeetingViewModel(meetingViewModel: MeetingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MeetingCallViewModel::class)
    fun bindMeetingCallViewModel(meetingViewModel: MeetingCallViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ReceptionViewModel::class)
    fun bindReceptionViewModel(receptionViewModel: ReceptionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CallingViewModel::class)
    fun bindCallingViewModel(CallingViewModel: CallingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DoneViewModel::class)
    fun bindDoneViewModel(doneViewModel: DoneViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopViewModel::class)
    fun bindTopViewModel(topViewModel: TopViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    fun bindSettingViewModel(settingViewModel: SettingViewModel): ViewModel
}