-- 오라클 시퀀스 생성과 Mysql 시퀀스 생성은 다르다.
CREATE SEQUENCE subj_seq

CREATE TABLE Subject (
	subj_seq DECIMAL PRIMARY KEY,
	subj_name VARCHAR(30)
);

-- subj_seq, subj_name는 생략이 가능하지만 가능하면 써주고 안쓰면 VALUES에서 컬럼 수만큼 타입도 정확히 값을 넣어야한다.
INSERT INTO Subject(subj_seq, subj_name) 
VALUES(subj_seq.NEXTVAL, 'java');
INSERT INTO Subject(subj_seq, subj_name) 
VALUES(subj_seq.NEXTVAL, 'jsp');
INSERT INTO Subject(subj_seq, subj_name) 
VALUES(subj_seq.NEXTVAL, 'spring');
INSERT INTO Subject(subj_seq, subj_name) 
VALUES(subj_seq.NEXTVAL, 'sql');

SELECT * FROM Subject;