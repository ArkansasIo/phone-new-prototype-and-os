#include "system_services.hpp"
#include <iostream>

namespace phoneos::services {

bool PowerService::set_performance_mode(bool enabled) {
    performance_ = enabled;
    return true;
}

bool PowerService::performance_mode() const {
    return performance_;
}

bool SettingsService::set(const std::string& key, const std::string& value) {
    if (key.empty()) return false;
    values_[key] = value;
    return true;
}

std::string SettingsService::get(const std::string& key) const {
    const auto it = values_.find(key);
    return it == values_.end() ? std::string{} : it->second;
}

bool NotificationService::post(const std::string& package_name, const std::string& title, const std::string& body) {
    return !package_name.empty() && !title.empty() && !body.empty();
}

} // namespace phoneos::services
