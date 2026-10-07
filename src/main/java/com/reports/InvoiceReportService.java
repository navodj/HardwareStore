package com.reports;

import com.mycompany.hardwarestore.config.DBConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class InvoiceReportService {

   public void showInvoice(int orderId) throws Exception {

    System.out.println("===== INVOICE DEBUG =====");
    System.out.println("Order ID value: " + orderId);
    System.out.println("Order ID Java type: "
            + Integer.valueOf(orderId).getClass().getName());

    InputStream reportStream =
            getClass().getResourceAsStream(
                    "/reports/invoice.jrxml"
            );

    System.out.println("Report found: " + (reportStream != null));

    if (reportStream == null) {
        throw new Exception(
                "Invoice report file could not be found."
        );
    }

    JasperReport report =
            JasperCompileManager.compileReport(reportStream);

    System.out.println("Parameters expected by report:");

    for (var parameter : report.getParameters()) {
        System.out.println(
                parameter.getName()
                + " -> "
                + parameter.getValueClassName()
        );
    }

    Map<String, Object> parameters = new HashMap<>();

    parameters.put(
            "ORDER_ID",
            Integer.valueOf(orderId)
    );

    System.out.println(
            "ORDER_ID being passed: "
            + parameters.get("ORDER_ID")
    );

    try (Connection connection =
                 DBConnection.getConnection()) {

        JasperPrint print =
                JasperFillManager.fillReport(
                        report,
                        parameters,
                        connection
                );

        JasperViewer viewer =
                new JasperViewer(print, false);

        viewer.setTitle(
                "Invoice - Order #" + orderId
        );

        viewer.setVisible(true);
    }
}
}
