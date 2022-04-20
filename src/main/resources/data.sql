-- 서버가 실행될 때 .sql 파일이 수행되어 초기 데이터를 생성한다.

INSERT INTO user VALUES (1, sysdate(), 'Shawn', 'pass1', '950110-1111111');
INSERT INTO user VALUES (2, sysdate(), 'Jack', 'pass2', '950110-2111111');
INSERT INTO user VALUES (3, sysdate(), 'John', 'pass3', '930110-1111111');
