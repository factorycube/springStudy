--멤버 테이블 생성
drop table tbl_member;
create table tbl_member(
    user_id varchar2(50) primary key,
    user_pw varchar2(50) not null,
    user_name varchar2(50) not null,
    user_email varchar2(50),
    user_regdate date default sysdate,
    user_updatedate date
);
--메시지 송수신을 위한 포인트 칼럼 추가
alter table tbl_member add user_point number default 0;
select * from tbl_member;

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

--게시글 삭제 상태 유무 칼럼 추가
alter table tbl_board add show char(1) default 'y';

--댓글 테이블 생성
-- 댓글 테이블
drop table tbl_reply;
create table tbl_reply (
    rno number not null primary key, -- 댓글 번호
    bno number default 0, -- board 테이블의 번호
    replytext varchar2(1000) not null,
    replyer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate
);
-- bno 필드에 foreign key 설정
-- add constraint 제약조건이름
-- foreign key(필드명) references 테이블(필드)
alter table tbl_reply add constraint fk_board
foreign key(bno) references tbl_board(bno);
-- 시퀀스 생성(1부터 시작, 1씩 증가)
create sequence reply_seq
start with 1
increment by 1;
alter table tbl_reply add secret_reply char(1) default 'n';
alter table tbl_reply drop COLUMN secretreply;
alter table tbl_reply modify (secret_reply char(1) default 'n');
select * from tbl_reply;

commit;


--메시지 저장 테이블 생성
create table tbl_message(
    mid number not null,
    targetid varchar2(50) not null,
    sender varchar2(50) not null,
    message varchar2(4000) not null,
    opendate date,
    senddate date default sysdate,
    primary key(mid)
);
--메시지 시퀀스 생성
create sequence message_seq
    start with 1
    increment by 1;
-- 제약조건(FK설정)
alter table tbl_message add constraint fk_usersender
foreign key (sender) references tbl_member(user_id);
alter table tbl_message add constraint fk_usertarget
foreign key (targetid) references tbl_member(user_id);
select * from tbl_message;