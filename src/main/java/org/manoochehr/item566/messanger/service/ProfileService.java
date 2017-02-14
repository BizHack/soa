package org.manoochehr.item566.messanger.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.manoochehr.itm566.message.model.Profile;
import org.manoochehr.itm566.messanger.database.ProfileDataBase;


public class ProfileService {


	private Map<Long, Profile> profiles = ProfileDataBase.getAllProfiles();
	
	public ProfileService() {
//		profiles.put(1L, new Profile(1L,"m1234","Manoochehr","Assa"));
//		profiles.put(2L,new Profile(2L,"m_assa","Manoochehr","Assa"));
//		
//		

	}
	
	public List<Profile> getAllProfile(){
//		
//		Profile p1= new Profile(1L, "ASfdeew", "Manoo", "ASsa");
//		Profile p2= new Profile(2L, "Manoo123", "Manoochehr", "Assa");
//		List<Profile> profList=new ArrayList<>();
//		profList.add(p1);
//		profList.add(p2);
//		return profList;
		ProfileDataBase db= new ProfileDataBase();
		
		//profileUpdated=DatabaseClass.getAllProfiles();
		//return new ArrayList<Profile>(profileUpdated.values());
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfileByID(long id){
		Profile mes=profiles.get(id);
		//System.out.println(mes.getFirstName());
		return mes;
	}


	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		
		ProfileDataBase.addProfile(profile);
		profiles.put(profile.getId(), profile);
		return profile;
	}
	public Profile updateProfile(Profile profile){
		if (profile.getId()==0) {
			return null;
		}
		profiles.put(profile.getId(), profile);
		ProfileDataBase.updateProfile(profile);
		
		return profile;
	}
	public Profile removeProfile(long id){
		 ProfileDataBase.removeProfile(id);
		 return profiles.remove(id);
		 
		//return  profiles.put(id, new Profile(id,"","",""));
	}

}

