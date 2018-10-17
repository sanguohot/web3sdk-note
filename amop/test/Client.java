package test;
	
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.Date;
	import java.util.Random;
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.support.ClassPathXmlApplicationContext;
	
	import org.bcos.channel.client.Service;
	import org.bcos.channel.dto.ChannelRequest;
	import org.bcos.channel.dto.ChannelResponse;
	
	public class Client {
		static Logger logger = LoggerFactory.getLogger(Client.class);
		
		public static void main(String[] args) throws Exception {
			if(args.length < 1) {
				System.out.println("参数: 目标topic");
				return;
			}
			
			String topic = args[0];
			
			DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext2.xml");
	
			Service service = context.getBean(Service.class);
			service.run();
			
			Thread.sleep(2000); //建立连接需要一点时间，如果立即发送消息会失败
	
			ChannelRequest request = new ChannelRequest();
			request.setToTopic(topic); //设置消息topic
			request.setMessageID(service.newSeq()); //消息序列号，唯一标识某条消息，可用newSeq()随机生成
			request.setTimeout(30000); //消息的超时时间
				
			request.setContent("request seq:" + request.getMessageID()); //发送的消息内容
				
			ChannelResponse response = service.sendChannelMessage2(request); //发送消息
				
			System.out.println(df.format(LocalDateTime.now()) + "收到回包 seq:" + String.valueOf(response.getMessageID()) + ", 错误码:" + response.getErrorCode() + ", 内容:" + response.getContent());
		}
	}
