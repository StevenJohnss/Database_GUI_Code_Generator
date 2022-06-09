package pk1;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import oracle.jdbc.OracleDriver;

public class StartUp_DAO {
    public static String userName = "clinic";
    public static String pass = "clinic";

    public static Connection getConnection() throws SQLException {
        String username = userName.toUpperCase();
        String password = pass;
        String thinConn = "jdbc:oracle:thin:@localhost:1521:XE";
        DriverManager.registerDriver(new OracleDriver());
        Connection conn = DriverManager.getConnection(thinConn, username, password);
        conn.setAutoCommit(true);
        return conn;
    }

    public static List<String> getTablesNames() {
        ResultSet rs = null;
        String sql = "SELECT TABLE_NAME FROM USER_TABLES ORDER BY TABLE_NAME";
        List<String> tablesNames = new ArrayList<String>();
        try {

            Connection conn = getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            rs = st.executeQuery();
            while (rs.next()) {
                String tableName = rs.getString(1);
                tablesNames.add(tableName);

            }
            JOptionPane.showMessageDialog(null, "Got tables");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "connection failed" + e.getMessage());
        }
        return tablesNames;
    }

    public static ResultSet getcolumns(String tablename) {
        String sql =
            "select col.column_id, \n" + "       col.owner as schema_name,\n" + "       col.table_name, \n" +
            "       col.column_name, \n" + "       col.data_type, \n" + "       col.data_length, \n" +
            "       col.data_precision, \n" + "       col.data_scale, \n" + "       col.nullable \n" +
            "from sys.dba_tab_columns col\n" + "inner join sys.dba_tables t on col.owner = t.owner \n" +
            "                              and col.table_name = t.table_name \n" + "where col.owner = '" +
            userName.toUpperCase() + "' \n" + "and col.table_name = '" + tablename.toString().toUpperCase() + "' \n" +
            "order by col.column_id ";

        ResultSet rs = null;
        try {

            Connection conn = StartUp_DAO.getConnection();
            PreparedStatement st = conn.prepareStatement(sql);

            rs = st.executeQuery();
            //                while (rs.next()) {
            //                    JOptionPane.showMessageDialog(null, "reached columns");
            //                    String colName = rs.getString(4);
            //                   JOptionPane.showMessageDialog(null,colName );
            //
            //                }

            JOptionPane.showMessageDialog(null, "got columns");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "falied to get columns" + e.getMessage());
        }

        return rs;
    }
  


}
