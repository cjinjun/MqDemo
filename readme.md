#采用最终一致性原理。
 @ 需要保证以下三要素:
 
### 确认生产者一定要将数据投递到MQ服务器中（采用MQ消息确认机制）
### MQ消费者消息能够正确消费消息，采用手动ACK模式（注意重试幂等性问题）
### 如何保证第一个事务先执行，采用补偿机制，在创建一个补单消费者进行监听，如果订单没有创建成功，进行补单。(如果第一个事务中出错，补单消费者会在重新执行一次第一个事务，例如第一个事务是添加订单表，如果失败在补单的时候重新生成订单记录，由于订单号唯一，所以不会重复)
### Queue参数解释
1. Message TTL(x-message-ttl)：设置队列中的所有消息的生存周期(统一为整个队列的所有消息设置生命周期), 也可以在发布消息的时候单独为某个消息指定剩余生存时间,单位毫秒, 类似于redis中的ttl，生存时间到了，消息会被从队里中删除，注意是消息被删除，而不是队列被删除， 特性Features=TTL, 单独为某条消息设置过期时间AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties().builder().expiration(“6000”); 
2. Auto Expire(x-expires): 当队列在指定的时间没有被访问(consume, basicGet, queueDeclare…)就会被删除,Features=Exp
3. Max Length(x-max-length): 限定队列的消息的最大值长度，超过指定长度将会把最早的几条删除掉， 类似于mongodb中的固定集合，例如保存最新的100条消息, Feature=Lim
4. Max Length Bytes(x-max-length-bytes): 限定队列最大占用的空间大小， 一般受限于内存、磁盘的大小, Features=Lim B
5. Dead letter exchange(x-dead-letter-exchange)： 当队列消息长度大于最大长度、或者过期的等，将从队列中删除的消息推送到指定的交换机中去而不是丢弃掉,Features=DLX
6. Dead letter routing key(x-dead-letter-routing-key)：将删除的消息推送到指定交换机的指定路由键的队列中去, Feature=DLK
7. Maximum priority(x-max-priority)：优先级队列，声明队列时先定义最大优先级值(定义最大值一般不要太大)，在发布消息的时候指定该消息的优先级， 优先级更高（数值更大的）的消息先被消费,
8. Lazy mode(x-queue-mode=lazy)： Lazy Queues: 先将消息保存到磁盘上，不放在内存中，当消费者开始消费的时候才加载到内存中
Master locator(x-queue-master-locator)
