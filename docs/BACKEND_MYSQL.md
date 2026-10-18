# UniversalPhoneOS backend + phone OS layer

This repository now contains a Kotlin backend service backed by MySQL plus the Kotlin phone runtime/UI layer. The phone should communicate with MySQL through the HTTPS backend API; do not ship database credentials in the APK.

## Backend

- Ktor 3 server
- Kotlin/JVM 21
- HikariCP connection pool
- MySQL 8.4
- Exposed SQL schema/data access
- `/health`
- `/api/v1/devices`
- `/api/v1/devices/{id}/settings`

## Local development

```bash
docker compose -f docker-compose.phone.yml up --build
```

Backend health check:

```bash
curl http://localhost:8080/health
```

Register a prototype device:

```bash
curl -X POST http://localhost:8080/api/v1/devices \
  -H 'Content-Type: application/json' \
  -d '{"id":"prototype-001","model":"UniversalPhone Prototype","osVersion":"0.1.0"}'
```

## Phone OS layer

`shared/src/commonMain/.../UniversalPhoneRuntime.kt` defines the portable system runtime and settings model. The Android front end uses Jetpack Compose and consumes that shared runtime.

The architecture intentionally keeps the OS framework separate from the native kernel/firmware boundary. A production device still requires a vendor BSP, bootloader, kernel, HAL, drivers, secure boot chain, signing infrastructure, hardware validation, and regulatory certification.

## Security

The default credentials are development-only placeholders. Production deployments must use secrets management, TLS, device authentication, scoped authorization, migrations, backups, rate limiting, audit logs, and rotated credentials.
