-- 서버가 실행될 때 .sql 파일이 수행되어 초기 데이터를 생성한다.

INSERT INTO user VALUES (101, sysdate(), 'Shawn', 'pass1', '950110-1111111');
INSERT INTO user VALUES (102, sysdate(), 'Jack', 'pass2', '950110-2111111');
INSERT INTO user VALUES (103, sysdate(), 'John', 'pass3', '930110-1111111');
