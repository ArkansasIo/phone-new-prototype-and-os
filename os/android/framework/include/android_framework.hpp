#pragma once
#include <string>
#include <vector>

namespace phoneos::android {

class ActivityManager {
public:
    bool start_activity(const std::string& package_name, const std::string& activity);
    bool stop_activity(const std::string& package_name, const std::string& activity);
};

class PackageManager {
public:
    bool install_package(const std::string& package_name, const std::string& version);
    bool remove_package(const std::string& package_name);
    bool is_installed(const std::string& package_name) const;
};

class SystemServer {
public:
    bool start();
    bool running() const;
};

} // namespace phoneos::android
