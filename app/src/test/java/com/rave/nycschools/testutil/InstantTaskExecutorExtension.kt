package com.rave.nycschools.testutil

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Testing utility class for instant execution of LiveData operations.
 * This class overrides the TaskExecutor used by Architecture Components
 * to execute tasks synchronously on the same thread.
 */
class InstantTaskExecutorExtension : BeforeEachCallback, AfterEachCallback {

    /**
     * Sets up the test environment before each test by overriding the default TaskExecutor.
     *
     * @param context The extension context.
     */
    override fun beforeEach(context: ExtensionContext?) {
        // Set a custom TaskExecutor that runs tasks immediately on the same thread
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()

            override fun postToMainThread(runnable: Runnable) = runnable.run()

            override fun isMainThread(): Boolean = true
        })
    }

    /**
     * Cleans up the test environment after each test by resetting the TaskExecutor.
     *
     * @param context The extension context.
     */
    override fun afterEach(context: ExtensionContext?) {
        // Reset the TaskExecutor to its original state
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}