package helpers;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class StyleComponents {
    private JTextField TextField;
    private JLabel Label;
    private JButton Button;
    private JPanel Panel;
    private JScrollPane ScrollPane;
    private JTable Table;
    private DefaultTableModel TableModel;
    private JSeparator Separator;
    private JComboBox<String> ComboBox;

    public JPanel Panel() {
        this.Panel = new JPanel();
        this.Panel.setLayout(null);
        this.Panel.setBackground(new Color(255, 255, 255));
        return this.Panel;
    }

    public JLabel Label(String text, int x, int y, int width, int height) {
        this.Label = new JLabel(text);
        this.Label.setFont(new java.awt.Font("Nunito", Font.PLAIN, 16));
        this.Label.setBackground(new Color(255, 255, 255));
        this.Label.setForeground(new Color(0, 0, 0));
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

    public JButton Button(String text, int x, int y, int width, int height) {
        this.Button = new JButton(text);
        this.Button.setFont(new java.awt.Font("Nunito", Font.PLAIN, 16));
        this.Button.setBackground(new Color(26, 82, 118));
        this.Button.setForeground(new Color(255, 255, 255));
        this.Button.setBounds(x, y, width, height);
        return this.Button;
    }

    public JScrollPane ScrollPane(int x, int y, int width, int height) {
        this.ScrollPane = new JScrollPane();
        this.ScrollPane.getViewport().setFont(new java.awt.Font("Nunito", Font.PLAIN, 16));
        this.ScrollPane.getViewport().setBackground(new Color(255, 255, 255));
        this.ScrollPane.getViewport().setForeground(new Color(0, 0, 0));
        this.ScrollPane.setBounds(x, y, width, height);
        return this.ScrollPane;
    }

    public JTable Table() {
        this.Table = new JTable();

        // body
        this.Table.setSelectionBackground(new Color(232, 234, 234));
        this.Table.setSelectionForeground(new Color(0, 0, 0));
        this.Table.setBorder(null);
        this.Table.setShowGrid(false);
        this.Table.setRowHeight(25);
        this.Table.setRowMargin(1);

        // header
        ((DefaultTableCellRenderer) this.Table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.LEFT);
        this.Table.getTableHeader().setBackground(new Color(255, 255, 255));
        this.Table.getTableHeader().setForeground(new Color(0, 0, 0));
        this.Table.getTableHeader().setFont(new Font("Nunito", Font.BOLD, 13));

        return this.Table;
    }

    public DefaultTableModel TableModel(String[] columns) {
        this.TableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };

        for (String column : columns) {
            this.TableModel.addColumn(column);
        }

        return this.TableModel;
    }

    public JSeparator Separator(String orientation, int x, int y, int width, int height) {
        this.Separator = new JSeparator();

        if (orientation == "horizontal") {
            this.Separator.setOrientation(SwingConstants.HORIZONTAL);
        } else if (orientation == "vertical") {
            this.Separator.setOrientation(SwingConstants.VERTICAL);
        }

        this.Separator.setForeground(new Color(26, 82, 118));
        this.Separator.setBounds(x, y, width, height);

        return this.Separator;
    }

    public JComboBox<String> ComboBox(String[] items, Color background, Color foreground, Border border, int x, int y,
            int width, int height) {
        this.ComboBox = new JComboBox<>();

        if (items.length > 0) {
            for (String item : items) {
                this.ComboBox.addItem(item);
            }
        } else {
            System.out.println("The ComboBox need al least 1 element");
        }

        this.ComboBox.setBorder(border);
        this.ComboBox.setBackground(background);
        this.ComboBox.setForeground(foreground);
        this.ComboBox.setFont(new Font("Nunito", Font.PLAIN, 16));
        this.ComboBox.setBounds(x, y, width, height);

        return this.ComboBox;
    }

}
