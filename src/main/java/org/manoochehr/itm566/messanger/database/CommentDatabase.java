package org.manoochehr.itm566.messanger.database;

import java.util.HashMap;
import java.util.Map;

import org.manoochehr.itm566.message.model.Comment;
import org.manoochehr.itm566.message.model.Message;

public class CommentDatabase {
	private static Map<Long, Comment> comments = new HashMap<>();
	//private static Map<String, Profile> profiles = new HashMap<>();

	
	public static Map<Long, Comment> getComments() {
		return comments;
	}
}
