package axway.db;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import axway.rest.util.*;

public class Functions extends OracleDB {

	public JSONArray queryByStudent (String nume_student, String prenume_student) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = databaseConnection();
			query = conn.prepareStatement("select ID from STUDENT where UPPER(NUME) = ?" +
			"AND UPPER(PRENUME) = ?");
			query.setString(1,nume_student.toUpperCase());
			query.setString(2,prenume_student.toUpperCase());
			ResultSet rs = query.executeQuery();
			
			PreparedStatement query1 = null;	
			if(rs.next())
			query1 = conn.prepareStatement("select ID_CURS from STUDENT_CURS where ID_STUDENT = " +
			rs.getString(1));
			ResultSet rs1 = query1.executeQuery(); // can have multiple entries 
			
			PreparedStatement query2 = null;
			String statement = "";
			if(rs1.next())
		    statement = "select DENUMIRE from CURS where ID = "+rs1.getString(1);
			while(rs1.next()) {
				statement = statement + "UNION select DENUMIRE from CURS where ID = "+rs1.getString(1);
			}
			
			query2 = conn.prepareStatement(statement);
			ResultSet rs2 = query2.executeQuery();
			json = converter.toJSONArray(rs2);
			query.close(); //close connection
			}
		
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	
}
	
	
public JSONArray queryByCourse (String denumire) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = databaseConnection();
			query = conn.prepareStatement("select ID from CURS where UPPER(DENUMIRE) = ?");
			query.setString(1,denumire.toUpperCase());
			ResultSet rs = query.executeQuery();
			
			PreparedStatement query1 = null;
			
			if(rs.next())
			query1 = conn.prepareStatement("select ID_STUDENT from STUDENT_CURS where ID_CURS = " +
			rs.getString(1));
			ResultSet rs1 = query1.executeQuery(); //can have multiple entries
			
			PreparedStatement query2 = null;
			String statement = "";
			if(rs1.next())
		    statement = "select NUME, PRENUME from STUDENT where ID = "+rs1.getString(1);
			while(rs1.next()) {
				statement = statement + "UNION select NUME, PRENUME from STUDENT where ID = "+rs1.getString(1);
			}
			
			query2 = conn.prepareStatement(statement);
			ResultSet rs2 = query2.executeQuery();
			json = converter.toJSONArray(rs2);
			query.close(); //close connection
			}
		
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	
}
}
	
	
