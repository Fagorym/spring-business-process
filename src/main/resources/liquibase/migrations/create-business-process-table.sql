CREATE TABLE business_process_stage(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
);


CREATE TABLE business_process(
    id BIGSERIAL PRIMARY KEY,
    business_process_stage_id BIGINT NOT NULL REFERENCES business_process_stage(id)
);

CREATE TABLE business_process_payload(
    id BIGSERIAL PRIMARY KEY,
    business_process_stage_id BIGINT NOT NULL REFERENCES business_process_stage(id),
    payload TEXT NOT NULL
);

CREATE TABLE business_process_edge(
    id BIGSERIAL PRIMARY KEY,
    from_stage_id BIGINT NOT NULL REFERENCES business_process_stage(id),
    to_stage_id BIGINT NOT NULL REFERENCES business_process_stage(id),
    result TEXT NOT NULL,
);
