# 前言：
本项目基于[hippo4j](https://github.com/mabaiwan/hippo4j)展开，通过该开源项目的源码阅读，让自己对动态线程池有更好的理解，同时为了方便大家能够更好理解动态线程池，自己也分享一下自己在学习并从0到1码该项目时的一些思考与笔记。在实际开发中线程池的使用是十分常见的，但是不正确的使用线程池也很容易导致OOM等其他异常情况，我们都知道，在使用线程池的时候，我们得注意其线程池的相关参数，但是这些参数的设置不是固定的，而是根据实际项目目前面对的流量已经请求的量级进行相应的调整的。不管业界关于对线程池参数的标准定义是如何，这也并不是适合每个项目的，我们得根据实际情况自己去定义。但是对于要修改线程池相关参数的时候，我们得嵌入项目代码中，然后进行参数更改，这样是十分麻烦的，此时此刻，就出现了动态线程池的概念。我们可以在控制端，比如web界面直接设置参数，然后后端线程池进行相应的改变。基于这样的背景，该项目就诞生了。

# 1. 项目架构
![1655369362098.png](https://cdn.nlark.com/yuque/0/2022/png/26709179/1655369367532-e45d1068-7e3e-4503-9804-53bd49f94f34.png#clientId=u5665175d-c309-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=351&id=u078d54ef&margin=%5Bobject%20Object%5D&name=1655369362098.png&originHeight=439&originWidth=708&originalType=binary&ratio=1&rotation=0&showTitle=false&size=14518&status=done&style=none&taskId=uf4106270-b607-447e-a737-536405ae619&title=&width=566.4)
# 2. 搭建教程
拉取项目下来，自行搭建即可。

- 运行ServerApplication程序，该程序端口号是6691，可在相应配置文件自行修改。
- 运行ExampleApplication程序。
- 控制台会打印类似如下的，即可。

![1655360909262.png](https://cdn.nlark.com/yuque/0/2022/png/26709179/1655360914939-3b1307b7-7ad4-48be-b4dd-2c86f1d336f6.png#clientId=u4c587e9c-fc5e-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=50&id=ua6dc3a2e&margin=%5Bobject%20Object%5D&name=1655360909262.png&originHeight=62&originWidth=593&originalType=binary&ratio=1&rotation=0&showTitle=false&size=7204&status=done&style=none&taskId=u076efcdc-8eb1-445c-83d4-bc93cecce77&title=&width=474.4)
上述表示接口调用成功了。
# 3. 监听原理
个人还写了一篇大致的源码解读，大家可以参考一下，链接为：[https://www.yuque.com/longyun-eqokj/tue21x/xu7ufw](https://www.yuque.com/longyun-eqokj/tue21x/xu7ufw)
如下为项目中监听机制的大概原理，读者可以根据源码进一步理解该流程。
![1655368721514.png](https://cdn.nlark.com/yuque/0/2022/png/26709179/1655368742777-c56079fc-61fc-4587-a874-a3aecf0fd898.png#clientId=u5665175d-c309-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=534&id=u7dc988bd&margin=%5Bobject%20Object%5D&name=1655368721514.png&originHeight=667&originWidth=1023&originalType=binary&ratio=1&rotation=0&showTitle=false&size=128086&status=done&style=none&taskId=uc4643045-27a7-4021-92e4-be8bf0d1aac&title=&width=818.4)

