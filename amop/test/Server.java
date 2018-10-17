package test;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.bcos.channel.client.Service;

public class Server {
	static Logger logger = LoggerFactory.getLogger(Server.class);
	
	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
			System.out.println("参数: 接收topic");
			return;
		}
		
		String topic = args[0];

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		
		//设置topic，支持多个topic
		List<String> topics = new ArrayList<String>();
		topics.add(topic);
		service.setTopics(topics);
		
		//处理消息的PushCallback类，参见Callback代码
		PushCallback cb = new PushCallback();
		service.setPushCallback(cb);
		
		//启动服务
		service.run();
	}
}
