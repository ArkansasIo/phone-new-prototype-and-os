#include "os_runtime.hpp"
#include <algorithm>
#include <unordered_map>
#include <unordered_set>

namespace phoneos {
namespace {
std::unordered_map<ProcessId, ApplicationManifest> processes;
std::unordered_set<std::string> services;
std::unordered_map<std::string, std::unordered_set<std::string>> grants;
ProcessId next_pid = 1;
}

ProcessId ApplicationManager::launch(const ApplicationManifest& manifest) {
    const ProcessId pid = next_pid++;
    processes.emplace(pid, manifest);
    return pid;
}

bool ApplicationManager::terminate(ProcessId pid) {
    return processes.erase(pid) != 0;
}

bool ApplicationManager::is_running(ProcessId pid) const {
    return processes.find(pid) != processes.end();
}

bool ServiceRegistry::register_service(const std::string& name) {
    if (name.empty()) return false;
    return services.insert(name).second;
}

bool ServiceRegistry::unregister_service(const std::string& name) {
    return services.erase(name) != 0;
}

bool ServiceRegistry::contains(const std::string& name) const {
    return services.find(name) != services.end();
}

bool PermissionManager::grant(const std::string& package_name, const std::string& permission) {
    if (package_name.empty() || permission.empty()) return false;
    return grants[package_name].insert(permission).second;
}

bool PermissionManager::revoke(const std::string& package_name, const std::string& permission) {
    const auto it = grants.find(package_name);
    if (it == grants.end()) return false;
    return it->second.erase(permission) != 0;
}

bool PermissionManager::allowed(const std::string& package_name, const std::string& permission) const {
    const auto it = grants.find(package_name);
    return it != grants.end() && it->second.find(permission) != it->second.end();
}
} // namespace phoneos
