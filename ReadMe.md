ssh root@47.75.87.7

host     = localhost
user     = debian-sys-maint
password = AeGd1Q50t5PoECl4

./gradlew bootJar

sftp ftpname@47.75.87.7
ftp123456

put /Users/cenzen/Projects/spring-demo/api-demo/api-demo-0.0.1.jar /home/ftp/
java -jar /home/ftp/api-demo-0.0.1.jar &


lsof -i:8080
kill pid

mysql -u root -p
sql123456

show variables like 'character%';
vi /etc/mysql/my.cnf
character-set-server = utf8

