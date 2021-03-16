create table if not exists task
(
    id uuid not null
        constraint task_pkey
            primary key,
    completed_at timestamp with time zone,
    created_at timestamp with time zone default CURRENT_DATE not null,
    is_completed boolean default false not null,
    name varchar(255) not null,
    notes varchar(500)
);

alter table task owner to postgres;

