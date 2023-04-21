package com.extraaedge.assignment.spacerocket.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.data.model.FakeRocket
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.*

@HiltAndroidTest
class SpaceRocketViewModelTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = MainDispatcherRule()

    private val repository: RocketRepository = mockk()

    private lateinit var rocketViewModel: SpaceRocketViewModel

    private lateinit var viewStates: MutableList<RocketResult>

    @Before
    fun setup() {
        hiltRule.inject()
        rocketViewModel = SpaceRocketViewModel(repository)
        viewStates = mutableListOf()
        runBlocking {
            rocketViewModel.rockets.collect {
                viewStates.add(it)
            }
        }
    }

    @After
    fun tearDown() {
        // rocketViewModel.rockets.removeObserver { }
    }

    @Test
    fun listEmptyRockets() {
        coEvery { repository.listRockets(any()) } returns flow { emit(RocketResult.Success(emptyList())) }
        coVerify {
            repository.listRockets(true)
        }
        Assert.assertEquals(true, viewStates[0] is RocketResult.Loading)
        Assert.assertEquals(true, (viewStates[1] is RocketResult.Success))
    }

    @Test
    fun listNoneEmptyRockets() {
        val result = RocketResult.Success(listOf(FakeRocket.fakeData()))
        coEvery { repository.listRockets(true) } returns flow { emit(result) }
        rocketViewModel.listRockets(true)
        coVerify {
            repository.listRockets(true)
        }
        Assert.assertEquals(viewStates[1], result)
    }

    @Test
    fun listRocketsFailed() {
        coEvery { repository.listRockets(true) } returns flow { RocketResult.Error(message = null) }
        rocketViewModel.listRockets(true)
        coVerify {
            repository.listRockets(true)
        }
        Assert.assertEquals(true, viewStates[0] is RocketResult.Loading)
        Assert.assertEquals(true, viewStates[1] is RocketResult.Error)
    }
}