import java.util.Scanner;
import java.util.Date; 
import java.sql.*;

public class App {
    static Connection conn;
    public static void main(String[] args) {
        Admin admin = new Admin();
        System.out.println("Admin\t: "+admin.NamaAdmin);
        System.out.println("Telepon\t: "+admin.NoHp);

        try (Scanner input = new Scanner(System.in)) {
			String Pilihan;
			boolean isLanjutkan = true;

			//pengolahan database
			String url = "jdbc:mysql://localhost:3306/db_utbk";
			try {
			    //method Date
			    Date date = new Date();
					String str = String.format("Date : %tc", date);
					System.out.println(str);
					System.out.println("");
				
				//pengolahan database
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, "root","");
				System.out.println("Class Driver ditemukan");
				Peserta peserta = new Peserta();

				//perulangan
			    while (isLanjutkan) {
					System.out.println("\n=====================");
					System.out.println("Database Peserta UTBK");
					System.out.println("=======================");
					System.out.println("1. Lihat Data Peserta");
					System.out.println("2. Tambah Data Peserta");
					System.out.println("3. Ubah Data Peserta");
					System.out.println("4. Hapus Data Peserta");
					System.out.println("5. Cari Data Peserta");
					
					System.out.print("\nPilihan anda (1/2/3/4/5): ");

			        Pilihan = input.next();

			        //membuat percabangan 
			        switch (Pilihan) {
						case "1":
							peserta.lihatData();
							break;
						case "2":
							peserta.tambahData();
							break;
						case "3":
							peserta.ubahData();
							break;
						case "4":
							peserta.hapusData();
							break;
						case "5":
							peserta.cariData();
							break;
						default:
							System.err.println("Pilih angka dalam rentang [1-5]");
					}
			        System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
					Pilihan = input.next();
					isLanjutkan = Pilihan.equalsIgnoreCase("y");
			    }
			    System.out.println("Program selesai...");
			}
			//exception
			catch(ClassNotFoundException ex){
			    System.err.println("Driver Error");
			    System.exit(0);
			}
			//exception
			catch(SQLException e){
			    System.out.println("Tidak berhasil koneksi");
			}
		}
    }
}