package t.consumer;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "rocketmq_test", topic = "${rocketmq.test-topic}", consumeThreadMax = 6)
public class TestConsumer implements RocketMQListener<MessageExt> {
	public TestConsumer() {
		System.out.println("start TestConsumer...");
	}
	
	@Override
	public void onMessage(MessageExt messageExt) {
		System.out.println(Thread.currentThread().getName() + " - 收到  - tag : " + messageExt.getTags() + "; body : "+ new String(messageExt.getBody()));
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
