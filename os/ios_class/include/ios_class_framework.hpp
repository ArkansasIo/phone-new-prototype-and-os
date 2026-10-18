#pragma once
#include <string>
#include <vector>

namespace phoneos::ios_class {

class ApplicationRuntime {
public:
    bool launch(const std::string& bundle_id);
    bool terminate(const std::string& bundle_id);
    bool running(const std::string& bundle_id) const;
};

class GraphicsService {
public:
    bool initialize();
    bool initialized() const;
};

class SecurityService {
public:
    bool request_permission(const std::string& bundle_id, const std::string& capability);
    bool authorized(const std::string& bundle_id, const std::string& capability) const;
};

} // namespace phoneos::ios_class
