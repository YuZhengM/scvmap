# Docker builds MySQL container

> Pull MySQL image

```shell
docker pull mysql:8.0.32
```

> Establish shared files

```shell
touch ${project_path}/mysql/my.cnf
```

> Add content

```shell
vim ${project_path}/mysql/my.cnf
```

```shell
# For advice on how to change settings please see
# http://dev.mysql.com/doc/refman/8.0/en/server-configuration-defaults.html

[mysqld]
#
# Remove leading # and set to the amount of RAM for the most important data
# cache in MySQL. Start at 70% of total RAM for dedicated server, else 10%.
# innodb_buffer_pool_size = 128M
#
# Remove leading # to turn on a very important data integrity option: logging
# changes to the binary log between backups.
# log_bin
#
# Remove leading # to set options mainly useful for reporting servers.
# The server defaults are faster for transactions and fast SELECTs.
# Adjust sizes as needed, experiment to find the optimal values.
# join_buffer_size = 128M
# sort_buffer_size = 2M
# read_rnd_buffer_size = 2M

# Remove leading # to revert to previous value for default_authentication_plugin,
# this will increase compatibility with older clients. For background, see:
# https://dev.mysql.com/doc/refman/8.0/en/server-system-variables.html#sysvar_default_authentication_plugin
# default-authentication-plugin=mysql_native_password
skip-host-cache
skip-name-resolve
datadir=/var/lib/mysql
socket=/var/run/mysqld/mysqld.sock
secure-file-priv=/var/lib/mysql-files
user=root

pid-file=/var/run/mysqld/mysqld.pid
[client]
socket=/var/run/mysqld/mysqld.sock

!includedir /etc/mysql/conf.d/
```

> Run MySQL container

```shell
docker run -d -it \
           -p ${mysql_port}:3306 \
           -v ${project_path}/mysql/my.cnf:/etc/mysql/my.cnf:rw \
           -v ${project_path}/mysql/data:/var/lib/mysql \
           -v ${project_path}/mysql/mysqlfile:/root \
           -e MYSQL_ROOT_PASSWORD=${mysql_password} \
           --privileged --name=scvdb_mysql mysql:8.0.32
```

> Here are some container operations
> [https://blog.csdn.net/YKenan/article/details/91396670](https://blog.csdn.net/YKenan/article/details/91396670)

> Common commands

```shell
docker logs scvdb_mysql -f --tail 50;
docker start scvdb_mysql;
docker stop scvdb_mysql;
docker rm scvdb_mysql;
docker restart scvdb_mysql;
```

> Entering the container

```shell
docker exec -it scvdb_mysql bash
```

> log on

```shell
mysql -uroot -p
${mysql_password}
```

> Enable remote connection permission

```mysql
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
CREATE USER 'root'@'0.0.0.0' IDENTIFIED BY '${mysql_password}';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'0.0.0.0' WITH GRANT OPTION;
FLUSH PRIVILEGES;
use mysql;
select host, user from user;
```
