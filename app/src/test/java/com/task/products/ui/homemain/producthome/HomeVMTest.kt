package com.task.products.ui.homemain.producthome

import com.task.products.base.BaseTestCase
import com.task.products.domain.IDataRepository
import io.mockk.clearAllMocks
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeVMTest : BaseTestCase() {
    // Subject under test
    lateinit var viewModel: HomeVM

    // Use a fake UseCase to be injected into the viewModel
    lateinit var dataRepo: IDataRepository

    @Before
    fun setUp() {
        dataRepo = mockk()
    }

    @Test
    fun `get device list success`() {
       //Given

    }

    @Test
    fun `get device list error`() {
        //Given
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}
