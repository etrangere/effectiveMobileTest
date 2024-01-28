FROM mysql:8.0

# Expose port 3307 
#EXPOSE 3306

COPY init.sql /docker-entrypoint-initdb.d/init.sql
