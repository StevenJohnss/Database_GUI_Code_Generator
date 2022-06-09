package pk1;

import java.awt.event.ActionListener;

import java.io.FileWriter;
import java.io.IOException;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GenerateTablesGui {

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

    public static void setGUI() {

        List<String> list = StartUp_DAO.getTablesNames();
        ResultSet rs = null;
        for (int i = 0; i < list.size(); i++) {

            rs = StartUp_DAO.getcolumns(list.get(i));
            ArrayList a = new ArrayList();
            try {
                while (rs.next()) {
                    a.add(rs.getString(4));
                }
            } catch (SQLException e) {
            }

            appendToFile("import javax.swing.JFrame;\n" + "import javax.swing.*;\n" + "\n" + "import java.sql.*;\n" +
                         "\n" + "import java.awt.*;\n" + "import java.awt.event.*;\n" + "\n" +
                         "import java.util.ArrayList;\n" + "import java.util.List;",
                         "D:/testing/" + list.get(i) + "Frame" + ".java");


            appendToFile("public class " + list.get(i) + "Frame extends JFrame implements ActionListener {",
                         "D:/testing/" + list.get(i) + "Frame" + ".java");

            appendToFile("JTextField[] tfs; \n", "D:/testing/" + list.get(i) + "Frame" + ".java");

          
            //our init func--------------------------------------
                     int counter = 20;
                     int k = 0;
                     String jlabeles = "";
                     String textfileds = "";
                     rs = StartUp_DAO.getcolumns(list.get(i));
                     try {
                         while (rs.next()) {
                             jlabeles +=
                                 " jlabels["+k+"] = new JLabel(\""+rs.getString(4)+"\");\n" +
                                 "jlabels["+k+"].setBounds(50,"+ counter+", 100, 30);\n" +
                                 " add(jlabels["+k+"]); \n";
                             textfileds+=
                             " tfs["+k+"] = new JTextField();\n" + 
                             " tfs["+k+"].setBounds(300, "+counter+", 500, 30);\n" + 
                             "add(tfs["+k+"]); \n";
                             k++;
                             counter += 50;
                         }
                     } catch (SQLException e) {
                     }
                        appendToFile("public void init() { \n" + "        tfs = new JTextField[" + a.size() + "];\n" +
                                     "JLabel jlabels[] = new JLabel[" + a.size() + "]; \n"+jlabeles+" \n "+textfileds+
                                     "        JButton b1, b2, b3, b4, b5;\n" +
                                     "        b1 = new JButton(\"Add\");\n" +
                                     "        b2 = new JButton(\"Update\");\n" +
                                     "        b3 = new JButton(\"Find\");\n" +
                                     "        b4 = new JButton(\"Delete\");\n" +
                                     "b5 = new JButton(\"Back\"); \n"+
                                     "\n" +
                                     "        b1.setBounds(250, "+(counter + 100)+" , 100, 30);\n" +
                                     "        b2.setBounds(350, "+(counter + 100)+", 100, 30);\n" +
                                     "        b3.setBounds(450, "+(counter + 100)+", 100, 30);\n" +
                                     "        b4.setBounds(550, "+(counter + 100)+" , 100, 30);\n" +
            "        b5.setBounds(650, "+(counter + 100)+" , 100, 30);\n" +
                                     "        add(b1);\n" +
                                     "        add(b2);\n" +
                                     "        add(b3);\n" +
                                     "        add(b4);\n" +
            "        add(b5);\n" +
                                     "        b1.addActionListener(this);\n" +
                                     "        b2.addActionListener(this);\n" +
                                     "        b3.addActionListener(this);\n" +
                                     "        b4.addActionListener(this);\n" +
            "        b5.addActionListener(this);\n" +
                                     "\n" +
                                     "        setLayout(null);\n" +
                                     "        setVisible(true);\n" +
                                     "        setSize(870, ("+(counter + 200)+")); \n }",
                                     "D:/testing/" + list.get(i) + "Frame" + ".java");





// action function-------------------------------------
            rs = StartUp_DAO.getcolumns(list.get(i));
            String inputs="";
            
            int t=0;
            inputs+="ArrayList inputVals = new ArrayList();";
            try {
                while (rs.next()) { 
                    if(rs.getString(5).equalsIgnoreCase("NUMBER")){
                  inputs+=" \n  inputVals.add(Integer.parseInt(tfs["+t+"].getText())); \n";
                    }else{
                            inputs+=" \n  inputVals.add(tfs["+t+"].getText()); \n";
                        }
                    t++;
                }
            } catch (SQLException e) {
            }
            appendToFile(" public void actionPerformed(ActionEvent e) { " +
                         "if(!tfs[0].getText().equalsIgnoreCase(\"\")) {"+
                        "        if (e.getActionCommand().equals(\"Add\")) {\n" + 
                        inputs+" \n "+ list.get(i)+" obj = "+" new "+list.get(i)+"( inputVals ); \n"+ 
                         "\n "+" "+list.get(i)+"_DAO.add"+list.get(i)+"(obj) ; \n "+
                        "            JOptionPane.showMessageDialog(null, \"Add\");\n" + 
                        "        } else if (e.getActionCommand().equals(\"Update\")) {\n" +
                        inputs+" \n "+ list.get(i)+" obj = "+" new "+list.get(i)+"( inputVals ); \n"+ 
            "\n "+" "+list.get(i)+"_DAO.update"+list.get(i)+"(obj) ; \n "+
                        "            JOptionPane.showMessageDialog(null, \"Update\"+\" id= \"+tfs[0].getText());\n" + 
                        "        }"+
                         
                         
                         " else if (e.getActionCommand().equals(\"Find\")) {\n" + 
                         
            "\n "+" ResultSet res = null; \n res = "+list.get(i)+"_DAO.find"+list.get(i)+"(Integer.parseInt(tfs[0].getText())) ; \n "+
                         "\n "+" int cont=0; try{ if(res.next()){ while(cont<"+a.size()+"){ " +
            "tfs[cont].setText(res.getString(cont+1)); cont++; } \n} } catch(Exception exc) {" +
                " JOptionPane.showMessageDialog(null,exc); } \n"+
                        "            JOptionPane.showMessageDialog(null, \"Find\"+\" id= \"+tfs[0].getText());\n" + 
                        "        }"
                         
                         
                         
                         +" else if (e.getActionCommand().equals(\"Delete\")) {\n" + 
            "\n "+" "+list.get(i)+"_DAO.delete"+list.get(i)+"(Integer.parseInt(tfs[0].getText())) ; \n "+
                        "            JOptionPane.showMessageDialog(null, \"Delete\"+\" id= \"+tfs[0].getText());\n" + 
                        "\n" + 
                        "        }else if (e.getActionCommand().equals(\"Back\")) {\n" + 
                        "            SubFrame f = new SubFrame(); f.init();  f.setVisible(true); dispose();     \n" + 
                        "\n" + 
                        "                } } else{if(e.getActionCommand().equals(\"Back\")){" +
                " SubFrame f = new SubFrame(); f.init();  f.setVisible(true); dispose(); }else{ JOptionPane.showMessageDialog(null, \"Must at least enter id\"); } } " +"}",
                         "D:/testing/" + list.get(i) + "Frame" + ".java");




            appendToFile("}", "D:/testing/" + list.get(i) + "Frame" + ".java");

        }


    }


    public static void main(String[] args) {
        GenerateClass.setClass();
        GenerateDAO.setDAO();
        setGUI();
        
        String s =
            "import javax.swing.JOptionPane;\n" +

            "import java.util.ArrayList;\n" + "\n" + "public class Runstuff{\n" +
        "    public static void main(String[] args) {\n" + "        System.out.println(\" the bat is running\");\n" +
        "       JOptionPane.showMessageDialog(null, \"My bat file runssssssss \");\n" + "  \n" +
        "       EMPLOYEESFrame s =new EMPLOYEESFrame();\n" +
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
