# systemhealthcheck
应用存活检查工具

## 工具产生的背景

* 在生产环境中，一般情况下，我们会把一个域名映射到多个IP上实现负载均衡，当我们对应用进行更新后，如何快速检测每个IP对应的应用是否正常启动？
传统方式下，我们通过修改host文件绑定IP的方式来实现DNS解析到指定的IP上。如果IP比较多，那么我们这么做将是非常低效。

* 该工具实现了程序动态DNS解析，所以，可以实现批量快速检测IP对应的应用是否存活。


## 使用方式

1. 在应用目录下新建一个 properties文件 例如：myapp.properties
2. 在文件增加以下配置
  >	url=http://myapp.com/check/   
    ip=10.187.104.156 172.22.22.235 10.191.172.19
  * url配置的是检测URL   
  * ip配置是所有要检测试IP节点

3. 启动java 程序 java -jar healthcheck-1.0-SNAPSHOT.jar myapp

