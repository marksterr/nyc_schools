package com.rave.nycschools.model

import com.rave.nycschools.model.local.entity.School
import com.rave.nycschools.model.remote.SchoolService
import javax.inject.Inject

/**
 * Repository class for retrieving school data.
 *
 * @param service The SchoolService instance for making API calls.
 */
class SchoolRepo @Inject constructor(private val service: SchoolService) {

    /**
     * Retrieves a list of schools.
     *
     * @return A list of School objects.
     */
    suspend fun getSchools(): List<School> {
        // Call the SchoolService to retrieve SchoolDTOs
        val schoolDTOs = service.getSchools()

        // Map SchoolDTOs to School objects
        return schoolDTOs.map {
            School(
                schoolName = it.schoolName,
                numOfSatTestTakers = it.numOfSatTestTakers,
                satCriticalReadingAvgScore = it.satCriticalReadingAvgScore,
                satMathAvgScore = it.satMathAvgScore,
                satWritingAvgScore = it.satWritingAvgScore
            )
        }
    }
}