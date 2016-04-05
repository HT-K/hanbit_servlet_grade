CREATE SEQUENCE score_seq 
INCREMENT BY 1
START WITH 1;
------------------------------------
CREATE TABLE Grade(
	 score_seq NUMBER PRIMARY KEY,
	 id VARCHAR2(30) NOT NULL,
	 java NUMBER NOT NULL,
	 sql NUMBER NOT NULL,
	 jsp NUMBER NOT NULL,
	 spring NUMBER NOT NULL
);
------------------------------------
<!-- 시퀀스명.NEXTVAL을 하면 시퀀스로 학번을 생성한다. -->
INSERT INTO Grade(score_seq,id,java,sql,jsp,spring)
VALUES (score_seq.NEXTVAL,'hong',90,90,90,90); 
INSERT INTO Grade(score_seq,id,java,sql,jsp,spring)
VALUES (score_seq.NEXTVAL,'kim',85,80,70,65);
INSERT INTO Grade(score_seq,id,java,sql,jsp,spring)
VALUES (score_seq.NEXTVAL,'lee',15,25,30,45);
INSERT INTO Grade(score_seq,id,java,sql,jsp,spring)
VALUES (score_seq.NEXTVAL,'song',95,100,60,50);
INSERT INTO Grade(score_seq,id,java,sql,jsp,spring)
VALUES (score_seq.NEXTVAL,'yoon',70,85,90,100);
INSERT INTO Grade(score_seq,id,java,sql,jsp,spring)
VALUES (score_seq.NEXTVAL,'kim2',35,25,15,65);
---------------------------------
SELECT * FROM Grade;
---------------------------
DROP TABLE Grade CASCADE CONSTRAINT;
--------------------
DROP SEQUENCE score_seq;
-------------------
<!-- 가상 테이블인 뷰를 만들어  이름으로 검색할 수 있게 한다. -->
CREATE VIEW v_Grade_Member
AS SELECT g.id '아이디', m.name '이름', g.hak '학번'
FROM Grade g, Member m
WHERE g.id = m.id;
----------------------------
ALTER TABLE Grade RENAME hak TO examSeq;
-------------------------------