package com.rave.nycschools.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rave.nycschools.model.local.entity.School

@Database(entities = [School::class], version = 1)
abstract class SchoolDatabase : RoomDatabase() {
    abstract val schoolDAO : SchoolDAO

    companion object{
        @Volatile
        private var INSTANCE : SchoolDatabase? = null
        fun getInstance(context: Context):SchoolDatabase{
            synchronized(lock = this){
                var instance: SchoolDatabase? = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SchoolDatabase::class.java,
                        "school_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}