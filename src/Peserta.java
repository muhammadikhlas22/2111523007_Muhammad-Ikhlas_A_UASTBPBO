import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

//sub class
public class Peserta extends Proses {
    static Connection conn;

    //pengolahan database
    String url = "jdbc:mysql://localhost:3306/db_utbk";
    

    Scanner input = new Scanner(System.in);

    public void lihatData() throws SQLException{
        String text1 = "\n==Daftar Nama Peserta UTBK==";
        System.out.println(text1.toUpperCase());
        //inheritance
        tampilData();
    }
    public void tambahData() throws SQLException{
        String text2 = "\n==Tambah data Peserta UTBK==";
        System.out.println(text2.toUpperCase());

        try {
            // Input NISN
            System.out.println("Inputkan NISN : ");
            nisn = input.nextInt();
            input.nextLine();

            //Input Nama Peserta
            System.out.println("Inputkan Nama Peserta : ");
            namaPeserta = input.nextLine();

            // input nilai PU
            System.out.println("inputkan nilai PU : ");
            nilaiPu = input.nextInt();
            input.nextLine();

            //input nilai PBM
            System.out.println("inputkan nilai PBM : ");
            nilaiPbm = input.nextInt();
            input.nextLine();

            // input nilai PPU
            System.out.println("inputkan nilai PPU : ");
            nilaiPpu = input.nextInt();
            input.nextLine();

            // input nilai PK
            System.out.println("inputkan nilai PK : ");
            nilaiPk = input.nextInt();
            input.nextLine();

            //perhitungan matematika - nilai akhir
            totalNilai = (nilaiPu + nilaiPbm + nilaiPpu + nilaiPk);
            // TotalNilai = TotalNilai/2;
            totalNilai = (int) totalNilai;
            System.out.println("\nNilai total : " +totalNilai);

            //masuk database
            String sql = "INSERT INTO skor_peserta(nisn, namaPeserta, nilaiPu, nilaiPbm, nilaiPpu, nilaiPk, totalNilai) VALUES ('"+nisn+"','"+namaPeserta+"','"+nilaiPu+"','"+nilaiPbm+"','"+nilaiPpu+"','"+nilaiPk+"','"+totalNilai+"')";
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Input data sukses!");

        }
        catch(SQLException e){
            System.err.println("Terjadi Kesalahan input");
        }
        catch(InputMismatchException e){
            System.err.println("input menggunakan angka!");
        }
    }

    public void ubahData() throws SQLException{
        String text3 = "\n==Update data peserta==";
        System.out.println(text3.toUpperCase());

        try{
            
            System.out.println("Inputkan NISN peserta :");
            Integer nisn = Integer.parseInt(input.nextLine());

            //pengolahan database
            String sql ="SELECT * FROM skor_peserta WHERE nisn =" +nisn;
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if(result.next()){
                System.out.println("Nama["+result.getString("namaPeserta")+"]\t:");
                String namaPeserta = input.nextLine();

                //pengolahan database
                sql = "UPDATE skor_peserta SET namaPeserta ='"+namaPeserta+"'WHERE nisn ='"+nisn+"'";
                if(statement.executeUpdate(sql)>0){
                    System.out.println("data sudah diupdate!");
                }
            }
            statement.close();
        }
        //exception
        catch(SQLException e){
            System.err.println("Terjadi kesalahan dalam update");
            System.err.println(e.getMessage());
        }
    }

    public void hapusData(){
        String text4 = "\n==Delete Data Peserta==";
        System.out.println(text4.toUpperCase());
        
        try{
            System.out.println("Inputkan NISN peserta :");
            Integer nisn = Integer.parseInt(input.nextLine());

            //pengolahan database
            String sql = "DELETE FROM skor_peserta WHERE nisn = "+nisn;
            conn = DriverManager.getConnection(url,"root","");
            Statement statement = conn.createStatement();

            if(statement.executeUpdate(sql)>0){
                System.out.println("Data Dihapus!");
            }

        }
        //exception
        catch (SQLException e){
            System.out.println("Terjadi kesalahan!");
        }
    }

    public void cariData() throws SQLException{
        String text5 = "\n==cari Data Peserta==";
        System.out.println(text5.toUpperCase());
        
        System.out.println("Inputkan Nama peserta : ");
        String cari = input.nextLine();

        //pengolahan database
        String sql = "SELECT * FROM skor_peserta WHERE namaPeserta LIKE '%"+cari+"%'";
        conn = DriverManager.getConnection(url,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql); 

        while(result.next()){
            System.out.println("\nNISN Peserta\t: ");
            System.out.println(result.getInt("nisn"));
            System.out.println("\nNama Peserta\t: ");
            System.out.println(result.getString("namaPeserta"));
            System.out.println("\nNilai PU\t: ");
            System.out.println(result.getInt("nilaiPu"));
            System.out.println("\nNilai PBM\t: ");
            System.out.println(result.getInt("nilaiPbm"));
            System.out.println("\nNilai PPU\t: ");
            System.out.println(result.getInt("nilaiPpu"));
            System.out.println("\nNilai PK\t: ");
            System.out.println(result.getInt("nilaiPk"));
            System.out.println("\nNilai total\t: ");
            System.out.println(result.getInt("totalNilai"));
            System.out.println("\n");
        }
    }
}
