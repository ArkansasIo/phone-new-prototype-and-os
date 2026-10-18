#include "smartphone.hpp"
#include <iostream>
#include <iomanip>

int main() {
    using namespace phoneos;
    PhoneConfig cfg{"ResearchPhone-6G", 6, 1024, 8, 2, 6400, 32};
    std::string error;
    if (!validate_config(cfg, error)) {
        std::cerr << "Invalid configuration: " << error << "\n";
        return 1;
    }
    std::cout << "Phone New Prototype — reference model\n"
              << "RAM target: " << cfg.ram_gib << " GiB\n"
              << "Storage: " << cfg.storage_gib << " GiB\n"
              << "CPU cores: " << cfg.cpu_cores << "\n"
              << "Theoretical memory bandwidth: " << std::fixed << std::setprecision(2)
              << theoretical_bandwidth_gbps(cfg) << " GB/s\n\n"
              << "Component inventory:\n";
    for (const auto& c : default_components())
        std::cout << (c.required ? "[required] " : "[optional] ")
                  << c.name << " — " << c.subsystem
                  << " — " << c.interface_name << "\n";
    std::cout << "\nThis is a reference model, not a bootable phone OS or validated hardware design.\n";
    return 0;
}
