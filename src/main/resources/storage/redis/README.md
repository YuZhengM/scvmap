
## Build Redis container

> Pull image

```shell
docker pull redis:6.2.11-alpine
docker pull redis-server:6.2.11-alpine
```

> Run container

```shell
docker run -d -it \
           -p ${redis_port}:6379 \
           --name scatacdb_redis redis:6.2.11-alpine redis-server --appendonly yes --requirepass "${redis_password}"
```

```shell
redis-cli
auth ${redis_password}
keys *
redis-cli -a ${redis_password} -n ${redis_database} flushdb
```
