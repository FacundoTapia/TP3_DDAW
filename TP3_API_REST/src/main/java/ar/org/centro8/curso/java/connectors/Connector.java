package ar.org.centro8.curso.java.connectors;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Connector {
    //private static String driver="com.mysql.cj.jdbc.Driver";    // driver mysql 6 o superior
    //private static String driver="com.mysql.jdbc.Driver";       //driver mysql 5
    //private static String driver="org.postgresql.Driver";
    private static String driver="org.mariadb.jdbc.Driver";
    //private static String vendor="mysql";
    //private static String vendor="postgresql";
    private static String vendor="mariadb";
    private static String server="localhost";
    //private static String server="freedb.tech";
    //private static String server="tuffi.db.elephantsql.com";
    private static String port="3306";
    //private static String port="5432";
    //private static String db="colegio";
    //private static String db="freedbtech_colegio";
    //private static String db="gdyhittm";
    //private static String db="freedbtech_negocioRopaWeb";
    private static String db="negocioWebRopa";
    //private static String params="?serverTimezone=UTC";
    private static String params="";
    private static String user="root";
    private static String pass="root";
    //private static String user="freedbtech_colegio";
    //private static String pass="colegio";
    
    //private static String user="gdyhittm";
    //private static String pass="3pJiSBDlGj5FbzcM-gTRP4y_NIu81RJg";

    //private static String user="freedbtech_negocioRopaWeb";
    //private static String pass="centro8";
    
    private static String url="jdbc:"+vendor+"://"+server+":"+port+"/"+db+params;
    
    private static Connection conn=null;
    
    private Connector(){ }
    
    public synchronized static Connection getConnection(){
        try{
            if(conn==null || conn.isClosed()){
                Class.forName(driver);
                conn=DriverManager.getConnection(url, user, pass);
            }
        }catch(SQLException e) { System.out.println("Problema de conexión");
        }catch(ClassNotFoundException e) { System.out.println("No se encontro el driver");
        }catch(Exception e){ System.out.println(e); }
        return conn;
    }
}