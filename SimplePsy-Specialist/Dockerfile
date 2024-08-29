# Указываем базовый образ с OpenJDK 17
FROM eclipse-temurin:17-jdk AS build

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем файлы build.gradle, settings.gradle и зависимости в контейнер
COPY build.gradle settings.gradle /app/

# Загружаем зависимости
RUN ./gradlew build --no-daemon -x test || return 0

# Копируем остальные исходные файлы проекта
COPY . /app

# Собираем приложение
RUN ./gradlew bootJar --no-daemon

# Сборка минимального образа для запуска приложения
FROM eclipse-temurin:17-jdk-alpine

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем собранный jar файл из предыдущего этапа
COPY --from=build /app/build/libs/*.jar app.jar

# Указываем точку входа для запуска приложения
ENTRYPOINT ["java","-jar","/app/app.jar"]
