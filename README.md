# spring-business-process
Последовательное исполнение бизнес-процессов.



### Начало работы

Импортируем библиотеку.

Добавляем необходимые переходы в базу данных с помощью миграций.
Например,

INSERT INTO business_process_stage(id, name) values
(1, 'DOWNLOAD_TABLES'),
(2, 'PARSING_TABLES'),
(3, 'COMPUTE_TABLE_DIFFS'),
(4, 'NOTIFY_ERROR');

Добавляем переходы между стадиями бизнес-процесса.

INSERT INTO business_process_edge(from, to, action) values
(1, 2, 'DOWNLOAD_SUCCESS'),
(2, 3, 'PARSE_SUCCESS'),
(1, 4, 'DOWNLOAD_ERROR'),
(2, 4, 'PARSE_ERROR');
