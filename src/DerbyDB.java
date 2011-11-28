import java.sql.*;

public class DerbyDB
{
    private Connection con = null ;    
    private static final String url = "jdbc:derby:" ;
   // private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver" ;
    private String NameTb = null;
    
    public DerbyDB(String NameTb) 
    {
        this.NameTb=NameTb;
        if(!dbExists())
        {
            try 
            {
                con = DriverManager.getConnection(url + NameTb + ";create=true");
            }
            catch (SQLException e) 
            {
                e.printStackTrace();
            }
        }
    }
    
    private Boolean dbExists() 
    {
        Boolean exists = false ;
        try
        {
            con = DriverManager.getConnection(url + NameTb);
            exists = true ;
        } 
        catch(Exception e)
        {
        	
        }
        return(exists) ;
    }
// ������ �� ���������� ���� ������ 
    public void executeUpdate(String sql) throws SQLException 
    {
        Statement stmt = con.createStatement() ;
        stmt.executeUpdate(sql) ;
        stmt.close() ;
    }
    
// ������ �� ������� ������ �� ����
    public ResultSet executeQuery(String sql) throws SQLException 
    {
        Statement stmt = con.createStatement() ;
        ResultSet result = stmt.executeQuery(sql) ;
        return result;
    }
}