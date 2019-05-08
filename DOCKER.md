#### mysql
1. docker pull mysql:5.7
2. docker run -p 3306:3306 --name mysql -v /mydata/mysql/log:/var/log/mysql -v /mydata/mysql/data:/var/lib/mysql -v /mydata/mysql/conf:/etc/mysql -e MYSQL_ROOT_PASSWORD=root -d mysql:5.7
3. docker exec -it mysql mysql -uroot -proot
4. grant all privileges on *.* to "root"@"%" identified by "root";
5. flush privileges;

#### redis
1. docker pull redis:3.2
2. docker run -p 6379:6379 --name redis -v /mydata/redis/data:/data -d redis:3.2 redis-server --appendonly yes
3. docker exec -it redis redis-cli

#### nginx
1. docker pull nginx:1.10
2. docker run -p 80:80 --name nginx -v /mydata/nginx/html:/usr/share/nginx/html -v /mydata/nginx/logs:/var/log/nginx -d nginx:1.10
3. docker container cp nginx:/etc/nginx .
4. mv nginx/ conf
5. docker stop nginx
6. docker rm $CONTAINER ID
7. docker run -p 80:80 --name nginx -v /mydata/nginx/html:/usr/share/nginx/html -v /mydata/nginx/logs:/var/log/nginx -v /mydata/nginx/conf:/etc/nginx -d nginx:1.10
    * conf文件夹要复制到/mydata/nginx下，否则容器启动失败

#### rabbit
1. docker pull rabbitmq:management
2. docker run -d --name rabbitmq --publish 5671:5671 --publish 5672:5672 --publish 4369:4369 --publish 25672:25672 --publish 15671:15671 --publish 15672:15672 rabbitmq:management
3. http://ip:15672/#/ 
    * guest/guest

#### mongodb
1. docker pull mongo:3.2
2. docker run -p 27017:27017 --name mongo -v /mydata/mongo/db:/data/db -d mongo:3.2
3. docker exec -it mongo mongo


````
SpringBoot应用命令部署

docker容器间进行连接才能互相访问
部署mall-admin

docker run -p 8080:8080 --name mall-admin
--link mysql:db
-v /etc/timezone:/etc/timezone
-v /etc/localtime:/etc/localtime
-v /mydata/app/admin/logs:/var/logs
-d mall/mall-admin:1.0-SNAPSHOT
部署mall-search

docker run -p 8081:8081 --name mall-search
--link elasticsearch:es
--link mysql:db
-v /etc/timezone:/etc/timezone
-v /etc/localtime:/etc/localtime
-v /mydata/app/search/logs:/var/logs
-d mall/mall-search:1.0-SNAPSHOT
部署mall-port

docker run -p 8085:8085 --name mall-portal
--link mysql:db
--link redis:redis
--link mongo:mongo
-v /etc/timezone:/etc/timezone
-v /etc/localtime:/etc/localtime
-v /mydata/app/portal/logs:/var/logs
-d mall/mall-portal:1.0-SNAPSHOT
SpringBoot应用自动化部署
部署文件

document/docker/docker-compose.yml
部署命令

docker-compose up -d


elasticsearch安装
下载镜像文件

docker pull elasticsearch:6.4.0
创建实例并运行

docker run -p 9200:9200 -p 9300:9300 --name elasticsearch
-v /mydata/elasticsearch/plugins:/usr/share/elasticsearch/plugins
-v /mydata/elasticsearch/data:/usr/share/elasticsearch/data
-d elasticsearch:6.4.0
测试

访问会返回版本信息：http://192.168.1.66:9200/
安装目录位置

/usr/share/elasticsearch
安装head插件(可以不安装，仅用于测试)

    进入docker内部bash:docker exec -it elasticsearch /bin/bash
    安装插件，具体参考：https://github.com/mobz/elasticsearch-head
    测试：http://192.168.1.66:9200/_plugin/head/

安装中文分词器IKAnalyzer

    进入docker内部bash:docker exec -it elasticsearch /bin/bash
    安装中文分词插件，执行以下命令：elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v6.2.2/elasticsearch-analysis-ik-6.2.2.zip
    测试：
        访问header插件：打开地址http://192.168.1.66:9200/_plugin/head/
        选择复合查询，输入地址：POST:http://192.168.1.66:9200/_analyze
        输入参数：JSON:{"analyzer":"ik","text":"我们是大数据开发人员"}

````

[参照某兄github](https://github.com/macrozheng/mall/blob/master/document/docker/docker-deploy.md)