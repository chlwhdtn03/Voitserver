package com.chlwhdtn.voit;

import java.util.HashMap;

public class Debate {

	public String id;
	public String title;
	public HashMap<String, String> messages;
	
	public int ticket_agree = 0;
	public int ticket_disagree = 0;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public HashMap<String, String> getMessages() {
		return messages;
	}
	public void setMessages(HashMap<String, String> messages) {
		this.messages = messages;
	}
	public int getTicket_agree() {
		return ticket_agree;
	}
	public void setTicket_agree(int ticket_agree) {
		this.ticket_agree = ticket_agree;
	}
	public int getTicket_disagree() {
		return ticket_disagree;
	}
	public void setTicket_disagree(int ticket_disagree) {
		this.ticket_disagree = ticket_disagree;
	}
	
	
	
}
