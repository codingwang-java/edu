#### docker mysql
docker run --name mysqlserver -e MYSQL_ROOT_PASSWORD=root -dit --net=host -p 13306:3306 mysql:latest