[![Build Status](https://travis-ci.org/tanxinzheng/spring-restful-demo.svg?branch=master)](https://travis-ci.org/tanxinzheng/spring-restful-demo)

# Spring REST Demo
Restful架构方式越来越主流，各种REST架构方式也越来越多，虽然可以架构REST的方式和语言越来越多，但是其最关键的是它的规范及定义。
之前在项目中也搭建过REST风格到架构，但是通用型和实用性并不符合界内的规范，
后来接触到了HATEOAS（Hypermedia as the Engine of Application State）这个概念后，才开始慢慢深入了解RES到真正含义。
此Demo基于HATEOAS设计理念结合Spring HATEOAS框架简单演示了HATEOAS的REST架构风格。

## 简介
REST 架构风格对于大多数开发人员来说都不陌生。
在 REST 架构中除了资源和 HTTP 方法之外，超媒体控制也是重要的一环。
HATEOAS (Hypermedia as the Engine of Application State) 是 REST 架构风格上的一种限制。
基于 HATEOAS 的 REST 服务可以允许服务端和客户端在最大程度上的解耦。
客户端和服务端并不需要严格的接口规范。
客户端可以通过服务端提供的超媒体表示来动态与服务端进行交互。
Spring HATEOAS 与 Spring 框架中已有的 Web 服务功能很好的整合在一起，可以创建满足 HATEOAS 要求的 REST 服务。
在开发 REST 服务时，推荐使用 Spring HATEOAS。

## REST 架构
REST 是 Representational state transfer 的缩写，翻译过来的意思是表达性状态转换。REST 是一种架构风格，它包含了一个分布式超文本系统中对于组件、连接器和数据的约束。REST 是作为互联网自身架构的抽象而出现的，其关键在于所定义的架构上的各种约束。只有满足这些约束，才能称之为符合 REST 架构风格。REST 的约束包括：
-   客户端-服务器结构。通过一个统一的接口来分开客户端和服务器，使得两者可以独立开发和演化。客户端的实现可以简化，而服务器可以更容易的满足可伸缩性的要求。
-   无状态。在不同的客户端请求之间，服务器并不保存客户端相关的上下文状态信息。任何客户端发出的每个请求都包含了服务器处理该请求所需的全部信息。
-   可缓存。客户端可以缓存服务器返回的响应结果。服务器可以定义响应结果的缓存设置。
-   分层的系统。在分层的系统中，可能有中间服务器来处理安全策略和缓存等相关问题，以提高系统的可伸缩性。客户端并不需要了解中间的这些层次的细节。
-   按需代码（可选）。服务器可以通过传输可执行代码的方式来扩展或自定义客户端的行为。这是一个可选的约束。
-   统一接口。该约束是 REST 服务的基础，是客户端和服务器之间的桥梁。该约束又包含下面 4 个子约束。
-   资源标识符。每个资源都有各自的标识符。客户端在请求时需要指定该标识符。在 REST 服务中，该标识符通常是 URI。客户端所获取的是资源的表达（representation），通常使用 XML 或 JSON 格式。
-   通过资源的表达来操纵资源。客户端根据所得到的资源的表达中包含的信息来了解如何操纵资源，比如对资源进行修改或删除。
-   自描述的消息。每条消息都包含足够的信息来描述如何处理该消息。
-   超媒体作为应用状态的引擎（HATEOAS）。客户端通过服务器提供的超媒体内容中动态提供的动作来进行状态转换。这也是本文所要介绍的内容。


## 示例

### 新增资源
请求头
··

··

### 自定义返回结果格式
在之前很多的API都会对返回结果进行封装如下图：
```
    {
      "data" : {
        "id" : 123,
        "name" : "John"
      }
    }
```
理由很简单：这样做可以很容易扩展返回结果，你可以加入一些分页信息，一些数据的元信息等－这对于那些不容易访问到返回头的API使用者来说确实有用，但是随着“标准”的发展（cors和http://tools.ietf.org/html/rfc5988#page-6都开始被加入到标准中了），我个人推荐不要那么做。

### 自动加载相关资源
很多复杂的API查询接口都会返回相对复杂的资源对象，此复杂的资源对象里面可能包含多个相关的子资源，
个人根据实际项目经验分为如下几种情况：在查询参数中添加embed（或expend），embed可以是一个逗号分隔的串，例如：
请求：
```
    GET /departments/12?embed=customer.name,assigned_user
```
响应：
```
    {
      "id" : 12,
      "subject" : "I have a question!",
      "summary" : "Hi, ....",
      "customer" : {
        "name" : "Bob"
      },
      assigned_user: {
       "id" : 42,
       "name" : "Jim",
      }
    }
```
* 注：若按照此功能对服务端的设计要求会比较高，但提高了API的可重用性

## 参考文献
- [使用 Spring HATEOAS 开发 REST 服务](http://www.ibm.com/developerworks/cn/java/j-lo-SpringHATEOAS/)
- [Restful API最佳实践及设计](http://segmentfault.com/a/1190000002949234#articleHeader3)
- [Restful API 设计最佳实践](http://blog.jobbole.com/41233/)