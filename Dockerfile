# 基于 tomcatr
FROM ykenan/tomcatr:java17tomcat10R4
# 作者
MAINTAINER ykenan
# 定义变量
ENV DIR_WEBAPP /home/tomcat/tomcat/webapps/
ENV WEB_NAME scvdb_service
# 切换工作路径
WORKDIR $DIR_WEBAPP
# 添加本地的 war 包到远程容器中
ADD ./target/SCVdb-1.0.0.war $WEB_NAME.war
# 解压 war 包解压
RUN unzip $WEB_NAME.war -d $WEB_NAME
