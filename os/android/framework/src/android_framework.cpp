#include "android_framework.hpp"
#include <unordered_set>

namespace phoneos::android {
namespace {
std::unordered_set<std::string> packages;
bool system_server_running = false;
}

bool ActivityManager::start_activity(const std::string& package_name, const std::string& activity) {
    return !package_name.empty() && !activity.empty() && packages.contains(package_name);
}

bool ActivityManager::stop_activity(const std::string& package_name, const std::string& activity) {
    return !package_name.empty() && !activity.empty();
}

bool PackageManager::install_package(const std::string& package_name, const std::string& version) {
    if (package_name.empty() || version.empty()) return false;
    return packages.insert(package_name).second;
}

bool PackageManager::remove_package(const std::string& package_name) {
    return packages.erase(package_name) != 0;
}

bool PackageManager::is_installed(const std::string& package_name) const {
    return packages.contains(package_name);
}

bool SystemServer::start() {
    system_server_running = true;
    return true;
}

bool SystemServer::running() const {
    return system_server_running;
}
} // namespace phoneos::android
