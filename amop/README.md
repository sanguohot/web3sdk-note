# web3sdk-note(amop)
## 准备工作
```
$ git clone https://github.com/sanguohot/web3sdk-note /opt/web3sdk-note
$ cd /opt/web3sdk-note/amop/test
```
## 开始编译:
```
$ javac -cp '/opt/web3sdk/dist/conf/:/opt/web3sdk/dist/apps/*:/opt/web3sdk/dist/lib/*:..' Server.java
$ javac -cp '/opt/web3sdk/dist/conf/:/opt/web3sdk/dist/apps/*:/opt/web3sdk/dist/lib/*:..' Client.java
$ ll
-rw-r--r--. 1 root root 2452 Oct 17 09:53 Client.class
-rw-r--r--. 1 root root 1766 Oct 12 19:25 Client.java
-rw-r--r--. 1 root root 1778 Oct 12 10:46 PushCallback.class
-rw-r--r--. 1 root root 1297 Oct 12 10:46 PushCallback.java
-rw-r--r--. 1 root root 1346 Oct 17 09:53 Server.class
-rw-r--r--. 1 root root 1024 Oct 11 14:54 Server.java
```
## 服务端监听主题（注意整个连接过程可能持续几十秒）:
```
$ java -cp '/opt/web3sdk/dist/conf/:/opt/web3sdk/dist/apps/*:/opt/web3sdk/dist/lib/*:..' test.Server hello
2018-10-17 10:22:00server:收到PUSH消息:request seq:3bf86c5268f84a339baf4834c28b984d
```
## 客户端往主题推送消息（注意整个连接过程可能持续几十秒）:
```
$ java -cp '/opt/web3sdk/dist/conf/:/opt/web3sdk/dist/apps/*:/opt/web3sdk/dist/lib/*:..' test.Client hello "my name is 007" 2
2018-10-17 10:22:00收到回包 seq:3bf86c5268f84a339baf4834c28b984d, 错误码:0, 内容:receive request seq:3bf86c5268f84a339baf4834c28b984d
```