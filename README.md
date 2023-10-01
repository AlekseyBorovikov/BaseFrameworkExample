# Rick&Morty (in developing)
A multi-module application developed based on [The Rick And Morty Api](https://rickandmortyapi.com).

## Module structure
- app: main module of the application
- buildSrc: dependency module
- core: modules of basic logic
    - common: a set of classes and files from past projects that I periodically reuse
        - framework
            - annotation: contains one annotation that can be used to mark classes that need to be cached. The caching algorithm is implemented in network.interceptors.CacheInterceptor
            - app: contains classes for initializing components at the start of the application
            - cache: work with SharedPrefs
            - data: some data classes
            - extension: extensions of individual classes
            - mvvm: base classes based on ViewModel and ViewState for MVVM architecture
            - network: classes for configuring Retrofit
                - interceptors: contains 1 custom interceptor that caches Retrofit requests marked with an annotation
            - usecase: contains a base class for a certain type of business logic tasks
        - tools
            - Util: contains a couple of general methods
    - data: stores general data processing logic
    - domain: contains use cases that will use interface modules
    - locale: configures the database
    - model: stores common data classes
    - remote: configures Retrofit and implements network operation
    - resources: contains the application’s shared resources
- feature: features modules
    - characters: characters screen
    - episodes: episodes screen
    - locations: locations screen
-----
## 🛠 Tech Stack
- Kotlin, Coroutines
- Dagger Hilt
- JetPack
  - Material
  - ViewModel
  - Lifecycle
  - Room
- OpenSourceLibraries
  - retrofit2
  - Gson for Room Converter
- Architecture
  - MVVM
  - Multi Module
  - Single Activity
-----
### 👨‍💻Notice
This app is for learning architecture on the example of the [Now In Adroid](https://github.com/android/nowinandroid/tree/main) app.
