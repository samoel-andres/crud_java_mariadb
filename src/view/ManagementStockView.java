package view;

import java.awt.Color;
import java.awt.Toolkit;
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

public class ManagementStockView extends JDialog {
    private JPanel panel;
    private JButton btnAdd, btnModify, btnRemove, btnClearForm, btnSearch, btnEdit, btnReturn, btnViewProvider;
    private JTextField txtUnits, txtUnitsByUnitType, txtUnitType, txtTotalUnits, txtPriceByUnitType, txtProductName,
            txtSize, txtPriceByUnit, txtPID;
    private JComboBox<String> cboUnitType, cboSize, cboProvider;
    private JSeparator separatorH1, separatorH2, separatorV1;

    private JScrollPane tScroll;
    private JTable tStockList;
    private DefaultTableModel tModel;

    private int space_between = 20;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;

    private String[] columns = {};
    private String[] row = new String[columns.length];

    // data of combobox
    private String[] uTypes = { "Specify the unit type", "Box", "Granel", "Kit", "Lote", "Other" };
    private String[] uSize = { "Specify the unit size", "Small", "Medium", "Big", "Kit", "Other" };
    private String[][] providesList = { { "Provider a", "Provider b" }, { "0", "1" } };

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
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "How many units contain each unit?"),
                (width - width + space_between), (height - height + space_between * 2 + 50), 300, 50);

        txtUnitType = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Please, specify the unit type"),
                (width - width + space_between * 2 + 300), (height - height + space_between * 2 + 50), 300, 50);

        txtTotalUnits = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "This is the total units"),
                (width - width + space_between), (height - height + space_between * 3 + 100), 300, 50);

        txtPriceByUnitType = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Price by unit type"),
                (width - width + space_between * 2 + 300), (height - height + space_between * 3 + 100), 300, 50);

        separatorH1 = new StyleComponents().Separator("horizontal", (width - width + space_between),
                (height - height + space_between * 4 + 150), 622, 1);

        txtProductName = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Product name"),
                (width - width + space_between), (height - height + space_between * 5 + 150), 300, 50);

        cboSize = new StyleComponents().ComboBox(uSize, new Color(255, 255, 255), new Color(0, 0, 0), null,
                (width - width + space_between * 2 + 300), (height - height + space_between * 5 + 150), 300, 50);

        txtPriceByUnit = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Price by unit"),
                (width - width + space_between), (height - height + space_between * 6 + 200), 300, 50);

        txtSize = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Please, specify the unit size"),
                (width - width + space_between * 2 + 300), (height - height + space_between * 6 + 200), 300, 50);

        separatorH2 = new StyleComponents().Separator("horizontal", (width - width + space_between),
                (height - height + space_between * 9 + 210), 622, 1);

        cboProvider = new StyleComponents().ComboBox(providesList[0], new Color(255, 255, 255), new Color(0, 0, 0),
                null, (width - width + space_between), (height - height + space_between * 10 + 210), 300, 50);

        txtPID = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "This is the provider ID"),
                (width - width + space_between * 2 + 300), (height - height + space_between * 10 + 210), 300, 50);

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

        // listeners

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

        // non editable
        txtTotalUnits.setEditable(false);
        txtPID.setEditable(false);

        // disabled by default
        txtUnitType.setVisible(false);
        txtSize.setVisible(false);

        // prepare table

        // load data

        // add panel at dialog
        this.add(panel);
    }

}
