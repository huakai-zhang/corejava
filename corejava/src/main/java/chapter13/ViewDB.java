package chapter13;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class ViewDB {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new ViewDBFrame();
            frame.setTitle("ViewDB");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class ViewDBFrame extends JFrame {
    private JButton previousButton;
    private JButton nextButton;
    private JButton deleteButton;
    private JButton saveButton;
    private DataPanel dataPanel;
    private Component scrollPane;
    private JComboBox<String> tableNames;
    private Properties props;
    private CachedRowSet crs;
    private Connection conn;

    public ViewDBFrame() {
        tableNames = new JComboBox<String>();

        try {
            readDatabaseProperties();
            conn = getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            try (ResultSet mrs = meta.getTables(null, null, null, new String[] {"TABLE"})) {
                while (mrs.next()) {
                    tableNames.addItem(mrs.getString(3));
                }
            }

        } catch (SQLException ex) {
            for (Throwable r : ex) {
                r.printStackTrace();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

        tableNames.addActionListener(
                e -> showTable((String) tableNames.getSelectedItem(), conn));
        add(tableNames, BorderLayout.NORTH);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    for (Throwable r : ex) {
                        r.printStackTrace();
                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        previousButton = new JButton("Previous");
        previousButton.addActionListener(event -> showPreviousRow());
        buttonPanel.add(previousButton);

        nextButton = new JButton("Next");
        nextButton.addActionListener(event -> showNextRow());
        buttonPanel.add(nextButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(event -> deleteRow());
        buttonPanel.add(deleteButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(event -> saveChanges());
        buttonPanel.add(saveButton);
        if (tableNames.getItemCount() > 0) {
            showTable(tableNames.getItemAt(0), conn);
        }
    }

    /**
     * 准备显示新表的文本字段，并显示第一行
     * @param tableName
     * @param conn
     */
    public void showTable(String tableName, Connection conn) {
        try(Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery("SELECT * FROM " + tableName)) {
            RowSetFactory factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.setTableName(tableName);
            crs.populate(result);

            if (scrollPane != null) {
                remove(scrollPane);
            }
            dataPanel = new DataPanel(crs);
            scrollPane = new JScrollPane(dataPanel);
            add(scrollPane, BorderLayout.CENTER);
            pack();
            showNextRow();
        } catch (SQLException ex) {
            for (Throwable r : ex) {
                r.printStackTrace();
            }
        }
    }

    public void showPreviousRow() {
        try {
            if (crs == null || crs.isFirst()) {
                return;
            }
            crs.previous();
            dataPanel.showRow(crs);
        } catch (SQLException ex) {
            for (Throwable r : ex) {
                r.printStackTrace();
            }
        }
    }

    public void showNextRow() {
        try {
            if (crs == null || crs.isLast()) {
                return;
            }
            crs.next();
            dataPanel.showRow(crs);
        } catch (SQLException ex) {
            for (Throwable r : ex) {
                r.printStackTrace();
            }
        }
    }

    public void deleteRow() {
        if (crs == null) {
            return;
        }
        new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() throws Exception {
                crs.deleteRow();
                crs.acceptChanges(conn);
                if (crs.isAfterLast()) {
                    if (!crs.last()) {
                        crs = null;
                    }
                }
                return null;
            }

            @Override
            public void done() {
                dataPanel.showRow(crs);
            }
        }.execute();
    }

    public void saveChanges() {
        if (crs == null) {
            return;
        }

        new SwingWorker<Void, Void>() {
            @Override
            public Void doInBackground() throws Exception {
                dataPanel.setRow(crs);
                crs.acceptChanges(conn);
                return null;
            }
        }.execute();
    }

    private void readDatabaseProperties() throws IOException {
        props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("E:\\IDEAFile\\corejava\\src\\chapter13\\section3\\database.properties"))) {
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.getProperty("jdbc.drivers", drivers);
        }
    }

    private Connection getConnection() throws SQLException {
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
}

/**
 * 此面板显示结果集的内容
 */
class DataPanel extends JPanel {
    private java.util.List<JTextField> fields;

    /**
     * 构造数据面板
     * @param rs 此面板显示其内容的结果集
     * @throws SQLException
     */
    public DataPanel(RowSet rs) throws SQLException {
        fields = new ArrayList<>();
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            gbc.gridy = i - 1;

            String columnName = rsmd.getColumnLabel(i);
            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            add(new JLabel(columnName), gbc);

            int columnWidth = rsmd.getColumnDisplaySize(i);
            JTextField tb = new JTextField(columnWidth);
            if (!rsmd.getColumnClassName(i).equals("java.lang.String")) {
                tb.setEditable(false);
            }

            fields.add(tb);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            add(tb, gbc);
        }
    }

    /**
     * 通过用列值填充所有文本字段来显示数据库行
     */
    public void showRow(ResultSet rs) {
        try {
            if (rs == null) {
                return;
            }
            for (int i = 1; i <= fields.size(); i++) {
                String field = rs == null ? "" : rs.getString(i);
                JTextField tb = fields.get(i - 1);
                tb.setText(field);
            }
        } catch (SQLException ex) {
            for (Throwable r : ex) {
                r.printStackTrace();
            }
        }
    }

    /**
     * 将更改的数据更新为行集的当前行
     */
    public void setRow(RowSet rs) throws SQLException {
        for (int i = 1; i <= fields.size(); i++) {
            String field = rs.getString(i);
            JTextField tb = fields.get(i - 1);
            if (!field.equals(tb.getText())) {
                rs.updateString(i, tb.getText());
            }
        }
        rs.updateRow();
    }
}