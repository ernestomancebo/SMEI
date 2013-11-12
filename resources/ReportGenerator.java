package com.claro.metricat1.reports;

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
import org.apache.log4j.Logger;

public class ReportGenerator implements Serializable {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ReportGenerator.class
			.getName());
	final ClassLoader cl = Thread.currentThread().getContextClassLoader();

	public void printExcelReport(ArrayList<InputStream> rutaJRXML,
			String rutaXLSX, Map<String, Object> parametros, Connection conexion)
			throws JRException {

		log.info("Entrando a printExcelReport.");

		Thread.currentThread().setContextClassLoader(null);

		ArrayList<JasperPrint> print_list = new ArrayList<JasperPrint>();
		JRXlsxExporter exportador = new JRXlsxExporter();

		for (InputStream ruta : rutaJRXML) {
			log.info("Entrando a reporte: " + ruta);

			print_list.add(JasperFillManager.fillReport(
					JasperCompileManager.compileReport(ruta), parametros,
					conexion));
		}
		log.info("Ha pasado el IS.");

		exportador.setParameter(JRExporterParameter.JASPER_PRINT_LIST,
				print_list);
		exportador.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, rutaXLSX);
		exportador.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, true);
		exportador.setParameter(
				JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
		exportador.setParameter(
				JRXlsAbstractExporterParameter.IS_IGNORE_CELL_BORDER, false);
		exportador
				.setParameter(
						JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,
						true);
		exportador.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,
				true);
		exportador.setParameter(
				JRXlsAbstractExporterParameter.IS_FONT_SIZE_FIX_ENABLED, true);

		exportador.exportReport();

		log.info("Reporte generado.");

		Thread.currentThread().setContextClassLoader(cl);
	}
}