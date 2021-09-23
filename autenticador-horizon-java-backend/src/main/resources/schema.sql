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

create tablespace horizon datafile '/u01/app/oracle/oradata/XE/horizon01.dbf' size 100M online;
create tablespace idx_horizon  datafile '/u01/app/oracle/oradata/XE/idx_horizon01.dbf' size 100M;
create user horizon  identified by h0r1z0n  default tablespace horizon  temporary tablespace temp;
grant resource to horizon ;
grant connect to horizon ;
grant create view to horizon ;
grant create procedure to horizon ;
grant create materialized view to horizon ;
alter user horizon default role connect, resource;

exit;
