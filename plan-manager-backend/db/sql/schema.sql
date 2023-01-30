create type role as ENUM ('ADMIN', 'USER');
create table public.user (
    id bigint not null,
    email varchar(256) unique not null,
    password varchar(128) not null,
    name varchar(32) not null,
    role_type role,
    PRIMARY KEY(id)
);
create table public.plan (
    id bigint not null,
    user_id bigint not null,
    title varchar(128) not null,
    description varchar(1024),
    prefecture  varchar(128) not null,
    start_date timestamp NOT NULL,
    end_date timestamp NOT NULL,
    status varchar(32) not null,
    PRIMARY KEY(id),
    foreign key (user_id) references public.user(id)
);

insert into public.user
(
     id,
     email,
     password,
     name,
     role_type
) values
    (
        1,
        'admin@test.com',
        '$2a$12$/mnPlMDXwZKdiHFNjG78MOsjT8oTMOx8FyG4jZddUXCFHnfh1Ix9K',
        '管理者',
        'ADMIN'
    ),
    (
        2,
        'user@test.com',
        '$2a$12$8b2CuMHMzkC94UTwwJE/g.ThR2gzlu1HKXrWRRJi8UNk7XyuRc.kK',
        'ユーザー',
        'USER'
    );
insert into public.plan
(
    id,
    user_id,
    title,
    description,
    prefecture,
    start_date,
    end_date,
    status
)
values
(1, 2, 'お客さんとゴルフ', 'お客さんとゴルフの接待をしに行く', '東京都', '2023-01-22T12:00:00.000', '2023-01-22T16:00:00.000', '未処理'),
(2, 2, 'お客さんとゴルフ', 'お客さんとゴルフの接待をしに行く', '埼玉県', '2023-01-30T12:00:00.000', '2023-01-30T16:00:00.000', '未処理'),
(3, 2, 'お客さんとゴルフ', 'お客さんとゴルフの接待をしに行く', '神奈川県', '2023-02-05T12:00:00.000', '2023-02-05T16:00:00.000', '未処理'),
(4, 2, 'お客さんとゴルフ', 'お客さんとゴルフの接待をしに行く', '神奈川県', '2023-02-09T12:00:00.000', '2023-02-09T16:00:00.000', '未処理');
