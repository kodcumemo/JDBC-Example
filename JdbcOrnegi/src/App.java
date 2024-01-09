import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Location;
import model.Product;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ProductRepository repository = new ProductRepository();
		
		Scanner sc = new Scanner(System.in);
		int sec = -1;
		System.out.println("Vektörel telefona hoş geldiniz.");
		while (true) {
			System.out.println("Lütfen seçiminizi yapınız");
			System.out.println("0 => Çıkış");
			System.out.println("1 => Ürünleri Göster");
			System.out.println("2 => Ürün Ekle");
			System.out.println("3 => Ürün Sil");
			System.out.println("4 => Ürün Güncelle");
			System.out.println("5 => Ara");
			System.out.println("6 => Sırala");
			sec = sc.nextInt();
			if(sec == 0) {
				System.out.println("Çıkış Yapıldı");
				break;
			}else if(sec == 1) {
				System.out.println("Ürünler");
				try {
					printList(repository.findAll());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Ürünler gösterilirken bir hata oldu");
					e.printStackTrace();
				}
			}else if(sec == 2) {
				sc.nextLine();
				System.out.println("isim yazın:");
				String name = sc.nextLine(); 
				System.out.println("Satıcı girin:");
				String vendor = sc.nextLine();
				System.out.println("Fiyat girin:");
				Double price = sc.nextDouble(); sc.nextLine();
				System.out.println("(yyyy.mm.dd) Üretim tarihi girin;");
				String date = sc.nextLine();
				System.out.println("Üretim yerini girin:");
				String location = sc.nextLine();
				System.out.println("Ürün Eklendi");
				LocalDate pDate = LocalDate.parse(date);
				Location pLoc = Location.valueOf(location.toUpperCase());// yyyy-MM-dd
				Product p = new Product( name, vendor, price, pDate, pLoc);
				repository.save(p);
				System.out.println(name + " " + vendor + " " + price + " " + pDate + " " + pLoc);
			}else if(sec == 3) {
				try {
				printList(repository.findAll());
				System.out.println("Silmek istediğiniiz ürünün idsini giriniz:");
				int id = sc.nextInt();
				
					repository.deleteById(id);
					System.out.println("Ürün silindi");
					printList(repository.findAll());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else	if(sec == 4) {
				
				try {
				printList(repository.findAll());
				System.out.print("Lütfen bir ürünün id numarasını seçin: ");
				int id = sc.nextInt();
				
				sc.nextLine();
				System.out.println("Yeni isim yazın:");
				String name = sc.nextLine(); 
				System.out.println("Yeni Satıcı girin:");
				String vendor = sc.nextLine();
				System.out.println("Yeni Fiyat girin:");
				Double price = sc.nextDouble(); sc.nextLine();
				System.out.println("(yyyy.mm.dd) Yeni Üretim tarihi girin;");
				String date = sc.nextLine();
				System.out.println("Yeni Üretim yerini girin:");
				String location = sc.nextLine();
				System.out.println("Yeni Ürün Eklendi");
				LocalDate pDate = LocalDate.parse(date);
				Location pLoc = Location.valueOf(location.toUpperCase());// yyyy-MM-dd
				Product p = new Product(id , name, vendor, price, pDate, pLoc);

				repository.update(p);
					
				printList(repository.findAll());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Ürün Güncellenirken Bir Hata Oluştu");
				}
				
			} else if(sec == 5) {
				System.out.println("Arama yapmak için ürün adı yada model giriniz");
				
				try {
					sc.nextLine();
					String ara = sc.nextLine();
					printSearch(repository.findBySearch(ara));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (sec == 6) {
				while(true) {
				System.out.println("Sıralama seçiniz");
				System.out.println("0 -> Geri");
				System.out.println("1 -> Fiyat Büyükten küçüğe");
				System.out.println("2 -> Fiyat Küçükten Büyüğe");
				System.out.println("3 -> Tarih Büyükten küçüğe");
				System.out.println("4 -> Tarih Küçükten Büyüğe");
				sec  = sc.nextInt();
				if(sec == 0 ) {
					System.out.println("Bir üst menüye dönüldü.");
					break;
				} else if(sec == 1) {
					System.out.println("Fiyata göre büyükten küçüğe");
					try {
						System.out.println(repository.sortPrice());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Veriler çekilirken bir hata oluştu");
					}
				} else if (sec == 2) {
					System.out.println("Fiyata göre küçükten büyüğe");
					try {
						System.out.println(repository.sortShortPrice());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(sec == 2) {
					System.out.println("Tarihe göre en yeni");
					try {
						System.out.println(repository.dateSortDesc());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if(sec == 3) {
					System.out.println("Tarihe göre en eski");
					try {
						System.out.println(repository.dateSortAsc());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
				
			}
			
		}
		
	}

	
	private static void printSearch(List<Product> findById) {
		// TODO Auto-generated method stub
		System.out.println(findById);
	}


	

	private static void printList(List<Product> list) {
		// TODO Auto-generated method stub
		if(list.isEmpty()) {
			System.out.println("Ürün bulunamadı");
		}else {
			System.out.println(list);
		}
		
		
		
	}

	

}
