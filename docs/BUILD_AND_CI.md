# Build and Continuous Integration

## CI workflow

The `.github/workflows/ci.yml` workflow runs on pushes to `main`, pull requests, and manual dispatch.

It validates two independent tracks:

- **C++20 reference model:** CMake configure, compile, and CTest.
- **Kotlin / Android:** Java 21 setup, shared multiplatform tests, JVM prototype tests, and Android debug APK compilation.

The workflow does not flash hardware or claim that a vendor-specific phone image has been produced. Device-specific kernel, firmware, radio, and certification validation require a supported physical board.

## C++ reference model

Linux/macOS with CMake 3.20+ and a C++20 compiler:

```sh
cmake -S . -B build -DCMAKE_BUILD_TYPE=Release
cmake --build build --parallel
ctest --test-dir build --output-on-failure
```

Windows PowerShell:

```powershell
./scripts/build.ps1
```

## Kotlin modules

Install JDK 21 and a compatible Gradle distribution. The repository currently does not commit Gradle Wrapper files, so CI uses the Gradle setup action and a system Gradle installation.

```sh
gradle :shared:allTests :prototype:test
gradle :androidApp:assembleDebug
```

For a local build, generate and commit the Gradle Wrapper from a trusted, compatible Gradle installation before relying on wrapper-based commands.

## Failure handling

- Check Java and Gradle versions first if Kotlin plugin resolution fails.
- Check Android SDK platform/build-tools availability if APK compilation fails.
- For C++ failures, retain the CMake and CTest output from the failed run.
- Treat a green CI run as source/build validation, not device certification or hardware validation.
