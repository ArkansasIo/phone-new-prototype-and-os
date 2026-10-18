#include "smartphone.hpp"
#include <cassert>
#include <iostream>

int main() {
    using namespace phoneos;
    PhoneConfig good{"test-6g", 6, 128, 8, 2, 6400, 32};
    std::string error;
    assert(validate_config(good, error));
    assert(theoretical_bandwidth_gbps(good) > 0.0);
    assert(default_components().size() >= 20);

    good.ram_gib = 0;
    assert(!validate_config(good, error));

    good.ram_gib = 7;
    assert(validate_config(good, error));

    std::cout << "All phone platform tests passed for the 6 GiB reference profile.\n";
}
