import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adim: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2.Adim: Database'e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/arcane", "postgres","2615Fat.");

        //3.Adim: Statement olustur
        Statement st = con.createStatement();

//1. ornek: companies tablosundan en yuksek ikinci number_of_employees degeri olanin company ve number_of_employees degerlerini yazdirin
        //1.yol
        String sql1 ="Select company_id, company, number_of_employees From companies Order By number_of_employees DESC Offset 1 Row Fetch Next Row Only";
        ResultSet rs1=  st.executeQuery(sql1);
        while (rs1.next()){
            System.out.println(rs1.getString("company_id")+ "---"+rs1.getString("company")+"--"+ rs1.getString("number_of_employees"));
        }

        System.out.println("---------------------------------------");


        //2.yol
        String sql2 = "SELECT company_id, company, number_of_employees \n" +
                " FROM companies\n" +
                "Where number_of_employees =\n" +
                "(Select Max(number_of_employees) \n" +
                "From companies\n" +
                "Where number_of_employees <\n" +
                "(Select Max(number_of_employees)\n" +
                "From companies))";

        ResultSet rs2=  st.executeQuery(sql2);
        while (rs2.next()){
            System.out.println(rs2.getString("company_id")+ "---"+rs2.getString("company")+"--"+ rs2.getString("number_of_employees"));
        }

        con.close();
        st.close();

    }
}