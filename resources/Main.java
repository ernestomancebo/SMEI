package com.claro.metricat1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.claro.metricat1.connection.ConnectionFactory;
import com.claro.metricat1.datafeeder.MetricaT1Updater;
import com.claro.metricat1.reports.ReportGenerator;
import com.claro.metricat1.util.Mail;
import com.claro.metricat1.util.Util;

public class Main {

	private static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		MetricaT1Updater mt1u = null;
		Connection conexion = null;
		ReportGenerator reporte = null;
		HashMap<String, Object> mp = null;

		try {
			mt1u = new MetricaT1Updater();
			mt1u.updateMetricaT1();

			reporte = new ReportGenerator();
			mp = new HashMap<String, Object>();

			String[] archivosJasper = { "Resumen_T1", "Calculo(normales)",
					"Calculo(pri)" };
			ArrayList<InputStream> fis = new ArrayList<InputStream>();

			for (String archivo : archivosJasper) {
				fis.add(new FileInputStream(new File("reports/semanal/"
						+ archivo + ".jrxml")));
			}

			log.info("Empezando la generación del reporte");

			conexion = ConnectionFactory.getMetricaOracleConnection();

			reporte.printExcelReport(
					fis,
					"reports/semanal/reporte T1 "
							+ Util.getBegginAndEndOfWeek() + ".xlsx", mp,
					conexion);

			log.info("Reporte T1 generado");
			conexion.close();

			Mail.enviarMail(false);
		} catch (Exception e) {
			log.error(e.toString());
			Mail.enviarMail(true);
			e.printStackTrace();
		} finally {
			try {
				if (conexion != null) {
					conexion.close();
				}
			} catch (Exception e) {
				log.error(e.toString());
				e.printStackTrace();
				Mail.enviarMail(true);
			}
		}
	}
}
