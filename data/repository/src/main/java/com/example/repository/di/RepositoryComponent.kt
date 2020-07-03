package com.example.repository.di

import com.example.api.FirebaseDbClient
import com.example.api.SlackService
import com.example.db.MemberDao
import com.example.repository.MemberRepository
import com.example.repository.SlackRepository
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositoryComponent {

    val slackRepository: SlackRepository

    val memberRepository: MemberRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance slackClient: SlackService,
            @BindsInstance firebaseDbClient: FirebaseDbClient,
            @BindsInstance memberDao: MemberDao
        ): RepositoryComponent

        companion object {
            fun factory(): Factory {
                return DaggerRepositoryComponent.factory()
            }
        }
    }
}