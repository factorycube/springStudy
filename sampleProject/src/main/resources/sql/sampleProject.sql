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




commit;