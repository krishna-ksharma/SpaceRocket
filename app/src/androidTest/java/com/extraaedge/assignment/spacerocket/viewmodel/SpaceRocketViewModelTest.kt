package com.extraaedge.assignment.spacerocket.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.data.model.FakeRocket
import com.extraaedge.assignment.spacerocket.data.model.Rocket
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
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

    private lateinit var viewStates: MutableList<RocketResult<List<Rocket>>>

    @Before
    fun setup() {
        hiltRule.inject()
        rocketViewModel = SpaceRocketViewModel(repository)
        viewStates = mutableListOf()
        rocketViewModel.rockets.observeForever {
            it?.let {
                viewStates.add(it)
            }
        }
    }

    @After
    fun tearDown() {
        rocketViewModel.rockets.removeObserver { }
    }

    @Test
    fun listEmptyRockets() {
        coEvery { repository.listRockets(any()) } returns RocketResult.Success(emptyList())
        rocketViewModel.listRockets(true)
        coVerify {
            repository.listRockets(true)
        }
        Assert.assertEquals(true, viewStates[0] is RocketResult.InProgress)
        Assert.assertEquals(true, (viewStates[1] is RocketResult.Success))
    }

    @Test
    fun listNoneEmptyRockets() {
        val result = RocketResult.Success(listOf(FakeRocket.fakeData()))
        coEvery { repository.listRockets(true) } returns result
        rocketViewModel.listRockets(true)
        coVerify {
            repository.listRockets(true)
        }
        Assert.assertEquals(viewStates[1], result)
    }

    @Test
    fun listRocketsFailed() {
        coEvery { repository.listRockets(true) } returns RocketResult.Error(message = null)
        rocketViewModel.listRockets(true)
        coVerify {
            repository.listRockets(true)
        }
        Assert.assertEquals(true, viewStates[0] is RocketResult.InProgress)
        Assert.assertEquals(true, viewStates[1] is RocketResult.Error)
    }
}