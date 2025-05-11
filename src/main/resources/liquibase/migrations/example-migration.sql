INSERT INTO business_process_stage(id, name) values
(1, 'GENERATE_RANDOM_NUMBER'),
(2, 'PRINT_RANDOM_NUMBER');

INSERT INTO business_process_edge(from_stage_id, to_stage_id, result) values (1, 2, 'SUCCESS');
