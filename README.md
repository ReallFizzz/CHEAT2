# Knockback Trainer Mod для Minecraft 1.21.1 Fabric

## Описание

Мод позволяет контролировать направление отбрасывания (knockback) при атаке врага путём автоматического поворота головы игрока перед ударом.

## Функциональность

- **Автоматический поворот головы** при атаке на заданный угол (Yaw Offset)
- **Настраиваемая задержка** между поворотом и ударом (Delay Ticks)
- **Toggle-бинд** для включения/выключения функции (по умолчанию клавиша R)
- **GUI настроек** через Mod Menu с ползунками для регулировки параметров

## Требования

- Minecraft 1.21.1
- Fabric Loader 0.16.8+
- Fabric API 0.86.0+1.21.1
- Mod Menu 11.0.1+ (опционально, для GUI)
- Java 21

## Сборка мода онлайн через Replit

### Шаг 1: Подготовка проекта в Replit

1. Зайдите на [Replit.com](https://replit.com) и создайте новый Repl
2. Выберите язык **Java** или **Blank Repl**
3. Загрузите все файлы проекта в Replit:
   - Можно загрузить через интерфейс Replit (Files → Upload)
   - Или склонировать через Git, если проект в репозитории

### Шаг 2: Структура проекта

Убедитесь, что структура папок выглядит так:

```
KnockbackTrainerFabric/
├── build.gradle
├── settings.gradle
├── gradle.properties (опционально)
└── src/
    └── main/
        ├── java/
        │   └── dev/
        │       └── knockbacktrainer/
        │           ├── KnockbackTrainer.java
        │           ├── ModKeybinds.java
        │           ├── AttackInterceptor.java
        │           └── config/
        │               ├── KnockbackConfig.java
        │               ├── KnockbackConfigScreen.java
        │               └── ModMenuIntegration.java
        └── resources/
            ├── fabric.mod.json
            └── assets/
                └── knockbacktrainer/
                    └── lang/
                        ├── en_us.json
                        └── ru_ru.json
```

### Шаг 3: Настройка Gradle в Replit

1. Создайте файл `.replit` в корне проекта:

```toml
run = "./gradlew build"
```

2. Создайте файл `gradle.properties` (если нужно):

```properties
org.gradle.jvmargs=-Xmx2G
org.gradle.daemon=false
```

### Шаг 4: Установка Gradle Wrapper

Выполните в консоли Replit:

```bash
# Перейдите в директорию проекта
cd KnockbackTrainerFabric

# Скачайте Gradle Wrapper (если его нет)
gradle wrapper --gradle-version 8.5

# Сделайте gradlew исполняемым
chmod +x gradlew
```

### Шаг 5: Сборка мода

Выполните команду сборки:

```bash
./gradlew build
```

Процесс сборки займёт несколько минут. Gradle:
1. Скачает все зависимости (Minecraft, Fabric API, и т.д.)
2. Декомпилирует и подготовит Minecraft
3. Скомпилирует код мода
4. Создаст .jar файл

### Шаг 6: Получение готового .jar файла

После успешной сборки файл будет находиться в:

```
build/libs/KnockbackTrainer-1.0.0.jar
```

Скачайте этот файл:
1. Откройте файл в Replit
2. Нажмите на три точки → Download
3. Сохраните файл на ваш компьютер

## Установка в Minecraft

1. Убедитесь, что установлен **Fabric Loader** для Minecraft 1.21.1
2. Скачайте **Fabric API 0.86.0+1.21.1** с [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) или [Modrinth](https://modrinth.com/mod/fabric-api)
3. (Опционально) Скачайте **Mod Menu 11.0.1+** для доступа к GUI настроек
4. Поместите файлы в папку `mods`:
   - `KnockbackTrainer-1.0.0.jar`
   - `fabric-api-0.86.0+1.21.1.jar`
   - `modmenu-11.0.1.jar` (если используете)

## Использование

### Активация мода

1. Запустите Minecraft с установленным модом
2. Нажмите клавишу **R** (по умолчанию) для включения/выключения функции
3. В логах консоли появится сообщение: `Knockback Trainer: ENABLED` или `DISABLED`

### Настройка параметров

**Через Mod Menu:**
1. Откройте главное меню Minecraft
2. Нажмите "Mods"
3. Найдите "Knockback Trainer" в списке
4. Нажмите кнопку настроек (иконка шестерёнки)
5. Настройте параметры с помощью ползунков:
   - **Yaw Offset**: угол поворота головы (-180° до +180°)
   - **Delay Ticks**: задержка перед ударом (0-20 тиков, 1 тик ≈ 50мс)

### Как работает мод

1. Включите мод клавишей R
2. Атакуйте врага (ЛКМ)
3. Голова игрока автоматически повернётся на заданный угол
4. После задержки произойдёт удар
5. Голова вернётся в исходное положение
6. Враг получит отбрасывание в направлении, куда была повёрнута голова

## Возможные проблемы и решения

### Проблема 1: Файл .jar называется `KnockbackTrainer-1.0.0 (1).jar`

**Причина:** Вы скачали файл несколько раз, и браузер добавил `(1)` к имени.

**Решение:** Переименуйте файл, убрав ` (1)` из названия.

### Проблема 2: Мод не загружается в Minecraft

**Возможные причины:**
- Не установлен Fabric Loader
- Не установлен Fabric API
- Версия Minecraft не совпадает (должна быть 1.21.1)
- Java версия ниже 21

**Решение:**
1. Проверьте логи Minecraft на наличие ошибок
2. Убедитесь, что установлены все необходимые компоненты
3. Переустановите Fabric Loader через [официальный установщик](https://fabricmc.net/use/)

### Проблема 3: Сборка падает с ошибкой в Replit

**Причина:** Недостаточно памяти или долгая загрузка зависимостей.

**Решение:**
1. Добавьте в `gradle.properties`:
   ```properties
   org.gradle.jvmargs=-Xmx2G
   org.gradle.daemon=false
   ```
2. Повторите сборку командой `./gradlew build --no-daemon`
3. Если ошибка повторяется, попробуйте выполнить `./gradlew clean` перед сборкой

### Проблема 4: GUI настроек не открывается

**Причина:** Не установлен Mod Menu.

**Решение:** 
- Установите Mod Menu 11.0.1+ в папку mods
- Или настраивайте параметры напрямую в коде (`KnockbackConfig.java`)

### Проблема 5: Gradle не скачивается в Replit

**Решение:**
```bash
# Установите Gradle вручную
wget https://services.gradle.org/distributions/gradle-8.5-bin.zip
unzip gradle-8.5-bin.zip
export PATH=$PATH:./gradle-8.5/bin
gradle wrapper
```

## Изменение параметров по умолчанию

Откройте файл `src/main/java/dev/knockbacktrainer/config/KnockbackConfig.java`:

```java
private static float yawOffset = 45.0f;  // Измените угол
private static int delayTicks = 2;        // Измените задержку
```

После изменения пересоберите мод.

## Изменение клавиши активации

Откройте файл `src/main/java/dev/knockbacktrainer/ModKeybinds.java`:

```java
GLFW.GLFW_KEY_R,  // Замените R на другую клавишу
```

Список кодов клавиш: [GLFW Key Codes](https://www.glfw.org/docs/3.3/group__keys.html)

Примеры:
- `GLFW.GLFW_KEY_V` для клавиши V
- `GLFW.GLFW_KEY_G` для клавиши G
- `GLFW.GLFW_KEY_X` для клавиши X

## Лицензия

MIT License

## Поддержка

При возникновении проблем проверьте:
1. Версию Minecraft (должна быть 1.21.1)
2. Версию Java (должна быть 21+)
3. Наличие Fabric API в папке mods
4. Логи игры на наличие ошибок

## Техническая информация

- **Версия мода:** 1.0.0
- **Minecraft:** 1.21.1
- **Fabric Loader:** 0.16.8+
- **Fabric API:** 0.86.0+1.21.1
- **Mod Menu:** 11.0.1+ (опционально)
- **Java:** 21+

---

**Примечание:** Этот мод предназначен только для обучения механике отбрасывания в одиночной игре. Использование на серверах может нарушать правила сервера.
