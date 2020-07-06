package com.example.callingapp.di

import com.example.api.FirebaseDbClient
import com.example.api.SlackService
import com.example.db.MemberDao
import com.example.repository.MemberRepository
import com.example.repository.SlackRepository
import com.example.repository.di.DaggerRepositoryComponent
import com.example.repository.di.RepositoryComponent
import dagger.Module
import dagger.Provides

@Module
object RepositoryComponentModule {

    @Provides
    fun provideRepositoryComponent(
        slackService: SlackService,
        firebaseDbClient: FirebaseDbClient,
        employeeDao: MemberDao
    ): RepositoryComponent {
        return DaggerRepositoryComponent.factory().create(
            slackService,
            firebaseDbClient,
            employeeDao
        )
    }

    @Provides
    fun provideSlackRepository(repositoryComponent: RepositoryComponent): SlackRepository {
        return repositoryComponent.slackRepository
    }

    @Provides
    fun provideEmployyRepository(repositoryComponent: RepositoryComponent): MemberRepository {
        return repositoryComponent.memberRepository
    }
}
