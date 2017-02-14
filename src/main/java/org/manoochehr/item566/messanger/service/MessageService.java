package org.manoochehr.item566.messanger.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.manoochehr.itm566.message.model.Comment;
import org.manoochehr.itm566.message.model.Message;
import org.manoochehr.itm566.messanger.database.CommentDatabase;
import org.manoochehr.itm566.messanger.database.DatabaseClass;


public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	private Map<Long, Comment> comments= CommentDatabase.getComments();
	
	
	
	public MessageService() {
		comments.put(1L, new Comment(1,"Comment1", "Manoochehr Assa"));
		comments.put(2L, new Comment(2,"Comment2", "Manoochehr Assa"));
		
		messages.put(1L, new Message(1,comments ,"First Message", "Manoochehr Assa"));
		messages.put(2L, new Message(2,comments ,"How are u doing?", "Manoochehr Assa"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values()); 
	}
	
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	
	
}