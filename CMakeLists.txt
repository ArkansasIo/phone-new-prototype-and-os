cmake_minimum_required(VERSION 3.20)
project(PhoneNewPrototypeOS VERSION 0.2 LANGUAGES CXX)
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

add_library(phone_model src/smartphone.cpp)
target_include_directories(phone_model PUBLIC include)

add_library(phone_os_core
    os/core/src/os_runtime.cpp
    os/services/src/system_services.cpp
)
target_include_directories(phone_os_core PUBLIC
    os/core/include
    os/services/include
)

add_library(phone_android_framework
    os/android/framework/src/android_framework.cpp
)
target_include_directories(phone_android_framework PUBLIC
    os/android/framework/include
)
target_link_libraries(phone_android_framework PRIVATE phone_os_core)

add_library(phone_ios_class_framework
    os/ios_class/src/ios_class_framework.cpp
)
target_include_directories(phone_ios_class_framework PUBLIC
    os/ios_class/include
)
target_link_libraries(phone_ios_class_framework PRIVATE phone_os_core)

add_executable(phone_reference_model src/main.cpp)
target_link_libraries(phone_reference_model PRIVATE phone_model phone_os_core)

enable_testing()
add_executable(phone_tests tests/test_phone.cpp)
target_link_libraries(phone_tests PRIVATE phone_model phone_os_core)
add_test(NAME phone_tests COMMAND phone_tests)
