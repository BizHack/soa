package org.manoochehr.itm566.messanger.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.manoochehr.itm566.message.model.Comment;
import org.manoochehr.itm566.message.model.Profile;

import com.mysql.jdbc.PreparedStatement;


public class ProfileDataBase {
private static Map<Long, Profile> profiles= new HashMap<>();


private static Map<Long, Comment> comments= new HashMap<>();

private static Connection connect=null;

private static Statement statement=null;

private static ResultSet resultset=null;

private static String url="jdbc:mysql://localhost:3306/profileservicedb";


	public static Map<Long, Profile> getAllProfiles(){
/*		
		profiles.put(1L, new Profile(1L, "manoo123","Manoochehr","Assa"));
		profiles.put(2L, new Profile(2L, "Sia123","Siavash","Assa"));
		return profiles;
		
	}
	
}*/
		
		
		
		try {
		//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Good");
			connect=(Connection)DriverManager.getConnection(url,"root","");
			statement=(Statement)connect.createStatement();
			System.out.println("good");
			
			resultset=statement.executeQuery("select profile_ID,profile_NAME,firstName,lastName from profile");
			
			
			System.out.println("Final Good");
			
			
			long i=1L;
			while (resultset.next()) {
				profiles.put(i, new Profile(resultset.getInt(1), resultset.getString(2), resultset.getString(3), resultset.getString(4)));
				i++;
			}


			
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e);
			System.out.println("Error from getAllProfiles method: Databaseclass");
			
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("This is the error");
			e.printStackTrace();
		}
		
		try {
			connect.close();
			connect=null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: "+ e);
		}
		
		
		return profiles;
		
			/*
			connect=(Connection)DriverManager.getConnection(url);
			statement=(Statement)connect.createStatement();
			System.out.println("good");
		
			
			
			resultset=statement.executeQuery("select profile_NAME,firstName,lastName from profile");
			
			long i=1L;
			while (resultset.next()) {
				profiles.put(i, new Profile(i, resultset.getString(1), resultset.getString(2), resultset.getString(3)));
				i++;
				
				
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e);
			System.out.println("Error from getAllProfiles method: Databaseclass");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("This is the error");
			e.printStackTrace();
		}
		
		try {
			connect.close();
			connect=null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: "+ e);
		}
		*/
		
	//	return profiles;
		
		
		
		/*
		profiles.put(1L, new Profile(1L, "manoo123","Manoochehr","Assa"));
		profiles.put(2L, new Profile(2L, "Sia123","Siavash","Assa"));
		return profiles;
		
		
		*/
	
	}
	
	public static void updateProfile(Profile p) {

		// Get a connection
		try {
			connect = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
		System.out.println("Good-Update");
		// Query to insert a record to the bank table
		String query = "UPDATE profile SET profile_NAME ='"+p.getProfileName()+"' ,firstName='"+p.getFirstName()+"' , lastName='"+p.getLastName()+"' WHERE profile_ID="+p.getId()+";";
		
		
		
		// Use prepared statements to avoid SQL injection attacks
		try (PreparedStatement statement = (PreparedStatement) connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

			statement.executeUpdate();

		} catch (SQLException e) {
			
			System.out.println("Error in Updating: " + e);
			
		}
		// Close the connection to the database - Very important!!!
		try {
			connect.close();
			connect = null;
		} catch (SQLException e) {
			System.out.println("Error closing connection: " + e);
			
		}

	}
	

	public static void addProfile(Profile p){
		// Get a connection
				try {
					connect = DriverManager.getConnection(url, "root", "");
				} catch (SQLException e) {
					System.out.println("Error creating connection to database: " + e);
					System.exit(-1);
				}
				// Query to insert a record to the bank table
				String query = "INSERT INTO profile (profile_NAME, firstName, lastName, profile_ID) VALUES (?,?,?,?) ;";
				// Use prepared statements to avoid SQL injection attacks
				try (PreparedStatement statement = (PreparedStatement) connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
					
					statement.setString(1, p.getProfileName());
					statement.setString(2, p.getFirstName());
					statement.setString(3, p.getLastName());
					statement.setLong(4, p.getId());
				
					statement.executeUpdate();
				

				} catch (SQLException e) {
					p = null;
					System.out.println("Error Creating Profile: " + e);
					
				}
				// Close the connection to the database
				try {
					connect.close();
					connect = null;
				} catch (SQLException e) {
					System.out.println("Error closing connection: " + e);
				
				}

	}

	public static void removeProfile(long id) {
		// Get a connection
				try {
					connect = DriverManager.getConnection(url, "root", "");
				} catch (SQLException e) {
					System.out.println("Error creating connection to database: " + e);
					System.exit(-1);
				}
			
				String query = "DELETE FROM profile where profile_ID="+ id+ ";";
				// Use prepared statements to avoid SQL injection attacks
				try (PreparedStatement statement = (PreparedStatement) connect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
					

					statement.executeUpdate();
					

				} catch (SQLException e) {
					
					
				}
				// Close the connection to the database 
				try {
					connect.close();
					connect = null;
				} catch (SQLException e) {
					
				}


	}
//
//	public static Map<Long, Comment> getAllComments(){
//		comments.put(1L, new Comment(1L,"Comment1","Manoochehr Assa"));
//		comments.put(2L, new Comment(2L,"Comment2","Masoud Assa"));
//		return comments;
//	}
	
}
