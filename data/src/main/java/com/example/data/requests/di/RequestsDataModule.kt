package com.example.data.requests.di

import android.content.Context
import androidx.room.Room
import com.example.data.auth.repository.AuthRepositoryImpl
import com.example.data.database.AppDatabase
import com.example.data.requests.repository.RequestsRepositoryImpl
import com.example.data.userManager.repository.UserManagerRepositoryImpl
import com.example.data.vehicles.dao.VehicleDao
import com.example.data.vehicles.repository.VehicleRepositoryImpl
import com.example.domain.auth.repository.AuthRepository
import com.example.domain.auth.repository.UserManagerRepository
import com.example.domain.requests.repository.RequestsRepository
import com.example.domain.vehicles.repository.VehiclesRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RequestsDataModule {

    @Provides
    fun provideRequestsRepository(firestore: FirebaseFirestore, mAuth: FirebaseAuth): RequestsRepository {
        return RequestsRepositoryImpl(firestore, mAuth)
    }

}