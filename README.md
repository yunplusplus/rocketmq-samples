# rocketmq-samples

rocketmq事务消息demo

### Rockermq安装

下载地址：

https://mirrors.bfsu.edu.cn/apache/rocketmq/4.8.0/rocketmq-all-4.8.0-bin-release.zip

1.解压二进制包

```unzip rocketmq-all-4.8.0-bin-release.zip```

2.进入目录

```cd rocketmq-all-4.8.0-bin-release```

3.启动NameServer

` nohup ./bin/mqnamesrv &`

4.启动broker

 `nohup sh ./bin/mqbroker -n localhost:9876 -c ./conf/broker.conf &`

Notice:  

4.8.0版本启动如下错误，启动broker前创建文件夹

2021-03-21 00:33:14 **ERROR** DiskCheckScheduledThread1 - **Error** when measuring disk space usage, file doesn't exist on this path: /Users/icefox/store/commitlog

```mkdir -p ~/store/commitlog/```

broker配置文件内容

具体配置见：

[http://rocketmq.apache.org/docs/rmq-deployment/](http://rocketmq.apache.org/docs/rmq-deployment/)

```
brokerClusterName = DefaultCluster

brokerName = broker-a

brokerId = 0

deleteWhen = 04

fileReservedTime = 48

brokerRole = ASYNC_MASTER

flushDiskType = ASYNC_FLUSH

storePathCommitLog=$HOME/store/commitlog/

traceTopicEnable=true

namesrvAddr=
```

storePathCommitLog : 默认用户家目录 store/commitlog/ ,可配置

namesrvAddr：默认空

5.启动Rocketmq  Console

下载地址:

https://download.csdn.net/download/icefox_zhaoyt/16075419

控制台默认端口：8080

前台启动：

`java -jar rocketmq-console-ng-2.0.0.jar --rocketmq.config.namesrvAddr=127.0.0.1:9876 &`

后台启动：

` nohup java -jar rocketmq-console-ng-2.0.0.jar --rocketmq.config.namesrvAddr=127.0.0.1:9876 &`

启动后访问：

`http://127.0.0.1:8080/`

