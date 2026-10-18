FROM gradle:8.14-jdk21 AS build
WORKDIR /workspace
COPY . .
RUN gradle :backend:installDist --no-daemon

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /workspace/backend/build/install/backend/ ./
EXPOSE 8080
ENTRYPOINT ["./bin/backend"]
