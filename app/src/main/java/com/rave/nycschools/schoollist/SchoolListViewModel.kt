package com.rave.nycschools.schoollist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rave.nycschools.model.SchoolRepo
import com.rave.nycschools.model.local.entity.School
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/**
 * ViewModel class for the school list feature.
 *
 * @param repo The SchoolRepo instance for retrieving school data.
 */
@HiltViewModel
class SchoolListViewModel @Inject constructor(private val repo: SchoolRepo) : ViewModel() {

    // State of the school list feature
    var state by mutableStateOf(SchoolListContract.State())

    // Favorites stored in a separate state so recomposition can be triggered
    var favorites by mutableStateOf(mutableStateListOf<String>())

    // Channel for emitting effects in the school list feature
    var effect = Channel<SchoolListContract.Effect>(Channel.UNLIMITED)
        private set

    // Exception handler for handling errors during data retrieval
    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        viewModelScope.launch {
            effect.send(
                SchoolListContract.Effect.Error(
                    exception.message ?: "An Unknown error has occurred"
                )
            )
        }
    }

    init {
        // Coroutine to fetch school data and update the state
        viewModelScope.launch(exceptionHandler) {
            state = state.copy(schools = repo.getSchools())
            if (state.schools == emptyList<School>()) {
                effect.send(SchoolListContract.Effect.NoSchoolsFound)
            } else {
                effect.send(SchoolListContract.Effect.DataWasLoaded)
            }
        }
    }

    /**
     * Retrieves a school by its name.
     *
     * @param name The name of the school to retrieve.
     * @return The School object with the specified name, or null if not found.
     */
    fun getSchoolByName(name: String): School? {
        return state.schools.find { it.schoolName == name }
    }

    /**
     * Toggles the favorite status of a school.
     *
     * @param schoolName The name of the school to toggle.
     */
    fun toggleFavorite(schoolName: String) {
        if (favorites.contains(schoolName)) {
            favorites.remove(schoolName)
        } else {
            favorites.add(schoolName)
        }
    }
}