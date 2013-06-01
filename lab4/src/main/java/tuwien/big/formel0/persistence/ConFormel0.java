package tuwien.big.formel0.persistence;
import java.sql.*;
import tuwien.big.formel0.picasa.*;

/**
 *
 * @author edem
 */
public class ConFormel0 {
 
    public static void main(String[] a)
            throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.
            getConnection("jdbc:h2:mem:lab4", "lab4", "lab4");
        // add application code here
        conn.close();
    }
}
    

