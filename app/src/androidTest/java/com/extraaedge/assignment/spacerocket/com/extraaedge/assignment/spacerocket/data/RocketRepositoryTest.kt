package com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.data.RocketRepository
import com.extraaedge.assignment.spacerocket.data.RocketResult
import com.extraaedge.assignment.spacerocket.data.local.RocketDatabase
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun listRockets() {
        val rocketRepository = RocketRepository(rocketApi, db.rocketDAo())
        runBlocking {
            val result = rocketRepository.listRockets(true)
            Assert.assertEquals(true, result as RocketResult.Success)

        }
    }
}