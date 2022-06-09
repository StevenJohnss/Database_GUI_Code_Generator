package pk1;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GenerateAllCode {
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
    public static void startGenerating(){
            GenerateClass.setClass();
            GenerateDAO.setDAO();
            GenerateSubFrame.setSub();
            GenerateTablesGui.setGUI();
            String s =
                "import javax.swing.JOptionPane;\n" +

                "import java.util.ArrayList;\n" + "\n" + "public class Runstuff{\n" +
            "    public static void main(String[] args) {\n" + "        System.out.println(\" the bat is running\");\n" +
            "       JOptionPane.showMessageDialog(null, \"My bat file runssssssss \");\n" + "  \n" +
            "       SubFrame s =new SubFrame();\n" +
                "        s.init();\n" + 
                "        s.setVisible(true);"+

            
            "              \n" + "    }\n" + "}";
            appendToFile(s, "D:/testing/Runstuff.java");
            
            appendToFile("cd  " + "D:/testing" + System.lineSeparator() + " javac  -cp ./jars/*.jar  *.java  " +
                        System.lineSeparator() + " java -cp ./jars/ojdbc6.jar;. Runstuff "+ System.lineSeparator() + " pause", "D:/testing/" + "myrun.bat");
            try {
                Process process = Runtime.getRuntime().exec("cmd /c start  " + "D:/testing/" + "myrun.bat");
                System.out.println(process.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }     

}
