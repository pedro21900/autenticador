--**********************************
--Ajuste do NLS_CHARACTERSET
--**********************************
connect sys/oracle as sysdba;
shutdown;
startup restrict;
Alter database character set INTERNAL_USE WE8ISO8859P1;
shutdown immediate;
startup;
connect system/oracle

--**********************************
--Tuning OracleXE
--**********************************
alter system set filesystemio_options=directio scope=spfile;
alter system set disk_asynch_io=false scope=spfile;

--**********************************
--Esquema springbootangulardxdatagridlab
--**********************************

create tablespace springbootangulardxdatagridlab datafile '/u01/app/oracle/oradata/XE/springbootangulardxdatagridlab01.dbf' size 100M online;
create tablespace idx_springbootangulardxdatagridlab datafile '/u01/app/oracle/oradata/XE/idx_springbootangulardxdatagridlab01.dbf' size 100M;
create user springbootangulardxdatagridlab identified by springbootangulardxdatagridlab default tablespace springbootangulardxdatagridlab temporary tablespace temp;
grant resource to springbootangulardxdatagridlab;
grant connect to springbootangulardxdatagridlab;
grant create view to springbootangulardxdatagridlab;
grant create procedure to springbootangulardxdatagridlab;
grant create materialized view to springbootangulardxdatagridlab;
alter user springbootangulardxdatagridlab default role connect, resource;
exit;
