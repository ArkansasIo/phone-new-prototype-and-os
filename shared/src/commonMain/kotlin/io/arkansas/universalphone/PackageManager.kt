package io.arkansas.universalphone

data class AppManifest(
    val packageName: String,
    val versionName: String,
    val versionCode: Long,
    val permissions: Set<Capability> = emptySet()
)

class PackageManager {
    private val packages = mutableMapOf<String, AppManifest>()

    fun install(manifest: AppManifest): Boolean {
        if (manifest.packageName.isBlank()) return false
        packages[manifest.packageName] = manifest
        return true
    }

    fun remove(packageName: String): Boolean = packages.remove(packageName) != null

    fun get(packageName: String): AppManifest? = packages[packageName]

    fun isInstalled(packageName: String): Boolean = packageName in packages

    fun list(): List<AppManifest> = packages.values.sortedBy { it.packageName }
}
