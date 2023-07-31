package com.rave.nycschools.schoollist

import com.rave.nycschools.model.local.entity.School

/**
 * Represents the contract for the state and effects in the school list feature.
 */
class SchoolListContract {
    /**
     * Represents the state of the school list feature.
     *
     * @property schools The list of schools.
     * @property loading Indicates if the data is currently being loaded.
     * @property error The error message, if any.
     */
    data class State(
        val schools: List<School> = emptyList(),
        var loading: Boolean = false,
        var error: String = ""
    )

    /**
     * Represents the possible effects in the school list feature.
     */
    sealed class Effect {
        /**
         * Indicates that the data was successfully loaded.
         */
        object DataWasLoaded : Effect()

        /**
         * Indicates that no schools were found.
         */
        object NoSchoolsFound : Effect()

        /**
         * Represents an error effect with a specific error message.
         *
         * @property message The error message.
         */
        data class Error(var message: String) : Effect()
    }
}