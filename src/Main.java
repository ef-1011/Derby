import java.sql.*;

public class Main 
{
    static Connection conn = null;
    static String NameTb = "testTb";
    static String NameDB = "testDb";
    
    public static void main(String[] args) 
    {
    	DerbyDB db = new DerbyDB(NameDB);
        
        try {
			connectDB(db);
			readDB(db);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void connectDB(DerbyDB db) throws SQLException
    {
        try
        {
                //пишем в таблицу
           addDB(db);
        }
        catch(SQLException e)
        {
               //если БД не существовала, то создаем таблицу и после этого заполняем её значениями
            db.executeUpdate("CREATE TABLE "+NameTb+"(i integer, i2 integer)");
            addDB(db);
        }
    }
    
    public static void readDB(DerbyDB db) throws SQLException
    {
        ResultSet rs = db.executeQuery("SELECT * FROM "+NameTb+"");
        while(rs.next())
        {
            System.out.println("i = "+rs.getString(1)+", i^2 = "+rs.getString(2));
        }
        freeDB(db);     
    } 
    
    public static void addDB(DerbyDB db) throws SQLException 
    {
        for(int i=0;i<5;i++)
        {
            db.executeUpdate("INSERT INTO "+NameTb+" VALUES("+String.valueOf(i)+","+String.valueOf(i*i)+")");    
        }
    }
   
    public static void freeDB(DerbyDB db) throws SQLException
    {
            db.executeUpdate("DELETE FROM "+NameTb+"");    
    }
}