package t.producer;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestProducer {
	@Autowired
	private RocketMQTemplate rocketMQTemplate;
	
	@Value("${rocketmq.test-topic}")
	private String topic;
	
	@Async
	public void t1() {
		try {
			for(int i = 0; i < 100; i++) {
				asyncSend(i+"");
				Thread.sleep(500);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void asyncSend(String msg) {
		Random random = new Random();
		
		String t = null;;
		if(random.nextInt() % 2 == 0) {
			t = topic + ":2";
		}else {
			t = topic + ":1";
		}
		rocketMQTemplate.asyncSend(t, MessageBuilder.withPayload(msg).build(), new SendCallback() {
			@Override
			public void onSuccess(SendResult arg0) {
				System.out.println(Thread.currentThread().getName() + " - 发送成功！");
			}
			@Override
			public void onException(Throwable arg0) {
				System.out.println("发送失败：" + arg0.getMessage());
			}
		});
	}
}
