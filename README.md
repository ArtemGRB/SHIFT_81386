Консольное Java-приложение для обработки входных файлов с автоматической классификацией данных и генерацией статистики.

## 🚀 Возможности

- 📂 Чтение данных из входных файлов
- 🔢 Автоматическая классификация на:
  - Целые числа
  - Вещественные числа
  - Строки
- 💾 Сохранение в отдельные файлы:
  - `integers.txt`
  - `floats.txt`
  - `strings.txt`
- 📊 Генерация статистики:
  - Краткая (количество элементов)
  - Полная (min/max, суммы, средние значения, длины строк)

## 🛠 Используемые технологии

- Java 11+
- Maven (опционально)
- JUnit 5 для тестирования

## 📁 Структура проекта
```bash
src/
   ├── org
   │   ├── box
   │   │   └── ServiceBox.java
   │   ├── config
   │   │   └── Config.java
   │   ├── enums
   │   │   └── TypeData.java
   │   ├── repository
   │   │   └── InMemoryRepository.java
   │   └── service
   │       ├── FileService.java
   │       └── StatService.java
   └── Main.java
⚙️ Как запустить
Скомпилируйте и запустите:

bash
javac -d out $(find ./src -name "*.java")
java -cp out org.Main -o output -s input.txt
Или используйте Maven:

bash
mvn clean package
java -jar target/app.jar -o output -s input.txt
📂 Выходные файлы
Данные сохраняются в указанную директорию (по умолчанию - текущая):

[prefix]integers.txt

[prefix]floats.txt

[prefix]strings.txt

💻 Аргументы командной строки
-o <path> - путь для выходных файлов

-p <prefix> - префикс для имен файлов

-s - краткая статистика

-f - полная статистика

-a - режим дополнения файлов

Пример:

bash
java -jar app.jar -o results -p mydata_ -f input1.txt input2.txt
🧑‍💻 Автор
Создатель: Гребнев Артем
GitHub: ArtemGRB
