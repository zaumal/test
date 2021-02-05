package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestDxDomain {
	private String messageId = UUID.randomUUID().toString();
	private List<TestDxMessage> messageList = new ArrayList<>();
	private List<String> destinationAddress;
	private String senderAddress;
	private String conversationId = UUID.randomUUID().toString();
	private String contributionId = UUID.randomUUID().toString();
	private List<ServiceCapability> serviceCapability = Arrays.asList(new ServiceCapability());
	private String trafficType;
	private boolean smsSupported = false;
	private String imFormat;
	private String inReplyTo;
//	sent：消息已发送
//	failed：消息发送失败
//	delivered：消息已送达
//	displayed：消息已阅读
//	deliveredToNetwork：已转短消息已送达
	private List<String> reportRequest = Arrays.asList("sent","delivered","displayed","failed");
	private boolean storeSupported = true;
	private String smsContent;
	
	public TestDxDomain(String senderAddress,String contentType,String contentText,String... destinationAddress) {
		TestDxMessage message = new TestDxMessage();
		messageList.add(message);
		message.setContentType(contentType);
		message.setContentText(contentText);
		this.senderAddress = senderAddress;
		this.destinationAddress = Arrays.asList(destinationAddress);
	}
	
	
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public List<TestDxMessage> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<TestDxMessage> messageList) {
		this.messageList = messageList;
	}
	public List<String> getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(List<String> destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getConversationId() {
		return conversationId;
	}
	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}
	public String getContributionId() {
		return contributionId;
	}
	public void setContributionId(String contributionId) {
		this.contributionId = contributionId;
	}
	public List<ServiceCapability> getServiceCapability() {
		return serviceCapability;
	}
	public void setServiceCapability(List<ServiceCapability> serviceCapability) {
		this.serviceCapability = serviceCapability;
	}
	public String getTrafficType() {
		return trafficType;
	}
	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}
	public boolean isSmsSupported() {
		return smsSupported;
	}
	public void setSmsSupported(boolean smsSupported) {
		this.smsSupported = smsSupported;
	}
	public String getImFormat() {
		return imFormat;
	}
	public void setImFormat(String imFormat) {
		this.imFormat = imFormat;
	}
	public String getInReplyTo() {
		return inReplyTo;
	}
	public void setInReplyTo(String inReplyTo) {
		this.inReplyTo = inReplyTo;
	}
	public List<String> getReportRequest() {
		return reportRequest;
	}
	public void setReportRequest(List<String> reportRequest) {
		this.reportRequest = reportRequest;
	}
	public boolean isStoreSupported() {
		return storeSupported;
	}
	public void setStoreSupported(boolean storeSupported) {
		this.storeSupported = storeSupported;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
}

class ServiceCapability{
	private String capabilityId = "ChatbotSA";
	private String version = "+g.gsma.rcs.botversion=\"#=1\"";
	
	public String getCapabilityId() {
		return capabilityId;
	}
	public void setCapabilityId(String capabilityId) {
		this.capabilityId = capabilityId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
