package com.rave.nycschools

import com.rave.nycschools.model.SchoolRepo
import com.rave.nycschools.model.local.entity.School
import com.rave.nycschools.schoollist.SchoolListViewModel
import com.rave.nycschools.testutil.CoroutinesExtension
import com.rave.nycschools.testutil.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Unit test class for the ViewModel component.
 * This class contains tests for retrieving schools from the ViewModel.
 */
@ExtendWith(InstantTaskExecutorExtension::class)
internal class ViewModelTest {

    @RegisterExtension
    private val testExtension = CoroutinesExtension()

    // Mock SchoolRepo for dependency injection
    private val repo: SchoolRepo = mockk()

    // ViewModel instance to be tested
    private val viewModel = SchoolListViewModel(repo)

    /**
     * Test case for retrieving schools from the ViewModel.
     * This test verifies if the ViewModel returns the expected result based on the mocked repository response.
     */
    @Test
    fun testGetSchools() = runTest(testExtension.testDispatcher) {
        // Expected result
        val expectedResult = listOf(
            School(
                numOfSatTestTakers = "200",
                satCriticalReadingAvgScore = "200",
                satMathAvgScore = "200",
                satWritingAvgScore = "200",
                schoolName = "NYU"
            )
        )

        // Mock the repository method to return the expected result
        coEvery { repo.getSchools() } coAnswers { expectedResult }

        // Retrieve the result from the ViewModel
        val result = viewModel.state.schools

        // Assert that the expected result is not null
        Assertions.assertNotEquals(null, expectedResult)

        // Assert that the result matches the expected result
        Assertions.assertEquals(expectedResult, result)
    }
}