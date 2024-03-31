package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controllers.Controller;
import helpers.StyleComponents;

public class ProductsView extends JDialog implements ActionListener, FocusListener, KeyListener {
    private JPanel panel;
    private JButton btnModify, btnReturn, btnSearch;
    private JTextField txtProductKey;

    private JScrollPane tScroll;
    private JTable tProductsList;
    private DefaultTableModel tModel;

    private int space_between = 20;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;

    private String[] columns = { "Product ID", "Product name", "Product size", "Product price", "Units in stock",
            "Stock ID", "Provider name" };
    private String[] row = new String[columns.length];

    public ProductsView(HomeView parent, String title, boolean modal, boolean showProductKey, boolean showBtnSearch,
            boolean showBtnModify) {
        // window configuration
        super(parent, title, modal);
        this.setLocationRelativeTo(parent);
        this.setBounds(50, 32, width, height);

        // create components
        panel = new StyleComponents().Panel();

        txtProductKey = new StyleComponents().Field(new Color(0, 0, 0), new Color(0, 255, 0), null,
                (width - width + space_between), (height - height + space_between), 300, 40);

        btnSearch = new StyleComponents().Button("Search", (width - width + space_between * 2 + 300),
                (height - height + space_between), 150, 40);

        btnModify = new StyleComponents().Button("Modify product", (width - space_between * 2 - 150),
                (height - height + space_between), 150, 40);

        tScroll = new StyleComponents().ScrollPane((width - width + space_between),
                (height - height + space_between * 2 + 40), (width - space_between * 3),
                (height - space_between * 2 - 160));

        btnReturn = new StyleComponents().Button("Back", (width - space_between * 2 - 150),
                (height - space_between * 5), (width - width + 150), 40);

        tProductsList = new StyleComponents().Table();

        tModel = new StyleComponents().TableModel(columns);

        // listeners
        btnModify.addActionListener(this);
        btnReturn.addActionListener(this);
        btnSearch.addActionListener(this);

        txtProductKey.addKeyListener(this);
        btnModify.addKeyListener(this);
        btnReturn.addKeyListener(this);
        btnSearch.addKeyListener(this);

        txtProductKey.addFocusListener(this);

        // add components at panel
        panel.add(txtProductKey);
        panel.add(btnSearch);
        panel.add(btnModify);
        panel.add(tScroll);
        panel.add(btnReturn);

        // show or hidden
        txtProductKey.setVisible(showProductKey);
        btnSearch.setVisible(showBtnSearch);
        btnModify.setVisible(showBtnModify);

        // prepare table
        tProductsList.setModel(tModel);
        tScroll.setViewportView(tProductsList);

        // request data
        loadProducts();

        // add panel at dialog
        this.add(panel);
    }

    private void loadProducts() {
        // clean table
        int x = tModel.getRowCount() - 1;
        for (int i = x; i >= 0; i--) {
            tModel.removeRow(i);
        }

        // generate rows
        try {
            ResultSet products = new Controller().readProducts(this.txtProductKey.getText());
            int rows = 0;

            while (products.next()) {
                this.row[0] = products.getString("Product ID");
                this.row[1] = products.getString("Product name");
                this.row[2] = products.getString("Product size");
                this.row[3] = products.getString("Product price");
                this.row[4] = products.getString("In stock");
                this.row[5] = products.getString("Stock ID");
                this.row[6] = products.getString("Provider name");
                this.tModel.addRow(row);
                rows++;
            }

            if (rows == 0) {
                JOptionPane.showMessageDialog(this, "No data", "Information", JOptionPane.INFORMATION_MESSAGE);
            }

            this.txtProductKey.setText("");
            products.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Data could not be loaded", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            this.setVisible(false);
        } else if (e.getSource() == btnSearch) {
            this.loadProducts();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == txtProductKey) {
            this.txtProductKey.setText("");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.loadProducts();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        } else if (e.getKeyCode() == KeyEvent.VK_F5) {
            this.txtProductKey.requestFocus();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
