package com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.data.local.RocketDatabase
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class RocketRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("FakeRocketApi")
    lateinit var rocketApi: RocketApi

    @Inject
    @Named("fakeDb")
    lateinit var db: RocketDatabase

    private lateinit var repository: FakeRocketRepository

    @Before
    fun setup() {
        hiltRule.inject()
        repository = FakeRocketRepository(rocketApi, db.rocketDAo())
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun listRocket_from_remote() {
        runBlocking {
            val result = repository.listRocket_from_remote()
            Assert.assertEquals(true, result is RocketResult.Success)
            Assert.assertEquals(1, (result as RocketResult.Success).data.size)
        }
    }

    @Test
    fun listRocket_failed_from_remote() {
        runBlocking {
            val result = repository.listRocket_failed_from_remote()
            Assert.assertEquals(true, result is RocketResult.Error)
           // Assert.assertEquals("Server Error", (result as RocketResult.Error).message)
        }
    }

    @Test
    fun listRocket_from_local() {
        runBlocking {
            val result = repository.listRocket_from_local()
            Assert.assertEquals(true, result is RocketResult.Success)
            Assert.assertEquals(1, (result as RocketResult.Success).data.size)
        }
    }

    @Test
    fun listEmptyRocket_from_local() {
        runBlocking {
            val result = repository.listEmptyRocket_from_local()
            Assert.assertEquals(true, result is RocketResult.Success)
            Assert.assertEquals(0, (result as RocketResult.Success).data.size)
        }
    }
}