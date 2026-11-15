package productsalesapplication.ProductSalesFrame;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProductSalesFrame extends JFrame {

    private JTextArea txtReportArea;
    private JLabel lblYearsProcessed;
    private final ProductSales productSales;

    public ProductSalesFrame() {
        productSales = new ProductSales();
        initComponents();
    }

    private void initComponents() {
        setTitle("Product Sales Application");
        setSize(380, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem loadItem = new JMenuItem("Load Product Data");
        JMenuItem saveItem = new JMenuItem("Save Product Data");
        JMenuItem clearItem = new JMenuItem("Clear");

        loadItem.addActionListener(e -> loadProductData());
        saveItem.addActionListener(e -> saveProductData());
        clearItem.addActionListener(e -> clearForm());

        toolsMenu.add(loadItem);
        toolsMenu.add(saveItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Buttons Panel
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 8));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 20));

        JButton btnLoad = new JButton("Load Product Data");
        JButton btnSave = new JButton("Save Product Data");

        btnLoad.addActionListener(e -> loadProductData());
        btnSave.addActionListener(e -> saveProductData());

        topPanel.add(btnLoad);
        topPanel.add(btnSave);
        add(topPanel, BorderLayout.NORTH);

        // Text Area Panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        txtReportArea = new JTextArea();
        txtReportArea.setEditable(false);
        txtReportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        centerPanel.add(new JScrollPane(txtReportArea), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Label Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 16, 10, 20));
        lblYearsProcessed = new JLabel("Years Processed: ");
        bottomPanel.add(lblYearsProcessed);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void loadProductData() {
        int total = productSales.GetTotalSales();
        long average = Math.round(productSales.GetAverageSales());
        int overLimit = productSales.GetSalesOverLimit();
        int underLimit = productSales.GetSalesUnderLimit();
        int years = productSales.GetProductsProcessed();

        String report = "Total Sales: " + total + "\n"
                      + "Average Sales: " + average + "\n"
                      + "Sales over limit: " + overLimit + "\n"
                      + "Sales under limit: " + underLimit;

        txtReportArea.setText(report);
        lblYearsProcessed.setText("Years Processed: " + years);
    }

    private void saveProductData() {
        if (txtReportArea.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please load product data first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
            writer.write("DATA LOG\n****************************************\n");
            writer.write(txtReportArea.getText());
            writer.write("\n****************************************");
            JOptionPane.showMessageDialog(this, "Data saved successfully to data.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        txtReportArea.setText("");
        lblYearsProcessed.setText("Years Processed: ");
    }
}
