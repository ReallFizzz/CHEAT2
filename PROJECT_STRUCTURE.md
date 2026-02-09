# Структура проекта KnockbackTrainer

## Полная структура файлов

```
KnockbackTrainerFabric/
│
├── build.gradle                    # Конфигурация сборки Gradle
├── settings.gradle                 # Настройки Gradle проекта
├── gradle.properties               # Свойства JVM для Gradle
├── .replit                         # Конфигурация для Replit
├── LICENSE                         # MIT лицензия
├── README.md                       # Полная документация мода
├── BUILD_GUIDE.md                  # Краткое руководство по сборке
│
└── src/
    └── main/
        ├── java/
        │   └── dev/
        │       └── knockbacktrainer/
        │           │
        │           ├── KnockbackTrainer.java         # Главный класс мода
        │           ├── ModKeybinds.java              # Регистрация клавиш
        │           ├── AttackInterceptor.java        # Логика поворота и атаки
        │           │
        │           └── config/
        │               ├── KnockbackConfig.java      # Хранение настроек
        │               ├── KnockbackConfigScreen.java # GUI экран настроек
        │               └── ModMenuIntegration.java   # Интеграция с Mod Menu
        │
        └── resources/
            ├── fabric.mod.json                       # Метаданные мода
            │
            └── assets/
                └── knockbacktrainer/
                    └── lang/
                        ├── en_us.json                # Английская локализация
                        └── ru_ru.json                # Русская локализация
```

## Описание файлов

### Корневая директория

#### `build.gradle`
- **Назначение:** Конфигурация сборки проекта
- **Содержит:**
  - Зависимости (Minecraft, Fabric API, Mod Menu)
  - Настройки компиляции Java 21
  - Задачи сборки JAR файла

#### `settings.gradle`
- **Назначение:** Настройки Gradle проекта
- **Содержит:**
  - Имя проекта
  - Репозитории плагинов

#### `gradle.properties`
- **Назначение:** Оптимизация сборки для Replit
- **Содержит:**
  - Настройки памяти JVM (-Xmx2G)
  - Отключение Gradle daemon

#### `.replit`
- **Назначение:** Автоматизация в Replit
- **Содержит:**
  - Команду запуска сборки
  - Настройки языка

#### `LICENSE`
- **Назначение:** MIT лицензия

#### `README.md`
- **Назначение:** Полная документация
- **Содержит:**
  - Описание функциональности
  - Инструкции по сборке
  - Установка и использование
  - Решение проблем

#### `BUILD_GUIDE.md`
- **Назначение:** Краткое руководство по сборке
- **Содержит:**
  - Быстрые шаги сборки
  - Решение типичных ошибок

---

### Java код (src/main/java)

#### `dev.knockbacktrainer.KnockbackTrainer`
**Главный класс мода**

**Функции:**
- Инициализация мода
- Регистрация event listeners
- Управление состоянием включения/выключения
- Логирование событий

**Основные методы:**
- `onInitializeClient()` - точка входа мода
- `isEnabled()` - проверка активности
- `toggleEnabled()` - переключение состояния

---

#### `dev.knockbacktrainer.ModKeybinds`
**Регистрация и обработка клавиш**

**Функции:**
- Регистрация toggle-бинда (клавиша R)
- Обработка нажатий клавиш
- Предотвращение срабатывания при удержании

**Основные методы:**
- `register()` - регистрация кейбиндов
- `handleKeybinds()` - обработка нажатий

**По умолчанию:** Клавиша R для toggle

---

#### `dev.knockbacktrainer.AttackInterceptor`
**Логика поворота головы и атаки**

**Функции:**
- Перехват атаки игрока
- Поворот головы на Yaw Offset
- Задержка Delay Ticks
- Выполнение атаки
- Возврат головы в исходное положение

**Основные методы:**
- `tick(MinecraftClient)` - главный цикл обработки
- `checkForAttack()` - проверка атаки
- `startRotation()` - начало поворота
- `performAttack()` - выполнение удара
- `resetRotation()` - сброс позиции

**Состояния:**
- `isRotating` - флаг процесса ротации
- `ticksWaited` - счётчик тиков задержки
- `originalYaw` - исходный угол поворота
- `targetEntity` - цель атаки

---

### Конфигурация (config/)

#### `dev.knockbacktrainer.config.KnockbackConfig`
**Хранение настроек мода**

**Параметры:**
- `yawOffset` (float): угол поворота головы
  - Диапазон: -180° до +180°
  - По умолчанию: 45°
  
- `delayTicks` (int): задержка перед ударом
  - Диапазон: 0 до 20 тиков
  - По умолчанию: 2 тика (~100мс)

**Методы:**
- `getYawOffset()` / `setYawOffset(float)`
- `getDelayTicks()` / `setDelayTicks(int)`

---

#### `dev.knockbacktrainer.config.KnockbackConfigScreen`
**GUI экран настроек**

**Функции:**
- Отображение ползунков для настройки
- Показ текущих значений
- Кнопка "Done" для возврата

**Компоненты:**
- `YawSlider` - ползунок угла поворота
- `DelaySlider` - ползунок задержки
- Отображение значений в реальном времени

---

#### `dev.knockbacktrainer.config.ModMenuIntegration`
**Интеграция с Mod Menu**

**Функции:**
- Регистрация экрана конфигурации в Mod Menu
- Предоставление фабрики экрана настроек

**Требует:** Mod Menu 11.0.1+

---

### Ресурсы (src/main/resources)

#### `fabric.mod.json`
**Метаданные мода**

**Содержит:**
- ID мода: `knockbacktrainer`
- Версия: `1.0.0`
- Точки входа (entrypoints):
  - `client`: главный класс мода
  - `modmenu`: интеграция с Mod Menu
- Зависимости:
  - Fabric Loader ≥ 0.16.0
  - Minecraft ~1.21.1
  - Java ≥ 21
  - Fabric API *

---

#### `assets/knockbacktrainer/lang/en_us.json`
**Английская локализация**

**Переводы:**
- Название кейбинда: "Toggle Knockback Trainer"
- Категория: "Knockback Trainer"

---

#### `assets/knockbacktrainer/lang/ru_ru.json`
**Русская локализация**

**Переводы:**
- Название кейбинда: "Переключить Knockback Trainer"
- Категория: "Knockback Trainer"

---

## Зависимости между классами

```
KnockbackTrainer (главный)
    ├── ModKeybinds (регистрация клавиш)
    │   └── вызывает KnockbackTrainer.toggleEnabled()
    │
    ├── AttackInterceptor (логика атаки)
    │   └── использует KnockbackConfig
    │
    └── ClientTickEvents (Fabric API)
        └── вызывает tick() каждый кадр

KnockbackConfig (хранение настроек)
    └── используется AttackInterceptor и KnockbackConfigScreen

KnockbackConfigScreen (GUI)
    ├── использует KnockbackConfig
    └── регистрируется через ModMenuIntegration

ModMenuIntegration
    └── предоставляет KnockbackConfigScreen в Mod Menu
```

## Потоки выполнения

### Запуск мода:
```
1. Minecraft загружает Fabric Loader
2. Fabric Loader находит fabric.mod.json
3. Вызывается KnockbackTrainer.onInitializeClient()
4. Регистрируются ModKeybinds
5. Регистрируется AttackInterceptor в ClientTickEvents
```

### Процесс атаки:
```
1. Игрок нажимает ЛКМ по врагу
2. AttackInterceptor.checkForAttack() обнаруживает атаку
3. startRotation():
   - Сохраняется originalYaw
   - Применяется yawOffset
   - Устанавливается isRotating = true
4. tick() ждёт delayTicks
5. performAttack():
   - Выполняется атака на targetEntity
   - Игрок бьёт рукой
6. resetRotation():
   - Возвращается originalYaw
   - Сбрасываются флаги
```

### Изменение настроек через GUI:
```
1. Игрок открывает Mod Menu
2. Нажимает на настройки "Knockback Trainer"
3. ModMenuIntegration создаёт KnockbackConfigScreen
4. Игрок двигает ползунки:
   - YawSlider обновляет KnockbackConfig.yawOffset
   - DelaySlider обновляет KnockbackConfig.delayTicks
5. Нажатие "Done" закрывает экран
6. Настройки сохраняются в памяти (во время сессии)
```

## Технические детали

### Система координат Minecraft:
- **Yaw (горизонтальный угол):**
  - 0° = направление на юг
  - 90° = запад
  - 180° / -180° = север
  - -90° = восток

### Система тиков:
- 1 тик = 50 миллисекунд
- 20 тиков = 1 секунда
- delayTicks = 2 означает задержку ~100мс

### Механика кнокбека в Minecraft:
- Направление отбрасывания зависит от yaw атакующего
- Поворот головы на 45° изменяет направление на 45°
- Это позволяет контролировать траекторию полёта врага

## Расширение функциональности

### Добавление сохранения настроек:

Можно добавить сохранение в файл JSON:
```java
// В KnockbackConfig.java
public static void save() {
    // Сохранение в config/knockbacktrainer.json
}

public static void load() {
    // Загрузка из config/knockbacktrainer.json
}
```

### Добавление визуальной индикации:

Можно добавить HUD индикатор:
```java
// Новый класс HudRenderer
public class HudRenderer {
    public static void render(DrawContext context) {
        if (KnockbackTrainer.isEnabled()) {
            context.drawText(..., "Knockback Trainer: ON", ...);
        }
    }
}
```

### Добавление звуковых эффектов:

```java
// В AttackInterceptor.startRotation()
client.player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
```

## Совместимость

### Fabric API модули используются:
- `fabric-key-binding-api-v1` - для кейбиндов
- `fabric-lifecycle-events-v1` - для ClientTickEvents

### Совместимость с другими модами:
- ✅ Sodium, Iris (графика)
- ✅ Mod Menu (GUI)
- ⚠️ Может конфликтовать с другими модами управления атакой
- ⚠️ Может быть заблокирован античитами на серверах

## Тестирование

### Ручное тестирование:
1. Запустите Minecraft с модом
2. Создайте тестовый мир
3. Нажмите R для активации
4. Атакуйте моба
5. Наблюдайте направление отбрасывания

### Проверка настроек:
1. Откройте Mod Menu → Knockback Trainer → Config
2. Измените Yaw Offset на 90°
3. Атакуйте моба - должен отлететь вбок
4. Измените Delay Ticks на 10
5. Атакуйте - заметная задержка перед ударом

---

**Автор структуры:** Документация проекта KnockbackTrainer
**Дата:** 2024
**Версия мода:** 1.0.0
