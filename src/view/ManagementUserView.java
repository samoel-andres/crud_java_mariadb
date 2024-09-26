package view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controllers.Controller;
import helpers.StyleComponents;
import helpers.Validator;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagementUserView extends JDialog implements ActionListener, FocusListener, KeyListener {
        private JPanel panel;
        private JButton btnAdd, btnModify, btnRemove, btnSearch, btnClearForm, btnEdit, btnReturn;
        private JTextField txtName, txtLastname, txtDni, txtCurp, txtPhone, txtEmail, txtStreet, txtExtNum, txtIntNum,
                        txtDelegation, txtCountry, txtSearch;
        private JSeparator separatorH1, separatorH2, separatorV1;

        private JScrollPane tScroll;
        private JTable tUsersList;
        private DefaultTableModel tModel;

        private int space_between = 20;
        private int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
        private int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;

        private String[] columns = { "UID", "Name", "Lastname", "CURP" };
        private String[] row = new String[columns.length];

        private String UID;
        private ResultSet userDetails;

        public ManagementUserView(MoreView parent, String title, boolean modal) {
                // windows configuration
                super(parent, title, modal);
                this.setLocationRelativeTo(parent);
                this.setBounds(50, 32, width, height);

                // create components
                panel = new StyleComponents().Panel();

                txtName = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Name"),
                                (width - width + space_between),
                                (height - height + space_between), 300, 50);

                txtLastname = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Lastame"),
                                (width - width + space_between * 2 + 300), (height - height + space_between), 300, 50);

                txtDni = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "DNI"),
                                (width - width + space_between), (height - height + space_between * 4), 300, 50);

                txtCurp = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "CURP"),
                                (width - width + space_between * 2 + 300), (height - height + space_between * 4), 300,
                                50);

                separatorH1 = new StyleComponents().Separator("horizontal", (width - width + space_between),
                                (height - height + space_between * 8), 622, 1);

                txtStreet = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Street"),
                                (width - width + space_between),
                                (height - height + space_between * 9), 300, 50);

                txtExtNum = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Exterior number"),
                                (width - width + space_between),
                                (height - height + space_between * 12), 300, 50);

                txtIntNum = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Interior number"),
                                (width - width + space_between * 2 + 300),
                                (height - height + space_between * 12), 300, 50);

                txtDelegation = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Delegation"),
                                (width - width + space_between),
                                (height - height + space_between * 15), 300, 50);

                txtCountry = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Country"),
                                (width - width + space_between * 2 + 300),
                                (height - height + space_between * 15), 300, 50);

                separatorH2 = new StyleComponents().Separator("horizontal", (width - width + space_between),
                                (height - height + space_between * 19), 622, 1);

                txtPhone = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Phone number"),
                                (width - width + space_between),
                                (height - height + space_between * 20), 300, 50);

                txtEmail = new StyleComponents().Field(new Color(255, 255, 255), getForeground(),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "E-mail"),
                                (width - width + space_between * 2 + 300), (height - height + space_between * 20), 300,
                                50);

                btnAdd = new StyleComponents().Button("Add new user", (width - width + space_between),
                                (height - height + space_between * 24), 192, 40);

                btnModify = new StyleComponents().Button("Save changes", (width - width + space_between * 2 + 192),
                                (height - height + space_between * 24), 192, 40);

                btnClearForm = new StyleComponents().Button("Clear form", (width - width + space_between * 3 + 384),
                                (height - height + space_between * 24), 192, 40);

                btnReturn = new StyleComponents().Button("Back", (width - width + space_between * 3 + 384),
                                (height - height + space_between * 27), 192, 40);

                separatorV1 = new StyleComponents().Separator("vertical", (width - width + space_between * 3 + 600),
                                (height - height + space_between), 1, (height - space_between * 4 - 10));

                txtSearch = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)),
                                                "Enter Name, DNI, CURP or E-mail to search"),
                                (width - width + space_between * 4 + 600), (height - height + space_between), 300, 50);

                btnSearch = new StyleComponents().Button("Search user", (width - width + space_between * 5 + 900),
                                (height - height + space_between + 8), 200, 40);

                tScroll = new StyleComponents().ScrollPane((width - width + space_between * 4 + 602),
                                (height - height + space_between * 4 + 10), 519, (height - space_between * 12 + 1));

                btnRemove = new StyleComponents().Button("Remove", (width - width + space_between * 4 + 601),
                                (height - space_between * 6 - 5), 200, 40);

                btnEdit = new StyleComponents().Button("Edit details", (width - width + space_between * 5 + 901),
                                (height - space_between * 6 - 5), 200, 40);

                tUsersList = new StyleComponents().Table();

                tModel = new StyleComponents().TableModel(columns);

                // listeners
                btnSearch.addActionListener(this);
                txtName.addActionListener(this);
                txtLastname.addActionListener(this);
                txtDni.addActionListener(this);
                txtCurp.addActionListener(this);
                txtStreet.addActionListener(this);
                txtExtNum.addActionListener(this);
                txtIntNum.addActionListener(this);
                txtDelegation.addActionListener(this);
                txtCountry.addActionListener(this);
                txtPhone.addActionListener(this);
                txtEmail.addActionListener(this);
                btnAdd.addActionListener(this);
                btnModify.addActionListener(this);
                btnClearForm.addActionListener(this);
                btnReturn.addActionListener(this);
                txtSearch.addActionListener(this);
                btnRemove.addActionListener(this);
                btnEdit.addActionListener(this);

                btnSearch.addKeyListener(this);
                txtName.addKeyListener(this);
                txtLastname.addKeyListener(this);
                txtDni.addKeyListener(this);
                txtCurp.addKeyListener(this);
                txtStreet.addKeyListener(this);
                txtExtNum.addKeyListener(this);
                txtIntNum.addKeyListener(this);
                txtDelegation.addKeyListener(this);
                txtCountry.addKeyListener(this);
                txtPhone.addKeyListener(this);
                txtEmail.addKeyListener(this);
                btnAdd.addKeyListener(this);
                btnModify.addKeyListener(this);
                btnClearForm.addKeyListener(this);
                btnReturn.addKeyListener(this);
                txtSearch.addKeyListener(this);
                btnRemove.addKeyListener(this);
                btnEdit.addKeyListener(this);

                txtSearch.addFocusListener(this);

                // add components at panel
                panel.add(txtName);
                panel.add(txtLastname);
                panel.add(txtDni);
                panel.add(txtCurp);
                panel.add(separatorH1);
                panel.add(txtStreet);
                panel.add(txtExtNum);
                panel.add(txtIntNum);
                panel.add(txtDelegation);
                panel.add(txtCountry);
                panel.add(separatorH2);
                panel.add(txtPhone);
                panel.add(txtEmail);
                panel.add(btnAdd);
                panel.add(btnModify);
                panel.add(btnClearForm);
                panel.add(btnReturn);
                panel.add(separatorV1);
                panel.add(txtSearch);
                panel.add(btnSearch);
                panel.add(tScroll);
                panel.add(btnRemove);
                panel.add(btnEdit);

                // disabled by default
                btnModify.setVisible(false);

                // prepare table
                tUsersList.setModel(tModel);
                tScroll.setViewportView(tUsersList);

                // load data
                loadUsers();

                // add panel at dialog
                this.add(panel);
        }

        private void loadUsers() {
                // clean table
                int x = tModel.getRowCount() - 1;
                for (int i = x; i >= 0; i--) {
                        tModel.removeRow(i);
                }

                this.clearForm();

                // generate rows
                try {
                        ResultSet users = new Controller().readUsers(this.txtSearch.getText(), "");
                        int rows = 0;

                        while (users.next()) {
                                this.row[0] = users.getString("User ID");
                                this.row[1] = users.getString("User name");
                                this.row[2] = users.getString("User lastname");
                                this.row[3] = users.getString("User CURP");
                                this.tModel.addRow(row);
                                rows++;
                        }

                        if (rows == 0) {
                                JOptionPane.showMessageDialog(this, "Unregistered user", "Information",
                                                JOptionPane.INFORMATION_MESSAGE);
                        }
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Data could not be loaded", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        private void clearForm() {
                txtName.setText("");
                txtLastname.setText("");
                txtDni.setText("");
                txtCurp.setText("");
                txtStreet.setText("");
                txtExtNum.setText("");
                txtIntNum.setText("");
                txtDelegation.setText("");
                txtCountry.setText("");
                txtPhone.setText("");
                txtEmail.setText("");
                btnModify.setVisible(false);
                this.UID = null;
                this.userDetails = null;
        }

        private void loadUserDetails() {
                try {
                        userDetails = new Controller().readUsers("", this.UID);

                        if (userDetails.next()) {
                                txtName.setText(userDetails.getString("User name"));
                                txtLastname.setText(userDetails.getString("User lastname"));
                                txtDni.setText(userDetails.getString("User DNI"));
                                txtCurp.setText(userDetails.getString("User CURP"));
                                txtStreet.setText(userDetails.getString("User address"));
                                txtExtNum.setText(userDetails.getString("User exterior number"));
                                txtIntNum.setText(userDetails.getString("User interior number"));
                                txtDelegation.setText(userDetails.getString("User delegation"));
                                txtCountry.setText(userDetails.getString("User country"));
                                txtPhone.setText(userDetails.getString("User phone"));
                                txtEmail.setText(userDetails.getString("User mail"));
                                btnModify.setVisible(true);
                        } else {
                                this.clearForm();
                        }
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        JOptionPane.showMessageDialog(this, "An error ocurred while loading data in the form", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                }
        }

        private void addNewUser() {
                String value = new Controller(
                                this.txtPhone.getText().trim(),
                                this.txtEmail.getText().toLowerCase().trim(),
                                this.txtStreet.getText().toUpperCase().trim(),
                                this.txtExtNum.getText().toUpperCase().trim(),
                                this.txtIntNum.getText().toUpperCase().trim(),
                                this.txtDelegation.getText().toUpperCase().trim(),
                                this.txtCountry.getText().toUpperCase().trim(),
                                this.txtName.getText().toUpperCase().trim(),
                                this.txtLastname.getText().toUpperCase().trim(),
                                this.txtDni.getText().toUpperCase().trim(),
                                this.txtCurp.getText().toUpperCase().trim())
                                .newUser();

                if (value.equals("address")) {
                        JOptionPane.showMessageDialog(this, "Please, check your address information", "Notice",
                                        JOptionPane.INFORMATION_MESSAGE);
                } else if (value.equals("contact")) {
                        JOptionPane.showMessageDialog(this, "Please, check your contact information", "Notice",
                                        JOptionPane.INFORMATION_MESSAGE);
                } else if (value.equals("user")) {
                        JOptionPane.showMessageDialog(this, "Please, check your personal information", "Notice",
                                        JOptionPane.INFORMATION_MESSAGE);
                } else if (value.equals("general_err")) {
                        JOptionPane.showMessageDialog(this, "Oops, unexpected wrong", "Notice",
                                        JOptionPane.ERROR_MESSAGE);
                } else {
                        String val = new Validator().VerifyInteger(value);

                        if (!val.equals("Err")) {
                                this.clearForm();
                                this.loadUsers();

                                JOptionPane.showMessageDialog(this, "New record successfully saved", "Successful",
                                                JOptionPane.INFORMATION_MESSAGE);
                        } else {
                                JOptionPane.showMessageDialog(this, "Oops, unexpected wrong, not saved", "Notice",
                                                JOptionPane.ERROR_MESSAGE);
                        }
                }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAdd) {
                        if (!this.txtName.getText().trim().isEmpty()
                                        || !this.txtLastname.getText().trim().isEmpty()
                                        || !this.txtDni.getText().trim().isEmpty()
                                        || !this.txtCurp.getText().trim().isEmpty()
                                        || !this.txtStreet.getText().trim().isEmpty()
                                        || !this.txtExtNum.getText().trim().isEmpty()
                                        || !this.txtIntNum.getText().trim().isEmpty()
                                        || !this.txtDelegation.getText().trim().isEmpty()
                                        || !this.txtCountry.getText().trim().isEmpty()
                                        || !this.txtPhone.getText().trim().isEmpty()
                                        || !this.txtEmail.getText().trim().isEmpty()) {
                                String validaEN = new Validator()
                                                .VerifyInteger(this.txtExtNum.getText().trim());
                                String validaIN = new Validator()
                                                .VerifyInteger(this.txtIntNum.getText().trim());
                                String validaPN = new Validator().VerifyInteger(this.txtPhone.getText().trim());
                                if (!validaEN.equals("Err")) {
                                        if (!validaIN.equals("Err")) {
                                                if (!validaPN.equals("Err")) {
                                                        if (this.txtPhone.getText().length() == 10) {
                                                                Pattern pattern = Pattern.compile(
                                                                                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                                                                Matcher matcher = pattern.matcher(this.txtEmail
                                                                                .getText().trim().toLowerCase());

                                                                if (matcher.find()) {
                                                                        this.addNewUser();
                                                                } else {
                                                                        JOptionPane.showMessageDialog(this,
                                                                                        "Oops, your email is invalid, please, enter another or check the syntax",
                                                                                        "Notice",
                                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                }
                                                        } else {
                                                                JOptionPane.showMessageDialog(this,
                                                                                "The phone field must be at least 10 digits",
                                                                                "Notice",
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        }
                                                } else {
                                                        JOptionPane.showMessageDialog(this,
                                                                        "The value in 'Phone number' must be numeric",
                                                                        "Notice",
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                }
                                        } else {
                                                JOptionPane.showMessageDialog(this,
                                                                "The value in 'Interior number' must be numeric",
                                                                "Notice", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                } else {
                                        JOptionPane.showMessageDialog(this,
                                                        "The value in 'Exterior number' must be numeric",
                                                        "Notice", JOptionPane.INFORMATION_MESSAGE);
                                }
                        } else {
                                JOptionPane.showMessageDialog(this, "You must provide complete information", "Notice",
                                                JOptionPane.INFORMATION_MESSAGE);
                        }
                } else if (e.getSource() == btnModify) {
                        // add event
                } else if (e.getSource() == btnRemove) {
                        // add event
                } else if (e.getSource() == btnSearch) {
                        this.loadUsers();
                } else if (e.getSource() == btnClearForm) {
                        this.clearForm();
                        this.txtName.requestFocus();
                } else if (e.getSource() == btnEdit) {
                        this.clearForm();

                        int rowSelected = tUsersList.getSelectedRow();

                        if (rowSelected >= 0) {
                                try {
                                        this.UID = (String) tModel.getValueAt(rowSelected, 0);
                                        this.loadUserDetails();
                                } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(this, "Oops, an unexpected error ocurred",
                                                        "Error", JOptionPane.ERROR_MESSAGE);
                                }
                        } else {
                                JOptionPane.showMessageDialog(this,
                                                "Please, select row from the table for edit the information",
                                                "Information", JOptionPane.INFORMATION_MESSAGE);
                        }
                } else if (e.getSource() == btnReturn) {
                        this.clearForm();
                        this.setVisible(false);
                }
        }

        @Override
        public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        this.clearForm();
                        this.setVisible(false);
                }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void focusGained(FocusEvent e) {
                if (e.getSource() == txtSearch) {
                        this.txtSearch.setText("");
                }
        }

        @Override
        public void focusLost(FocusEvent e) {
        }
}
