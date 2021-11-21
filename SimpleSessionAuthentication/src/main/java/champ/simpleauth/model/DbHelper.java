package champ.simpleauth.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class DbHelper {
	
	
	private DataSource dataSource;
	
	
	public DbHelper(DataSource data) {
		dataSource = data;
	}
	
	public boolean insertNewUser(User user, String hashedPass) {
		Connection connection = null;
		PreparedStatement statement = null;
		boolean isSuccess = false;
		try {
			// get db connection
			connection = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into users "
					   + "(email, password, fullname) "
					   + "values (?, ?, ?)";
			
			statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, user.getEmail());
			statement.setString(2, hashedPass);
			statement.setString(3, user.getFullName());
			
			// execute sql insert
			statement.execute();
			isSuccess = true;
		}
		catch(Exception exc) {
			exc.printStackTrace();
			isSuccess = false;
		}
		finally {
			
			close(connection, statement, null);
		}
		
		return isSuccess;
		
	}
	
	public User getUser(String email, String password) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		User user = null;
		
		try {
			// get db connection
			connection = dataSource.getConnection();
			
			// create sql for insert
			String sql = "select * from users where email= ? ";
			
			statement = connection.prepareStatement(sql);
			
			
			statement.setString(1, email);
	
		
			result = statement.executeQuery();
	         while (result.next()) {
					

	            if(AuthHelper.verifyPassword(password,result.getString("password"))){ // verify if the hashed pass is the same
	            	user = new User(result.getInt("id"), result.getString("email"), result.getString("fullname"));
	            }
	         }
	
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
			
		}
		finally {
			
			close(connection, statement, result);
		}
		
		return user;
		
	}
	private void close(Connection connection, Statement statement, ResultSet result) {

		try {
			if (result != null) {
				result.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connection != null) {
				connection.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
