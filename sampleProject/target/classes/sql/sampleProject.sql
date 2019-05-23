--��� ���̺� ����
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
values ('test00','1234','ȫ�浿','test@test.com');
insert into tbl_member (user_id,user_pw,user_name,user_email)
values ('test01','1234','��浿','test@test.com');
insert into tbl_member (user_id,user_pw,user_name,user_email)
values ('test02','1234','�̱浿','test@test.com');

select * from tbl_member;

UPDATE tbl_member
        SET
            user_pw = '1234',
            user_name = '��ƹ���',
            user_email = 'ddd@ddd.co.kr',
            user_updatedate = SYSDATE
        WHERE
            user_id = 'test01';


-- �Խ��� ���̺� ����
drop table tbl_board;
delete tbl_board;
create table tbl_board(
bno number not null,              -- �Խù� ��ȣ
title varchar2(200) not null,     -- ����
content clob,                     -- ����
writer varchar2(50) not null,     -- �̸�
regdate date default sysdate,     -- �ۼ�����  
viewcnt number default 0,         -- ��ȸ��
primary key(bno)                  -- �⺻Ű ����
);

--��� ���̺� ����
-- ��� ���̺�
drop table tbl_reply;
create table tbl_reply (
    rno number not null primary key, -- ��� ��ȣ
    bno number default 0, -- board ���̺��� ��ȣ
    replytext varchar2(1000) not null,
    replyer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate
);
-- bno �ʵ忡 foreign key ����
-- add constraint ���������̸�
-- foreign key(�ʵ��) references ���̺�(�ʵ�)
alter table tbl_reply add constraint fk_board
foreign key(bno) references tbl_board(bno);
-- ������ ����(1���� ����, 1�� ����)
create sequence reply_seq
start with 1
increment by 1;
alter table tbl_reply add secretReply varchar2(1000);
select * from tbl_reply;

commit;