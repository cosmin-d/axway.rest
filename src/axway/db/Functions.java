package axway.db;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import axway.rest.util.*;

public class Functions extends OracleDB {
//this function searches for a student and returns all courses he is participating at 
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
	
	//this function searches for a course name and returns all students enrolled at that respective course
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

//this function inserts a student into STUDENT table
public int insertStudent(String nume, String prenume) throws Exception {
	
	PreparedStatement query = null;
	Connection conn = null;
	
	try {
		//data validation needed 
		
		conn = databaseConnection();
		query = conn.prepareStatement("insert into STUDENT (ID, NUME, PRENUME) VALUES (STUDENT_SEQ.nextval, ?, ?)");
		
		query.setString(1, nume);
		query.setString(2, prenume);
		query.executeUpdate();
		
	} catch(Exception e) {
		e.printStackTrace();
		return 500; // something went wrong 
	}
	finally {
		if (conn != null) conn.close();
	}
	
	return 200; //success
}


public int insertCourse(String denumire) throws Exception {
	
	PreparedStatement query = null;
	Connection conn = null;
	
	try {
		//data validation needed 
		
		conn = databaseConnection();
		query = conn.prepareStatement("insert into CURS (ID, DENUMIRE) VALUES (CURS_SEQ.nextval, ?)");
		
		query.setString(1, denumire);
		query.executeUpdate();
		
	} catch(Exception e) {
		e.printStackTrace();
		return 500; // something went wrong 
	}
	finally {
		if (conn != null) conn.close();
	}
	
	return 200; //success
}


public int insertStudent_to_Course(String id_student, String id_curs) throws Exception {
	
	PreparedStatement query = null;
	Connection conn = null;
	
	try {
		//data validation needed 
		
		conn = databaseConnection();
		query = conn.prepareStatement("insert into STUDENT_CURS (ID_STUDENT, ID_CURS) VALUES (?, ?)");
		
		query.setString(1, id_student);
		query.setString(2, id_curs);
		query.executeUpdate();
		
	} catch(Exception e) {
		e.printStackTrace();
		return 500; // something went wrong 
	}
	finally {
		if (conn != null) conn.close();
	}
	
	return 200; //success
}


}
	
	
