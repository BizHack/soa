package org.manoochehr.itm566.message.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {

	private long id;
    private String profileName;
    private String firstName;
    private String lastName;
    private List<Link> links = new ArrayList<>();
    
    private Map<Long, Comment> comments= new HashMap<>();
    //private Date created;
    
    public Profile() {
    	
    }
    
	public Profile(long id, String profileName, String firstName, String lastName) {
		this.id = id;
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Map<Long, Comment> getComments(){
		return comments;
	}
	public void setComments(Map<Long, Comment> comments){
		this.comments=comments;
	}
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addLink(String url, String rel) {
			Link link = new Link();
			link.setLink(url);
			link.setRel(rel);
			links.add(link);
	}
		
		
		
	
	/*
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	*/
}
