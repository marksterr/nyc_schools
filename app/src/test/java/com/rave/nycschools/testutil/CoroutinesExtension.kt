package com.rave.nycschools.testutil

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Testing utility class for managing coroutines in tests.
 *
 * @param testDispatcher The test dispatcher to be used for coroutines.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesExtension(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : BeforeEachCallback, AfterEachCallback {

    /**
     * Sets up the test environment before each test by setting the main dispatcher to the provided test dispatcher.
     *
     * @param context The extension context.
     */
    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    /**
     * Cleans up the test environment after each test by resetting the main dispatcher.
     *
     * @param context The extension context.
     */
    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}