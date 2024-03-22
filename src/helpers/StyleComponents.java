package helpers;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.LayoutManager;

public class StyleComponents {
    private JTextField TextField;
    private JLabel Label;
    private JButton Button;
    private JPanel Panel;
    private JScrollPane ScrollPane;
    private JTable Table;
    private DefaultTableModel TableModel;

    public JPanel Panel(LayoutManager layout, Color background) {
        this.Panel = new JPanel();
        this.Panel.setLayout(layout);
        this.Panel.setBackground(background);
        return this.Panel;
    }

    public JLabel Label(String text, Color background, Color foreground, int x, int y, int width, int height) {
        this.Label = new JLabel(text);
        this.Label.setFont(new java.awt.Font("Nunito", Font.PLAIN, 16));
        this.Label.setBackground(background);
        this.Label.setForeground(foreground);
        this.Label.setBounds(x, y, width, height);
        return this.Label;
    }

    public JTextField Field(Color background, Color foreground, Border border, int x, int y, int width, int height) {
        this.TextField = new JTextField();
        this.TextField.setFont(new java.awt.Font("Nunito", Font.PLAIN, 16));
        this.TextField.setBackground(background);
        this.TextField.setForeground(foreground);
        this.TextField.setBounds(x, y, width, height);
        this.TextField.setBorder(border);
        return this.TextField;
    }

    public JButton Button(String text, Color background, Color foreground, int x, int y, int width, int height) {
        this.Button = new JButton(text);
        this.Button.setFont(new java.awt.Font("Nunito", Font.PLAIN, 16));
        this.Button.setBackground(background);
        this.Button.setForeground(foreground);
        this.Button.setBounds(x, y, width, height);
        return this.Button;
    }

    public JScrollPane ScrollPane(Color background, Color foreground, int x, int y, int width, int height) {
        this.ScrollPane = new JScrollPane();
        this.ScrollPane.getViewport().setFont(new java.awt.Font("Nunito", Font.PLAIN, 16));
        this.ScrollPane.getViewport().setBackground(background);
        this.ScrollPane.getViewport().setForeground(foreground);
        this.ScrollPane.setBounds(x, y, width, height);
        return this.ScrollPane;
    }

    public JTable Table() {
        this.Table = new JTable();
        return this.Table;
    }

    public DefaultTableModel TableModel(String[] columns) {
        this.TableModel = new DefaultTableModel();

        for (String column : columns) {
            this.TableModel.addColumn(column);
        }

        return this.TableModel;
    }

}
