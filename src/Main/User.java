package Main;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class User {
	
	private Integer id;
	private String name;
	public User(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public static Vector<User> getAllUser() {
		// siapin vector untuk kita dapetin list of usernya
		Vector<User> users = new Vector<>();
		//ambil connectnya
		Connect connect = Connect.getConnection();
		// siapin query
		String query = "SELECT * FROM users";
		// eksekusi
		ResultSet rs = connect.executeQuery(query);
		try {
			while(rs.next()) {
		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
		
	
	//read user
	//delete user
	public static void delete(Integer id) {
		//siapin query
		//execute
		Connect connect = Connect.getConnection();
		String query = "DELETE FROM users WHERE id = ?";
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setInt(1, id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//update user 
	public static void update(Integer id, String name) {
		// siapin query
		// execute
		Connect connect = Connect.getConnection();
		String query = "UPDATE users SET name = ? WHERE id = ?";
		// tanda tanya pertama -> name
		// tanda tanya kedua -> id
		PreparedStatement ps = connect.prepareStatement(query);
		try {
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//insert user
	
	//MVC -> model view controller
	// dimana semua yang berkaitan dengan database itu berada pada modelnya

}
