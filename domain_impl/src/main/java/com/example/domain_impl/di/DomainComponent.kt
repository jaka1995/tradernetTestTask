package com.example.domain_impl.di

import com.example.data_api.StockRepository
import com.example.domain_api.StockInteractor
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DomainDepsModule::class, DomainModule::class])
@Singleton
interface DomainComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun repo(repo: StockRepository): Builder

        fun build(): DomainComponent
    }

    val stockInteractor: StockInteractor
}
