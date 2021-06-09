package com.teamtuna.emotionaldiary

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@ExperimentalCoroutinesApi
open class CoroutineTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    protected fun runWorkerTest(
        context: CoroutineContext = EmptyCoroutineContext,
        action: suspend () -> Unit
    ) = testDispatcher.runBlockingTest {
        launch(context, action)
    }

    private fun launch(context: CoroutineContext, action: suspend () -> Unit) {
        testScope.launch(context) {
            action()
        }
    }
}