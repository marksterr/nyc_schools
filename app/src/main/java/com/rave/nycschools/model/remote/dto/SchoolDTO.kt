package com.rave.nycschools.model.remote.dto

import com.rave.nycschools.model.local.entity.School
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the Data Transfer Object (DTO) for a school.
 * This class is used for serializing and deserializing JSON data.
 *
 * @param dbn The school's identification number.
 * @param numOfSatTestTakers The number of SAT test takers at the school.
 * @param satCriticalReadingAvgScore The SAT critical reading average score at the school.
 * @param satMathAvgScore The SAT math average score at the school.
 * @param satWritingAvgScore The SAT writing average score at the school.
 * @param schoolName The name of the school.
 */
@Serializable
data class SchoolDTO(
    @SerialName("dbn")
    val dbn: String?,
    @SerialName("num_of_sat_test_takers")
    val numOfSatTestTakers: String,
    @SerialName("sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String,
    @SerialName("sat_math_avg_score")
    val satMathAvgScore: String,
    @SerialName("sat_writing_avg_score")
    val satWritingAvgScore: String,
    @SerialName("school_name")
    val schoolName: String
) {
    /**
     * Converts the SchoolDTO object to a School object.
     *
     * @param dto The SchoolDTO object to convert.
     * @return The converted School object.
     */
    companion object {
        fun convertDTO(dto: SchoolDTO): School {
            return School(
                schoolName = dto.schoolName,
                numOfSatTestTakers = dto.numOfSatTestTakers,
                satCriticalReadingAvgScore = dto.satCriticalReadingAvgScore,
                satMathAvgScore = dto.satMathAvgScore,
                satWritingAvgScore = dto.satWritingAvgScore
            )
        }
    }
}