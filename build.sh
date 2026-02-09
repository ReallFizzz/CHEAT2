#!/bin/bash

echo "======================================"
echo "Knockback Trainer Mod - Build Script"
echo "======================================"
echo ""

# Переход в директорию проекта
cd "$(dirname "$0")"

# Проверка наличия gradlew
if [ ! -f "gradlew" ]; then
    echo "❌ gradlew не найден. Создание Gradle Wrapper..."
    gradle wrapper --gradle-version 8.5
    chmod +x gradlew
    echo "✅ Gradle Wrapper создан"
fi

# Делаем gradlew исполняемым
chmod +x gradlew

echo ""
echo "🔨 Начинаем сборку мода..."
echo ""

# Сборка
./gradlew clean build

# Проверка результата
if [ $? -eq 0 ]; then
    echo ""
    echo "======================================"
    echo "✅ Сборка успешно завершена!"
    echo "======================================"
    echo ""
    echo "Готовый мод находится в:"
    echo "build/libs/KnockbackTrainer-1.0.0.jar"
    echo ""
    echo "Размер файла:"
    ls -lh build/libs/KnockbackTrainer-1.0.0.jar | awk '{print $5}'
    echo ""
    echo "📦 Скачайте файл и поместите его в папку mods Minecraft"
else
    echo ""
    echo "======================================"
    echo "❌ Ошибка при сборке!"
    echo "======================================"
    echo ""
    echo "Попробуйте:"
    echo "1. ./gradlew clean"
    echo "2. ./gradlew build --info"
    exit 1
fi
