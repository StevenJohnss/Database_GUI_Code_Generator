package pk1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.lang.Runtime;

public class GenerateClass {

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

    public static void setClass() {
        List<String> list = StartUp_DAO.getTablesNames();
        ResultSet rs;
        for (int i = 0; i < list.size(); i++) {
            appendToFile(" import java.util.ArrayList; ", "D:/testing/" + list.get(i) + ".java");

            rs = StartUp_DAO.getcolumns(list.get(i));
            try {
                appendToFile("public class " + list.get(i) + " {", "D:/testing/" + list.get(i) + ".java");
                //if(rs.next()){}
                while (rs.next()) {


                    String coltype = rs.getString(5);
                    if (coltype.equalsIgnoreCase("NUMBER")) {
                        String variables = "";
                        variables = "private int " + rs.getString(4) + " ;";
                        appendToFile(variables, "D:/testing/" + list.get(i) + ".java");
                        String funcs = "";
                        funcs =
                            " \n \n   public void set" + rs.getString(4) + " (int " + rs.getString(4) + " ) {\n" +
                            "        this." + rs.getString(4) + "= " + rs.getString(4) + " ; \n" + "    }";

                        appendToFile(funcs, "D:/testing/" + list.get(i) + ".java");

                        funcs =
                            " \n \n   public int get" + rs.getString(4) + " () {\n" + "       return " +
                            rs.getString(4) + " ; \n" + "    }";

                        appendToFile(funcs, "D:/testing/" + list.get(i) + ".java");
                    }

                    else if (coltype.contains("VARCHAR")) {

                        String variables = "";
                        variables = "private String " + rs.getString(4) + " ;";
                        appendToFile(variables, "D:/testing/" + list.get(i) + ".java");
                        String funcs = "";
                        funcs =
                            " \n \n   public void set" + rs.getString(4) + " (String " + rs.getString(4) + " ) {\n" +
                            "        this." + rs.getString(4) + "= " + rs.getString(4) + " ; \n" + "    }";

                        appendToFile(funcs, "D:/testing/" + list.get(i) + ".java");

                        funcs =
                            " \n \n   public String get" + rs.getString(4) + " () {\n" + "       return " +
                            rs.getString(4) + " ; \n" + "    }";

                        appendToFile(funcs, "D:/testing/" + list.get(i) + ".java");


                    }


                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            String cons = "";
            cons += "\n public " + list.get(i) + " ( ArrayList a){ \n";
            try {
                int counter = 0;
                rs = StartUp_DAO.getcolumns(list.get(i));
                while (rs.next()) {
                    String coltype = rs.getString(5);
                    if (coltype.equalsIgnoreCase("NUMBER")) {
                        cons += "this." + rs.getString(4) + " = " + " (Integer) a.get(" + counter + ") ; \n";
                    } else {
                        cons += "this." + rs.getString(4) + " = " + " (String) a.get(" + counter + ") ; \n";
                    }
                    counter++;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

            appendToFile(cons + " }", "D:/testing/" + list.get(i) + ".java");

            appendToFile(" }", "D:/testing/" + list.get(i) + ".java");
            JOptionPane.showMessageDialog(null, list.get(i));
        }

    }

    //    public static void main(String[] args) {
    //
    //
    //        setClass();
    //
    //        //cd D:/testing to run a comand t go to same folder where you run the runstuff file also .bat file must be in the same folder and java Runstuff runs main
    //        //appendToFile("cd D:/testing "+ System.lineSeparator()+" javac Runstuff.java  "+ System.lineSeparator()+" java Runstuff "+ System.lineSeparator()+" pause","D:/testing/myrun.bat");
    //
    //
    //        appendToFile("cd D:/testing " + System.lineSeparator() + " javac *.java " + System.lineSeparator() +
    //                     "java Runstuff" + System.lineSeparator() + "pause", "D:/testing/myrun.bat");
    //
    //        try {
    //            Process process = Runtime.getRuntime().exec("cmd /c start  " + "D:/testing/myrun.bat");
    //            System.out.println(process.getInputStream());
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //        }
    //
    //
    //    }


}
