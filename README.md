# School List App

## Overview

The School List App is an Android application developed in Jetpack Compose. It fetches data about schools and displays it in a clear and concise manner. The app provides the ability to mark schools as favorites and filter them accordingly.

## Features

- **School List**: The application presents a list of schools. Each school is represented as a card containing the school's name and a star icon that can be tapped to mark the school as a favorite.

- **Favorites**: Users can mark schools as favorites by tapping the star icon. These favorites are highlighted in yellow and can be optionally shown at the top of the list.

- **School Details**: By tapping on a school card, users can view detailed information about the school, including the average SAT scores in different subjects.

## Screenshots

(Include screenshots of your app here)

## Tech Stack

- **Jetpack Compose**: The UI is built entirely using Jetpack Compose, Google's modern toolkit for building native Android UI.

- **Hilt**: Hilt is used for dependency injection to ensure that the architecture of the app is scalable and maintainable.

- **Coroutines**: Kotlin's coroutines are used for handling asynchronous tasks, such as fetching school data.

- **ViewModel**: The app follows the MVVM architectural pattern, with ViewModel being used to manage the state of the UI and handle the business logic.

## Future Improvements

- Implement a search feature to search for schools by name.
- Add more school details, like location, number of students, etc.
- Include more sorting options to sort by scores, number of students, etc.
