import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Singleton Design Pattern / Tasarım Örüntüsü
public class Baglanti {

	private static Baglanti baglanti;
	private Connection connection ;
	
	
	private Baglanti() {
	System.out.println("baglanti consturcturu");	
	try {
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sales",
				"root",
				"1234"
				);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public static Connection getInstance() {
		if(baglanti == null) {
			baglanti = new Baglanti();
			System.out.println("baglanti oluşturuldu");
		}
		return baglanti.connection;
	}
	public static void close() throws SQLException {
		if(baglanti != null) {
			baglanti.connection.close();
			System.out.println("database kapatıldı");
		}else {
			System.out.println("Database kapatılırken bir hata oluştu");
		}
	}
}
