package com.rave.nycschools.model.remote

import com.rave.nycschools.model.remote.dto.SchoolDTO
import retrofit2.http.GET

/**
 * Service interface for retrieving school data.
 */
interface SchoolService {
    /**
     * Retrieves a list of schools.
     *
     * @return A list of SchoolDTO objects.
     */
    @GET(SCHOOLS_ENDPOINT)
    suspend fun getSchools(): List<SchoolDTO>

    /**
     * Represents the endpoint for retrieving schools.
     */
    companion object {
        private const val SCHOOLS_ENDPOINT = "f9bf-2cp4.json"
    }
}