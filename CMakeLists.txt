cmake_minimum_required(VERSION 3.20)
project(PhoneNewPrototypeOS VERSION 0.1 LANGUAGES CXX)
set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

add_library(phone_model src/smartphone.cpp)
target_include_directories(phone_model PUBLIC include)

add_executable(phone_reference_model src/main.cpp)
target_link_libraries(phone_reference_model PRIVATE phone_model)

enable_testing()
add_executable(phone_tests tests/test_phone.cpp)
target_link_libraries(phone_tests PRIVATE phone_model)
add_test(NAME phone_tests COMMAND phone_tests)
