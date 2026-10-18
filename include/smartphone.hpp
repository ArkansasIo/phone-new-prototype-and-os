#pragma once
#include <string>
#include <vector>
#include <cstdint>

namespace phoneos {
struct Component {
    std::string name;
    std::string subsystem;
    std::string interface_name;
    bool required;
};
struct PhoneConfig {
    std::string name;
    std::uint64_t ram_gib;
    std::uint64_t storage_gib;
    std::uint32_t cpu_cores;
    std::uint32_t memory_channels;
    std::uint32_t data_rate_mt_s;
    std::uint32_t bus_width_bits;
};
std::vector<Component> default_components();
double theoretical_bandwidth_gbps(const PhoneConfig& config);
bool validate_config(const PhoneConfig& config, std::string& error);
}
