
## Build Redis container

> Pull image

```shell
docker pull redis:6.2.11-alpine
docker pull redis-server:6.2.11-alpine
```

> Run container

```shell
docker run -d -it \
           -p 8053:6379 \
           --name scatacdb_redis redis:6.2.11-alpine redis-server --appendonly yes --requirepass "scatacdb123456"
```

```shell
redis-cli
auth scatacdb123456
keys *
redis-cli -a scatacdb123456 -n 4 flushdb
```
