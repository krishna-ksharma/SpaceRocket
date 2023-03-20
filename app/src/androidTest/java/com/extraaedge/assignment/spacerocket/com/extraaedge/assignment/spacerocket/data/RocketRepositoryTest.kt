package com.extraaedge.assignment.spacerocket.com.extraaedge.assignment.spacerocket.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.extraaedge.assignment.spacerocket.data.local.RocketDatabase
import com.extraaedge.assignment.spacerocket.data.remote.RocketApi
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

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

    }
}