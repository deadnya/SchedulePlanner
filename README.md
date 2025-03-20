# Контрольная точка 1. Сервис управления расписаниями
## To run:
```bash
mvn clean build -DskipTests
docker compose up --build
```

## Структура бд

### Расписание
| Поле | Тип | Обязательность | Описание |
| ------ | ------ | ------ | ------ |
| id | VARCHAR(32) | + | Идентификатор. Заполняется при создании автоматически. UUID без разделителей |
| schedule_name | VARCHAR(255) | - | Название 
| creation_date | TIMESTAMPTZ | + | Дата создания. Заполняется автоматически при создании |
| update_date | TIMESTAMPTZ | + | Дата последнего изменения сущности |
### Шаблон расписания
| Поле | Тип | Обязательность | Описание |
| ------ | ------ | ------ | ------ |
| id | VARCHAR(32) | + | Идентификатор. Заполняется при создании автоматически. UUID без разделителей |
| creation_date | TIMESTAMPTZ | + | Дата создания. Заполняется автоматически при создании |
| template_type | VARCHAR(2) | + | Тип шаблона. Кодируется двума произвольными заглавными буквами |
### Слот расписания
| Поле | Тип | Обязательность | Описание |
| ------ | ------ | ------ | ------ |
| id | VARCHAR(32) | + | Идентификатор. Заполняется при создании автоматически. UUID без разделителей |
| schedule_template_id | VARCHAR(32) | + | Идентификатор шаблона расписания, в рамках которого существует слот |
| begin_time | TIMETZ | + | Время начала слота |
| end_time | TIMETZ | + | Время завершения слота |
### Период расписания
| Поле | Тип | Обязательность | Описание |
| ------ | ------ | ------ | ------ |
| id | VARCHAR(32) | + | Идентификатор. Заполняется при создании автоматически. UUID без разделителей |
| slot_id | VARCHAR(32) | + | Идентификатор слота, реализуемого сущностью периода |
| schedule_id | VARCHAR(32) | + | Идентификатор сущности расписания, в рамках которой существует слот |
| slot_type | VARCHAR(20) | + | Тип слота. Возможные значения: [LOCAL, FROM HOME, UNDEFINED]. Вместо null всегда заносить UNDEFINED |
| administrator_id | VARCHAR(32) | + | Идентификатор владельца слота. Заполняется из заголовка x-current-user при создании|
| executor_id | VARCHAR(32) | - | Идентификатор исполнителя слота. Не заполняется, если совпадает с администратором |
### Сотрудники
| Поле | Тип | Обязательность | Описание |
| ------ | ------ | ------ | ------ |
| id | VARCHAR(32) | + | Идентификатор. Заполняется при создании автоматически. UUID без разделителей |
| employee_name | VARCHAR(255) | + | Имя сотрудника |
| status | VARCHAR(20) | + | Статус сотрудника. Возможные значения: [WORKING, TRIAL, TIME_OFF, DISMISSED] |
| position | VARCHAR(20) | + | Позиция сотрудника. Возможные значения: [MANAGER, EMPLOYEE, UNDEFINED, TECH]. Вместо null всегда заносить UNDEFINED  |

## Задачи в работе
1) Для всех сущностей необходимы методы создания
2) Для всех сущностей необходимы методы получения по id
3) Для периодов должен быть метод получения запросов с фильтром и сортировкой

| Поле | Тип | Обязательность | Описание |
| ------ | ------ | ------ | ------ |
| schedulePeriodFilter | JSON | - | ------ |
| schedulePeriodFilter.id | VARCHAR(32) | - | Идентификатор |
| schedulePeriodFilter.slotId | VARCHAR(32) | - | Идентификатор слота |
| schedulePeriodFilter.scheduleId | VARCHAR(32) | - | Идентификатор сущности расписания, в рамках которой существует слот |
| schedulePeriodFilter.slotType | VARCHAR(20) | - | Тип слота. Возможные значения: [LOCAL, FROM HOME, UNDEFINED] |
| schedulePeriodFilter.administratorId | VARCHAR(32) | - | Идентификатор владельца слота|
| schedulePeriodFilter.executorId | VARCHAR(32) | - | Идентификатор исполнителя слота |
| sort | JSON | - | ------ |
| sort.field | ENUM | - | Поле, по которому осуществляется сортировка в формате lowerCamelCase|
| sort.direction | ENUM | - | Направление соритировки.[ASC, DESC] |
| page | INTEGER | - | Страница |
4) При создании периодов производить проверку на отсутствие перекрывающихся периодов

5) Получение полного расписания по идентификатору и по имени (обрабаиттывать когда указано хотя бы одно). Помимо полей расписания, в ответ должны входить связанные периоды, упорядоченные по времени начала слота