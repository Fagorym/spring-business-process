INSERT INTO business_process_stage(id, name) values
(1, 'GENERATE_RANDOM_NUMBER'),
(2, 'PRINT_RANDOM_NUMBER');

Добавляем переходы между стадиями бизнес-процесса.

INSERT INTO business_process_edge(from, to, action) values (1, 2, 'SUCCESS');
