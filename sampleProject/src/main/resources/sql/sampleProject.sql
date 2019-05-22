drop table tbl_member;
create table tbl_member(
    user_id varchar2(50) primary key,
    user_pw varchar2(50) not null,
    user_name varchar2(50) not null,
    user_email varchar2(50),
    user_regdate date default sysdate,
    user_updatedate date
);

insert into tbl_member (user_id,user_pw,user_name,user_email)
values ('test00','1234','홍길동','test@test.com');
insert into tbl_member (user_id,user_pw,user_name,user_email)
values ('test01','1234','김길동','test@test.com');
insert into tbl_member (user_id,user_pw,user_name,user_email)
values ('test02','1234','이길동','test@test.com');

select * from tbl_member;

UPDATE tbl_member
        SET
            user_pw = '1234',
            user_name = '김아무개',
            user_email = 'ddd@ddd.co.kr',
            user_updatedate = SYSDATE
        WHERE
            user_id = 'test01';


-- 게시판 테이블 생성
drop table tbl_board;
delete tbl_board;
create table tbl_board(
bno number not null,              -- 게시물 번호
title varchar2(200) not null,     -- 제목
content clob,                     -- 내용
writer varchar2(50) not null,     -- 이름
regdate date default sysdate,     -- 작성일자  
viewcnt number default 0,         -- 조회수
primary key(bno)                  -- 기본키 설정
);




commit;