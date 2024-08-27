package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import helpers.StyleComponents;

public class RProviderView extends JDialog implements ActionListener, KeyListener, FocusListener {
        private JPanel panel;
        private JButton btnReturn;
        private JComboBox<String> cboPerson;
        private JTextField txtCompany, txtPhone, txtEmail, txtStreet, txtIntNum, txtExtNum, txtDelegation, txtCountry;
        private JSeparator separatorH1, separatorH2;

        private int space_between = 20;
        private int width = 680;
        private int height = Toolkit.getDefaultToolkit().getScreenSize().height - 150;

        private String[] activity = { "Business activity", "Legal entity", "Physics person" };

        public RProviderView(ManagementStockView parent, String title, boolean modal, String PID) {
                // window configuration
                super(parent, title, modal);
                this.setLocationRelativeTo(parent);
                this.setBounds(325, 57, width, height);

                // create components
                panel = new StyleComponents().Panel();

                txtCompany = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Company name"),
                                (width - width + space_between), (height - height + space_between), 300, 50);

                cboPerson = new StyleComponents().ComboBox(activity, new Color(255, 255, 255), new Color(0, 0, 0), null,
                                (width - width + 300 + space_between * 2), (height - height + space_between + 8), 300,
                                40);

                separatorH1 = new StyleComponents().Separator("horizontal", (width - width + space_between),
                                (height - height + space_between * 4 + 10), 622, 1);

                txtStreet = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Street"),
                                (width - width + space_between), (height - height + space_between * 6 - 10), 300, 50);

                txtExtNum = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Exterior number"),
                                (width - width + space_between), (height - height + space_between * 9 - 10), 300, 50);

                txtIntNum = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Interior number"),
                                (width - width + space_between * 2 + 300), (height - height + space_between * 9 - 10),
                                300, 50);

                txtDelegation = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Delegation"),
                                (width - width + space_between), (height - height + space_between * 12), 300, 50);

                txtCountry = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Country"),
                                (width - width + space_between * 2 + 300), (height - height + space_between * 12), 300,
                                50);

                separatorH2 = new StyleComponents().Separator("horizontal", (width - width + space_between),
                                (height - height + space_between * 16 - 10), 622, 1);

                txtPhone = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Phone number"),
                                (width - width + space_between), (height - height + space_between * 17 - 10), 300, 50);

                txtEmail = new StyleComponents().Field(new Color(255, 255, 255), getForeground(),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "E-mail"),
                                (width - width + space_between * 2 + 300), (height - height + space_between * 17 - 10),
                                300, 50);

                btnReturn = new StyleComponents().Button("Back", (width - width + space_between * 3 + 172),
                                (height - height + space_between * 13 + 250), 192, 40);

                // listeners
                txtCompany.addActionListener(this);
                cboPerson.addActionListener(this);
                txtStreet.addActionListener(this);
                txtExtNum.addActionListener(this);
                txtIntNum.addActionListener(this);
                txtDelegation.addActionListener(this);
                txtCountry.addActionListener(this);
                txtPhone.addActionListener(this);
                txtEmail.addActionListener(this);
                btnReturn.addActionListener(this);

                txtCompany.addKeyListener(this);
                cboPerson.addKeyListener(this);
                txtStreet.addKeyListener(this);
                txtExtNum.addKeyListener(this);
                txtIntNum.addKeyListener(this);
                txtDelegation.addKeyListener(this);
                txtCountry.addKeyListener(this);
                txtPhone.addKeyListener(this);
                txtEmail.addKeyListener(this);
                btnReturn.addKeyListener(this);

                // add components at panel
                panel.add(txtCompany);
                panel.add(cboPerson);
                panel.add(separatorH1);
                panel.add(txtStreet);
                panel.add(txtExtNum);
                panel.add(txtIntNum);
                panel.add(txtDelegation);
                panel.add(txtCountry);
                panel.add(separatorH2);
                panel.add(txtPhone);
                panel.add(txtEmail);
                panel.add(btnReturn);

                // disabled by default
                txtCompany.setEditable(false);
                cboPerson.setEditable(false);
                txtStreet.setEditable(false);
                txtExtNum.setEditable(false);
                txtIntNum.setEditable(false);
                txtDelegation.setEditable(false);
                txtCountry.setEditable(false);
                txtPhone.setEditable(false);
                txtEmail.setEditable(false);

                // load data

                // add panel at dialog
                this.add(panel);
        }

        private void clearForm() {
                txtCompany.setText("");
                cboPerson.setSelectedItem(activity[0]);
                txtStreet.setText("");
                txtExtNum.setText("");
                txtIntNum.setText("");
                txtDelegation.setText("");
                txtCountry.setText("");
                txtPhone.setText("");
                txtEmail.setText("");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
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

}
