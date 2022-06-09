package pk1;

import java.io.FileWriter;
import java.io.IOException;

import java.sql.ResultSet;

import java.util.List;

import javax.swing.JButton;

public class GenerateSubFrame {

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

    public static void setSub() {
        List<String> list = StartUp_DAO.getTablesNames();
        ResultSet rs;
        String buttons = "";
        String actions = "";
       
        int counter=50;
        for (int i = 0; i < list.size(); i++) {
            buttons +=
                "jb["+i+"] = new JButton();\n" + "            add(jb["+i+"]);\n" + "            bg.add(jb["+i+"]);\n" +
                "            jb["+i+"].setBounds(200, "+counter+", 150, 50);\n" + "            jb["+i+"].setText(\""+list.get(i)+"\");\n" +
                "           \n" + "            jb["+i+"].addActionListener(this);";
            actions+="                   if (e.getActionCommand().equals(\""+list.get(i)+"\")) {\n" + 
           list.get(i)+"Frame f = new "+list.get(i)+"Frame(); f.init() ; f.setVisible(true); dispose();" +"JOptionPane.showMessageDialog(null, \""+list.get(i)+"\");"+
            "                              }";
           
            
            counter+=100;
        }

        appendToFile(" import javax.swing.JFrame;\n" + "import javax.swing.*;\n" + "\n" + "import java.sql.*;\n" +
                     "\n" + "import java.awt.*;\n" + "import java.awt.event.*;\n" + "import java.util.List; \n",
                     "D:/testing/SubFrame" + ".java");

        appendToFile("public class SubFrame extends JFrame implements ActionListener {", "D:/testing/SubFrame"+".java");

            //for gui subframe int function which initalize the buttons and thier names
        appendToFile("    public void init(){\n" + "          \n" + "            JLabel l;\n" +
                     "            ButtonGroup bg;\n" + "            JButton jb[] = new JButton["+list.size()+"];\n" +
                     "            bg = new ButtonGroup();\n" + "           \n" + buttons + " \n" +
                     "                  l = new JLabel();\n" + "                  add(l);\n" +
                     "                  setLayout(null);\n" + "                  setVisible(true);\n" +
                     "                  setSize(570, 550);\n" + "        \n" + "        }", "D:/testing/SubFrame"+".java");

      
      
        //for gui subframe actionfunctions which directs me to the approbiat GUI according to table name
        appendToFile("  public void actionPerformed(ActionEvent e) {\n" +actions+" \n }", "D:/testing/SubFrame"+".java");

        appendToFile("}", "D:/testing/SubFrame"+".java");


    }
    
    
    
    
       

}
