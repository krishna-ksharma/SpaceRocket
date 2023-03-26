package com.extraaedge.assignment.spacerocket.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.data.model.FakeRocket
import com.extraaedge.assignment.spacerocket.data.local.RocketDatabase
import com.extraaedge.assignment.spacerocket.data.model.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.*
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class RocketDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("fakeDb")
    lateinit var db: RocketDatabase

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun listRocketTest() = runTest {
        val rockets = listOf<Rocket>(
            FakeRocket.fakeData()
        )
        rockets.forEach {
            db.rocketDAo().insert(it)
        }
        Assert.assertEquals(db.rocketDAo().getAllRockets().size, 1)
    }

    @After
    fun tearDown() {
        db.close()
    }
}