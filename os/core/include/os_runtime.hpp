#pragma once
#include <cstdint>
#include <string>
#include <vector>

namespace phoneos {

using ProcessId = std::uint64_t;

struct ApplicationManifest {
    std::string package_name;
    std::string version;
    std::vector<std::string> permissions;
};

class ApplicationManager {
public:
    ProcessId launch(const ApplicationManifest& manifest);
    bool terminate(ProcessId pid);
    bool is_running(ProcessId pid) const;
};

class ServiceRegistry {
public:
    bool register_service(const std::string& name);
    bool unregister_service(const std::string& name);
    bool contains(const std::string& name) const;
};

class PermissionManager {
public:
    bool grant(const std::string& package_name, const std::string& permission);
    bool revoke(const std::string& package_name, const std::string& permission);
    bool allowed(const std::string& package_name, const std::string& permission) const;
};

} // namespace phoneos
