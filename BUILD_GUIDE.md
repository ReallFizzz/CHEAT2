# Краткое руководство по сборке в Replit

## Быстрый старт

### 1. Создайте Repl
- Зайдите на replit.com
- Создайте новый Java Repl
- Загрузите все файлы проекта

### 2. Установите Gradle Wrapper
```bash
cd KnockbackTrainerFabric
gradle wrapper --gradle-version 8.5
chmod +x gradlew
```

### 3. Соберите мод
```bash
./gradlew build
```

### 4. Скачайте .jar
Файл находится в: `build/libs/KnockbackTrainer-1.0.0.jar`

## Возможные проблемы

### Ошибка: "Permission denied"
```bash
chmod +x gradlew
./gradlew build
```

### Ошибка нехватки памяти
Добавьте в `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx2G
org.gradle.daemon=false
```

### Долгая загрузка зависимостей
Это нормально при первой сборке (10-15 минут). Gradle скачивает:
- Minecraft 1.21.1
- Fabric API
- Все библиотеки и зависимости

### Сборка падает
```bash
./gradlew clean
./gradlew build --no-daemon --stacktrace
```

## Проверка сборки

После успешной сборки должны появиться:
```
BUILD SUCCESSFUL in XXs
```

И файлы:
- `build/libs/KnockbackTrainer-1.0.0.jar` - основной мод
- `build/libs/KnockbackTrainer-1.0.0-sources.jar` - исходники

## Альтернативная сборка (если Replit не работает)

### Локальная сборка на ПК:

1. Установите Java 21
2. Установите Git
3. Склонируйте проект
4. Выполните:
```bash
./gradlew build  # Linux/Mac
gradlew.bat build  # Windows
```

### Использование GitHub Actions:

Создайте `.github/workflows/build.yml`:
```yaml
name: Build
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'
      - run: chmod +x gradlew
      - run: ./gradlew build
      - uses: actions/upload-artifact@v3
        with:
          name: mod-jar
          path: build/libs/*.jar
```

## Почему .jar может быть "битым"

### Проблема: Файл назван `KnockbackTrainer-1.0.0 (1).jar`
**Причина:** Браузер добавил `(1)` при повторном скачивании
**Решение:** Переименуйте, удалив ` (1)`

### Проблема: Размер файла слишком мал (< 50 KB)
**Причина:** Сборка не завершилась полностью
**Решение:** Пересоберите с флагом `--info`:
```bash
./gradlew clean build --info
```

### Проблема: Minecraft не видит мод
**Причина:** 
- Неправильная версия Minecraft
- Отсутствует Fabric API
- Файл поврежден при скачивании

**Решение:**
1. Проверьте версию Minecraft (должна быть 1.21.1)
2. Установите Fabric API
3. Перескачайте .jar файл
4. Проверьте логи Minecraft

## Отладка

### Просмотр логов сборки:
```bash
./gradlew build --info
```

### Просмотр всех задач Gradle:
```bash
./gradlew tasks --all
```

### Очистка build директории:
```bash
./gradlew clean
```

### Пересборка с нуля:
```bash
./gradlew clean build --refresh-dependencies
```

## Тестирование мода локально

Если у вас установлен Minecraft локально:

```bash
./gradlew runClient
```

Это запустит тестовый клиент Minecraft с вашим модом.

## Размер финального .jar

Нормальный размер: **50-150 KB**

Если файл больше 10 MB - возможно в него попали лишние зависимости.
Если файл меньше 30 KB - сборка неполная.

---

**Успешная сборка означает:**
- Файл `build/libs/KnockbackTrainer-1.0.0.jar` существует
- Размер файла 50-150 KB
- В консоли написано `BUILD SUCCESSFUL`
- Нет ошибок в логах
