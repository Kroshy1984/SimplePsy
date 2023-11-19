package ru.sfedu.simplepsy.database;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

class DataProviderMeeting{

	public static void createMeetingTable(String username, String password){
	
		try(Connection connect = PsyDB.getConnection(username, password)){
			String query = """
			DROP TABLE IF EXISTS meetings ;

			CREATE TABLE meetings (
				id integer CONSTRAINT primary_key PRIMARY KEY,
				lastMeetingDateTime timestamp,
	    			nextMeetingDateTime timestamp,
	    			meetingFormat varchar(128),
	    			clientRequest text,
	    			therapistState varchar(256),
	    			topicsDiscussed text,
	    			clientInsights text,
	    			clientEmotions text,
	    			therapistEmotions text,
	    			therapistUnexpressedEmotions text,
	    			techniquesAndMethods text,
	    			obstaclesAndResistance text,
	    			therapistStateAfterSession varchar(256),
	    			planForNextSession text,
	    			difficultiesAndSupervisionTopics text,
	    			postponed boolean,
	    			countertransference boolean
			)""";

			try{
				Statement statement = connect.createStatement();
				statement.executeQuery(query);
			} catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
