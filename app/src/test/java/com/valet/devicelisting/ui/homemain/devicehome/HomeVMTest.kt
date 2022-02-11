package com.valet.devicelisting.ui.homemain.devicehome

import com.valet.devicelisting.base.BaseTestCase
import com.valet.devicelisting.base.getOrAwaitValue
import com.valet.devicelisting.data.models.DeviceDto
import com.valet.devicelisting.domain.IDataRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert
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
        val list: ArrayList<DeviceDto> = arrayListOf(DeviceDto(), DeviceDto(), DeviceDto())
        //1- Mock calls
        coEvery { dataRepo.getDeviceList() } returns list

        //2-Call
        viewModel = HomeVM(spyk(), dataRepo)
        viewModel.getDeviceList()

        //3-verify
        Assert.assertEquals(false, viewModel.deviceList.getOrAwaitValue().isNullOrEmpty())
    }

    @Test
    fun `get device list error`() {
        //Given
        val list: ArrayList<DeviceDto> = arrayListOf()
        //1- Mock calls
        coEvery { dataRepo.getDeviceList() } returns list

        //2-Call
        viewModel = HomeVM(spyk(), dataRepo)
        viewModel.getDeviceList()

        //3-verify
        Assert.assertEquals(true, viewModel.deviceList.getOrAwaitValue().isNullOrEmpty())
    }

    @After
    fun cleanUp() {
        clearAllMocks()
    }
}
