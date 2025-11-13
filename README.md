Galería Compose con carrusel horizontal
Aplicación Android en Kotlin que muestra una galería de 5 imágenes locales en un carrusel horizontal con LazyRow, navegación a detalle con Navigation Compose y diseño con Material 3.​

Características
Lista horizontal con LazyRow, desplazamiento hacia la derecha.​

Navegación entre Home y Detalle con NavHost, rutas tipadas y backstack gestionado por NavController.​

UI estructurada con Scaffold y TopAppBar de Material 3.​

Carga de imágenes locales desde res/drawable usando painterResource.​

Buenas prácticas de Manifest: label con @string/app_name y activity LAUNCHER.​

Requisitos
Android Studio reciente con Android 16 SDK (API 36) instalado.​

Proyecto compilado con compileSdk 36 y targetSdk 36 en el módulo app.​

JDK recomendado 21 configurado como toolchain para alinear Java/Kotlin.​

Estructura del proyecto
app/src/main/AndroidManifest.xml: declara la aplicación, íconos, label y MainActivity con intent-filter MAIN/LAUNCHER.​

MainActivity.kt: punto de entrada que invoca setContent y aplica el tema antes de mostrar App().​

App.kt: crea rememberNavController y dibuja el grafo de navegación.​

navigation/Screen.kt: rutas de navegación como sealed class con helper para construir details/{id}.​

navigation/AppNavGraph.kt: NavHost con startDestination, destino Home y destino Details con argumento Int tipado.​

model/ImageItem.kt: data class con id, title, description y drawableRes.​

model/ImageRepository.kt: lista fija de 5 elementos y getById(id) para la pantalla de detalle.​

ui/components/ImageCard.kt: Card de Material 3 con Image(painterResource) y título superpuesto clicable.​

ui/screens/HomeScreen.kt: Scaffold + TopAppBar y LazyRow con contentPadding y Arrangement.spacedBy.​

ui/screens/DetailsScreen.kt: Scaffold con TopAppBar y botón back que ejecuta popBackStack, mostrando la imagen y textos.​

res/drawable: imágenes locales en minúsculas referenciadas como R.drawable.nombre.​

Configuración de compilación
Asegura estas propiedades en app/build.gradle.kts.​

kotlin
android {
    namespace = "com.example.ejercicioimagenes"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.example.ejercicioimagenes"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
}
El namespace define el paquete donde se generan R y BuildConfig y debe coincidir con el paquete base de tu código.​

Para evitar incompatibilidades de JVM, configura la toolchain a 21 y alinea jvmTarget en Kotlin y targetCompatibility en Java.​

Cómo ejecutar
Desde Android Studio: selecciona un AVD o dispositivo y pulsa Run para instalar y lanzar MainActivity.​

Desde consola: compila e instala con Gradle/ADB.​

bash
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.example.ejercicioimagenes/.MainActivity
Estos comandos generan el APK de depuración y lo abren en el dispositivo o emulador conectado.​

Decisiones técnicas
Se usa LazyRow para carrusel horizontal por su eficiencia al componer solo ítems visibles.​

Se eligió Navigation Compose para rutas tipadas y un backstack gestionado sin Fragments.​

Material 3 provee Scaffold/TopAppBar y un sistema de theming moderno en Compose.​

Las imágenes se empaquetan como recursos y se cargan con painterResource, evitando dependencias externas.​

Guía de código clave
HomeScreen: lista horizontal con separación y relación de aspecto vertical en cada tarjeta.​

kotlin
LazyRow(
    contentPadding = PaddingValues(12.dp),
    horizontalArrangement = Arrangement.spacedBy(12.dp)
) {
    items(ImageRepository.items) { item ->
        ImageCard(
            title = item.title,
            drawableRes = item.drawableRes,
            onClick = { onOpenDetails(item.id) },
            modifier = Modifier.width(180.dp).aspectRatio(3f / 4f)
        )
    }
}
Navegación: NavHost con argumento tipado y back.​

kotlin
NavHost(navController, startDestination = Screen.Home.route) {
    composable(Screen.Home.route) { HomeScreen(onOpenDetails = { id ->
        navController.navigate(Screen.Details.create(id))
    }) }
    composable(
        route = Screen.Details.route,
        arguments = listOf(navArgument(Screen.Details.ARG_ID){ type = NavType.IntType })
    ) { backStack ->
        val id = backStack.arguments?.getInt(Screen.Details.ARG_ID) ?: -1
        DetailsScreen(id = id, onBack = { navController.popBackStack() })
    }
}
Solución de problemas
“Namespace not specified”: añade android { namespace = "…" } en el módulo app.​

“Dependencias requieren compileSdk 36”: instala Android 16 SDK y sube compileSdk/targetSdk a 36.​

“adaptive-icon requiere SDK 26”: deja ic_launcher.xml en mipmap-anydpi-v26 y PNG legacy para minSdk < 26.​

“Inconsistent JVM-target compatibility”: alinea Java/Kotlin a 21 y define jvmToolchain(21).​

“No conecta al Kotlin daemon”: detener daemons, limpiar y, si persiste, usar estrategia in-process y ajustar memoria.​
