#### Eureka自我保护机制 -- 作用

    避免因网络分区故障导致服务不可用，服务集体剔除问题

#### Eureka server 缓存机制

    具体实现： {@link com.netflix.eureka.registry.ResponseCacheImpl}

##### 说明

    1.Eureka Server 为了提供响应效率，提供了两层的缓存结构，将 Eureka Client 所需要的注册信息，直接存储在缓存结构中。
    2.第一层缓存：readOnlyCacheMap，本质上是 ConcurrentHashMap，依赖定时从 readWriteCacheMap 同步数据，默认时间为 30 秒。
    3.第二层缓存：readWriteCacheMap，本质上是 Guava 缓存。

#### readOnlyCacheMap -- CurrentHashMap 只读缓存，

    主要是为了供客户端获取注册信息时使用，其缓存更新，依赖于定时器的更新，通过和 readWriteCacheMap 的值做对比，如果数据不一致，则以 readWriteCacheMap 的数据为准。

#### readWriteCacheMap -- 同步于存储层，一级缓存与存储层的中间层

    readWriteCacheMap 的数据主要同步于存储层。当获取缓存时判断缓存中是否没有数据，如果不存在此数据，则通过 CacheLoader 的 load 方法去加载，加载成功之后将数据放入缓存，同时返回数据。
    1.readWriteCacheMap 缓存过期时间，默认为 180 秒，当服务下线、过期、注册、状态变更，都会来清除此缓存中的数据。
    2.Eureka Client 获取全量或者增量的数据时，会先从一级缓存中获取；如果一级缓存中不存在，再从二级缓存中获取；如果二级缓存也不存在，这时候先将存储层的数据同步到缓存中，再从缓存中获取。

#### #### Eureka client 缓存机制

    具体实现：{@link com.netflix.discovery.DiscoveryClient#initScheduledTasks} 会启动周期性任务，每隔30秒从Eureka server获取服务列表信息
    1.Eureka Client 启动时会全量拉取服务列表，启动后每隔 30 秒从 Eureka Server 增量获取服务列表信息，并保持在本地缓存中。
    2.Eureka Client 增量拉取失败，或者增量拉取之后对比 hashcode 发现不一致，就会执行全量拉取，这样避免了网络某时段分片带来的问题，同样会更新到本地缓存。
    3.同时对于服务调用，如果涉及到 ribbon 负载均衡，那么 ribbon 对于这个实例列表也有自己的缓存，这个缓存定时(默认30秒)从 Eureka Client 的缓存更新。