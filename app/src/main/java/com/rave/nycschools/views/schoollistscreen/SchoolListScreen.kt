package com.rave.nycschools.views.schoollistscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rave.nycschools.model.local.entity.School
import com.rave.nycschools.schoollist.SchoolListViewModel
import com.rave.nycschools.views.Screens

/**
 * Composable function representing the screen displaying the list of schools.
 *
 * @param viewModel The SchoolListViewModel instance for managing the state and data.
 * @param navigate The callback function for navigating to the detail screen.
 */
@Composable
fun SchoolListScreen(
    viewModel: SchoolListViewModel,
    navigate: (screen: Screens, school: School) -> Unit
) {
    val state = viewModel.state

    var showFavoritesFirst by remember { mutableStateOf(false) }

    // If showFavoritesFirst is true, reorder the schools list to show favorites first
    val schools = if (showFavoritesFirst) {
        state.schools.sortedByDescending { viewModel.favorites.contains(it.schoolName) }
    } else {
        state.schools
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Display the title "School List" at the center horizontally
                Text(
                    text = "School List",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                // Add a Checkbox that toggles the state of showFavoritesFirst
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Checkbox(
                        checked = showFavoritesFirst,
                        onCheckedChange = { showFavoritesFirst = it }
                    )
                    Text(
                        text = "Sort by favorites",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp)) // Space between title and list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Render a SchoolCard for each school in the reordered schools list
                items(schools) { school ->
                    SchoolCard(viewModel, school) { selectedSchool ->
                        // Invoke the navigate callback to navigate to the detail screen
                        navigate(Screens.DetailScreen, selectedSchool)
                    }
                }
            }
        }
    }
}

/**
 * Composable function representing a card displaying information about a school.
 *
 * @param school The School object to display.
 * @param navigate The callback function for navigating to the detail screen.
 */
@Composable
fun SchoolCard(viewModel: SchoolListViewModel, school: School, navigate: (School) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigate(school) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Display the school name with the specified typography and color
            Text(
                text = school.schoolName,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.weight(0.8f)  // Allocate 80% of width to the Text
            )
            IconButton(onClick = { viewModel.toggleFavorite(school.schoolName) }) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Favorite",
                    tint = if (viewModel.favorites.contains(school.schoolName)) Color.Yellow else Color.LightGray,
                    modifier = Modifier.weight(0.2f)  // Allocate 20% of width to the IconButton
                )
            }
        }
    }
}
