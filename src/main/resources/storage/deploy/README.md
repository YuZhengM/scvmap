# Deploy

> Docker IDEA plugin deployment

> Execute `Dockerfile` file, which can be directly run using IDEA plugin (.run/java.run.xml)

> After executing the IDEA plugin, the following command does not need to be executed.

```bash
# Build image
docker build -t scvdb .
# Generate Container
docker run -d -it --privileged -p ${host_port}:${container_port} \
       -v ${project_path}/data/data:${project_path}/data/data \
       -v ${project_path}/data/code/service:/home/tomcat/tomcat/webapps \
       --name=scvdb scvdb
# redeployment
docker stop scvdb; docker rm scvdb; docker rmi scvdb;
```

> Clear Cache

```shell
docker exec -it scatacdb_redis sh
```

```shell
redis-cli -n 4 -a ${redis_password} FLUSHDB
```

### The content added to the host `nginx.conf`

```shell
location /scvdb {
    proxy_pass http://localhost:${front_end_port}/scvdb;
    index  index.html index.htm index.jsp;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header REMOTE-HOST $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header Host $host:$server_port;
}

location /scvdb_static {
    proxy_pass http://localhost:${front_end_port}/static;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header REMOTE-HOST $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header Host $host:$server_port;
}

location /scvdb_service/ {
    proxy_pass http://localhost:${back_end_port}/scvdb_service/;
    index  index.html index.htm index.jsp;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header REMOTE-HOST $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header Host $host:$server_port;
}

```
