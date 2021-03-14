#### Ribbon的负载均衡策略

    由{@link com.netflix.loadbalancer.IRule}接口定义

#### 定义负载平衡器操作的接口--具体的负载均衡实现(负载均衡入口)

    {@linke com.netflix.loadbalancer.ILoadBalancer}
    1.获取服务
    2.选择服务
    3.标记已经宕机的服务
    4.可以正常访问的服务ILoadBalancer
    5.所有服务（可访问 + 不可访问）