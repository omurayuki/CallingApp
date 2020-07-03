package com.example.repository.di

import com.example.api.FirebaseDbClient
import com.example.api.SlackService
import com.example.db.MemberDao
import com.example.repository.MemberRepository
import com.example.repository.SlackRepository
import dagger.Module
import dagger.Provides

@Module
internal object RepositoryModule {

    @Provides
    fun provideSlackRepository(
        client: SlackService
    ): SlackRepository {
        return SlackRepository(client)
    }

    @Provides fun provideMemberRepository(
        api: FirebaseDbClient,
        dao: MemberDao
    ): MemberRepository {
        return MemberRepository(api, dao)
    }
}