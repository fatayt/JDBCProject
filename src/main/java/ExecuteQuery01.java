import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adim: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adim: Database'e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/arcane", "postgres","2615Fat.");

        //3.Adim: Statement olustur
        Statement st = con.createStatement();

        //1. Orn: region id'si 1 olan "country name" degerlerini cagirin

        String sql1 = "SELECT country_name from  countries where region_id=1";
        boolean sonuc = st.execute(sql1);
        System.out.println("sonuc = " + sonuc);

        //Record/satirlari gormek icn ExecuteQuery() methodunu kullanmaliyiz
        ResultSet rS1 = st.executeQuery(sql1);
        System.out.println("rS1 = " + rS1);

        while (rS1.next()){
            rS1.getString(1); // veya rS1.getString("country_name");
            System.out.println(rS1.getString(1));
        }
//2. orn: "region_id'nin 2'den buyuk oldugu "country_id" ve "country_name" degerlerini cagirin

    }
}