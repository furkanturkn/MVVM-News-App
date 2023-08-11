# MVVM News App

![GitHub last commit](https://img.shields.io/github/last-commit/furkanturkn/MVVM-News-App)
![GitHub top language](https://img.shields.io/github/languages/top/furkanturkn/MVVM-News-App)
![GitHub](https://img.shields.io/github/license/furkanturkn/MVVM-News-App)

MVVM News App is an Android application that demonstrates the MVVM architecture pattern while fetching and displaying news articles using the News API. It integrates various modern Android technologies, approaches and libraries to showcase best practices in Android app development.

## Technologies Used

- MVVM Architecture
- Room Library
- Kotlin Coroutines
- Jetpack Compose
- Pagination
- [News API](https://newsapi.org/docs/endpoints/)

## Getting Started

To run this application locally, follow these steps:

1. Clone this repository to your local machine using:

   bash
   git clone https://github.com/furkanturkn/MVVM-News-App.git
2. local.properties
    bash
    BASE_URL=https://newsapi.org/
    API_KEY=YOUR_NEWS_API_KEY
## Features
- Fetches news articles from the News API.
- Utilizes the MVVM architecture for separation of concerns and maintainability.
- Persists user favorite article data using Room for offline access.
- Handles asynchronous operations with Kotlin Coroutines.
- Implements pagination for smooth scrolling and loading of news articles.
- UI built using Jetpack Compose for modern UI development.
