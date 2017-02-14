package org.manoochehr.itm566.message;


import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
//import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.manoochehr.item566.messanger.service.ProfileService;
import org.manoochehr.itm566.message.model.Message;
import org.manoochehr.itm566.message.model.Profile;


/**
 * Root resource (exposed at "profiles" path)
 */
@Path("profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.APPLICATION_JSON)

public class ProfileResource{

   

	ProfileService ps= new ProfileService();
	
	
	    @GET
	//    @Consumes(MediaType.APPLICATION_JSON)
	  //  @Produces(MediaType.APPLICATION_JSON)
	    
	//   @Produces(MediaType.APPLICATION_XML)
//	    public String getIt() {
//	        return "Got it, the second version!";
//	    }
	    
	    
	    public List<Profile> getProfiles(){
	    	return ps.getAllProfile();
	    }
	 /**
	  * 
	  * This is for finding profiles by ID
	  */
	    /* 
	    @GET
	    @Path("/{profileId}")
	    public Profile getProfileByID(@PathParam("profileId") long id){
	    	Profile profile= ps.getProfileByID(id);
	    	System.out.println(profile.getFirstName());
	    	List<Profile> pList= ps.getAllProfile();
	    	for (int i = 0; i < pList.size(); i++) {
				if (pList.get(i).getId()==id) {
					System.out.println(true);
					System.out.println(i);
				}
			}
	    	return profile;
	    }
	    */
	  
	    /**
	     * 
	     * This is for finding profiles by profile name
	     */
	    @GET
	    @Path("/{profileName}")
	    public Profile getProfileByName(@PathParam("profileName") String profileName, @Context UriInfo uriInfo){
	    	List<Profile> pList= ps.getAllProfile();
	    	for (int i = 0; i < pList.size(); i++) {
				if (pList.get(i).getProfileName().equals(profileName)) {
					System.out.println(true);
					System.out.println(i);
					
					pList.get(i).addLink(getUriForSelf(uriInfo, pList.get(i)), "self");
					pList.get(i).addLink(getUriForMessages(uriInfo, pList.get(i)), "Message");
					
					return pList.get(i);
					
				}
			}
	    	return null;
	    }
	    
	    /*private String getUriForMessages(UriInfo uriInfo, Profile profile) {
	    	URI uri = uriInfo.getBaseUriBuilder() 
		}*/

	    
	    private String getUriForMessages(UriInfo uriInfo, Profile profile){
	    	String uri=uriInfo.getBaseUriBuilder()
	    			.path(MessageResource.class)
	    			.path(Long.toString(profile.getId()))
	    			.build()
	    			.toString();
	    	return uri;
	    }
		
		private String getUriForSelf(UriInfo uriInfo, Profile profile) {
			URI uri=uriInfo.getBaseUriBuilder()
					.path(ProfileResource.class)
					.path((profile.getProfileName()))
					.build();
			return uri.toString();
					
					
					
		}
	   
	    
	    @POST
	    public Profile addProfile(Profile profile){
	    	Profile newProfile=ps.addProfile(profile);
	    	return newProfile;
	    	
	    }
	    @PUT
	    @Path("/{profileId}")
	    public Profile updateProfile(@PathParam("profileId") long id, Profile profile){
	    	profile.setId(id);
	    	return ps.updateProfile(profile);
	    }
	    
	    @DELETE
	    @Path("/{profileId}")
	    public Profile removeProfile(@PathParam("profileId") long id){
	     return ps.removeProfile(id);
	    }    
    
	
}










