package view;

import java.awt.Color;
import java.awt.Toolkit;
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

public class ManagementProvidersView extends JDialog {
    private JPanel panel;
    private JButton btnAdd, btnModify, btnRemove, btnSearch, btnClearForm, btnEdit, btnReturn, btnViewProducts;
    private JComboBox<String> cboPerson;
    private JTextField txtCompany, txtPhone, txtEmail, txtStreet, txtIntNum, txtExtNum, txtDelegation, txtCountry;
    private JSeparator separatorH1, separatorH2, separatorV1;

    private JScrollPane tScroll;
    private JTable tUserList;
    private DefaultTableModel tModel;

    private int space_between = 20;
    private int width = Toolkit.getDefaultToolkit().getScreenSize().width - 100;
    private int height = Toolkit.getDefaultToolkit().getScreenSize().height - 100;

    private String[] activity = { "Business activity", "Legal entity", "Physics person" };

    public ManagementProvidersView(MoreView parent, String title, boolean modal) {
        // window configuration
        super(parent, title, modal);
        this.setLocationRelativeTo(parent);
        this.setBounds(50, 32, width, height);

        // create components
        panel = new StyleComponents().Panel();

        txtCompany = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Company name"),
                (width - width + space_between), (height - height + space_between), 300, 50);

        cboPerson = new StyleComponents().ComboBox(activity, new Color(255, 255, 255), new Color(0, 0, 0), null,
                (width - width + 300 + space_between * 2), (height - height + space_between + 8), 300, 40);

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
                (width - width + space_between * 2 + 300), (height - height + space_between * 9 - 10), 300, 50);

        txtDelegation = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Delegation"),
                (width - width + space_between), (height - height + space_between * 12), 300, 50);

        txtCountry = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Country"),
                (width - width + space_between * 2 + 300), (height - height + space_between * 12), 300, 50);

        separatorH2 = new StyleComponents().Separator("horizontal", (width - width + space_between),
                (height - height + space_between * 16 - 10), 622, 1);

        txtPhone = new StyleComponents().Field(new Color(255, 255, 255), new Color(0, 0, 0),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "Phone number"),
                (width - width + space_between), (height - height + space_between * 17 - 10), 300, 50);

        txtEmail = new StyleComponents().Field(new Color(255, 255, 255), getForeground(),
                new TitledBorder(new LineBorder(new Color(26, 82, 118)), "E-mail"),
                (width - width + space_between * 2 + 300), (height - height + space_between * 17 - 10), 300, 50);

        btnAdd = new StyleComponents().Button("Add new provider", (width - width + space_between),
                (height - height + space_between * 21 - 10), 192, 40);

        btnModify = new StyleComponents().Button("Save changes", (width - width + space_between * 2 + 192),
                (height - height + space_between * 21 - 10), 192, 40);

        btnClearForm = new StyleComponents().Button("Clear form", (width - width + space_between * 3 + 384),
                (height - height + space_between * 21 - 10), 192, 40);

        btnReturn = new StyleComponents().Button("Back", (width - width + space_between * 3 + 384),
                (height - height + space_between * 24 - 10), 192, 40);

        separatorV1 = new StyleComponents().Separator("vertical", (width - width + space_between * 3 + 600),
                (height - height + space_between), 1, (height - space_between * 4 - 10));

        // listeners

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
        panel.add(btnAdd);
        panel.add(btnModify);
        panel.add(btnClearForm);
        panel.add(btnReturn);
        panel.add(separatorV1);

        // disabled by default

        // prepare table

        // load data

        // add panel at dialog
        this.add(panel);
    }

}
