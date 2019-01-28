package com.moraware.cheapdonkey.dependencyinjection

import com.moraware.cheapdonkey.base.BaseViewModel
import com.moraware.cheapdonkey.dependencyinjection.application.ApplicationScope
import com.moraware.cheapdonkey.main.MainActivityViewModelTest
import dagger.Component

@ApplicationScope
@Component(
        modules = [
            TestModule::class,
            TestSettingsModule::class,
            TestDomainDependencyModule::class
        ]
)
interface TestComponent {
    fun inject(test: MainActivityViewModelTest)
    fun inject(viewModel: BaseViewModel)
}