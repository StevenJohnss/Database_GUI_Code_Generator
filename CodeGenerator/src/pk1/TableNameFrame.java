package pk1;


import javax.swing.JFrame;
import javax.swing.*;

import java.sql.*;

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.List;

public class TableNameFrame extends JFrame implements ActionListener {
    public List<String> list = StartUp_DAO.getTablesNames();
    JTextField[] tfs;
    public void init() {
        ResultSet rs = null;
        for (int i = 0; i < list.size(); i++) {
            rs = StartUp_DAO.getcolumns(list.get(i));
            ;
        }
        ArrayList a = new ArrayList();
        try {
            while (rs.next()) {
                a.add(rs.getString(4));
            }
        } catch (SQLException e) {
        }
        int counter = 20;
        

        tfs = new JTextField[a.size()];
        JLabel jlabels[] = new JLabel[a.size()];
        int i = 0;
        rs = StartUp_DAO.getcolumns(list.get((list.size() - 1)));
        try {
            while (rs.next()) {
                jlabels[i] = new JLabel(rs.getString(4));
                jlabels[i].setBounds(50, counter, 100, 30);
                add(jlabels[i]);
                
                tfs[i] = new JTextField();
                tfs[i].setBounds(300, counter, 500, 30);
                add(tfs[i]);
                
                i++;
                counter += 50;
            }
        } catch (SQLException e) {
        }

        JButton b1, b2, b3, b4, b5;
        b1 = new JButton("Add");
        b2 = new JButton("Update");
        b3 = new JButton("Find");
        b4 = new JButton("Delete");
        b5 = new JButton("Back");

        b1.setBounds(250, (counter + 100), 100, 30);
        b2.setBounds(350, (counter + 100), 100, 30);
        b3.setBounds(450, (counter + 100), 100, 30);
        b4.setBounds(550, (counter + 100), 100, 30);
        b5.setBounds(550, (counter + 100), 100, 30);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        setLayout(null);
        setVisible(true);
        setSize(870, (counter + 200));

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            JOptionPane.showMessageDialog(null, "Add");
        } else if (e.getActionCommand().equals("Update")) {
            JOptionPane.showMessageDialog(null, "Update");
        } else if (e.getActionCommand().equals("Find")) {
            JOptionPane.showMessageDialog(null, "Find"+" id= "+tfs[0].getText());
        } else if (e.getActionCommand().equals("Delete")) {
            JOptionPane.showMessageDialog(null, "Delete");

        }else if (e.getActionCommand().equals("Back")) {
                    JOptionPane.showMessageDialog(null, "Back");

                }
    }

    public static void main(String[] args) {
        TableNameFrame s = new TableNameFrame();
        s.init();
        s.setVisible(true);

    }
}
