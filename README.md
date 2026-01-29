# FlightsApp

## Overview
FlightsApp is an Android application that displays flight information using a mock REST API.  
The project focuses on **clean architecture**, **predictable UI state**, and modern Android development practices using **Jetpack Compose**.

---

## Tech Stack
- Kotlin
- Jetpack Compose
- MVVM Architecture
- Kotlin Coroutines
- Firebase Authentication
- Android Navigation

---

## Architecture
The app follows **MVVM with a repository pattern**:

- UI layer (Compose) observes immutable UI state
- ViewModel handles business logic and exposes state
- Repository abstracts data sources and network logic
  
This separation improves maintainability and allows the UI to remain stateless and reactive.

---

## UI State Management
The UI reacts to explicit states:
- Loading
- Success
- Error

This ensures predictable rendering and easier debugging.

---

## Key Features
- User authentication flow (Firebase)
- Flight listing from mock API
- Loading and error handling
- Multi-screen navigation using Jetpack Compose
- Animated wave background
- Flight search functionality
  
--- 

## 📸 Screenshots & Demo

## Demo Video

https://github.com/user-attachments/assets/ce5df8c8-f675-4061-b4a2-feae5497435a


### Screenshots
| Flight List | Search | Login  | Welcome | SignUp |
|-------|---------|------------|--------| --------|
| ![Flight List](https://github.com/user-attachments/assets/678d7021-c36c-4406-aaf8-0bb869e7471c) | ![Search](https://github.com/user-attachments/assets/4c32fefa-ddc3-448e-ac17-1d44e27dff21) | ![Login](https://github.com/user-attachments/assets/8d69954b-beee-46c8-82b3-550d496aff38) | ![Login](https://github.com/user-attachments/assets/e1228e8c-dd9d-4e93-a208-8ea30a5702a7) | ![SignUp](https://github.com/user-attachments/assets/e4f665eb-caf7-4ad7-9211-05a169c3f80f )

--- 

## Feature Status & Roadmap

The application currently focuses on the flight search and exploration experience.

Some UI elements (such as the **“Book Now” button** and the **profile/bookings section**)
are part of the planned architecture and currently act as placeholders.

---

## Future Improvements
- Local caching for offline-first support
- Unit tests for ViewModels
- Replace mock API with real backend

