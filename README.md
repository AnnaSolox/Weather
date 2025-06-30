# Weather

![Screenshot de la app](./app/assets/images/weather_github_cover.jpg)

> [Versión en Español / Spanish version](README_ES.md)

**Weather** is an Android application developed in `Kotlin` using `Jetpack` libraries (Room, ViewModel, Compose), `coroutines`, `flow`, `livedata`, and `Material Design`, based on `Clean Architecture` with an `MVVM` presentation model.

## Technologies and Libraries

<p float="right">
  <img src="./app/assets/images/weather_gif.gif" width="240px" alt="Gif weather app" align="right" style="margin-left: 20px;" />
</p>

- Minimum SDK: 28.
- [Kotlin](https://kotlinlang.org/) with [Coroutines](https://github.com/Kotlin/kotlinx.coroutines), [Flow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) and [Livedata](https://developer.android.com/topic/libraries/architecture/livedata?hl=es-419#create_livedata_objects) for asynchronous operations.
- Jetpack libraries:
    - Jetpack Compose: Modern Android toolkit for building declarative UIs.
    - ViewModel: Handles UI-related logic and survives configuration changes.
    - [Navigation](https://developer.android.com/develop/ui/compose/navigation?hl=es-419): Simplifies screen navigation. Integrated with [Hilt Navigation Compose](https://developer.android.com/develop/ui/compose/libraries?hl=es-419#hilt) for dependency injection, particularly with `SharedViewModel`.
    - [Room](https://developer.android.com/jetpack/androidx/releases/room?hl=es-419): Builds a database layer over SQLite to access city data efficiently.
    - [Hilt](https://dagger.dev/hilt/): Simplifies dependency injection.
- Architecture:
  - MVVM (Model - View - ViewModel): Promotes separation of concerns and improves maintainability.
  - Clean Arquitecture: Separates logic into layers (data, domain, and presentation) for better structure and scalability.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): For HTTP requests to the REST API.
- [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization): Reflection-free, multiplatform, multi-format serialization.
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing for code generation and analysis.
- [SplashScreen](https://developer.android.com/develop/ui/views/launch/splash-screen?hl=es-419#getting-started): For creating launch screens.

## OpenWeather API

<p float="right">
<a href="https://openweathermap.org/api">
<img src="./app/assets/images/openweather_logo.png" width="160px" alt="OpenWeather logo" align="right" style="margin-left: 20px;" />
</a>
</p>

Weather information is provided by the [OpenWeather](https://openweathermap.org/api) API.


## License
Designed and developed by AnnaSolox in 2025.

This project is licensed under the [Apache 2.0](./LICENSE).
You may not use this file except in compliance with the License.  
You may obtain a copy of the License at:

https://opensource.org/license/apache-2-0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an “AS IS” BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and limitations under the License.
