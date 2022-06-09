package pk1;

import java.io.FileWriter;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;

public class GenerateDAO {

    public static Connection getConnection() throws SQLException {
        String username = StartUp_DAO.userName.toUpperCase();
        String password = StartUp_DAO.pass;
        String thinConn = "jdbc:oracle:thin:@localhost:1521:XE";
        DriverManager.registerDriver(new OracleDriver());
        Connection conn = DriverManager.getConnection(thinConn, username, password);
        conn.setAutoCommit(true);
        return conn;
    }

    public static void appendToFile(String text, String path) {
        //true boolean means append data and vice versa
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(System.lineSeparator() + text);
            ;

        } catch (IOException e) {
            // TODO handle exception
            System.out.println(e);
        }
    }

    public static void setDAO() {
        List<String> list = StartUp_DAO.getTablesNames();
        ResultSet rs;
        for (int i = 0; i < list.size(); i++) {
            appendToFile(" \n" + "import java.sql.Connection;\n" + "import java.sql.DriverManager;\n" +
                         "import java.sql.PreparedStatement;\n" + "import java.sql.ResultSet;\n" +
                         "import java.sql.SQLException;\n" + "\n" + "import javax.swing.JOptionPane;\n" + "\n" +
                         "import oracle.jdbc.OracleDriver; \n", "D:/testing/" + list.get(i) + "_DAO.java");

            rs = StartUp_DAO.getcolumns(list.get(i));
            ArrayList a = new ArrayList();
            try {
                while (rs.next()) {
                    a.add(rs.getString(4));
                }
            } catch (SQLException e) {
            }
            try {
                appendToFile("public class " + list.get(i) + "_DAO {", "D:/testing/" + list.get(i) + "_DAO.java");

                appendToFile("    public static Connection getConnection() throws SQLException {\n" +
                             "        String username = \"" + StartUp_DAO.userName.toUpperCase() + "\";\n" +
                             "        String password = \"" + StartUp_DAO.pass + "\" ; \n" +
                             "        String thinConn = \"jdbc:oracle:thin:@localhost:1521:XE\";\n" +
                             "        DriverManager.registerDriver(new OracleDriver());\n" +
                             "        Connection conn = DriverManager.getConnection(thinConn, username, password);\n" +
                             "        conn.setAutoCommit(true);\n" + "        return conn;\n" + "    }",
                             "D:/testing/" + list.get(i) + "_DAO.java");


                String setsql = "";
                String setvar = "";
                int counter = 1;
                rs = StartUp_DAO.getcolumns(list.get(i));
                //ADD function-------------------------------------------
                while (rs.next()) {

                    if (a.size() == counter) {
                        System.out.println("rs last");
                        setsql += "?";

                    } else {
                        setsql += " ?, ";
                    }
                    if (rs.getString(5).equalsIgnoreCase("NUMBER")) {
                        setvar += "st.setInt(" + counter + ", obj.get" + rs.getString(4) + "()); \n";
                    } else {
                        setvar += "st.setString(" + counter + ", obj.get" + rs.getString(4) + "()); \n";
                    }
                    counter++;
                }

                appendToFile("  public static void add" + list.get(i) + "(" + list.get(i) + " obj) {\n" +
                             "            String sql = \"INSERT into " + list.get(i) + " values(" + setsql + ")\";\n" +
                             "        try {\n" + "            Connection conn = getConnection();\n" +
                             "            JOptionPane.showMessageDialog(null, \"Conn Success\");\n" +
                             "            PreparedStatement st = conn.prepareStatement(sql);\n" + setvar +
                             "             st.executeUpdate();\n" +
                             "           JOptionPane.showMessageDialog(null, \"ADD Success\");\n" +
                             "            conn.close();\n" + "        } catch (SQLException e) {\n" +
                             "            JOptionPane.showMessageDialog(null, \"Error\" + e);\n" + "\n" +
                             "        }\n" + "\n" + "    }", "D:/testing/" + list.get(i) + "_DAO.java");


                setsql = "";
                setvar = "";
                counter = 1;
                rs = StartUp_DAO.getcolumns(list.get(i));
                //Update function-------------------------------------------
                String col1 = "";
                while (rs.next()) {
                    if (counter == 1) {
                        col1 = rs.getString(4);
                    } else {
                        if (a.size() == counter) {
                            System.out.println("rs last");
                            setsql += " " + rs.getString(4) + " = ?  ";

                        } else {
                            setsql += " " + rs.getString(4) + " = ? , ";
                        }
                        if (rs.getString(5).equalsIgnoreCase("NUMBER")) {
                            setvar += "st.setInt(" + (counter - 1) + ", obj.get" + rs.getString(4) + "()); \n";
                        } else {
                            setvar += "st.setString(" + (counter - 1) + ", obj.get" + rs.getString(4) + "()); \n";
                        }
                    }


                    counter++;
                }

                appendToFile("  public static void update" + list.get(i) + "(" + list.get(i) + " obj) {\n" +
                             "            String sql = \"UPDATE " + list.get(i) + " SET " + setsql + " WHERE " + col1 +
                             " = ? \";\n" + "        try {\n" + "            Connection conn = getConnection();\n" +
                             "            JOptionPane.showMessageDialog(null, \"Conn Success\");\n" +
                             "            PreparedStatement st = conn.prepareStatement(sql);\n" + setvar +
                             "st.setInt(" + (a.size()) + ", obj.get" + col1 + "()); \n" +
                             "             st.executeUpdate();\n" +
                             "           JOptionPane.showMessageDialog(null, \"Update Success\");\n" +
                             "            conn.close();\n" + "        } catch (SQLException e) {\n" +
                             "            JOptionPane.showMessageDialog(null, \"Error\" + e);\n" + "\n" +
                             "        }\n" + "\n" + "    }", "D:/testing/" + list.get(i) + "_DAO.java");


                //Delete function-------------------------------------------
                appendToFile("  public static void delete" + list.get(i) + "( int id) {\n" +
                             "            String sql = \"Delete FROM " + list.get(i) + " WHERE " + col1 + " = ? \";\n" +
                             "        try {\n" + "            Connection conn = getConnection();\n" +
                             "            JOptionPane.showMessageDialog(null, \"Conn Success\");\n" +
                             "            PreparedStatement st = conn.prepareStatement(sql);\n" + "st.setInt(" + (1) +
                             ", id ); \n" + "             st.executeUpdate();\n" +
                             "           JOptionPane.showMessageDialog(null, \"Delete Success\");\n" +
                             "            conn.close();\n" + "        } catch (SQLException e) {\n" +
                             "            JOptionPane.showMessageDialog(null, \"Error\" + e);\n" + "\n" +
                             "        }\n" + "\n" + "    }", "D:/testing/" + list.get(i) + "_DAO.java");


                //Find function-------------------------------------------
                appendToFile("  public static ResultSet  find" + list.get(i) + "( int id) {\n" +
                             "    ResultSet myrs=null; \n       String sql = \"SELECT * FROM  " + list.get(i) +
                             " WHERE " + col1 + " = ? \";\n" + "        try {\n" +
                             "            Connection conn = getConnection();\n" +
                             "            JOptionPane.showMessageDialog(null, \"Conn Success\");\n" +
                             "            PreparedStatement st = conn.prepareStatement(sql);\n" + "st.setInt(" + (1) +
                             ", id ); \n" + "               myrs = st.executeQuery();\n" +
                             "           JOptionPane.showMessageDialog(null, \"Found Success\");\n" +
                             "           \n" + "        } catch (SQLException e) {\n" +
                             "            JOptionPane.showMessageDialog(null, \"Error\" + e);\n " + "\n" +
                             "        }\n" + "\n" + "  return myrs;   }", "D:/testing/" + list.get(i) + "_DAO.java");


            } catch (Exception e) {
            }


            appendToFile(" }", "D:/testing/" + list.get(i) + "_DAO.java");
        }
    }


//    public static void main(String[] args) {
//        GenerateClass.setClass();
//        setDAO();
//
//        String s =
//            "import javax.swing.JOptionPane;\n" +
//
//            "import java.util.ArrayList;\n" + "\n" + "public class Runstuff{\n" +
//     "    public static void main(String[] args) {\n" + "        System.out.println(\" the bat is running\");\n" +
//     "       JOptionPane.showMessageDialog(null, \"My bat file runssssssss \");\n" + "	\n" +
//     "       ArrayList a =new ArrayList();\n" +
//
//     "try{ ADMIN_DAO.getConnection(); JOptionPane.showMessageDialog(null, \"Conn success \"); } catch(Exception e){JOptionPane.showMessageDialog(null, \"myerror: \"+e);} " +
//     "              \n" + "    }\n" + "}";
//        appendToFile(s, "D:/testing/Runstuff.java");
//
//        appendToFile("cd  " + "D:/testing" + System.lineSeparator() + " javac  -cp ./jars/*.jar  *.java  " +
//                     System.lineSeparator() + " java -cp ./jars/ojdbc6.jar;. Runstuff " + System.lineSeparator() +
//                     "pause", "D:/testing/" + "myrun.bat");
//
//        try {
//            Process process = Runtime.getRuntime().exec("cmd /c start  " + "D:/testing/" + "myrun.bat");
//            System.out.println(process.getInputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //
//
//
//    }
}
