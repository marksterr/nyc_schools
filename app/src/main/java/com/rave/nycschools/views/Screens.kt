package com.rave.nycschools.views

/**
 * Enum class representing the screens in the application.
 *
 * @property route The route associated with each screen.
 */
enum class Screens(val route: String) {
    /**
     * Screen for displaying the school list.
     */
    SchoolListScreen("SchoolListScreen"),

    /**
     * Screen for displaying the detail of a school.
     */
    DetailScreen("DrinkScreen")
}