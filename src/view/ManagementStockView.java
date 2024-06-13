package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import helpers.StyleComponents;

public class ManagementStockView extends JDialog implements ActionListener, FocusListener, KeyListener, ItemListener {
        private JPanel panel;
        private JButton btnAdd, btnModify, btnRemove, btnClearForm, btnSearch, btnEdit, btnReturn, btnViewProvider;
        private JTextField txtUnits, txtUnitsByUnitType, txtUnitType, txtTotalUnits, txtPriceByUnitType, txtProductName,
                        txtSize, txtPriceByUnit, txtPID, txtSearch;
        private JComboBox<String> cboUnitType, cboSize, cboProvider;
        private JSeparator separatorH1, separatorH2, separatorV1;

        private JScrollPane tScroll;
        private JTable tStockList;
        private DefaultTableModel tModel;

        private int space_between = 20;
        private int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
        private int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;

        private String[] columns = { "SID", "Product name", "Stock" };
        private String[] row = new String[columns.length];

        // data of combobox
        private String[] uTypes = { "Specify the unit type", "Box", "Granel", "Kit", "Lote", "Other" };
        private String[] uSize = { "Specify the unit size", "Small", "Medium", "Big", "Kit", "Other" };
        private String[][] providersList = { { "Specify the provider", "Provider a", "Provider b" },
                        { null, "0", "1" } };

        private String SID;
        private ResultSet stockDetails;

        public ManagementStockView(MoreView parent, String title, boolean modal) {
                // window configuration
                super(parent, title, modal);
                this.setLocationRelativeTo(parent);
                this.setBounds(50, 32, width, height);

                // create components
                panel = new StyleComponents().Panel();

                txtUnits = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "How many units?"),
                                (width - width + space_between), (height - height + space_between), 300, 50);

                cboUnitType = new StyleComponents().ComboBox(uTypes, new Color(255, 255, 255), new Color(0, 0, 0), null,
                                (width - width + 300 + space_between * 2), (height - height + space_between), 300, 50);

                txtUnitsByUnitType = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)),
                                                "How many units contain each unit?"),
                                (width - width + space_between), (height - height + space_between * 2 + 50), 300, 50);

                txtUnitType = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)),
                                                "Please, specify the unit type"),
                                (width - width + space_between * 2 + 300), (height - height + space_between * 2 + 50),
                                300, 50);

                txtTotalUnits = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "This is the total units"),
                                (width - width + space_between), (height - height + space_between * 3 + 100), 300, 50);

                txtPriceByUnitType = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Price by unit type"),
                                (width - width + space_between * 2 + 300), (height - height + space_between * 3 + 100),
                                300, 50);

                separatorH1 = new StyleComponents().Separator("horizontal", (width - width + space_between),
                                (height - height + space_between * 4 + 150), 622, 1);

                txtProductName = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Product name"),
                                (width - width + space_between), (height - height + space_between * 5 + 150), 300, 50);

                cboSize = new StyleComponents().ComboBox(uSize, new Color(255, 255, 255), new Color(0, 0, 0), null,
                                (width - width + space_between * 2 + 300), (height - height + space_between * 5 + 150),
                                300, 50);

                txtPriceByUnit = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Price by unit"),
                                (width - width + space_between), (height - height + space_between * 6 + 200), 300, 50);

                txtSize = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)),
                                                "Please, specify the unit size"),
                                (width - width + space_between * 2 + 300), (height - height + space_between * 6 + 200),
                                300, 50);

                separatorH2 = new StyleComponents().Separator("horizontal", (width - width + space_between),
                                (height - height + space_between * 9 + 210), 622, 1);

                cboProvider = new StyleComponents().ComboBox(providersList[0], new Color(255, 255, 255),
                                new Color(0, 0, 0),
                                null, (width - width + space_between), (height - height + space_between * 10 + 210),
                                300, 50);

                txtPID = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "This is the provider ID"),
                                (width - width + space_between * 2 + 300), (height - height + space_between * 10 + 210),
                                300, 50);

                btnAdd = new StyleComponents().Button("Add new stock", (width - width + space_between),
                                (height - height + space_between * 11 + 260), 192, 40);

                btnModify = new StyleComponents().Button("Save changes", (width - width + space_between * 2 + 192),
                                (height - height + space_between * 11 + 260), 192, 40);

                btnClearForm = new StyleComponents().Button("Clear form", (width - width + space_between * 3 + 384),
                                (height - height + space_between * 11 + 260), 192, 40);

                btnReturn = new StyleComponents().Button("Back", (width - width + space_between * 3 + 384),
                                (height - height + space_between * 12 + 300), 192, 40);

                separatorV1 = new StyleComponents().Separator("vertical", (width - width + space_between * 3 + 600),
                                (height - height + space_between), 1, (height - space_between * 4 - 10));

                txtSearch = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                                new TitledBorder(new LineBorder(new Color(26, 82, 118)),
                                                "Enter Product name, provider or SID to search"),
                                (width - width + space_between * 4 + 600), (height - height + space_between), 300, 50);

                btnSearch = new StyleComponents().Button("Search stock", (width - width + space_between * 5 + 900),
                                (height - height + space_between + 8), 200, 40);

                tScroll = new StyleComponents().ScrollPane((width - width + space_between * 4 + 602),
                                (height - height + space_between * 4 + 10), 519, (height - space_between * 12 + 1));

                btnRemove = new StyleComponents().Button("Remove", (width - width + space_between * 4 + 601),
                                (height - space_between * 6 - 5), 150, 40);

                btnViewProvider = new StyleComponents().Button("View provider",
                                (width - width + space_between * 6 + 751 - 5), (height - space_between * 6 - 5), 150,
                                40);

                btnEdit = new StyleComponents().Button("Edit details", (width - width + space_between * 5 + 951),
                                (height - space_between * 6 - 5), 150, 40);

                tStockList = new StyleComponents().Table();

                tModel = new StyleComponents().TableModel(columns);

                // listeners
                cboUnitType.addItemListener(this);
                cboSize.addItemListener(this);
                cboProvider.addItemListener(this);

                txtUnits.addActionListener(this);
                cboUnitType.addActionListener(this);
                txtUnitsByUnitType.addActionListener(this);
                txtUnitType.addActionListener(this);
                txtTotalUnits.addActionListener(this);
                txtPriceByUnitType.addActionListener(this);
                txtProductName.addActionListener(this);
                cboSize.addActionListener(this);
                txtPriceByUnit.addActionListener(this);
                txtSize.addActionListener(this);
                cboProvider.addActionListener(this);
                txtPID.addActionListener(this);
                btnAdd.addActionListener(this);
                btnModify.addActionListener(this);
                btnClearForm.addActionListener(this);
                btnReturn.addActionListener(this);
                txtSearch.addActionListener(this);
                btnSearch.addActionListener(this);
                btnRemove.addActionListener(this);
                btnViewProvider.addActionListener(this);
                btnEdit.addActionListener(this);

                txtUnits.addKeyListener(this);
                cboUnitType.addKeyListener(this);
                txtUnitsByUnitType.addKeyListener(this);
                txtUnitType.addKeyListener(this);
                txtTotalUnits.addKeyListener(this);
                txtPriceByUnitType.addKeyListener(this);
                txtProductName.addKeyListener(this);
                cboSize.addKeyListener(this);
                txtPriceByUnit.addKeyListener(this);
                txtSize.addKeyListener(this);
                cboProvider.addKeyListener(this);
                txtPID.addKeyListener(this);
                btnAdd.addKeyListener(this);
                btnModify.addKeyListener(this);
                btnClearForm.addKeyListener(this);
                btnReturn.addKeyListener(this);
                txtSearch.addKeyListener(this);
                btnSearch.addKeyListener(this);
                btnRemove.addKeyListener(this);
                btnViewProvider.addKeyListener(this);
                btnEdit.addKeyListener(this);

                txtSearch.addFocusListener(this);

                // add components at panel
                panel.add(txtUnits);
                panel.add(cboUnitType);
                panel.add(txtUnitsByUnitType);
                panel.add(txtUnitType);
                panel.add(txtTotalUnits);
                panel.add(txtPriceByUnitType);
                panel.add(separatorH1);
                panel.add(txtProductName);
                panel.add(cboSize);
                panel.add(txtPriceByUnit);
                panel.add(txtSize);
                panel.add(separatorH2);
                panel.add(cboProvider);
                panel.add(txtPID);
                panel.add(btnAdd);
                panel.add(btnModify);
                panel.add(btnClearForm);
                panel.add(btnReturn);
                panel.add(separatorV1);
                panel.add(txtSearch);
                panel.add(btnSearch);
                panel.add(tScroll);
                panel.add(btnRemove);
                panel.add(btnViewProvider);
                panel.add(btnEdit);

                // non editable
                txtTotalUnits.setEditable(false);
                txtPID.setEditable(false);

                // disabled by default
                txtUnitType.setVisible(false);
                txtSize.setVisible(false);
                btnModify.setEnabled(false);

                // prepare table
                tStockList.setModel(tModel);
                tScroll.setViewportView(tStockList);

                // load data

                // add panel at dialog
                this.add(panel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void focusLost(FocusEvent arg0) {
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
        }

}
