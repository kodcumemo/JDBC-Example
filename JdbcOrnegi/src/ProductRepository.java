import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.Query;
import com.mysql.cj.protocol.Resultset;

import model.Location;
import model.Product;

public class ProductRepository {

	public void save(Product p) {
		String q = """
				INSERT INTO product(name, vendor, price, date, location)
				VALUES(?,?,?,?,?)
				""";
		try {
			PreparedStatement pst = Baglanti.getInstance().prepareStatement(q);
			
			pst.setString(1, p.getName());
			pst.setString(2, p.getVendor());
			pst.setDouble(3, p.getPrice());
			pst.setString(4, p.getDate().toString());
			pst.setString(5, p.getLocation().toString());
			
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Product> findAll() throws SQLException{
		ArrayList<Product> list = new ArrayList<>();
		Statement st = Baglanti.getInstance().createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM product ORDER BY price DESC");
		while(rs.next())  {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String vendor = rs.getString("vendor");
			double price = rs.getDouble("price");
			LocalDate date = rs.getDate("date").toLocalDate();
			Location locaton = Location.valueOf( rs.getString("location"));
			Product p = new Product(id,name, vendor, price, date, locaton);
			list.add(p);
		}
		st.close();
		rs.close();
		return list;
	}
	public int deleteById(int id) throws SQLException {
		
		String sorgu = "DELETE FROM product WHERE id = ?";
		
		PreparedStatement pst = Baglanti.getInstance().prepareStatement(sorgu);
		
		pst.setInt(1, id);
		pst.execute();
		pst.close();
		return id;
	}
	public void update(Product p) throws SQLException {
		
		String sorgu = "UPDATE product SET "
				+ "name=?,"
				+ "vendor = ?,"
				+ "price = ?,"
				+ "date = ?,"
				+ "location = ?"
				+ "WHERE id = ?"
				;
		PreparedStatement pst = Baglanti.getInstance().prepareStatement(sorgu);
		
		pst.setString(1, p.getName());
		pst.setString(2, p.getVendor());
		pst.setDouble(3, p.getPrice());
		pst.setString(4, p.getDate().toString());
		pst.setString(5, p.getLocation().toString());
		
		pst.setInt(6,p.getId());
		pst.execute();
		pst.close();
		System.out.println("update çalışıyor"  + p.getId() + " "+ p.getName() + " "  );
	}

	public List<Product> findBySearch(String ara) throws SQLException {
		// TODO Auto-generated method stub
		String sorgu = "SELECT * FROM product WHERE name LIKE '%"+ara+"%' OR vendor LIKE '%"+ara+"%'";
		ArrayList<Product> list = new ArrayList<>();
		Statement st = Baglanti.getInstance().createStatement();
		
		ResultSet rs = st.executeQuery(sorgu);
		while(rs.next())  {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String vendor = rs.getString("vendor");
			double price = rs.getDouble("price");
			LocalDate date = rs.getDate("date").toLocalDate();
			Location locaton = Location.valueOf( rs.getString("location"));
			Product p = new Product(id,name, vendor, price, date, locaton);
			list.add(p);
		}
		st.close();
		rs.close();
		return list;
		
	}

	public List<Product> sortPrice() throws SQLException {
		// TODO Auto-generated method stub
		String sorgu = "SELECT * FROM product ORDER BY price desc";
		ArrayList<Product> list = new ArrayList<>();
		Statement st = Baglanti.getInstance().createStatement();
		
		ResultSet rs = st.executeQuery(sorgu);
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String vendor = rs.getString("vendor");
			Double price = rs.getDouble("price");
			LocalDate date = rs.getDate("date").toLocalDate();
			Location location = Location.valueOf(rs.getString("location"));
			Product product = new Product(id, name, vendor, price, date, location);
			
			list.add(product);
		}
		rs.close();
		st.close();
		return list;
	}

	public List<Product> sortShortPrice() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Product> list = new ArrayList<>();
		String sorgu = "SELECT * FROM product ORDER BY price asc";
		Statement st = Baglanti.getInstance().createStatement();
		ResultSet rs = st.executeQuery(sorgu);
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String vendor = rs.getString("vendor");
			double price = rs.getDouble("price");
			LocalDate date = rs.getDate("date").toLocalDate();
			Location location = Location.valueOf(rs.getString("location"));
			Product pr = new Product(id, name, vendor, price, date, location);
			list.add(pr);
		}
		rs.close();
		st.close();
		return list;
	}
	
	public List<Product> dateSortDesc() throws SQLException {
		ArrayList<Product> list = new ArrayList<>();
		String sorgu = "SELECT * FROM product ORDER BY date Desc";
		Statement st = Baglanti.getInstance().createStatement();
		ResultSet rs = st.executeQuery(sorgu);
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String vendor = rs.getString("vendor");
			double price = rs.getDouble("price");
			LocalDate date = rs.getDate("date").toLocalDate();
			Location location = Location.valueOf(rs.getString("location"));
			Product p = new Product(id, name, vendor, price, date, location);
			list.add(p);
		}
		rs.close();
		st.close();
		return list;
	}

	public List<Product> dateSortAsc() throws SQLException {
			ArrayList<Product> list = new ArrayList<>();
			String sorgu = "SELECT * FROM product ORDER BY date asc";
			Statement st = Baglanti.getInstance().createStatement();
			ResultSet rs = st.executeQuery(sorgu);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String vendor = rs.getString("vendor");
				double price = rs.getDouble("price");
				LocalDate date = rs.getDate("date").toLocalDate();
				Location location = Location.valueOf(rs.getString("location"));
				Product p = new Product(id, name, vendor, price, date, location);
				list.add(p);
			}
			rs.close();
			st.close();
			return list;
	}
}
