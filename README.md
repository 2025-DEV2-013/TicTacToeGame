# Tic-Tac-Toe Game

A clean architecture implementation of the classic Tic-Tac-Toe game for Android, built with modern Android development practices.

## Features

- Clean Architecture with clear separation of concerns
- MVVM (Model-View-ViewModel) pattern
- Jetpack Compose for UI
- Kotlin Coroutines and StateFlow for asynchronous operations
- Dependency Injection with Hilt
- Unit tests for domain and presentation layers

## Tech Stack

- **Language**: Kotlin 2.2.21
- **Build System**: Gradle with Kotlin DSL
- **Android Gradle Plugin**: 8.13.1
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 36 (Android 16)
- **Compile SDK**: 36 (Android 16)
- **Architecture**: Clean Architecture, MVVM
- **Dependency Injection**: Hilt 2.57.2
- **Coroutines**: 1.10.2
- **Testing**: JUnit Jupiter, MockK, Turbine

## 🔄 Data Flow (Domain ↔ UI)

The app follows a unidirectional data flow:

1. **Domain Layer**:
    - Contains pure Kotlin business logic
    - Defines the GameState interface that acts as a contract
    - GameStateImpl implements the core game logic

2. **UI Layer**:
    - GameViewModel observes the domain state
    - Uses mappers to convert domain models to UI models
    - Exposes `StateFlow<GameUiState>` for the UI to observe

3. **Contract**:
    - Domain exposes `StateFlow<GameStateDomainModel>`
    - UI observes this state and maps it to UI-specific models
    - User actions are passed back to the domain through the GameState interface


## How to Compile and Run

Clone the repository:
git clone [https://github.com/2025-DEV2-013/TicTacToeGame.git](https://github.com/2025-DEV2-013/TicTacToeGame.git)

## Using Command Line

#### Clean build
./gradlew clean

#### Build debug APK
./gradlew assembleDebug

#### Install on device
./gradlew installDebug

#### Run unit tests
./gradlew testDebugUnitTest


  




   
