package com.example.domain.requests.di

import com.example.domain.requests.repository.RequestsRepository
import com.example.domain.requests.usecases.DeleteRequest
import com.example.domain.requests.usecases.FetchRequests
import com.example.domain.requests.usecases.GetRequestById
import com.example.domain.requests.usecases.RequestsUseCases
import com.example.domain.requests.usecases.SaveRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RequestsDomainModule {

    @Provides
    @Singleton
    fun provideRequestsUseCases(requestsRepository: RequestsRepository): RequestsUseCases = RequestsUseCases(
        fetchRequests = FetchRequests(requestsRepository),
        saveRequest = SaveRequest(requestsRepository),
        deleteRequest = DeleteRequest(requestsRepository),
        getRequestById = GetRequestById(requestsRepository)
    )

}