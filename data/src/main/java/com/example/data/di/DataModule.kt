package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.auth.repository.AuthRepositoryImpl
import com.example.data.database.AppDatabase
import com.example.data.userManager.repository.UserManagerRepositoryImpl
import com.example.data.vehicles.dao.VehicleDao
import com.example.data.vehicles.repository.VehicleRepositoryImpl
import com.example.domain.auth.repository.AuthRepository
import com.example.domain.auth.repository.UserManagerRepository
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
object DataModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideVehicleDao(database: AppDatabase): VehicleDao {
        return database.vehicleDao()
    }

    @Provides
    fun provideVehicleRepository(firestore: FirebaseFirestore, vehicleDao: VehicleDao): VehiclesRepository {
        return VehicleRepositoryImpl(firestore, vehicleDao)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        mAuth: FirebaseAuth, firestore: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(mAuth, firestore)

    @Provides
    @Singleton
    fun provideUserManagerRepository(
        mAuth: FirebaseAuth, firestore: FirebaseFirestore
    ): UserManagerRepository =
        UserManagerRepositoryImpl(mAuth, firestore)

    private const val DATABASE_NAME = "road_assist_database"
}