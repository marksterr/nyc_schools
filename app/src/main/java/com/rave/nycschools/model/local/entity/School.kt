package com.rave.nycschools.model.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a School entity.
 *
 * @property numOfSatTestTakers The number of SAT test takers at the school.
 * @property satCriticalReadingAvgScore The SAT critical reading average score at the school.
 * @property satMathAvgScore The SAT math average score at the school.
 * @property satWritingAvgScore The SAT writing average score at the school.
 * @property isFavorite The favorite status of the school.
 * @property schoolName The name of the school.
 */
@Entity(tableName = "school_data_table")
data class School(
    val numOfSatTestTakers: String,
    val satCriticalReadingAvgScore: String,
    val satMathAvgScore: String,
    val satWritingAvgScore: String,
    var isFavorite: Boolean = false,
    @PrimaryKey
    val schoolName: String
)
