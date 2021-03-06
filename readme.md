#### docker mysql
docker run --name mysqlserver -e MYSQL_ROOT_PASSWORD=root -dit --net=host -p 3306:3306 mysql:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci