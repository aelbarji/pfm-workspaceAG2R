package pilotage.database.changements;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

import pilotage.metier.Changements;
import pilotage.session.PilotageSession;
import pilotage.utils.Pagination;

public class ChangementsDatabaseService {

	public void createNewChangement(String resume, String priorite,
			String etat, Date dateDebut, Date dateFin, String demandeur,
			String valideur) throws Exception {

		Session session = PilotageSession.getCurrentSession();

		try {
			Changements changement = new Changements();
			changement.setResume(resume);
			changement.setPriorite(priorite);
			changement.setEtat(etat);
			changement.setDateDebut(dateDebut);
			changement.setDateFin(dateFin);
			changement.setDemandeur(demandeur);
			changement.setValideur(valideur);
			session.save(changement);
			session.getTransaction().commit();
		} catch (Exception e) {
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Changements> getAll() {

		Session session = PilotageSession.getCurrentSession();
		List<Changements> changementsList = session.createCriteria(
				Changements.class).list();
		session.getTransaction().commit();
		return changementsList;
	}
	
	public static List<Changements> getAll(Pagination<Changements> pagination) {
		
		Session session = PilotageSession.getCurrentSession();
		Criteria criteria = session.createCriteria(Changements.class);
		
		criteria.addOrder(Order.asc("dateDebut"));
		
		List<Changements> c = pagination.setCriteriaPagination(criteria);
		session.getTransaction().commit();
		return c;
		
	}

	public static void saveChangements(List<Changements> list) throws Exception {

		Session session = PilotageSession.getCurrentSession();
		try {
			String stringQuery = "DELETE FROM Changements";
			Query query = session.createQuery(stringQuery);
			query.executeUpdate();
			for (Changements c : list) {
				session.save(c);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			PilotageSession.rollbackTransaction(session);
			throw e;
		}
	}

	public static List<Changements> importExcel(File file) throws Exception {
		List<Changements> list = new ArrayList<Changements>();
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
				cell = row.getCell(0);
				hasDataFlag = (cell != null);
				if (hasDataFlag)
					hasDataFlag = (cell.getCellType() != Cell.CELL_TYPE_BLANK);
				if (hasDataFlag) {
					String idChangement = row.getCell(0).getStringCellValue();
					String resume = row.getCell(1).getStringCellValue();
					String priorite = row.getCell(2).getStringCellValue();
					String etat = row.getCell(3).getStringCellValue();

					java.util.Date util_date_debut = row.getCell(4)
							.getDateCellValue();
					java.util.Date util_date_fin = row.getCell(5)
							.getDateCellValue();
					Timestamp date_debut = new Timestamp(
							util_date_debut.getTime());
					Timestamp date_fin = new Timestamp(util_date_fin.getTime());

					String demandeur;
					if (row.getCell(6) == null) {
						demandeur = "";
					} else {
						demandeur = row.getCell(6).getStringCellValue();
					}

					String valideur;
					if (row.getCell(7) == null) {
						valideur = "";
					} else {
						valideur = row.getCell(7).getStringCellValue();
					}

					list.add(new Changements(resume, priorite, etat,
							date_debut, date_fin, demandeur, valideur, idChangement));
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
