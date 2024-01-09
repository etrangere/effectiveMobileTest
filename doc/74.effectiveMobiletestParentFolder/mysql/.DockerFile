# Dockerfile for MySQL
FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=74.effectiveMobileTest
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=Smdilia2012@

EXPOSE 3306

COPY init.sql /docker-entrypoint-initdb.d/
