package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controllers.Controller;
import helpers.StyleComponents;
import helpers.Validator;

public class HomeView extends JFrame implements ActionListener, FocusListener, KeyListener {
        private JPanel panel;
        private JTextField txtProductKey, txtSubtotal;
        private JButton btnAddCart, btnQuoteProduct, btnMore, btnCancelSale, btnPurchase;
        private JScrollPane tScroll;
        private JTable tListCart;
        private DefaultTableModel tModel;
        private JLabel lblUserName, lblUID, lblID, lblUName;

        private int space_between = 20;
        private int width = Toolkit.getDefaultToolkit().getScreenSize().width - 50;
        private int height = Toolkit.getDefaultToolkit().getScreenSize().height - 50;

        private String[] columns = { "Units", "Product ID", "Units in stock", "Product name", "Product size",
                        "Price by unit", "Subtotal" };
        private String[] row = new String[columns.length];

        public HomeView() {
                // window configuration
                super("Store");
                this.setSize(width, height);
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);

                // create components
                panel = new StyleComponents().Panel();

                txtProductKey = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(20, 82, 118)), "Enter PID or Name"),
                                (width - width + space_between), (height - height + space_between),
                                300, 50);

                btnAddCart = new StyleComponents().Button("Add to cart", (width - width + space_between * 2 + 300),
                                (height - height + space_between + 9), 150, 40);

                btnQuoteProduct = new StyleComponents().Button("View stock",
                                (width - width + space_between * 3 + 450), (height - height + space_between + 9),
                                150, 40);

                btnMore = new StyleComponents().Button("More", (width - width + space_between * 4 + 600),
                                (height - height + space_between + 9), 150, 40);

                tScroll = new StyleComponents().ScrollPane((width - width + space_between),
                                (height - height + space_between * 4 + 10), (width - space_between * 3),
                                (height - 190));

                txtSubtotal = new StyleComponents().Field(new Color(0, 0, 0), new Color(0, 255, 0), null,
                                (width - width + space_between), (height - space_between * 4), 300, 40);
                txtSubtotal.setFont(new Font("Nunito", Font.PLAIN, 25));

                lblID = new StyleComponents().Label("UID:", (width - width + space_between * 2 + 365),
                                (height - space_between * 4), 30, 20);
                lblID.setFont(new Font("Nunito", Font.BOLD, 15));
                lblID.setForeground(Color.RED);

                lblUID = new StyleComponents().Label("8", (width - width + space_between * 2 + 405),
                                (height - space_between * 4), 100, 20);

                lblUName = new StyleComponents().Label("USER NAME:", (width - width + space_between * 2 + 300),
                                (height - space_between * 3), 100, 20);
                lblUName.setFont(new Font("Nunito", Font.BOLD, 15));
                lblUName.setForeground(Color.RED);

                lblUserName = new StyleComponents().Label("USUARIO GENERAL", (width - width + space_between * 2 + 405),
                                (height - space_between * 3), 300, 20);

                btnCancelSale = new StyleComponents().Button("Cancel sale", (width - space_between * 10 - 150),
                                (height - space_between * 4), 150, 40);

                btnPurchase = new StyleComponents().Button("Pay", (width - space_between * 9 - 10),
                                (height - space_between * 4), 150, 40);

                tListCart = new StyleComponents().Table();

                tModel = new StyleComponents().TableModel(columns);

                // listeners
                btnAddCart.addActionListener(this);
                btnQuoteProduct.addActionListener(this);
                btnMore.addActionListener(this);
                btnPurchase.addActionListener(this);
                btnCancelSale.addActionListener(this);

                txtProductKey.addKeyListener(this);
                btnAddCart.addKeyListener(this);
                btnQuoteProduct.addKeyListener(this);
                btnMore.addKeyListener(this);
                btnPurchase.addKeyListener(this);
                btnCancelSale.addKeyListener(this);
                tListCart.addKeyListener(this);

                txtProductKey.addFocusListener(this);

                // add components at panel
                panel.add(txtProductKey);
                panel.add(btnAddCart);
                panel.add(btnQuoteProduct);
                panel.add(btnMore);
                panel.add(tScroll);
                panel.add(txtSubtotal);
                panel.add(btnCancelSale);
                panel.add(btnPurchase);
                panel.add(lblID);
                panel.add(lblUID);
                panel.add(lblUName);
                panel.add(lblUserName);

                // disabled components
                this.txtSubtotal.setEditable(false);

                // prepare table
                tListCart.setModel(tModel);
                tScroll.setViewportView(tListCart);

                // initialize subtotal
                this.calculateTotalPurchase();

                // add panel at frame
                this.getContentPane().add(panel);
        }

        public void calculateTotalPurchase() {
                int cantRows = tModel.getRowCount() - 1;
                double subtotal = 0;

                if (cantRows >= 0) {
                        for (int i = cantRows; i >= 0; i--) {
                                subtotal += Double.parseDouble((String) tModel.getValueAt(i, 6));
                        }
                        this.txtSubtotal.setText(
                                        "$ " + new Validator().VerifyDouble(String.valueOf(subtotal)));
                        this.btnCancelSale.setVisible(true);
                        this.btnPurchase.setVisible(true);
                } else {
                        this.btnCancelSale.setVisible(false);
                        this.btnPurchase.setVisible(false);
                        this.txtSubtotal.setText("$ -------");
                }
        }

        public void addToSaleLlist() {
                try {
                        String product = this.txtProductKey.getText();

                        if (!product.isEmpty()) {
                                ResultSet item = new Controller().readProducts(product, null);
                                int rows = 0;

                                while (item.next()) {
                                        String PID = item.getString("Product ID");
                                        double nItems = checkIfExistProduct(PID);
                                        String nItemss = String.valueOf(nItems);
                                        String nItemsss = (String) new Validator().VerifyDouble(nItemss);

                                        this.row[0] = nItemsss;
                                        this.row[1] = item.getString("Product ID");
                                        this.row[2] = item.getString("In stock");
                                        this.row[3] = item.getString("Product name");
                                        this.row[4] = item.getString("Product size");
                                        this.row[5] = item.getString("Product price");

                                        double cantItems = Double.parseDouble(this.row[0]);
                                        double price = Double.parseDouble(item.getString("Product price"));
                                        double cost = (cantItems * price) * 1.0;
                                        String scost = new Validator().VerifyDouble(String.valueOf(cost));

                                        this.row[6] = scost;

                                        this.tModel.addRow(row);
                                        rows++;
                                }

                                if (rows == 0) {
                                        JOptionPane.showMessageDialog(this, "Unregistered product", "Information",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                }

                                this.txtProductKey.setText("");
                                item.close();
                        } else {
                                JOptionPane.showMessageDialog(this, "Please, enter some name or product ID",
                                                "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                } catch (Exception e) {
                        System.out.println("ERROR " + e.getMessage());
                        JOptionPane.showMessageDialog(this, "An error ocurred while retrieving data",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                }
        }

        public double checkIfExistProduct(String productID) {
                double items = 1;
                int x = tModel.getRowCount() - 1;

                for (int i = x; i >= 0; i--) {
                        if (tModel.getValueAt(i, 1).equals(productID)) {
                                String cant = String.valueOf(tModel.getValueAt(i, 0));
                                String cantValidate = new Validator().VerifyDouble(cant);
                                items = Double.parseDouble(cantValidate);
                                tModel.removeRow(i);
                                items++;
                        }
                }

                return items;
        }

        public void removeFromSaleList() {
                int selectedRow = tListCart.getSelectedRow();
                double less = 0;
                String itemss = (String) String.valueOf(tModel.getValueAt(selectedRow, 0));
                double items = Double.parseDouble(new Validator().VerifyDouble(itemss));
                if (items > 1) {
                        try {
                                String op = JOptionPane.showInputDialog(this, "How many items you want to delete?",
                                                "Information", JOptionPane.QUESTION_MESSAGE);

                                if (op != null) {
                                        less = Double.parseDouble(op);
                                        if (!(less > items)) {
                                                items = items - less;
                                                if (items == 0) {
                                                        int opp = JOptionPane.showConfirmDialog(this,
                                                                        "Are you sure that you want remove all items?",
                                                                        "Alert",
                                                                        JOptionPane.WARNING_MESSAGE);
                                                        if (opp == 0) {
                                                                tModel.removeRow(selectedRow);
                                                        }
                                                } else if (items > 0) {
                                                        double priceUnit = Double
                                                                        .parseDouble(new Validator().VerifyDouble(
                                                                                        (String) tModel.getValueAt(
                                                                                                        selectedRow,
                                                                                                        5)));
                                                        String subtotal = new Validator()
                                                                        .VerifyDouble(String
                                                                                        .valueOf(items * priceUnit));
                                                        tModel.setValueAt(items, selectedRow, 0);
                                                        tModel.setValueAt(subtotal, selectedRow, 6);
                                                }
                                        } else {
                                                JOptionPane.showMessageDialog(this,
                                                                "It is not possible to delete " + less
                                                                                + " items 'cause there are only "
                                                                                + items + " items",
                                                                "Alert", JOptionPane.WARNING_MESSAGE);
                                        }

                                }

                        } catch (Exception e) {
                                JOptionPane.showMessageDialog(this,
                                                "You need enter an amount of items that you want delete.", "Alert",
                                                JOptionPane.WARNING_MESSAGE);
                        }

                } else if (items <= 1) {
                        int opp = JOptionPane.showConfirmDialog(this,
                                        "Are you sure that you want remove this item?", "Alert",
                                        JOptionPane.WARNING_MESSAGE);
                        if (opp == 0) {
                                tModel.removeRow(selectedRow);
                        }
                }
        }

        public void cancelSale() {
                int cantRows = tModel.getRowCount() - 1;

                if (cantRows >= 0) {
                        int op = JOptionPane.showConfirmDialog(this, "Are you sure that you want cancel this sale?",
                                        "Alert", JOptionPane.WARNING_MESSAGE);

                        if (op == 0) {
                                for (int i = cantRows; i >= 0; i--) {
                                        tModel.removeRow(i);
                                }
                        }
                }
        }

        private String generateProductList() {
                int cantRows = tModel.getRowCount() - 1;
                String list = "";

                if (cantRows >= 0) {
                        for (int index = 0; index <= cantRows; index++) {
                                list += "|"
                                                + (String) tModel.getValueAt(index, 0) + " Units of "
                                                + (String) tModel.getValueAt(index, 3) + " "
                                                + (String) tModel.getValueAt(index, 4) + ", C/U $"
                                                + (String) tModel.getValueAt(index, 5) + ", Subtotal $"
                                                + (String) tModel.getValueAt(index, 6);
                        }
                        return list;
                }
                return "general_err";
        }

        private String countItems() {
                int cantRows = tModel.getRowCount() - 1;
                String value = "";
                double n = 0;

                if (cantRows >= 0) {
                        for (int index = 0; index <= cantRows; index++) {
                                value = (String) tModel.getValueAt(index, 0);
                                n += Double.parseDouble(value);
                        }
                        return String.valueOf(n);
                }
                return "general_err";
        }

        private String calculateTotal() {
                int cantRows = tModel.getRowCount() - 1;
                double n = 0;
                String value = "";

                if (cantRows >= 0) {
                        for (int index = 0; index <= cantRows; index++) {
                                value = (String) tModel.getValueAt(index, 6);
                                n += Double.parseDouble(value);
                        }
                        return String.valueOf(n);
                }
                return "general_err";
        }

        private String addNewSale(String listItems, String cantItems, String subtotal, String flag, String UID,
                        String CID, String COUPON) {
                String val = new Controller(
                                listItems,
                                cantItems,
                                subtotal,
                                flag)
                                .newSale(new BigDecimal(UID), new BigDecimal(CID), new BigDecimal(COUPON));

                if (val.equals("sale")) {
                        JOptionPane.showMessageDialog(this, "Oops, unexpected wrong on sale", "Notice",
                                        JOptionPane.INFORMATION_MESSAGE);
                } else if (val.equals("key_customer")) {
                        JOptionPane.showMessageDialog(this, "Oops, unexpected wrong on customer key", "Notice",
                                        JOptionPane.INFORMATION_MESSAGE);
                } else if (val.equals("key_user")) {
                        JOptionPane.showMessageDialog(this, "Oops, unexpected wrong on user key", "Notice",
                                        JOptionPane.INFORMATION_MESSAGE);
                } else if (val.equals("general_err")) {
                        JOptionPane.showMessageDialog(this, "Oops, unexpected wrong", "Notice",
                                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                        String valu = new Validator().VerifyInteger(val);

                        if (!valu.equals("Err")) {
                                return "ok";
                        } else {
                                return "Err";
                        }
                }
                return null;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAddCart) {
                        this.addToSaleLlist();
                        this.calculateTotalPurchase();
                } else if (e.getSource() == btnQuoteProduct) {
                        ProductsView spv = new ProductsView(this, "Search product(s)", true, true, true, false, "home",
                                        null);
                        spv.setVisible(true);
                } else if (e.getSource() == btnMore) {
                        MoreView mv = new MoreView(this, "More actions", true);
                        mv.setVisible(true);
                } else if (e.getSource() == btnCancelSale) {
                        this.cancelSale();
                        this.calculateTotalPurchase();
                } else if (e.getSource() == btnPurchase) {
                        String CID = "12";
                        String UID = lblUID.getText();
                        String COUPON = "1";
                        String listItems = this.generateProductList();
                        String cantIntems = this.countItems();
                        String subtotal = this.calculateTotal();
                        String flag = "PAGADA";

                        String value = "";

                        do {
                                value = JOptionPane.showInputDialog(this,
                                                "The value must be numeric.\nCustomer pays...");

                                if (value == null) {
                                        break;
                                } else {
                                        value = new Validator().VerifyDouble(value);
                                }
                        } while (value.equals("Err"));

                        if (value != null & value != "Err") {
                                if (Double.parseDouble(value) < Double.parseDouble(subtotal)) {
                                        JOptionPane.showMessageDialog(this,
                                                        "¡The amount is greater than the income!", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                } else {
                                        String a = this.addNewSale(listItems, cantIntems, subtotal, flag, UID, CID,
                                                        COUPON);

                                        if (a.equals("ok")) {
                                                Double change = Math.abs(Double.parseDouble(subtotal)
                                                                - Double.parseDouble(value));

                                                JOptionPane.showMessageDialog(this,
                                                                "Successfully\nYour remaining cash... $ " + change,
                                                                "Successful", JOptionPane.INFORMATION_MESSAGE);

                                                // clean table
                                                int x = tModel.getRowCount() - 1;
                                                for (int i = x; i >= 0; i--) {
                                                        tModel.removeRow(i);
                                                }
                                        }
                                }
                        }
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
                        this.addToSaleLlist();
                        this.calculateTotalPurchase();
                } else if (e.getKeyCode() == KeyEvent.VK_F1) {
                        ProductsView spv = new ProductsView(this, "Search product(s)", true, true, true, false, "home",
                                        null);
                        spv.setVisible(true);
                } else if (e.getKeyCode() == KeyEvent.VK_F5) {
                        this.txtProductKey.requestFocus();
                } else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                        this.removeFromSaleList();
                        this.calculateTotalPurchase();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        this.cancelSale();
                        this.calculateTotalPurchase();
                } else if (e.getKeyCode() == KeyEvent.VK_F2) {
                        MoreView mv = new MoreView(this, "More actions", true);
                        mv.setVisible(true);
                } else if (e.getKeyCode() == KeyEvent.VK_F4) {
                        int cantRows = tModel.getRowCount() - 1;

                        if (cantRows >= 0) {
                                tListCart.changeSelection(0, 0, false, false);
                                tListCart.requestFocus();
                        }

                        this.calculateTotalPurchase();
                }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
        }

}