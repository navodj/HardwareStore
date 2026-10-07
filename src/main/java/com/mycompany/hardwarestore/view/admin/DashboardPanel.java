package com.mycompany.hardwarestore.view.admin;

import com.mycompany.hardwarestore.dao.DashboardDAO;
import com.mycompany.hardwarestore.model.Product;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author navod
 */
public class DashboardPanel extends javax.swing.JPanel {

    // DAO object used to retrieve dashboard data from MySQL
    private final DashboardDAO dashboardDAO = new DashboardDAO();

    /**
     * Creates new form DashboardPanel
     */
    public DashboardPanel() {

        // Creates all Swing components designed using NetBeans
        initComponents();

        // Loads real database data after the components exist
        loadDashboardData();
    }

    /**
     * Loads the main dashboard statistics.
     */
    private void loadDashboardData() {

        try {

            // Get statistics from database
            int totalProducts =
                    dashboardDAO.getTotalProducts();

            int totalSales =
                    dashboardDAO.getTotalSales();

            double totalRevenue =
                    dashboardDAO.getTotalRevenue();


            // Display statistics
            lblTotalProducts.setText(
                    String.valueOf(totalProducts)
            );

            lblTotalSales.setText(
                    String.valueOf(totalSales)
            );

            lblTotalRevenue.setText(
                    String.format(
                            "LKR %,.2f",
                            totalRevenue
                    )
            );


            // Load low-stock products into JTable
            loadLowStockProducts();


        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load dashboard data.\n"
                    + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }


    /**
     * Loads products with low stock into tblLowStock.
     */
    private void loadLowStockProducts()
            throws SQLException {

        // Retrieve products from database
        List<Product> products =
                dashboardDAO.getLowStockProducts();


        // Get the table model used by tblLowStock
        DefaultTableModel model =
                (DefaultTableModel)
                tblLowStock.getModel();


        // Remove the dummy rows created by NetBeans
        model.setRowCount(0);


        // Add every low-stock product to the table
        for (Product product : products) {

            model.addRow(
                    new Object[]{
                        product.getProductName(),
                        product.getStockQuantity()
                    }
            );
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        pnlProductsCard = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalProducts = new javax.swing.JLabel();
        pnlSalesCard = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblTotalSales = new javax.swing.JLabel();
        pnlRevenueCard = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblTotalRevenue = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLowStock = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(421, 170));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setText("Dashboard");

        jLabel1.setText("Overview of Hardware Store");

        pnlProductsCard.setPreferredSize(new java.awt.Dimension(150, 170));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Total Products");

        lblTotalProducts.setText("0");

        javax.swing.GroupLayout pnlProductsCardLayout = new javax.swing.GroupLayout(pnlProductsCard);
        pnlProductsCard.setLayout(pnlProductsCardLayout);
        pnlProductsCardLayout.setHorizontalGroup(
            pnlProductsCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductsCardLayout.createSequentialGroup()
                .addGroup(pnlProductsCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlProductsCardLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2))
                    .addGroup(pnlProductsCardLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblTotalProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlProductsCardLayout.setVerticalGroup(
            pnlProductsCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductsCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(52, 52, 52)
                .addComponent(lblTotalProducts)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pnlSalesCard.setPreferredSize(new java.awt.Dimension(150, 170));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Total Sales");

        lblTotalSales.setText("0");

        javax.swing.GroupLayout pnlSalesCardLayout = new javax.swing.GroupLayout(pnlSalesCard);
        pnlSalesCard.setLayout(pnlSalesCardLayout);
        pnlSalesCardLayout.setHorizontalGroup(
            pnlSalesCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalesCardLayout.createSequentialGroup()
                .addGroup(pnlSalesCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSalesCardLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel7))
                    .addGroup(pnlSalesCardLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblTotalSales, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        pnlSalesCardLayout.setVerticalGroup(
            pnlSalesCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSalesCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(53, 53, 53)
                .addComponent(lblTotalSales)
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pnlRevenueCard.setPreferredSize(new java.awt.Dimension(150, 170));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Total Revenue");

        lblTotalRevenue.setText("LKR 0,00");

        javax.swing.GroupLayout pnlRevenueCardLayout = new javax.swing.GroupLayout(pnlRevenueCard);
        pnlRevenueCard.setLayout(pnlRevenueCardLayout);
        pnlRevenueCardLayout.setHorizontalGroup(
            pnlRevenueCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRevenueCardLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRevenueCardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        pnlRevenueCardLayout.setVerticalGroup(
            pnlRevenueCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRevenueCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(50, 50, 50)
                .addComponent(lblTotalRevenue)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jLabel3.setText("Low Stock Products");

        tblLowStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Product", "Stock"
            }
        ));
        tblLowStock.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(tblLowStock);
        tblLowStock.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlProductsCard, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlSalesCard, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnlRevenueCard, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlProductsCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSalesCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlRevenueCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalProducts;
    private javax.swing.JLabel lblTotalRevenue;
    private javax.swing.JLabel lblTotalSales;
    private javax.swing.JPanel pnlProductsCard;
    private javax.swing.JPanel pnlRevenueCard;
    private javax.swing.JPanel pnlSalesCard;
    private javax.swing.JTable tblLowStock;
    // End of variables declaration//GEN-END:variables
}
