## Purpose
Canine Catalogue is a demo application dedicated to showcasing the latest Android technologies and best practices. It's a modern Android application built using Jetpack Compose, Hilt, Retrofit, Room, and other Android libraries. It's also a showcase of modern Android architecture, leveraging the principles of Clean Architecture and MVVM.

## Libraries Used
The Canine Catalogue leverages an array of modern Android libraries and tools to provide a seamless user experience. Here's a breakdown:

### AndroidX Libraries - Used for foundational app components and UI:

- Lifecycle LiveData & ViewModel
- Room for local database operations
- Navigation for in-app navigation
- Compose UI & Material Design for the UI
- DataStore for lightweight data storage
- Dependency Injection:

### Dagger-Hilt for dependency management and injection
- Hilt Navigation Compose for DI within navigation components
- Networking & Serialization:

### Retrofit for API calls
- Gson for JSON serialization/deserialization
- OkHttp for efficient HTTP operations
- kotlinx Serialization for JSON operations

### Image Loading & Animation:

- Coil-Compose for asynchronous image loading within Compose UI
- Lottie-Compose for rich and fluid animations

### Testing:

- MockK for Kotlin-centric mocking in unit tests
- JUnit for unit testing frameworks

## Architecture
Canine Catalogue is architectured around a Domain-Centered approach, influenced heavily by the principles of Clean Architecture and MVVM (Model-View-ViewModel). Here's a detailed look:

- Domain Layer: Central to the application, this layer is insulated from external changes and houses business logic and use-cases. It remains independent of other layers, ensuring a clear separation of concerns and adhering to SOLID principles.
- Data Layer: Operating under the Domain, this layer manages remote and local data sources. It interfaces with databases (such as Room) and network data (using Retrofit), ensuring a seamless flow of data to the domain layer.
- Presentation Layer: Built on the principles of MVVM, this layer showcases the user interface using Jetpack Compose and interacts with the Domain layer via ViewModels. It ensures a reactive UI experience, with the ViewModel acting as a mediator between the View and the Model.

## Notable Design Patterns:

- UseCase Pattern: This pattern represents a single unit of work. It abstracts complex actions the app needs to perform, ensuring each UseCase remains independent of the outer world, focusing solely on its respective task. This encapsulation allows for easier testing and maintenance.
- UI State Pattern: A design pattern that separates UI components from their states, promoting a more maintainable codebase and providing a reactive user interface equipped to handle changes efficiently.
- Repository Pattern: Situated in the data layer, repositories act as middlemen between various data sources. They abstract the origin of data, which can come from network sources, caching, or local databases.
- MVVM: Stands for Model-View-ViewModel. It's a design pattern that ensures a separation between the visual elements of the app (View), the data (Model), and the logic (ViewModel). This separation ensures a scalable, maintainable, and testable codebase.
- Clean Architecture: An architecture principle that emphasizes the SOLID principle. It divides the software into layers, ensuring that dependencies only point inwards. This guarantees that the core domain remains untouched by external changes, enhancing flexibility and maintainability.