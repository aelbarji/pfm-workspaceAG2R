package pilotage.database.incidents.itsm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import pilotage.metier.Incidents_Itsm;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class IncidentsItsmDatabaseService {

	@SuppressWarnings("unchecked")
	public static List<Incidents_Itsm> getAll() {
		Session session = PilotageSession.getCurrentSession();
		List<Incidents_Itsm> incidentsList = session.createCriteria(
				Incidents_Itsm.class).list();
		session.getTransaction().commit();
		return incidentsList;
	}

	public static List<Incidents_Itsm> getAll(
			Pagination<Incidents_Itsm> pagination) {
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Incidents_Itsm.class);

		criteria.addOrder(Order.asc("dateModification"));

		List<Incidents_Itsm> list = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return list;
	}

	public static void saveIncidents(List<Incidents_Itsm> list)
			throws Exception {

		Session session = PilotageSession.getCurrentSession();
		try {
			String stringQuery = "DELETE FROM Incidents_Itsm";
			Query query = session.createQuery(stringQuery);
			query.executeUpdate();
			for (Incidents_Itsm incident : list) {
				session.save(incident);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	public static String getCellValueAsString(final Cell cell) {
		String res = null;
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				final double cellValDbl = cell.getNumericCellValue();
				res = String.valueOf(cellValDbl).trim();
			} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
				res = null; // on ignore
			} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_ERROR) {
					res = null; // on ignore
				} else if (cell.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
					final double cellValDbl = cell.getNumericCellValue();
					res = String.valueOf(cellValDbl).trim();
				} else {
					res = cell.getStringCellValue();
				}
			} else {
				res = cell.getStringCellValue();
			}
		}

		if (res != null && res.equals("")) {
			res = null;
		}
		return res;
	}

	public static List<Incidents_Itsm> importExcel(File file) throws Exception {
		List<Incidents_Itsm> list = new ArrayList<Incidents_Itsm>();
		try {
			FileInputStream input_document = new FileInputStream(file);
			// Load workbook
			XSSFWorkbook myWorkBook = new XSSFWorkbook(input_document);
			// Load worksheet
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);

			for (int i = 2; i <= mySheet.getLastRowNum(); i++) {
				Row row = mySheet.getRow(i);

				String idRequete = getCellValueAsString(row.getCell(0));
				String etat = getCellValueAsString(row.getCell(21));
				String priorite = getCellValueAsString(row.getCell(22));
				String prioriteParts[] = priorite.split("-");
				String impact = getCellValueAsString(row.getCell(24));
				String impactParts[] = impact.split("-");
				Integer nbRelance = (int) row.getCell(25).getNumericCellValue();
				String urgence = getCellValueAsString(row.getCell(23));
				String urgenceParts[] = urgence.split("-");
				String resume = getCellValueAsString(row.getCell(41));

				java.util.Date util_date_creation = row.getCell(1)
						.getDateCellValue();
				java.util.Date util_date_modification = row.getCell(3)
						.getDateCellValue();
				Timestamp date_creation = new Timestamp(
						util_date_creation.getTime());
				Timestamp date_modification = new Timestamp(
						util_date_modification.getTime());

				if (priorite.contains("-") && urgence.contains("-")
						&& impact.contains("-")) {
					list.add(new Incidents_Itsm(idRequete, date_modification,
							date_creation, resume, etat, prioriteParts[1],
							urgenceParts[1], impactParts[1], nbRelance));
				} else {
					list.add(new Incidents_Itsm(idRequete, date_modification,
							date_creation, resume, etat, priorite, urgence,
							impact, nbRelance));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
}
