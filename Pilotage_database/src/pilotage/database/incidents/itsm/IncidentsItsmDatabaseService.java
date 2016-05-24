package pilotage.database.incidents.itsm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
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

	public static List<Incidents_Itsm> importExcel(File file) throws Exception {
		List<Incidents_Itsm> list = new ArrayList<Incidents_Itsm>();
		try {
			FileInputStream input_document = new FileInputStream(file);
			// Load workbook
			XSSFWorkbook myWorkBook = new XSSFWorkbook(input_document);
			// Load worksheet
			XSSFSheet mySheet = myWorkBook.getSheetAt(0);

			boolean hasDataFlag = true;
			Cell cell = null;
			Iterator<Row> rowIterator = mySheet.rowIterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				if (row.getRowNum() == 0) {
					continue;
				}

				if (row.getRowNum() == 1) {
					continue;
				}

				cell = row.getCell(0);
				hasDataFlag = (cell != null);
				if (hasDataFlag)
					hasDataFlag = (cell.getCellType() != Cell.CELL_TYPE_BLANK);
				if (hasDataFlag) {
					String idRequete = row.getCell(0).getStringCellValue();
					String etat = row.getCell(3).getStringCellValue();
					String priorite = row.getCell(4).getStringCellValue();
					String prioriteParts[] = priorite.split("-");
					String impact = row.getCell(6).getStringCellValue();
					String impactParts[] = impact.split("-");
					Integer nbRelance = (int) row.getCell(7)
							.getNumericCellValue();
					String urgence = row.getCell(5).getStringCellValue();
					String urgenceParts[] = urgence.split("-");
					String resume = row.getCell(8).getStringCellValue();

					java.util.Date util_date_creation = row.getCell(1)
							.getDateCellValue();
					java.util.Date util_date_modification = row.getCell(2)
							.getDateCellValue();
					Timestamp date_creation = new Timestamp(
							util_date_creation.getTime());
					Timestamp date_modification = new Timestamp(
							util_date_modification.getTime());

					if (priorite.contains("-") && urgence.contains("-")
							&& impact.contains("-")) {
						list.add(new Incidents_Itsm(idRequete,
								date_modification, date_creation, resume, etat,
								prioriteParts[1], urgenceParts[1],
								impactParts[1], nbRelance));
					} else {
						list.add(new Incidents_Itsm(idRequete,
								date_modification, date_creation, resume, etat,
								priorite, urgence, impact, nbRelance));
					}
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
}
