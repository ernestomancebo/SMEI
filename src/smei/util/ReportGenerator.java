package smei.util;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

public class ReportGenerator implements Serializable {

    private static final long serialVersionUID = 1L;
    final ClassLoader cl = Thread.currentThread().getContextClassLoader();

    public void printExcelReport(ArrayList<InputStream> rutaJRXML,
            String rutaXLSX, Map<String, Object> parametros, Connection conexion)
            throws JRException {
        Thread.currentThread().setContextClassLoader(null);

//        ArrayList<JasperPrint> print_list = new ArrayList<JasperPrint>();
        JRXlsxExporter exportador = new JRXlsxExporter();
        JasperPrint print = null;
        try {
//            print_list.add(JasperFillManager.fillReport(
//                    "resources/reports/Maestro_Reservas.jasper",
//                    parametros,
//                    conexion));
            print = JasperFillManager.fillReport(
                    JasperCompileManager.compileReport(rutaJRXML.get(0)), parametros,
                    conexion);
        } catch (JRException e) {
            e.printStackTrace();
        }

//        for (InputStream ruta : rutaJRXML) {
//            print_list.add(JasperFillManager.fillReport(
//                    JasperCompileManager.compileReport(ruta), parametros,
//                    conexion));
//        }
        exportador.setParameter(JRExporterParameter.JASPER_PRINT,
                print);
        exportador.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, rutaXLSX);
        exportador.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, true);
        exportador.setParameter(
                JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
        exportador.setParameter(
                JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER, false);
        exportador.setParameter(
                JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
                true);
        exportador.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
                true);
        exportador.setParameter(
                JRXlsAbstractExporterParameter.IS_FONT_SIZE_FIX_ENABLED, true);
        exportador.exportReport();

        Thread.currentThread().setContextClassLoader(cl);
    }
}
