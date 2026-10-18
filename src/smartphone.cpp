#include "smartphone.hpp"
namespace phoneos {
std::vector<Component> default_components() {
    return {
        {"Application processor / SoC","compute","vendor-specific BGA",true},
        {"CPU clusters","compute","inside SoC",true},
        {"GPU","graphics","inside SoC",true},
        {"NPU / AI accelerator","compute","inside SoC",false},
        {"ISP and camera pipeline","camera","inside SoC + CSI",true},
        {"LPDDR package","memory","dedicated memory PHY",true},
        {"UFS flash","storage","UFS/M-PHY-class",true},
        {"Display and display driver","display","MIPI DSI-class",true},
        {"Touch digitizer/controller","input","I2C/SPI/vendor bus",true},
        {"Camera modules","camera","MIPI CSI-class",true},
        {"Cellular modem and RF front end","radio","vendor-specific",false},
        {"Wi-Fi/Bluetooth","radio","PCIe/SDIO/USB-dependent",false},
        {"GNSS","location","chip-specific",false},
        {"NFC","payments/proximity","I2C/SPI + antenna",false},
        {"Audio codec and amplifiers","audio","I2S/SLIMbus-class",true},
        {"Microphones and speakers","audio","analog/digital audio",true},
        {"IMU and environmental sensors","sensors","I2C/SPI/GPIO",false},
        {"PMIC and regulators","power","I2C/SPI + rails",true},
        {"Battery, protection and fuel gauge","power","battery-specific",true},
        {"USB-C and charging controller","connectivity/power","USB-C/PD-dependent",true},
        {"Security processor / key store","security","SoC-specific secure path",true},
        {"Thermal spreader and sensors","thermal","mechanical + sensor bus",true},
        {"PCB, antennas and chassis","mechanical","multilayer PCB / RF",true}
    };
}
double theoretical_bandwidth_gbps(const PhoneConfig& c) {
    if (c.bus_width_bits == 0 || c.memory_channels == 0) return 0.0;
    return static_cast<double>(c.data_rate_mt_s) *
           static_cast<double>(c.bus_width_bits) *
           static_cast<double>(c.memory_channels) / 8000.0;
}
bool validate_config(const PhoneConfig& c, std::string& error) {
    if (c.ram_gib == 0 || c.ram_gib > 256) {
        error = "RAM must be between 1 and 256 GiB in this reference model.";
        return false;
    }
    if (c.storage_gib == 0 || c.cpu_cores == 0 ||
        c.memory_channels == 0 || c.data_rate_mt_s == 0 ||
        c.bus_width_bits == 0) {
        error = "Storage, CPU cores, memory channels, data rate and bus width must be non-zero.";
        return false;
    }
    error.clear();
    return true;
}
}
