package com.example.feature_mover.di
//
//import android.content.Context
import android.content.Context
import com.example.feature_mover.data.datasoruce.JsonDataSoruce
import com.example.feature_mover.data.repository.MoverRepositoryImpl
import com.example.feature_mover.domain.repository.MoverRepository
import com.example.feature_mover.domain.usecase.GetMoversUseCase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideJsonDataSoruce(@ApplicationContext context: Context): JsonDataSoruce {
//        return JsonDataSoruce(context)
//    }
//
//    @Provides
//    @Singleton
//    fun provideMoverRepository(jsonDataSource: JsonDataSoruce): MoverRepository {
//        return MoverRepositoryImpl(jsonDataSource)
//    }
//
//    @Provides
//    fun provideGetMoversUseCase(repository: MoverRepository): GetMoversUseCase {
//        return GetMoversUseCase(repository)
//    }
//}



object AppModule {

    fun provideJsonDataSource(context: Context): JsonDataSoruce {
        return JsonDataSoruce(context)
    }

    fun provideMoverRepository(context: Context): MoverRepository {
        return MoverRepositoryImpl(provideJsonDataSource(context))
    }

    fun provideGetMoversUseCase(context: Context): GetMoversUseCase {
        return GetMoversUseCase(provideMoverRepository(context))
    }


}
