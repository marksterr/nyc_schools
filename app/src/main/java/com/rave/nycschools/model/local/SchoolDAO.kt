package com.rave.nycschools.model.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.rave.nycschools.model.local.entity.School

@Dao
interface SchoolDAO {
    @Insert
    suspend fun insertSchool(school: School): Long

    @Delete
    suspend fun deleteSchool(school: School): Int
}