package com.rave.nycschools

import com.rave.nycschools.model.SchoolRepo
import com.rave.nycschools.model.local.entity.School
import com.rave.nycschools.model.remote.SchoolService
import com.rave.nycschools.model.remote.dto.SchoolDTO
import com.rave.nycschools.testutil.CoroutinesExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Unit test class for the Repository (Repo) component.
 * This class contains tests for retrieving schools from the repository.
 */
internal class RepoTest {

    @RegisterExtension
    private val testExtension = CoroutinesExtension()

    // Mock SchoolService for dependency injection
    private val service: SchoolService = mockk()

    // Repository instance to be tested
    private val repo = SchoolRepo(service)

    /**
     * Test case for retrieving schools from the repository.
     * This test verifies if the repository returns the expected result based on the mocked service response.
     */
    @Test
    fun testGetSchools() = runTest(testExtension.testDispatcher) {
        // Mocked service response
        val serviceResponse = listOf(
            SchoolDTO(
                dbn = null,
                numOfSatTestTakers = "200",
                satCriticalReadingAvgScore = "200",
                satMathAvgScore = "200",
                satWritingAvgScore = "200",
                schoolName = "NYU"
            )
        )

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

        // Mock the service method and provide the mocked response
        coEvery { service.getSchools() } coAnswers { serviceResponse }

        // Call the repository method and retrieve the result
        val result = repo.getSchools()

        // Assert that the result matches the expected result
        Assertions.assertEquals(expectedResult, result)
    }
}