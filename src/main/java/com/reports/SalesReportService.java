package com.reports;

import com.mycompany.hardwarestore.config.DBConnection;

import java.io.InputStream;
import java.sql.Connection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class SalesReportService {

    public void showSalesReport() throws Exception {

        InputStream reportStream =
                SalesReportService.class.getResourceAsStream(
                        "/reports/sales.jrxml"
                );

        if (reportStream == null) {
            throw new Exception(
                    "Sales report file could not be found."
            );
        }

        JasperReport report =
                JasperCompileManager.compileReport(reportStream);

        try (Connection connection =
                     DBConnection.getConnection()) {

            JasperPrint print =
                    JasperFillManager.fillReport(
                            report,
                            null,
                            connection
                    );

            JasperViewer viewer =
                    new JasperViewer(print, false);

            viewer.setTitle("Sales Performance Report");
            viewer.setVisible(true);
        }
    }

    public static void main(String[] args) {

        try {
            SalesReportService service =
                    new SalesReportService();

            service.showSalesReport();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}