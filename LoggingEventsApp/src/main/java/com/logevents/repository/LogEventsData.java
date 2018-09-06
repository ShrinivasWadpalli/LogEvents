package com.logevents.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LogEventsData {
	
	public void insertLogEvents(String eventId, String eventDuration) {

		ConnectionDetails connnectionDetails = new ConnectionDetails();
		Connection conn = connnectionDetails.getConnection();
		PreparedStatement preparedStatement = null;

		try {

			String insertQuery = "Insert into eventdetails(eventid,eventduration,alert) values (?,?,?)";
			preparedStatement = conn.prepareStatement(insertQuery);
			preparedStatement.setString(1, eventId);
			preparedStatement.setString(2, eventDuration);
			preparedStatement.setString(3, "true");
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (preparedStatement != null) {
					preparedStatement.close();
				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
