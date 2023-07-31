package com.rave.nycschools.views

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rave.nycschools.model.local.entity.School
import com.rave.nycschools.schoollist.SchoolListViewModel
import com.rave.nycschools.ui.theme.NYCSchoolsTheme
import com.rave.nycschools.views.detailscreen.DetailScreen
import com.rave.nycschools.views.schoollistscreen.SchoolListScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity of the application.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<SchoolListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Apply the NYCSchoolsTheme to the entire activity
            NYCSchoolsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Display the SchoolListApp composable with the view model
                    SchoolListApp(viewModel = viewModel)
                }
            }
        }
    }
}

/**
 * Composable function representing the entry point of the application.
 *
 * @param viewModel The SchoolListViewModel instance for managing the state and data.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SchoolListApp(viewModel: SchoolListViewModel) {
    val navController = rememberNavController()

    // Set up the navigation graph with the start destination
    NavHost(navController = navController, startDestination = Screens.SchoolListScreen.route) {
        composable(Screens.SchoolListScreen.route) {
            // Display the SchoolListScreen composable and navigate to the detail screen
            Column {
                TopAppBar(
                    title = { Text("Home") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(Color.LightGray),
                )
                SchoolListScreen(viewModel) { screen: Screens, school: School ->
                    // Encode the school name to be passed as a parameter in the route
                    val schoolName = java.net.URLEncoder.encode(school.schoolName, "UTF-8")
                    navController.navigate("${screen.route}/$schoolName")
                }
            }
        }
        composable(route = "${Screens.DetailScreen.route}/{schoolName}") { backStackEntry ->
            // Decode the school name from the route parameter
            val schoolName = java.net.URLDecoder.decode(backStackEntry.arguments?.getString("schoolName"), "UTF-8")
            // Retrieve the School object based on the decoded school name
            val school = viewModel.getSchoolByName(schoolName)
            Column {
                TopAppBar(
                    title = { Text("Details") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(Color.LightGray),
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
                DetailScreen(school!!)
            }
        }
    }
}