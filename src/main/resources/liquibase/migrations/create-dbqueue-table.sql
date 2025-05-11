CREATE TABLE queue_tasks
(
    id                BIGSERIAL PRIMARY KEY,
    queue_name        TEXT NOT NULL,
    payload           TEXT,
    created_at        TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    next_process_at   TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    attempt           INTEGER                  DEFAULT 0,
    reenqueue_attempt INTEGER                  DEFAULT 0,
    total_attempt     INTEGER                  DEFAULT 0
);

CREATE INDEX queue_tasks_name_time_desc_idx
    ON queue_tasks USING btree (queue_name, next_process_at, id DESC);
