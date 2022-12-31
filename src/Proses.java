import java.util.Scanner;
import java.sql.*;

//super class
public class Proses implements Nilai{
    public int nisn;
    public String namaPeserta;
    public int nilaiPu = 0;
    public int nilaiPbm = 0;
    public int nilaiPpu = 0;
    public int nilaiPk = 0;
    public int totalNilai = 0;

    Scanner input = new Scanner(System.in);

    static Connection conn;
    String url = "jdbc:mysql://localhost:3306/db_utbk";

    //inheritance dari interface Nilai.java
    public void tampilData() throws SQLException{
        String sql = "SELECT * FROM skor_peserta";
        conn = DriverManager.getConnection(url, "root", "");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        //perulangan
        while(result.next()){
            System.out.println("\nNISN Peserta\t: ");
            System.out.println(result.getInt("nisn"));
            System.out.println("Nama Peserta\t: ");
            System.out.println(result.getString("namaPeserta"));
            System.out.println("Nilai PU\t: ");
            System.out.println(result.getInt("nilaiPu"));
            System.out.println("Nilai PBM\t: ");
            System.out.println(result.getInt("nilaiPbm"));
            System.out.println("Nilai PPU\t: ");
            System.out.println(result.getInt("nilaiPpu"));
            System.out.println("Nilai PK\t: ");
            System.out.println(result.getInt("nilaiPk"));
            System.out.println("Nilai total\t: ");
            System.out.println(result.getInt("totalNilai"));
            System.out.println("\n");

        }
    }

}
