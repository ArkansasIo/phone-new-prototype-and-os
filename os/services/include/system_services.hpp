#pragma once
#include <string>
#include <unordered_map>

namespace phoneos::services {

class PowerService {
public:
    bool set_performance_mode(bool enabled);
    bool performance_mode() const;
private:
    bool performance_ = false;
};

class SettingsService {
public:
    bool set(const std::string& key, const std::string& value);
    std::string get(const std::string& key) const;
private:
    std::unordered_map<std::string, std::string> values_;
};

class NotificationService {
public:
    bool post(const std::string& package_name, const std::string& title, const std::string& body);
};

} // namespace phoneos::services
