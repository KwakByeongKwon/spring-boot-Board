-- create table "USER" (
--                         "NUM" number primary key,
--                         "TITLE" varchar2(50) not null,
--                         "WRITER" varchar2(50) not null,
--                         "CONTENT" varchar2(1000),
--                         "CNT" number default 0
-- );


create table "BOARD" (
                         "NUM" number primary key,
                         "TITLE" varchar2(50) not null,
                         "WRITER" varchar2(50) not null,
                         "CONTENT" varchar2(1000),
                         "REGDATE" date,
                         "CNT" number default 0
);

create sequence "BOARD_SEQ"
    start with 1
    increment by 1
    maxvalue 99999
    nocache
    nocycle
    noorder;

// 삽입
INSERT INTO "BOARD" ("NUM","TITLE","WRITER","CONTENT","REGDATE","CNT") VALUES ("BOARD_SEQ".nextval,?,?,?,sysdate,0);

// 조회(하나만)
SELECT * FROM "BOARD" * WHERE "NUM" = ?;
// 조회(모두)
SELECT * FROM "BOARD";

// 수정
UPDATE "BOARD" set "Title"=?,"CONTENT"=? WHERE "NUM"=?;

//삭제
DELETE FROM "BOARD" WHERE "NUM"=?