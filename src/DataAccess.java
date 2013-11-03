import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DataAccess {
	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	public static void connectToDatabase() {
		if(connect != null)
			return;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/CN?user=shas&password=shas");
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String getUserIPByID(String userID) {
		connectToDatabase();
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select ip from IPS where uid = \"" + userID + "\";");
			String IP = "";
			while(resultSet.next())
				IP = resultSet.getString("ip");
			close();
			return IP;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return "Not found!";
	}
	public static String getCompleteFilePathByIDandFileName(String userID, String fileName) {
		connectToDatabase();
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select filepath from FILES where uid = \"" + userID 
					+ "\" and filename = \"" + fileName + "\";");
			String filePath = "";
			while(resultSet.next())
				filePath = resultSet.getString("filepath");
			close();
			return filePath;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return "Not found!";
	}
	public static String registerUser(String userID, String IP) {
		connectToDatabase();
		try {
			statement = connect.createStatement();
			preparedStatement = connect.prepareStatement("insert into IPS values (?, ?);");
			preparedStatement.setString(1, userID);
			preparedStatement.setString(2, IP);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return "Error";
	}
	public static String addFile(String userID, String fileName, String filePath) {
		connectToDatabase();
		try {
			statement = connect.createStatement();
			preparedStatement = connect.prepareStatement("insert into FILES values (?, ?, ?);");
			preparedStatement.setString(1, userID);
			preparedStatement.setString(2, fileName);
			preparedStatement.setString(3, filePath);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return "Error";
	}
	public static ArrayList<String> getUsersWhoHave(String fileName){
		connectToDatabase();
		try {
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select filepath from FILES where filename = \"" 
					+ fileName + "\";");
			ArrayList<String> res = new ArrayList<String>();
			while(resultSet.next())
				res.add(resultSet.getString("filepath"));
			close();
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	private static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		registerUser("aryaz", "127.0.0.1");
	}
}
