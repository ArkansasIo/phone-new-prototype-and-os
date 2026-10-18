#include "ios_class_framework.hpp"
#include <unordered_map>
#include <unordered_set>

namespace phoneos::ios_class {
namespace {
std::unordered_set<std::string> running_apps;
std::unordered_map<std::string, std::unordered_set<std::string>> permissions;
bool graphics_ready = false;
}

bool ApplicationRuntime::launch(const std::string& bundle_id) {
    if (bundle_id.empty()) return false;
    return running_apps.insert(bundle_id).second;
}

bool ApplicationRuntime::terminate(const std::string& bundle_id) {
    return running_apps.erase(bundle_id) != 0;
}

bool ApplicationRuntime::running(const std::string& bundle_id) const {
    return running_apps.contains(bundle_id);
}

bool GraphicsService::initialize() {
    graphics_ready = true;
    return true;
}

bool GraphicsService::initialized() const {
    return graphics_ready;
}

bool SecurityService::request_permission(const std::string& bundle_id, const std::string& capability) {
    if (bundle_id.empty() || capability.empty()) return false;
    return permissions[bundle_id].insert(capability).second;
}

bool SecurityService::authorized(const std::string& bundle_id, const std::string& capability) const {
    const auto it = permissions.find(bundle_id);
    return it != permissions.end() && it->second.contains(capability);
}
} // namespace phoneos::ios_class
