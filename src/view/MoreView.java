package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import helpers.StyleComponents;

public class MoreView extends JDialog implements ActionListener, KeyListener {
    private JPanel panel;
    private JButton btnReturn, btnCustomer, btnOrder, btnProvider, btnStock, btnUser, btnCheckSales;

    private int space_between = 20;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;

    public MoreView(HomeView parent, String title, boolean modal) {
        // windows configuration
        super(parent, title, true);
        this.setLocationRelativeTo(parent);
        this.setBounds(50, 32, width, height);

        // create components
        panel = new StyleComponents().Panel();

        btnReturn = new StyleComponents().Button("Back", (width / 2 - space_between - 10 - 50),
                (height / 2 - 40 - space_between - 10), 150, 40);

        btnCustomer = new StyleComponents().Button("Customer management", (width / 3 - space_between * 2 - 10 - 150),
                (height / 2 - 40 - space_between - 10), 200, 40);

        btnOrder = new StyleComponents().Button("New order", (width / 3 - space_between * 2 - 10 - 150),
                (height / 3 - 40 - space_between * 2 - 10), 200,
                40);

        btnStock = new StyleComponents().Button("Stock management", (width / 3 - space_between * 2 - 10 - 150),
                (height / 2 + 40 + space_between + 10), 200, 40);

        btnProvider = new StyleComponents().Button("Providers management", (width / 2 + 40 + space_between + 10 + 150),
                (height / 3 - 40 - space_between * 2 - 10), 200, 40);

        btnUser = new StyleComponents().Button("Users management", (width / 2 + 40 + space_between + 10 + 150),
                (height / 2 - 40 - space_between - 10), 200, 40);

        btnCheckSales = new StyleComponents().Button("Check sales", (width / 2 + 40 + space_between + 10 + 150),
                (height / 2 + 40 + space_between + 10), 200, 40);

        // disabled
        // btnCustomer.setEnabled(false);
        btnOrder.setEnabled(false);
        btnStock.setEnabled(false);
        btnProvider.setEnabled(false);
        btnUser.setEnabled(false);
        btnCheckSales.setEnabled(false);

        // listeners
        btnReturn.addActionListener(this);
        btnCustomer.addActionListener(this);
        btnOrder.addActionListener(this);
        btnStock.addActionListener(this);
        btnProvider.addActionListener(this);
        btnUser.addActionListener(this);
        btnCheckSales.addActionListener(this);

        btnReturn.addKeyListener(this);
        btnCustomer.addKeyListener(this);
        btnOrder.addKeyListener(this);
        btnStock.addKeyListener(this);
        btnProvider.addKeyListener(this);
        btnUser.addKeyListener(this);
        btnCheckSales.addKeyListener(this);

        // add components at panel
        panel.add(btnReturn);
        panel.add(btnCustomer);
        panel.add(btnOrder);
        panel.add(btnStock);
        panel.add(btnProvider);
        panel.add(btnUser);
        panel.add(btnCheckSales);

        // add panel at dialog
        this.add(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReturn) {
            this.setVisible(false);
        } else if (e.getSource() == btnCustomer) {
            ManagementCustomerView mcv = new ManagementCustomerView(this, "Management customer", true);
            mcv.setVisible(true);
        } else if (e.getSource() == btnOrder) {
            // disabled
        } else if (e.getSource() == btnProvider) {
            // disabled
        } else if (e.getSource() == btnStock) {
            // disabled
        } else if (e.getSource() == btnUser) {
            // disabled
        } else if (e.getSource() == btnCheckSales) {
            // disabled
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.setVisible(false);
        } else if (e.getKeyCode() == KeyEvent.VK_F1) {
            // disabled
        } else if (e.getKeyCode() == KeyEvent.VK_F2) {
            // disabled
        } else if (e.getKeyCode() == KeyEvent.VK_F3) {
            // disabled
        } else if (e.getKeyCode() == KeyEvent.VK_F4) {
            // disabled
        } else if (e.getKeyCode() == KeyEvent.VK_F5) {
            // disabled
        } else if (e.getKeyCode() == KeyEvent.VK_F6) {
            // disabled
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
