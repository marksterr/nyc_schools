package com.rave.nycschools.views.detailscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rave.nycschools.model.local.entity.School

/**
 * Composable function representing the detail screen for a school.
 *
 * @param school The School object to display details for.
 */
@Composable
fun DetailScreen(school: School) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display the school name at the top center of the screen
        Text(
            text = school.schoolName,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            // Display the title for SAT scores
            Text(
                text = "SAT Scores",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Display the number of SAT test takers
            Text(
                text = "Number of SAT Test Takers: ${school.numOfSatTestTakers}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Display the SAT critical reading average score
            Text(
                text = "SAT Critical Reading Average Score: ${school.satCriticalReadingAvgScore}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Display the SAT math average score
            Text(
                text = "SAT Math Average Score: ${school.satMathAvgScore}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Display the SAT writing average score
            Text(
                text = "SAT Writing Average Score: ${school.satWritingAvgScore}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}