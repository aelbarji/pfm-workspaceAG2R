package pilotage.service.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.util.IOUtils;
import org.apache.poi.ss.usermodel.Comment;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import pilotage.metier.Astreinte_Domaine;
import pilotage.metier.Astreinte_Planning;
import pilotage.metier.Meteo_Caisse;
import pilotage.metier.Meteo_Environnement;
import pilotage.metier.Meteo_EtatPossible;
import pilotage.metier.Meteo_Meteo;
import pilotage.metier.Meteo_Service;
import pilotage.metier.Planning_Nom_Equipe;
import pilotage.metier.Planning_Vacation;
import pilotage.metier.Users;
import pilotage.service.color.ColorService;
import pilotage.service.constants.PilotageConstants;
import pilotage.service.date.DateService;

public class ExportExcel {

	private static final Short TH_COLOR = HSSFColor.GREY_40_PERCENT.index;
	private static final Short SOUS_TH_COLOR = HSSFColor.DARK_RED.index;
	private static final Short CONTENT_COLOR = HSSFColor.WHITE.index;
	private static final Short SERVICE_COLOR = HSSFColor.GREY_25_PERCENT.index;

	/**
	 * Titre des colonnes
	 */

	// colonnes des astreintes
	private static final String ASTREINTES = "Astreintes";
	private static final String PERIODE = "Période";
	private static final String COLLABORATEUR = "Collaborateur";
	private static final String TELEPHONE = "Téléphone";

	// colonnes des planning semaine
	private static final String PILOTE = "Pilote";
	private static final String LUNDI = "Lundi ";
	private static final String MARDI = "Mardi ";
	private static final String MERCREDI = "Mercredi ";
	private static final String JEUDI = "Jeudi ";
	private static final String VENDREDI = "Vendredi ";
	private static final String SAMEDI = "Samedi ";
	private static final String DIMANCHE = "Dimanche ";

	public static HSSFWorkbook exportAstreintePlanning(
			Map<Astreinte_Domaine, List<Astreinte_Planning>> firstWeekMap,
			Map<Astreinte_Domaine, List<Astreinte_Planning>> sencondWeekMap) {
		int rowIndex = 0;
		HSSFRichTextString text;
		HSSFRow row;
		HSSFCell cell;
		HSSFWorkbook lExcelDoc = new HSSFWorkbook();

		// Style du font
		HSSFFont fontWhiteBold = lExcelDoc.createFont();
		fontWhiteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontWhiteBold.setColor(HSSFColor.WHITE.index);

		// Style centré
		HSSFCellStyle centerStyle = lExcelDoc.createCellStyle();
		centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// Style alignement à droite
		HSSFCellStyle rightStyle = lExcelDoc.createCellStyle();
		rightStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		// style des titres de colonnes
		HSSFCellStyle colomnTitleStyle = lExcelDoc.createCellStyle();
		colomnTitleStyle.setFont(fontWhiteBold);
		colomnTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		colomnTitleStyle.setFillForegroundColor(TH_COLOR);
		colomnTitleStyle.setBorderTop((short) 1);
		colomnTitleStyle.setBorderLeft((short) 1);
		colomnTitleStyle.setBorderRight((short) 1);
		colomnTitleStyle.setBorderBottom((short) 1);
		colomnTitleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFSheet sheet = lExcelDoc.createSheet();
		sheet.setColumnWidth(0, 5800);
		sheet.setColumnWidth(1, 12600);
		sheet.setColumnWidth(2, 5800);
		sheet.setColumnWidth(3, 5800);

		// Style type
		HSSFCellStyle typeStyle = lExcelDoc.createCellStyle();
		typeStyle.setFont(fontWhiteBold);
		typeStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		typeStyle.setFillForegroundColor(SOUS_TH_COLOR);
		typeStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		typeStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		typeStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		typeStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// Style contenu
		HSSFCellStyle contentStyle = lExcelDoc.createCellStyle();
		contentStyle.setFillForegroundColor(CONTENT_COLOR);
		contentStyle.setBorderTop((short) 1);
		contentStyle.setBorderLeft((short) 1);
		contentStyle.setBorderBottom((short) 1);
		contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

		// Titre du tableau
		text = new HSSFRichTextString(DateService.getHeaderString(1));
		row = sheet.createRow((short) 0);
		cell = row.createCell(0);
		row.setHeight((short) 400);
		cell.setCellValue(text);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// sheet.addMergedRegion(new CellRangeAddress(0,(short)0,0,(short)3));
		text.applyFont(fontWhiteBold);
		HSSFCellStyle headerStyle = lExcelDoc.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFillForegroundColor(TH_COLOR);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(headerStyle);
		rowIndex++;

		// La blanc de première ligne
		row = sheet.createRow(rowIndex++);
		cell = row.createCell(0);
		row.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));
		cell.setCellStyle(headerStyle);

		// Titre du document
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 300);
		text = new HSSFRichTextString(ASTREINTES);
		cell = row.createCell(0);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(PERIODE);
		cell = row.createCell(1);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(COLLABORATEUR);
		cell = row.createCell(2);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(TELEPHONE);
		cell = row.createCell(3);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		// tableau 1
		for (Entry<Astreinte_Domaine, List<Astreinte_Planning>> firstWeek : firstWeekMap
				.entrySet()) {
			row = sheet.createRow(rowIndex);
			text = new HSSFRichTextString(firstWeek.getKey().getDomaine());
			cell = row.createCell(0);
			row.setHeight((short) 100);
			cell.setCellValue(text);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex++, rowIndex++,
					0, 3));
			cell.setCellStyle(typeStyle);

			for (Astreinte_Planning aPlanning : firstWeek.getValue()) {
				row = sheet.createRow(rowIndex++);

				text = new HSSFRichTextString(aPlanning.getAstreinte()
						.getType().getType());
				row.createCell(0).setCellValue(text);
				row.getCell(0).setCellStyle(contentStyle);

				text = new HSSFRichTextString(DateService.getPeriode(
						aPlanning.getDatedeb(), aPlanning.getDatefin(),
						DateService.p1));
				row.createCell(1).setCellValue(text);
				row.getCell(1).setCellStyle(contentStyle);

				text = new HSSFRichTextString(aPlanning.getAstreinte().getNom()
						+ " " + aPlanning.getAstreinte().getPrenom());
				row.createCell(2).setCellValue(text);
				row.getCell(2).setCellStyle(contentStyle);

				text = new HSSFRichTextString(aPlanning.getTel());
				row.createCell(3).setCellValue(text);
				row.getCell(3).setCellStyle(contentStyle);
			}
		}

		rowIndex++;
		rowIndex++;

		// Titre du tableau
		text = new HSSFRichTextString(DateService.getHeaderString(2));
		row = sheet.createRow((short) rowIndex);
		cell = row.createCell(0);
		row.setHeight((short) 400);
		cell.setCellValue(text);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
		text.applyFont(fontWhiteBold);
		cell.setCellStyle(headerStyle);
		rowIndex++;

		// La blanc de première ligne
		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		row.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 3));
		cell.setCellStyle(headerStyle);
		rowIndex++;

		// Titre du document
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 300);
		text = new HSSFRichTextString(ASTREINTES);
		cell = row.createCell(0);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(PERIODE);
		cell = row.createCell(1);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(COLLABORATEUR);
		cell = row.createCell(2);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(TELEPHONE);
		cell = row.createCell(3);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		// tableau 2
		for (Entry<Astreinte_Domaine, List<Astreinte_Planning>> sencondWeek : sencondWeekMap
				.entrySet()) {
			row = sheet.createRow(rowIndex);
			text = new HSSFRichTextString(sencondWeek.getKey().getDomaine());
			cell = row.createCell(0);
			row.setHeight((short) 100);
			cell.setCellValue(text);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex++, rowIndex++,
					0, 3));
			cell.setCellStyle(typeStyle);

			for (Astreinte_Planning aPlanning : sencondWeek.getValue()) {
				row = sheet.createRow(rowIndex++);

				text = new HSSFRichTextString(aPlanning.getAstreinte()
						.getType().getType());
				row.createCell(0).setCellValue(text);
				row.getCell(0).setCellStyle(contentStyle);

				text = new HSSFRichTextString(DateService.getPeriode(
						aPlanning.getDatedeb(), aPlanning.getDatefin(),
						DateService.p1));
				row.createCell(1).setCellValue(text);
				row.getCell(1).setCellStyle(contentStyle);

				text = new HSSFRichTextString(aPlanning.getAstreinte().getNom()
						+ " " + aPlanning.getAstreinte().getPrenom());
				row.createCell(2).setCellValue(text);
				row.getCell(2).setCellStyle(contentStyle);

				text = new HSSFRichTextString(aPlanning.getTel());
				row.createCell(3).setCellValue(text);
				row.getCell(3).setCellStyle(contentStyle);
			}
		}

		return lExcelDoc;
	}

	public static HSSFWorkbook exportCRA(String nomIntervenant,
			List<List<List<Planning_Vacation>>> listCRA, Integer nbJourMois) {
		HSSFRow row;
		HSSFCell cell;
		HSSFWorkbook lExcelDoc = new HSSFWorkbook();
		Integer rowIndex = 2;
		/****** DEFINITION DES STYLES *****/

		// Style du font
		HSSFFont fontBlackBold = lExcelDoc.createFont();
		fontBlackBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontBlackBold.setColor(HSSFColor.BLACK.index);

		HSSFFont fontRedBoldItalique = lExcelDoc.createFont();
		fontRedBoldItalique.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontRedBoldItalique.setColor(HSSFColor.RED.index);
		fontRedBoldItalique.setItalic(true);

		// Style centré rouge
		HSSFCellStyle centerStyleRed = lExcelDoc.createCellStyle();
		centerStyleRed.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		centerStyleRed.setVerticalAlignment(HSSFCellStyle.VERTICAL_JUSTIFY);
		centerStyleRed.setFont(fontRedBoldItalique);

		// Style centré colonne
		HSSFCellStyle centerStyleTitleColumn = lExcelDoc.createCellStyle();
		centerStyleTitleColumn.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		centerStyleTitleColumn.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		centerStyleTitleColumn
				.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);
		centerStyleTitleColumn
				.setVerticalAlignment(HSSFCellStyle.VERTICAL_JUSTIFY);
		centerStyleTitleColumn.setBorderBottom((short) 2);
		centerStyleTitleColumn.setBorderTop((short) 2);

		centerStyleTitleColumn.setBorderLeft((short) 2);
		centerStyleTitleColumn.setBorderRight((short) 2);
		centerStyleTitleColumn.setFont(fontBlackBold);

		// Style centré gras
		HSSFCellStyle centerStyleBold = lExcelDoc.createCellStyle();
		centerStyleBold.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// centerStyleBold.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
		centerStyleBold.setVerticalAlignment(HSSFCellStyle.VERTICAL_JUSTIFY);
		centerStyleBold.setBorderBottom((short) 1);
		centerStyleBold.setBorderTop((short) 1);

		centerStyleBold.setBorderLeft((short) 1);
		centerStyleBold.setBorderRight((short) 1);
		centerStyleBold.setFont(fontBlackBold);

		// Style alignement à droite
		HSSFCellStyle rightStyle = lExcelDoc.createCellStyle();
		rightStyle.setBorderBottom((short) 1);
		rightStyle.setBorderTop((short) 1);

		rightStyle.setBorderLeft((short) 1);
		rightStyle.setBorderRight((short) 1);
		rightStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		// Style alignement au milieu
		HSSFCellStyle centerStyle = lExcelDoc.createCellStyle();
		centerStyle.setBorderBottom((short) 1);
		centerStyle.setBorderTop((short) 1);

		centerStyle.setBorderLeft((short) 1);
		centerStyle.setBorderRight((short) 1);
		centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// Style alignement au milieu verticale
		HSSFCellStyle centerStyleVert = lExcelDoc.createCellStyle();
		centerStyleVert.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		centerStyleVert.setVerticalAlignment(HSSFCellStyle.VERTICAL_JUSTIFY);
		centerStyleVert.setBorderBottom((short) 1);
		centerStyleVert.setBorderTop((short) 1);

		centerStyleVert.setBorderLeft((short) 1);
		centerStyleVert.setBorderRight((short) 1);
		centerStyleVert.setRotation((short) 90);
		// centerStyle.setWrapText(true);

		HSSFSheet sheet = lExcelDoc.createSheet();

		sheet.setDefaultColumnWidth(13);
		short var = 1750;
		sheet.setColumnWidth(2, var);
		sheet.setColumnWidth(3, var);
		sheet.setColumnWidth(4, var);
		sheet.setColumnWidth(5, var);
		sheet.setColumnWidth(6, var);
		sheet.setColumnWidth(7, var);
		sheet.setColumnWidth(8, var);
		sheet.setColumnWidth(9, var);
		sheet.setColumnWidth(10, var);
		sheet.setColumnWidth(11, var);
		sheet.setColumnWidth(12, var);
		sheet.setColumnWidth(13, var);
		sheet.setColumnWidth(14, var);
		sheet.setColumnWidth(15, var);
		sheet.setColumnWidth(16, var);
		sheet.setColumnWidth(17, var);
		sheet.setColumnWidth(18, var);
		sheet.setColumnWidth(19, var);
		sheet.setColumnWidth(20, var);
		sheet.setColumnWidth(21, var);
		sheet.setColumnWidth(22, var);
		sheet.setColumnWidth(23, var);

		/******* 3 PREMIERS ENCADRES *******/
		int i;
		row = sheet.createRow(rowIndex); // création de la ligne 2

		for (i = 6; i < 15; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(centerStyleTitleColumn);
			if (i == 6)
				cell.setCellValue("RAPPORT D'ACTIVITE");
		}
		LocalDate ld = new LocalDate();
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 6, 14));
		String[] nomMois = { "Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre" };
		for (rowIndex = 4; rowIndex < 9; ++rowIndex) {
			row = sheet.createRow(rowIndex);
			for (int c = 1; c < 9; c++) {
				cell = row.createCell(c);
				cell.setCellStyle(rightStyle);

				if (rowIndex == 4 && c == 1) // MOIS
					cell.setCellValue("Mois :");
				if (rowIndex == 4 && c == 4){ // MOIS
					
					cell.setCellValue(nomMois[ld.getMonthOfYear()-1] + " " + ld.getYear());
				}
				if (rowIndex == 5 && c == 1) // MOIS
					cell.setCellValue("Société Cliente :");
				if (rowIndex == 5 && c == 4) // MOIS
					cell.setCellValue("ITCE");
				if (rowIndex == 6 && c == 1) // MOIS
					cell.setCellValue("Projet :");
				if (rowIndex == 6 && c == 4) // MOIS
					cell.setCellValue("Pilotage Mutualisé");
				if (rowIndex == 7 && c == 1) // MOIS
					cell.setCellValue("Nom/Prénom de l'intervenant :");
				if (rowIndex == 7 && c == 4) // MOIS
					cell.setCellValue(nomIntervenant);
				if (rowIndex == 8 && c == 1) // MOIS
					cell.setCellValue("Signature :");
			}

			for (int c = 10; c < 17; c++) {
				cell = row.createCell(c);
				cell.setCellStyle(rightStyle);

				if (rowIndex == 4 && c == 10) // MOIS
					cell.setCellValue("CLIENT");
				if (rowIndex == 5 && c == 10) // MOIS
					cell.setCellValue("RS :");
				if (rowIndex == 5 && c == 12) // MOIS
					cell.setCellValue("Bernard Bensiam");
				if (rowIndex == 6 && c == 10) // MOIS
					cell.setCellValue("Date");
				if (rowIndex == 6 && c == 12){ // MOIS
					cell.setCellValue(ld.getDayOfMonth() + "/" + ld.getMonthOfYear() + "/" + ld.getYear());
				}
				if (rowIndex == 7 && c == 10) // MOIS
					cell.setCellValue("Signature");
			}

			for (int c = 18; c < 24; c++) {
				cell = row.createCell(c);
				cell.setCellStyle(rightStyle);

				if (rowIndex == 4 && c == 18) // MOIS
					cell.setCellValue("COMMENTAIRES");
			}
		}

		// troisème tableau
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 18, 23));
		sheet.addMergedRegion(new CellRangeAddress(5, 8, 18, 23));

		// deuxieme tableau
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 10, 16));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 10, 11));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 12, 16));
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 10, 11));
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 12, 16));
		sheet.addMergedRegion(new CellRangeAddress(7, 7, 10, 11));
		sheet.addMergedRegion(new CellRangeAddress(7, 8, 12, 16));

		// premier tableau
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 3));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 4, 8));
		// Société cliente
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 3));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 4, 8));
		// PROJET
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 3));
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 4, 8));
		// INTERVENANT
		sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 3));
		sheet.addMergedRegion(new CellRangeAddress(7, 7, 4, 8));

		// SIGNATURE
		sheet.addMergedRegion(new CellRangeAddress(8, 8, 1, 3));
		sheet.addMergedRegion(new CellRangeAddress(8, 8, 4, 8));

		/******* TABLEAU HEURES EFFECTUEES *******/
		rowIndex = rowIndex + 2;
		row = sheet.createRow(rowIndex); // création de la ligne 11

		cell = row.createCell(3);
		cell.setCellValue("LUNDI");
		cell.setCellStyle(centerStyleTitleColumn);
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 3, 5));

		cell = row.createCell(4);
		cell.setCellStyle(centerStyleTitleColumn);
		cell = row.createCell(5);
		cell.setCellStyle(centerStyleTitleColumn);

		cell = row.createCell(6);
		cell.setCellValue("MARDI");
		cell.setCellStyle(centerStyleTitleColumn);
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 6, 8));

		cell = row.createCell(7);
		cell.setCellStyle(centerStyleTitleColumn);
		cell = row.createCell(8);
		cell.setCellStyle(centerStyleTitleColumn);

		cell = row.createCell(9);
		cell.setCellValue("MERCREDI");
		cell.setCellStyle(centerStyleTitleColumn);
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 9, 11));

		cell = row.createCell(10);
		cell.setCellStyle(centerStyleTitleColumn);
		cell = row.createCell(11);
		cell.setCellStyle(centerStyleTitleColumn);

		cell = row.createCell(12);
		cell.setCellValue("JEUDI");
		cell.setCellStyle(centerStyleTitleColumn);
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 12, 14));

		cell = row.createCell(13);
		cell.setCellStyle(centerStyleTitleColumn);
		cell = row.createCell(14);
		cell.setCellStyle(centerStyleTitleColumn);

		cell = row.createCell(15);
		cell.setCellValue("VENDREDI");
		cell.setCellStyle(centerStyleTitleColumn);
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 15, 17));

		cell = row.createCell(16);
		cell.setCellStyle(centerStyleTitleColumn);
		cell = row.createCell(17);
		cell.setCellStyle(centerStyleTitleColumn);

		cell = row.createCell(18);
		cell.setCellValue("SAMEDI");
		cell.setCellStyle(centerStyleTitleColumn);
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 18, 20));

		cell = row.createCell(19);
		cell.setCellStyle(centerStyleTitleColumn);
		cell = row.createCell(20);
		cell.setCellStyle(centerStyleTitleColumn);

		cell = row.createCell(21);
		cell.setCellValue("DIMANCHE");
		cell.setCellStyle(centerStyleTitleColumn);
		sheet.addMergedRegion(new CellRangeAddress(11, 11, 21, 23));

		cell = row.createCell(22);
		cell.setCellStyle(centerStyleTitleColumn);
		cell = row.createCell(23);
		cell.setCellStyle(centerStyleTitleColumn);

		row = sheet.createRow(++rowIndex); // création de la ligne 12
		row.setHeight((short) 1800);
		cell = row.createCell(2);
		cell.setCellValue("SEMAINE");
		cell.setCellStyle(centerStyleVert);

		for (i = 3; i < 22; i = i + 3) {
			cell = row.createCell(i);
			cell.setCellValue("Matin");
			cell.setCellStyle(centerStyleVert);
		}
		for (i = 4; i < 23; i = i + 3) {
			cell = row.createCell(i);
			cell.setCellValue("Après-Midi");
			cell.setCellStyle(centerStyleVert);
		}

		for (i = 5; i < 24; i = i + 3) {
			cell = row.createCell(i);
			cell.setCellValue("Nuit (nb d'heures)");
			cell.setCellStyle(centerStyleVert);
		}

		Duration dtotale = new Duration(0);
		Duration dtotaleSem1 = new Duration(0);
		Duration dtotaleSem2 = new Duration(0);
		Duration dtotaleSem3 = new Duration(0);
		Duration dtotaleSem4 = new Duration(0);
		Duration dtotaleSem5 = new Duration(0);
		Duration dtotaleSem6 = new Duration(0);
		
		Integer tJourSem1 = 0;
		Integer tJourSem2 = 0;
		Integer tJourSem3 = 0;
		Integer tJourSem4 = 0;
		Integer tJourSem5 = 0;
		Integer tJourSem6 = 0;
		Integer tJourAllSem = 0;
		
		Integer tJourMA1 = 0;
		Integer tJourMA2 = 0;
		Integer tJourMA3 = 0;
		Integer tJourMA4 = 0;
		Integer tJourMA5 = 0;
		Integer tJourMA6 = 0;
		Integer tJourMA = 0;

		Duration tmpsNuit1 = new Duration(0);
		Duration tmpsNuit2 = new Duration(0);
		Duration tmpsNuit3 = new Duration(0);
		Duration tmpsNuit4 = new Duration(0);
		Duration tmpsNuit5 = new Duration(0);
		Duration tmpsNuit6 = new Duration(0);
		Duration tmpsNuitAll = new Duration(0);

		for (rowIndex = 13; rowIndex < 19; ++rowIndex) { // création de la ligne
															// 13 à 18
			row = sheet.createRow(rowIndex);
			cell = row.createCell(2);
			cell.setCellStyle(centerStyleBold);
			if (rowIndex == 13) {
				cell.setCellValue("1ère");
			} else if (rowIndex == 14) {
				cell.setCellValue("2ème");
			} else if (rowIndex == 15) {
				cell.setCellValue("3ème");
			} else if (rowIndex == 16) {
				cell.setCellValue("4ème");
			} else if (rowIndex == 17) {
				cell.setCellValue("5ème");
			} else {
				cell.setCellValue("6ème");
			}

			for (int j = 3; j < 24; j++) {
				cell = row.createCell(j);
				cell.setCellStyle(centerStyle);
			}
		}
		
		Long tSem = (long) 0;
		Long tmpsNuit = (long) 0;
		Integer tJourSem = 0;
		boolean jourTravaillé = false;
		Integer cpt = 0;
		int z = 3;
		int ind = 0;
		int noSem = 0;
		int noJour = 0;

		int r;
		for (r = 0; r < 7; r++) {
			if (listCRA.get(noSem).get(r) != null)
				break;
		}
		noJour = r;
		rowIndex = 13;
		
		while (cpt < nbJourMois) {

			row = sheet.getRow(rowIndex);
			if (listCRA.get(noSem).get(noJour).get(0).getId() != null) {

				if ((listCRA.get(noSem).get(noJour).get(0).getPartiJour().getId() == 1 && listCRA.get(noSem).get(noJour).get(1) == null)
						|| (listCRA.get(noSem).get(noJour).get(0).getPartiJour().getId() == 1 && listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() == 5)
						|| (listCRA.get(noSem).get(noJour).get(1) != null && listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() == 1)) {
					ind = z;
					jourTravaillé = true;
				} else if ((listCRA.get(noSem).get(noJour).get(0).getPartiJour().getId() == 2 && listCRA.get(noSem).get(noJour).get(1) == null)
						|| (listCRA.get(noSem).get(noJour).get(0).getPartiJour().getId() == 2 && listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() == 5)
						|| (listCRA.get(noSem).get(noJour).get(1) != null && listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() ==2)) {
					ind = z + 1;
					jourTravaillé = true;
				} else if ((listCRA.get(noSem).get(noJour).get(0).getPartiJour().getId() == 3 && listCRA.get(noSem).get(noJour).get(1) == null)
						|| (listCRA.get(noSem).get(noJour).get(0).getPartiJour().getId() == 3 && listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId().equals(5))
						|| (listCRA.get(noSem).get(noJour).get(1) != null && listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() == 3)) {
					ind = z + 2;
					jourTravaillé = true;
				} else if (listCRA.get(noSem).get(noJour).get(1) != null && listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() == 4) {
					cell = row.getCell(1+z);
					cell.setCellValue("AM");
					tJourMA += 1;
					jourTravaillé = false;
				} else {
					jourTravaillé = false;
				}
				if (jourTravaillé) {
					tJourSem += 1;
					if(listCRA.get(noSem).get(noJour).get(1) == null){
						DateTime heureDeb = new DateTime(listCRA.get(noSem)
								.get(noJour).get(0).getHeureDebut());
						DateTime heureFin = new DateTime(listCRA.get(noSem)
								.get(noJour).get(0).getHeureFin());
						if (heureFin.isBefore(heureDeb))
							heureFin = heureFin.plusDays(1);
						Duration duree = new Duration(heureFin.getMillis() - heureDeb.getMillis());
						cell = row.getCell(ind);
						cell.setCellValue(duree.getStandardHours() + ":" + (duree.getStandardMinutes() % 60));
						tSem += duree.getMillis();
						
						if(ind == z+2)
							tmpsNuit += duree.getMillis();
					}
					else if(listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() == 1
							|| listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() == 2
							|| listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() == 3){
						DateTime heureDeb = new DateTime(listCRA.get(noSem)
								.get(noJour).get(1).getHeureDebut());
						DateTime heureFin = new DateTime(listCRA.get(noSem)
								.get(noJour).get(1).getHeureFin());
						if (heureFin.isBefore(heureDeb))
							heureFin = heureFin.plusDays(1);
						Duration duree = new Duration(heureFin.getMillis() - heureDeb.getMillis());
						cell = row.getCell(ind);
						cell.setCellValue(duree.getStandardHours() + ":" + (duree.getStandardMinutes() % 60));
						tSem += duree.getMillis();
						
						if(ind == z+2)
							tmpsNuit += duree.getMillis();
					}
					else{
						if(listCRA.get(noSem).get(noJour).get(1).getPartiJour().getId() == 5){
							cell = row.getCell(ind);
							cell.setCellValue(listCRA.get(noSem).get(noJour).get(1).getLibelle());
						}
							
					}
				}
				cpt++;
			}
			z = z + 3;
			noJour++;

			if (noJour == 7 || cpt.equals(nbJourMois)) {
				noJour = 0;
				noSem++;
				z = 3;
				if (rowIndex == 13) {
					dtotaleSem1 = dtotaleSem1.plus(tSem);
					tmpsNuit1 = tmpsNuit1.plus(tmpsNuit);
					tJourMA1 = tJourMA;
					tJourSem1 = tJourSem;
				}
				if (rowIndex == 14) {
					dtotaleSem2 = dtotaleSem2.plus(tSem);
					tmpsNuit2 = tmpsNuit2.plus(tmpsNuit);
					tJourMA2 = tJourMA;
					tJourSem2 = tJourSem;
				}
				if (rowIndex == 15) {
					dtotaleSem3 = dtotaleSem3.plus(tSem);
					tmpsNuit3 = tmpsNuit3.plus(tmpsNuit);
					tJourMA3 = tJourMA;
					tJourSem3 = tJourSem;
				}
				if (rowIndex == 16) {
					dtotaleSem4 = dtotaleSem4.plus(tSem);
					tmpsNuit4 = tmpsNuit4.plus(tmpsNuit);
					tJourMA4 = tJourMA;
					tJourSem4 = tJourSem;
				}
				if (rowIndex == 17) {
					dtotaleSem5 = dtotaleSem5.plus(tSem);
					tmpsNuit5 = tmpsNuit5.plus(tmpsNuit);
					tJourMA5 = tJourMA;
					tJourSem5 = tJourSem;
				}
				if (rowIndex == 18) {
					dtotaleSem6 = dtotaleSem6.plus(tSem);
					tmpsNuit6 = tmpsNuit6.plus(tmpsNuit);
					tJourMA6 = tJourMA;
					tJourSem6 = tJourSem;
				}
				tJourSem = 0;
				tSem = (long) 0;
				tmpsNuit = (long) 0;
				rowIndex++;
				tJourMA = 0;
			}
			
		}

		/****** TABLEAUX TOTAUX ET RECAPITULATIF ******/

		for (rowIndex = 20; rowIndex < 22; ++rowIndex) {
			row = sheet.createRow(rowIndex); // création de la ligne 20 et 21
			for (int j = 3; j < 6; j++) {
				cell = row.createCell(j);
				cell.setCellStyle(centerStyleTitleColumn);
				if (j == 3)
					cell.setCellValue("Totaux \n hebdomadaires");
			}

			for (int j = 8; j < 13; j++) {
				cell = row.createCell(j);
				cell.setCellStyle(centerStyleTitleColumn);
				if (j == 8)
					cell.setCellValue("Récapitulatif des \n Absences Hebdomadaires");
			}

		}
		sheet.addMergedRegion(new CellRangeAddress(20, 21, 3, 5));
		sheet.addMergedRegion(new CellRangeAddress(20, 21, 8, 12));

		row = sheet.createRow(rowIndex); // création de la ligne 22
		row.setHeight((short) 3000);
		cell = row.createCell(2);
		cell.setCellValue("SEMAINE");
		cell.setCellStyle(centerStyleVert);

		cell = row.createCell(3);
		cell.setCellValue("Jours travaillés");
		cell.setCellStyle(centerStyleVert);

		cell = row.createCell(4);
		cell.setCellValue("Heures travaillées");
		cell.setCellStyle(centerStyleVert);

		cell = row.createCell(5);
		cell.setCellValue("Heures de nuit");
		cell.setCellStyle(centerStyleVert);

		cell = row.createCell(7);
		cell.setCellValue("SEMAINE");
		cell.setCellStyle(centerStyleVert);

		cell = row.createCell(8);
		cell.setCellValue("Congés Légaux (CL)");
		cell.setCellStyle(centerStyleVert);

		cell = row.createCell(9);
		cell.setCellValue("Récupération Temps \n Travail Employé (RTTE)");
		cell.setCellStyle(centerStyleVert);

		cell = row.createCell(10);
		cell.setCellValue("Récupération Temps \n Travail Employeur (RTTS)");
		cell.setCellStyle(centerStyleVert);
		cell = row.createCell(11);
		cell.setCellValue("Congés exceptionnels (CE)");
		cell.setCellStyle(centerStyleVert);

		cell = row.createCell(12);
		cell.setCellValue("Arrêt Maladie (AM)");
		cell.setCellStyle(centerStyleVert);

		cell = row.createCell(15);
		cell.setCellValue("* A remplir et à co-signer chaque mois et en fin de mission par le client \n et par le collaborateur du  Groupe HN \n * A envoyer au Service Commercial après signature, \n par télécopie au 01.44.67.13.49");
		cell.setCellStyle(centerStyleRed);
		sheet.addMergedRegion(new CellRangeAddress(22, 22, 15, 23));

		for (rowIndex = 23; rowIndex < 30; ++rowIndex) {
			row = sheet.createRow(rowIndex); // création de la ligne 23
			cell = row.createCell(2);
			cell.setCellStyle(centerStyleBold);
			if (rowIndex == 23)
				cell.setCellValue("1ère");
			if (rowIndex == 24)
				cell.setCellValue("2ème");
			if (rowIndex == 25)
				cell.setCellValue("3ème");
			if (rowIndex == 26)
				cell.setCellValue("4ème");
			if (rowIndex == 27)
				cell.setCellValue("5ème");
			if (rowIndex == 28)
				cell.setCellValue("6ème");
			if (rowIndex == 29)
				cell.setCellValue("TOTAL");

			cell = row.createCell(7);
			cell.setCellStyle(centerStyleBold);
			if (rowIndex == 23)
				cell.setCellValue("1ère");
			if (rowIndex == 24)
				cell.setCellValue("2ème");
			if (rowIndex == 25)
				cell.setCellValue("3ème");
			if (rowIndex == 26)
				cell.setCellValue("4ème");
			if (rowIndex == 27)
				cell.setCellValue("5ème");
			if (rowIndex == 28)
				cell.setCellValue("6ème");
			if (rowIndex == 29)
				cell.setCellValue("TOTAL");

			
			 for (int j = 8; j < 13; j++) { 
				 	cell = row.createCell(j);
			 		cell.setCellStyle(centerStyle); 
			 }
		}

		dtotale = dtotale.plus(dtotaleSem1.getMillis());
		dtotale = dtotale.plus(dtotaleSem2.getMillis());
		dtotale = dtotale.plus(dtotaleSem3.getMillis());
		dtotale = dtotale.plus(dtotaleSem4.getMillis());
		dtotale = dtotale.plus(dtotaleSem5.getMillis());
		dtotale = dtotale.plus(dtotaleSem6.getMillis());

		tmpsNuitAll = tmpsNuitAll.plus(tmpsNuit1.getMillis());
		tmpsNuitAll = tmpsNuitAll.plus(tmpsNuit2.getMillis());
		tmpsNuitAll = tmpsNuitAll.plus(tmpsNuit3.getMillis());
		tmpsNuitAll = tmpsNuitAll.plus(tmpsNuit4.getMillis());
		tmpsNuitAll = tmpsNuitAll.plus(tmpsNuit5.getMillis());
		tmpsNuitAll = tmpsNuitAll.plus(tmpsNuit6.getMillis());

		tJourAllSem += tJourSem1;
		tJourAllSem += tJourSem2;
		tJourAllSem += tJourSem3;
		tJourAllSem += tJourSem4;
		tJourAllSem += tJourSem5;
		tJourAllSem += tJourSem6;
		String s = "";

		row = sheet.getRow(23);
		cell = row.createCell(3);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(tJourSem1);

		cell = row.createCell(4);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(dtotaleSem1.getStandardHours() + "h"
				+ (dtotaleSem1.getStandardMinutes() % 60));
		cell = row.createCell(5);
		cell.setCellStyle(centerStyle);
		s = tmpsNuit1.getStandardHours() + "h"
				+ (tmpsNuit1.getStandardMinutes() % 60);
		s = s.equals("0h0") ? s = "" : s;
		cell.setCellValue(s);

		cell = row.getCell(12);
		cell.setCellValue(tJourMA1);
		
		row = sheet.getRow(24);

		cell = row.createCell(3);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(tJourSem2);

		cell = row.createCell(4);
		cell.setCellStyle(centerStyle);
		s = dtotaleSem2.getStandardHours() + "h"
				+ (dtotaleSem2.getStandardMinutes() % 60);
		s = s.equals("0h0") ? s = "" : s;
		cell.setCellValue(s);

		cell = row.createCell(5);
		cell.setCellStyle(centerStyle);
		s = tmpsNuit2.getStandardHours() + "h"
				+ (tmpsNuit2.getStandardMinutes() % 60);
		s = s.equals("0h0") ? s = "" : s;
		cell.setCellValue(s);
		cell = row.getCell(12);
		cell.setCellValue(tJourMA2);
		
		row = sheet.getRow(25);

		cell = row.createCell(3);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(tJourSem3);

		cell = row.createCell(4);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(dtotaleSem3.getStandardHours() + "h"
				+ (dtotaleSem3.getStandardMinutes() % 60));

		cell = row.createCell(5);
		cell.setCellStyle(centerStyle);
		s = tmpsNuit3.getStandardHours() + "h"
				+ (tmpsNuit3.getStandardMinutes() % 60);
		s = s.equals("0h0") ? s = "" : s;
		cell.setCellValue(s);
		cell = row.getCell(12);
		cell.setCellValue(tJourMA3);

		row = sheet.getRow(26);

		cell = row.createCell(3);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(tJourSem4);

		cell = row.createCell(4);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(dtotaleSem4.getStandardHours() + "h"
				+ (dtotaleSem4.getStandardMinutes() % 60));

		cell = row.createCell(5);
		cell.setCellStyle(centerStyle);
		s = tmpsNuit4.getStandardHours() + "h"
				+ (tmpsNuit4.getStandardMinutes() % 60);
		s = s.equals("0h0") ? s = "" : s;
		cell.setCellValue(s);
		cell = row.getCell(12);
		cell.setCellValue(tJourMA4);
		
		row = sheet.getRow(27);

		cell = row.createCell(3);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(tJourSem5);
		cell = row.createCell(4);

		cell.setCellStyle(centerStyle);
		cell.setCellValue(dtotaleSem5.getStandardHours() + "h"
				+ (dtotaleSem5.getStandardMinutes() % 60));

		cell = row.createCell(5);
		cell.setCellStyle(centerStyle);
		s = tmpsNuit5.getStandardHours() + "h"
				+ (tmpsNuit5.getStandardMinutes() % 60);
		s = s.equals("0h0") ? s = "" : s;
		cell.setCellValue(s);
		cell = row.getCell(12);
		cell.setCellValue(tJourMA5);
		
		row = sheet.getRow(28);
		
		cell = row.createCell(3);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(tJourSem6);
		cell = row.createCell(4);
		
		cell.setCellStyle(centerStyle);
		cell.setCellValue(dtotaleSem6.getStandardHours() + "h"
				+ (dtotaleSem6.getStandardMinutes() % 60));

		cell = row.createCell(5);
		cell.setCellStyle(centerStyle);
		s = tmpsNuit6.getStandardHours() + "h"
				+ (tmpsNuit6.getStandardMinutes() % 60);
		s = s.equals("0h0") ? s = "" : s;
		cell.setCellValue(s);
		cell = row.getCell(12);
		cell.setCellValue(tJourMA6);
		
		row = sheet.getRow(29);

		cell = row.createCell(3);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(tJourAllSem);

		cell = row.createCell(4);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(dtotale.getStandardHours() + "h"
				+ (dtotale.getStandardMinutes() % 60));

		cell = row.createCell(5);
		cell.setCellStyle(centerStyle);
		cell.setCellValue(tmpsNuitAll.getStandardHours() + "h"
				+ (tmpsNuitAll.getStandardMinutes() % 60));
		cell = row.getCell(12);
		cell.setCellValue(tJourMA1 + tJourMA2 + tJourMA3 + tJourMA4 + tJourMA5 + tJourMA6);

		/*********************************************************************/
		/************** DEUXIEME FEUILLE ASTREINTES + HEURES SUPP ************/
		/*********************************************************************/

		HSSFSheet sheet2 = lExcelDoc.createSheet();
		sheet2.setDefaultColumnWidth(11);

		row = sheet2.createRow(2); // création de la ligne 2
		for (i = 3; i < 11; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(centerStyleTitleColumn);
			if (i == 3)
				cell.setCellValue("ASTREINTES ET HEURES SUPPLEMENTAIRES");
		}
		sheet2.addMergedRegion(new CellRangeAddress(2, 2, 3, 10));

		for (int l = 4; l < 9; l++) {
			row = sheet2.createRow(l);
			for (int c = 1; c < 6; c++) {
				cell = row.createCell(c);
				cell.setCellStyle(rightStyle);

				if (l == 4 && c == 1) // MOIS
					cell.setCellValue("Mois :");
				if (l == 4 && c == 3) // MOIS
					cell.setCellValue("Novembre 2013");
				if (l == 5 && c == 1) // MOIS
					cell.setCellValue("Société Cliente :");
				if (l == 5 && c == 3) // MOIS
					cell.setCellValue("ITCE");
				if (l == 6 && c == 1) // MOIS
					cell.setCellValue("Projet :");
				if (l == 6 && c == 3) // MOIS
					cell.setCellValue("Pilotage Mutualisé");
				if (l == 7 && c == 1) // MOIS
					cell.setCellValue("Nom/Prénom de l'intervenant :");
				if (l == 7 && c == 3) // MOIS
					cell.setCellValue(nomIntervenant);
				if (l == 8 && c == 1) // MOIS
					cell.setCellValue("Signature :");
			}

			for (int c = 7; c < 11; c++) {
				cell = row.createCell(c);
				cell.setCellStyle(rightStyle);

				if (l == 4 && c == 7) // MOIS
					cell.setCellValue("CLIENT");
				if (l == 5 && c == 7) // MOIS
					cell.setCellValue("RS :");
				if (l == 5 && c == 9) // MOIS
					cell.setCellValue("Bernard Bensiam");
				if (l == 6 && c == 7) // MOIS
					cell.setCellValue("Date");
				if (l == 6 && c == 9) // MOIS
					cell.setCellValue("03/01/2014");
				if (l == 7 && c == 7) // MOIS
					cell.setCellValue("Signature");
			}
		}

		// deuxieme tableau
		sheet2.addMergedRegion(new CellRangeAddress(4, 4, 7, 10));
		sheet2.addMergedRegion(new CellRangeAddress(5, 5, 7, 8));
		sheet2.addMergedRegion(new CellRangeAddress(5, 5, 9, 10));
		sheet2.addMergedRegion(new CellRangeAddress(6, 6, 7, 8));
		sheet2.addMergedRegion(new CellRangeAddress(6, 6, 9, 10));
		sheet2.addMergedRegion(new CellRangeAddress(7, 7, 7, 8));
		sheet2.addMergedRegion(new CellRangeAddress(7, 8, 9, 10));

		// premier tableau
		sheet2.addMergedRegion(new CellRangeAddress(4, 4, 1, 2));
		sheet2.addMergedRegion(new CellRangeAddress(4, 4, 3, 5));
		// Société cliente
		sheet2.addMergedRegion(new CellRangeAddress(5, 5, 1, 2));
		sheet2.addMergedRegion(new CellRangeAddress(5, 5, 3, 5));
		// PROJET
		sheet2.addMergedRegion(new CellRangeAddress(6, 6, 1, 2));
		sheet2.addMergedRegion(new CellRangeAddress(6, 6, 3, 5));
		// INTERVENANT
		sheet2.addMergedRegion(new CellRangeAddress(7, 7, 1, 2));
		sheet2.addMergedRegion(new CellRangeAddress(7, 7, 3, 5));

		// SIGNATURE
		sheet2.addMergedRegion(new CellRangeAddress(8, 8, 1, 2));
		sheet2.addMergedRegion(new CellRangeAddress(8, 8, 3, 5));

		/** TABLEAU ASTREINTE ET HEURES SUPPLEMENTAIRES **/

		row = sheet2.createRow(11); // création de la ligne 11

		for (i = 1; i < 8; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(centerStyleTitleColumn);
			if (i == 1)
				cell.setCellValue("ASTREINTES");
		}
		sheet2.addMergedRegion(new CellRangeAddress(11, 11, 1, 7));

		for (i = 10; i < 20; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(centerStyleTitleColumn);
			if (i == 10)
				cell.setCellValue("HEURES SUPPLEMENTAIRES (Présence sur site client)");
		}
		sheet2.addMergedRegion(new CellRangeAddress(11, 11, 10, 19));

		row = sheet2.createRow(12); // création de la ligne 12
		cell = row.createCell(1);
		cell.setCellValue("Dates");
		cell.setCellStyle(centerStyle);

		cell = row.createCell(2);
		cell.setCellValue("En semaine (Jour ou Nuit)");
		cell.setCellStyle(centerStyle);
		sheet2.addMergedRegion(new CellRangeAddress(12, 12, 2, 3));

		cell = row.createCell(4);
		cell.setCellValue("Samedi (Jour ou Nuit)");
		cell.setCellStyle(centerStyle);
		sheet2.addMergedRegion(new CellRangeAddress(12, 12, 4, 5));

		cell = row.createCell(6);
		cell.setCellValue("Dimanche et Jour Férié");
		cell.setCellStyle(centerStyle);
		sheet2.addMergedRegion(new CellRangeAddress(12, 12, 6, 7));

		cell = row.createCell(10);
		cell.setCellValue("Du Lundi au Vendredi");
		cell.setCellStyle(centerStyle);
		sheet2.addMergedRegion(new CellRangeAddress(12, 12, 10, 11));

		cell = row.createCell(12);
		cell.setCellValue("Samedi (Jour)");
		cell.setCellStyle(centerStyle);
		sheet2.addMergedRegion(new CellRangeAddress(12, 12, 12, 13));

		cell = row.createCell(14);
		cell.setCellValue("Samedi (Nuit)");
		cell.setCellStyle(centerStyle);
		sheet2.addMergedRegion(new CellRangeAddress(12, 12, 14, 15));

		cell = row.createCell(16);
		cell.setCellValue("Dimanche");
		cell.setCellStyle(centerStyle);
		sheet2.addMergedRegion(new CellRangeAddress(12, 12, 16, 17));

		cell = row.createCell(18);
		cell.setCellValue("Jour Férié");
		cell.setCellStyle(centerStyle);
		sheet2.addMergedRegion(new CellRangeAddress(12, 12, 18, 19));

		row = sheet2.createRow(13); // création de la ligne 13

		for (i = 1; i < 8; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(centerStyle);
		}

		cell = row.createCell(9);
		cell.setCellValue("Dates");
		cell.setCellStyle(centerStyle);

		for (i = 10; i < 19; i = i + 2) {
			cell = row.createCell(i);
			cell.setCellValue("Plage Horaire");
			cell.setCellStyle(centerStyle);
		}

		for (i = 11; i < 20; i = i + 2) {
			cell = row.createCell(i);
			cell.setCellValue("Nb d'heures");
			cell.setCellStyle(centerStyle);
		}

		for (int j = 14; j < 20; j++) {
			row = sheet2.createRow(j);
			for (i = 1; i < 8; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(centerStyle);
			}

			for (i = 9; i < 20; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(centerStyle);
			}
		}

		row = sheet2.createRow(21); // création de la ligne 21

		for (i = 1; i < 7; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(centerStyleTitleColumn);
			if (i == 1)
				cell.setCellValue("APPELS (Pendant Astreintes)");
		}
		sheet2.addMergedRegion(new CellRangeAddress(21, 21, 1, 6));

		for (i = 9; i < 17; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(centerStyleTitleColumn);
			if (i == 9)
				cell.setCellValue("COMMENTAIRES");
		}
		sheet2.addMergedRegion(new CellRangeAddress(21, 21, 9, 16));

		for (int l = 22; l < 29; l++) {
			row = sheet2.createRow(l);
			for (int c = 1; c < 7; c++) {
				cell = row.createCell(c);
				cell.setCellStyle(centerStyle);
				if (c == 1 && l == 22)
					cell.setCellValue("Dates");
				if (c == 3 && l == 22)
					cell.setCellValue("Nombres d'appels");
				if (c == 5 && l == 22)
					cell.setCellValue("Intervention");

			}

			for (int c = 9; c < 17; c++) {
				cell = row.createCell(c);
				cell.setCellStyle(centerStyle);
			}
		}
		sheet2.addMergedRegion(new CellRangeAddress(22, 28, 9, 16));
		sheet2.addMergedRegion(new CellRangeAddress(22, 22, 1, 2));
		sheet2.addMergedRegion(new CellRangeAddress(22, 22, 3, 4));
		sheet2.addMergedRegion(new CellRangeAddress(22, 22, 5, 6));
		return lExcelDoc;

	}

	public static HSSFWorkbook exportPlanningSemaine(
			Map<Integer, String> mapJour, List<Map<String, String>> mapSemaine,
			Map<Integer, String> mapNumSemaine, Map<String, String> mapAnnee,
			String numSelectedSemaine, String numSelectedAnnee,
			List<Planning_Vacation> listeCouleur) {
		int rowIndex = 0;
		HSSFRichTextString text;
		HSSFRow row;
		HSSFCell cell;
		HSSFWorkbook lExcelDoc = new HSSFWorkbook();

		// on prepare une liste d'index de couleur temporaires
		List<Short> listeIndexCouleurDejaUtilise = new ArrayList<Short>();
		// short compteurListeIndexCouleur=0;

		// Style du font
		HSSFFont fontWhiteBold = lExcelDoc.createFont();
		fontWhiteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontWhiteBold.setColor(HSSFColor.WHITE.index);

		// Style du font petite
		HSSFFont fontPetite = lExcelDoc.createFont();
		fontPetite.setFontHeightInPoints((short) 8);
		fontPetite.setColor(HSSFColor.BLACK.index);

		// Style centré
		HSSFCellStyle centerStyle = lExcelDoc.createCellStyle();
		centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// Style alignement à droite
		HSSFCellStyle rightStyle = lExcelDoc.createCellStyle();
		rightStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		// style des titres de colonnes
		HSSFCellStyle colomnTitleStyle = lExcelDoc.createCellStyle();
		colomnTitleStyle.setFont(fontWhiteBold);
		colomnTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		colomnTitleStyle.setFillForegroundColor(TH_COLOR);
		colomnTitleStyle.setBorderTop((short) 1);
		colomnTitleStyle.setBorderLeft((short) 1);
		colomnTitleStyle.setBorderRight((short) 1);
		colomnTitleStyle.setBorderBottom((short) 1);
		colomnTitleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFSheet sheet = lExcelDoc.createSheet();
		// sheet.setColumnWidth(0, (short)5800);
		sheet.setColumnWidth(0, (short) 5800);
		sheet.setColumnWidth(1, (short) 5800);
		sheet.setColumnWidth(2, (short) 5800);
		sheet.setColumnWidth(3, (short) 5800);
		sheet.setColumnWidth(4, (short) 5800);
		sheet.setColumnWidth(5, (short) 5800);
		sheet.setColumnWidth(6, (short) 5800);
		sheet.setColumnWidth(7, (short) 5800);

		// Style type
		HSSFCellStyle typeStyle = lExcelDoc.createCellStyle();
		typeStyle.setFont(fontWhiteBold);
		typeStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		typeStyle.setFillForegroundColor(SOUS_TH_COLOR);
		typeStyle.setBorderTop((short) 1);
		typeStyle.setBorderLeft((short) 1);
		typeStyle.setBorderRight((short) 1);
		typeStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// Style contenu
		HSSFCellStyle contentStyle = lExcelDoc.createCellStyle();
		contentStyle.setFillForegroundColor(CONTENT_COLOR);
		contentStyle.setBorderTop((short) 1);
		contentStyle.setBorderLeft((short) 1);
		contentStyle.setBorderRight((short) 1);
		contentStyle.setBorderBottom((short) 1);
		contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

		// Titre du tableau
		int selectedAnnee;
		int selectedSemaine;
		String libelleAnnee = "";
		String libelleSemaine = "";
		String title = "Planning hebdomadaire des pilotes ";
		String titleVacation = " Légende vacation ";
		selectedAnnee = Integer.parseInt(numSelectedAnnee);
		libelleAnnee = mapAnnee.get(String.valueOf(selectedAnnee));
		selectedSemaine = Integer.parseInt(numSelectedSemaine);
		libelleSemaine = mapNumSemaine.get(selectedSemaine);

		StringBuffer bufferVacation = new StringBuffer(titleVacation);

		// titre vacation
		text = new HSSFRichTextString(new String(bufferVacation));
		row = sheet.createRow((short) 0);
		row.setHeight((short) 400);
		cell = row.createCell(0);
		CellRangeAddress cellsLegend = new CellRangeAddress(0, 0, 0, 9);
		cell.setCellValue(text);
		sheet.addMergedRegion(cellsLegend);
		text.applyFont(fontWhiteBold);
		HSSFCellStyle headerStyle = lExcelDoc.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFillForegroundColor(TH_COLOR);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(headerStyle);
		rowIndex++;
		HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);

		// couleurs de vacation
		row = sheet.createRow(rowIndex++);
		int k = 0;
		for (Planning_Vacation planning_vacation : listeCouleur) {
			text = new HSSFRichTextString(planning_vacation.getLibelle());
			String couleur = planning_vacation.getCodeCouleur();

			cell = row.createCell(k);
			row.getCell((k)).setCellValue(text);

			HSSFCellStyle couleurStyle = lExcelDoc.createCellStyle();
			couleurStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			couleurStyle.setFont(fontPetite);

			// on reconstitue le code couleur récupérée si il ne fait pas partie
			// de la palette
			HSSFColor hssfcolorCouleur = new HSSFColor();
			HSSFPalette paletteCouleur = lExcelDoc.getCustomPalette();

			byte red = ColorService.valueRedColor(couleur);
			byte green = ColorService.valueGreenColor(couleur);
			byte blue = ColorService.valueBlueColor(couleur);

			hssfcolorCouleur = paletteCouleur.findColor(red, green, blue);

			if (hssfcolorCouleur == null) {
				// on definit un index nouveau pour chaque nouvelle couleur
				// utilisée
				Short nouvelIndex = ColorService
						.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
				// on ajoute l'index dans la liste
				listeIndexCouleurDejaUtilise.add(nouvelIndex);
				// on fige la couleur de l'index
				paletteCouleur.setColorAtIndex(nouvelIndex, red, green, blue);
				hssfcolorCouleur = paletteCouleur.getColor(nouvelIndex);
			}

			couleurStyle.setFillForegroundColor(hssfcolorCouleur.getIndex());

			couleurStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			row.getCell((k)).setCellStyle(couleurStyle);
			CellRangeAddress cellsColor = new CellRangeAddress(row.getRowNum(),
					row.getRowNum(), k, k/* +1 */);
			HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN,
					cellsColor, sheet, lExcelDoc);
			HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN,
					cellsColor, sheet, lExcelDoc);
			HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellsColor,
					sheet, lExcelDoc);
			HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellsColor,
					sheet, lExcelDoc);

			k += 1;
		}

		StringBuffer buffer = new StringBuffer();
		buffer.append(title.trim());
		buffer.append('-');
		buffer.append(libelleAnnee.trim());
		buffer.append('-');
		buffer.append(libelleSemaine.trim());

		text = new HSSFRichTextString(new String(buffer));
		row = sheet.createRow((short) 0);
		cell = row.createCell(0);
		row.setHeight((short) 400);
		cell.setCellValue(text);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 9));
		text.applyFont(fontWhiteBold);
		cell.setCellStyle(headerStyle);

		// La blanc de première ligne
		row = sheet.createRow(rowIndex++);
		cell = row.createCell(0);
		row.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 9));
		cell.setCellStyle(headerStyle);

		// Titre du document
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 300);
		text = new HSSFRichTextString(PILOTE);
		cell = row.createCell(0);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(LUNDI + mapJour.get(1));
		cell = row.createCell(1);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(MARDI + mapJour.get(2));
		cell = row.createCell(2);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(MERCREDI + mapJour.get(3));
		cell = row.createCell(3);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(JEUDI + mapJour.get(4));
		cell = row.createCell(4);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(VENDREDI + mapJour.get(5));
		cell = row.createCell(5);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(SAMEDI + mapJour.get(6));
		cell = row.createCell(6);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		text = new HSSFRichTextString(DIMANCHE + mapJour.get(7));
		cell = row.createCell(7);
		cell.setCellValue(text);
		cell.setCellStyle(colomnTitleStyle);

		// tableau des planning de la semaine
		// <s:iterator value="mapSemaine" status="stat">

		// tableau 1
		for (Map<String, String> ligneUser : mapSemaine) {

			row = sheet.createRow(rowIndex++);
			text = new HSSFRichTextString(ligneUser.get("pilote"));
			row.createCell(0).setCellValue(text);
			row.getCell(0).setCellStyle(contentStyle);

			// lundi
			text = new HSSFRichTextString(ligneUser.get("lundiText"));
			cell = row.createCell(1);
			cell.setCellValue(text);
			String couleur = (String) ligneUser.get("lundi");

			HSSFCellStyle lundiStyle = lExcelDoc.createCellStyle();
			lundiStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			lundiStyle.setBorderTop((short) 1);
			lundiStyle.setBorderLeft((short) 1);
			lundiStyle.setBorderBottom((short) 1);

			// on reconstitue le code couleur récupérée si il ne fait pas partie
			// de la palette
			byte red;
			byte green;
			byte blue;

			if (!couleur.equals("")) {
				HSSFColor hssfcolorLundi = new HSSFColor();
				HSSFPalette paletteLundi = lExcelDoc.getCustomPalette();

				red = ColorService.valueRedColor(couleur);
				green = ColorService.valueGreenColor(couleur);
				blue = ColorService.valueBlueColor(couleur);

				hssfcolorLundi = paletteLundi.findColor(red, green, blue);

				if (hssfcolorLundi == null) {
					// paletteLundi.setColorAtIndex(HSSFColor.LAVENDER.index,
					// red, green, blue);
					// hssfcolorLundi =
					// paletteLundi.getColor(HSSFColor.LAVENDER.index);
					// on definit un index nouveau pour chaque nouvelle couleur
					// utilisée
					Short nouvelIndex = ColorService
							.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
					// on ajoute l'index dans la liste
					listeIndexCouleurDejaUtilise.add(nouvelIndex);
					// on fige la couleur de l'index
					paletteLundi.setColorAtIndex(nouvelIndex, red, green, blue);
					hssfcolorLundi = paletteLundi.getColor(nouvelIndex);

				}

				lundiStyle.setFillForegroundColor(hssfcolorLundi.getIndex());

			} else {
				lundiStyle.setFillForegroundColor(CONTENT_COLOR);
			}
			lundiStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(lundiStyle);

			// mardi
			HSSFCellStyle mardiStyle = lExcelDoc.createCellStyle();
			mardiStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			mardiStyle.setBorderTop((short) 1);
			mardiStyle.setBorderLeft((short) 1);
			mardiStyle.setBorderBottom((short) 1);

			if (!couleur.equals("")) {
				HSSFColor hssfcolorMardi = new HSSFColor();
				HSSFPalette paletteMardi = lExcelDoc.getCustomPalette();
				text = new HSSFRichTextString(ligneUser.get("mardiText"));
				cell = row.createCell(2);
				cell.setCellValue(text);

				couleur = (String) ligneUser.get("mardi");

				// on reconstitue le code couleur récupérée si il ne fait pas
				// partie de la palette

				red = ColorService.valueRedColor(couleur);
				green = ColorService.valueGreenColor(couleur);
				blue = ColorService.valueBlueColor(couleur);

				hssfcolorMardi = paletteMardi.findColor(red, green, blue);
				if (hssfcolorMardi == null) {
					// paletteMardi.setColorAtIndex(HSSFColor.GOLD.index, red,
					// green, blue);
					// hssfcolorMardi =
					// paletteMardi.getColor(HSSFColor.GOLD.index);

					// on definit un index nouveau pour chaque nouvelle couleur
					// utilisée
					Short nouvelIndex = ColorService
							.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
					// on ajoute l'index dans la liste
					listeIndexCouleurDejaUtilise.add(nouvelIndex);
					// on fige la couleur de l'index
					paletteMardi.setColorAtIndex(nouvelIndex, red, green, blue);
					hssfcolorMardi = paletteMardi.getColor(nouvelIndex);
				}

				mardiStyle.setFillForegroundColor(hssfcolorMardi.getIndex());

			} else {
				mardiStyle.setFillForegroundColor(CONTENT_COLOR);
			}
			mardiStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(mardiStyle);

			// mercredi
			HSSFPalette paletteMercredi = lExcelDoc.getCustomPalette();
			HSSFColor hssfcolorMercredi = new HSSFColor();
			text = new HSSFRichTextString(ligneUser.get("mercrediText"));
			cell = row.createCell(3);
			cell.setCellValue(text);

			couleur = (String) ligneUser.get("mercredi");
			HSSFCellStyle mercrediStyle = lExcelDoc.createCellStyle();
			mercrediStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			mercrediStyle.setBorderTop((short) 1);
			mercrediStyle.setBorderLeft((short) 1);
			mercrediStyle.setBorderBottom((short) 1);
			// on reconstitue le code couleur récupérée si il ne fait pas partie
			// de la palette
			if (!couleur.equals("")) {
				red = ColorService.valueRedColor(couleur);
				green = ColorService.valueGreenColor(couleur);
				blue = ColorService.valueBlueColor(couleur);

				hssfcolorMercredi = paletteMercredi.findColor(red, green, blue);
				if (hssfcolorMercredi == null) {
					// paletteMercredi.setColorAtIndex(HSSFColor.BROWN.index,
					// red, green, blue);
					// hssfcolorMercredi =
					// paletteMercredi.getColor(HSSFColor.BROWN.index);
					// on definit un index nouveau pour chaque nouvelle couleur
					// utilisée
					Short nouvelIndex = ColorService
							.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
					// on ajoute l'index dans la liste
					listeIndexCouleurDejaUtilise.add(nouvelIndex);
					// on fige la couleur de l'index
					paletteMercredi.setColorAtIndex(nouvelIndex, red, green,
							blue);
					hssfcolorMercredi = paletteMercredi.getColor(nouvelIndex);
				}
				mercrediStyle.setFillForegroundColor(hssfcolorMercredi
						.getIndex());
			} else {
				mercrediStyle.setFillForegroundColor(CONTENT_COLOR);
			}

			mercrediStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(mercrediStyle);

			// jeudi
			HSSFPalette paletteJeudi = lExcelDoc.getCustomPalette();
			HSSFColor hssfcolorJeudi = new HSSFColor();
			text = new HSSFRichTextString(ligneUser.get("jeudiText"));
			cell = row.createCell(4);
			cell.setCellValue(text);

			couleur = (String) ligneUser.get("jeudi");
			HSSFCellStyle jeudiStyle = lExcelDoc.createCellStyle();
			jeudiStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			jeudiStyle.setBorderTop((short) 1);
			jeudiStyle.setBorderLeft((short) 1);
			jeudiStyle.setBorderBottom((short) 1);
			// on reconstitue le code couleur récupérée si il ne fait pas partie
			// de la palette

			if (!couleur.equals("")) {
				red = ColorService.valueRedColor(couleur);
				green = ColorService.valueGreenColor(couleur);
				blue = ColorService.valueBlueColor(couleur);

				hssfcolorJeudi = paletteJeudi.findColor(red, green, blue);
				if (hssfcolorJeudi == null) {
					// paletteJeudi.setColorAtIndex(HSSFColor.AQUA.index, red,
					// green, blue);
					// hssfcolorJeudi =
					// paletteJeudi.getColor(HSSFColor.AQUA.index);
					// on definit un index nouveau pour chaque nouvelle couleur
					// utilisée
					Short nouvelIndex = ColorService
							.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
					// on ajoute l'index dans la liste
					listeIndexCouleurDejaUtilise.add(nouvelIndex);
					// on fige la couleur de l'index
					paletteJeudi.setColorAtIndex(nouvelIndex, red, green, blue);
					hssfcolorJeudi = paletteJeudi.getColor(nouvelIndex);
				}
				jeudiStyle.setFillForegroundColor(hssfcolorJeudi.getIndex());
			} else {
				jeudiStyle.setFillForegroundColor(CONTENT_COLOR);
			}

			jeudiStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(jeudiStyle);

			// vendredi
			HSSFPalette paletteVendredi = lExcelDoc.getCustomPalette();
			HSSFColor hssfcolorVendredi = new HSSFColor();
			text = new HSSFRichTextString(ligneUser.get("vendrediText"));
			cell = row.createCell(5);
			cell.setCellValue(text);

			couleur = (String) ligneUser.get("vendredi");
			HSSFCellStyle vendrediStyle = lExcelDoc.createCellStyle();
			vendrediStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			vendrediStyle.setBorderTop((short) 1);
			vendrediStyle.setBorderLeft((short) 1);
			vendrediStyle.setBorderBottom((short) 1);
			// on reconstitue le code couleur récupérée si il ne fait pas partie
			// de la palette

			if (!couleur.equals("")) {
				red = ColorService.valueRedColor(couleur);
				green = ColorService.valueGreenColor(couleur);
				blue = ColorService.valueBlueColor(couleur);

				hssfcolorVendredi = paletteVendredi.findColor(red, green, blue);
				if (hssfcolorVendredi == null) {
					// paletteVendredi.setColorAtIndex(HSSFColor.INDIGO.index,
					// red, green, blue);
					// hssfcolorVendredi =
					// paletteVendredi.getColor(HSSFColor.INDIGO.index);
					// on definit un index nouveau pour chaque nouvelle couleur
					// utilisée
					Short nouvelIndex = ColorService
							.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
					// on ajoute l'index dans la liste
					listeIndexCouleurDejaUtilise.add(nouvelIndex);
					// on fige la couleur de l'index
					paletteVendredi.setColorAtIndex(nouvelIndex, red, green,
							blue);
					hssfcolorVendredi = paletteVendredi.getColor(nouvelIndex);
				}
				vendrediStyle.setFillForegroundColor(hssfcolorVendredi
						.getIndex());
			} else {
				vendrediStyle.setFillForegroundColor(CONTENT_COLOR);
			}

			vendrediStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(vendrediStyle);

			// samedi
			HSSFPalette paletteSamedi = lExcelDoc.getCustomPalette();
			HSSFColor hssfcolorSamedi = new HSSFColor();
			text = new HSSFRichTextString(ligneUser.get("samediText"));
			cell = row.createCell(6);
			cell.setCellValue(text);

			couleur = (String) ligneUser.get("samedi");
			HSSFCellStyle samediStyle = lExcelDoc.createCellStyle();
			samediStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			samediStyle.setBorderTop((short) 1);
			samediStyle.setBorderLeft((short) 1);
			samediStyle.setBorderBottom((short) 1);
			// on reconstitue le code couleur récupérée si il ne fait pas partie
			// de la palette

			if (!couleur.equals("")) {
				red = ColorService.valueRedColor(couleur);
				green = ColorService.valueGreenColor(couleur);
				blue = ColorService.valueBlueColor(couleur);

				hssfcolorSamedi = paletteSamedi.findColor(red, green, blue);
				if (hssfcolorSamedi == null) {
					// paletteSamedi.setColorAtIndex(HSSFColor.CORNFLOWER_BLUE.index,
					// red, green, blue);
					// hssfcolorSamedi =
					// paletteSamedi.getColor(HSSFColor.CORNFLOWER_BLUE.index);
					// on definit un index nouveau pour chaque nouvelle couleur
					// utilisée
					Short nouvelIndex = ColorService
							.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
					// on ajoute l'index dans la liste
					listeIndexCouleurDejaUtilise.add(nouvelIndex);
					// on fige la couleur de l'index
					paletteSamedi
							.setColorAtIndex(nouvelIndex, red, green, blue);
					hssfcolorSamedi = paletteSamedi.getColor(nouvelIndex);
				}
				samediStyle.setFillForegroundColor(hssfcolorSamedi.getIndex());
			} else {
				samediStyle.setFillForegroundColor(CONTENT_COLOR);
			}

			samediStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(samediStyle);

			// dimanche
			HSSFPalette paletteDimanche = lExcelDoc.getCustomPalette();
			HSSFColor hssfcolorDimanche = new HSSFColor();
			text = new HSSFRichTextString(ligneUser.get("dimancheText"));
			cell = row.createCell(7);
			cell.setCellValue(text);

			couleur = (String) ligneUser.get("dimanche");
			HSSFCellStyle dimancheStyle = lExcelDoc.createCellStyle();
			dimancheStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			dimancheStyle.setBorderTop((short) 1);
			dimancheStyle.setBorderLeft((short) 1);
			dimancheStyle.setBorderBottom((short) 1);
			// on reconstitue le code couleur récupérée si il ne fait pas partie
			// de la palette
			if (!couleur.equals("")) {
				red = ColorService.valueRedColor(couleur);
				green = ColorService.valueGreenColor(couleur);
				blue = ColorService.valueBlueColor(couleur);

				hssfcolorDimanche = paletteDimanche.findColor(red, green, blue);
				if (hssfcolorDimanche == null) {
					// paletteDimanche.setColorAtIndex(HSSFColor.CORAL.index,
					// red, green, blue);
					// hssfcolorDimanche =
					// paletteDimanche.getColor(HSSFColor.CORAL.index);
					// on definit un index nouveau pour chaque nouvelle couleur
					// utilisée
					Short nouvelIndex = ColorService
							.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
					// on ajoute l'index dans la liste
					listeIndexCouleurDejaUtilise.add(nouvelIndex);
					// on fige la couleur de l'index
					paletteDimanche.setColorAtIndex(nouvelIndex, red, green,
							blue);
					hssfcolorDimanche = paletteDimanche.getColor(nouvelIndex);
				}
				dimancheStyle.setFillForegroundColor(hssfcolorDimanche
						.getIndex());
			} else {
				samediStyle.setFillForegroundColor(CONTENT_COLOR);
			}

			dimancheStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(dimancheStyle);
		}

		return lExcelDoc;
	}

	public static HSSFWorkbook exportPlanningMoisPilote(
			Map<Integer, Map<Integer, Map<String, String>>> listeVacationsMoisParPilote,
			List<Users> listeUser, Date dateSelected,
			List<Planning_Vacation> listeCouleur) throws Exception {
		int rowIndex = 0;
		HSSFRichTextString text;
		// HSSFRichTextString textvacation;
		HSSFRow row;
		HSSFCell cell;
		HSSFWorkbook lExcelDoc = new HSSFWorkbook();

		// on prepare une liste d'index de couleur temporaires
		List<Short> listeIndexCouleurDejaUtilise = new ArrayList<Short>();
		// short compteurListeIndexCouleur=0;

		// Definition des style de caratères
		// Style du font
		HSSFFont fontTitleWhiteBold = lExcelDoc.createFont();
		fontTitleWhiteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontTitleWhiteBold.setFontHeightInPoints((short) 16);
		fontTitleWhiteBold.setColor(HSSFColor.WHITE.index);

		// Style du font
		HSSFFont fontWhiteBold = lExcelDoc.createFont();
		fontWhiteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontWhiteBold.setColor(HSSFColor.WHITE.index);

		// Style du font petite
		HSSFFont fontPetite = lExcelDoc.createFont();
		fontPetite.setFontHeightInPoints((short) 8);
		fontPetite.setColor(HSSFColor.BLACK.index);

		// Style centré
		HSSFCellStyle centerStyle = lExcelDoc.createCellStyle();
		centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// Style alignement à droite
		HSSFCellStyle rightStyle = lExcelDoc.createCellStyle();
		rightStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		// Définition des style de cellules
		// style des titres de la page
		HSSFCellStyle TitleStyle = lExcelDoc.createCellStyle();
		TitleStyle.setFont(fontWhiteBold);
		TitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		TitleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		TitleStyle.setFillForegroundColor(TH_COLOR);
		TitleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		TitleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		TitleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		TitleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		TitleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// style des titres de colonnes
		HSSFCellStyle colomnTitleStyle = lExcelDoc.createCellStyle();
		colomnTitleStyle.setFont(fontWhiteBold);
		colomnTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		colomnTitleStyle.setFillForegroundColor(TH_COLOR);
		colomnTitleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		colomnTitleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		colomnTitleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		colomnTitleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		colomnTitleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// Style type
		HSSFCellStyle typeStyle = lExcelDoc.createCellStyle();
		typeStyle.setFont(fontWhiteBold);
		typeStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		typeStyle.setFillForegroundColor(SOUS_TH_COLOR);
		typeStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		typeStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		typeStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		typeStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		typeStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// Style contenu
		HSSFCellStyle contentStyle = lExcelDoc.createCellStyle();
		contentStyle.setFillForegroundColor(CONTENT_COLOR);
		contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

		// Style de l'entête du tableau
		HSSFCellStyle headerStyle = lExcelDoc.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFillForegroundColor(TH_COLOR);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// Mise en forme des colonnes de la feuille
		HSSFSheet sheet = lExcelDoc.createSheet();
		// sheet.setColumnWidth(0, (short)5800);
		sheet.setColumnWidth(0, 5300);
		sheet.setColumnWidth(1, 1000);
		sheet.setColumnWidth(2, 1000);
		sheet.setColumnWidth(3, 1000);
		sheet.setColumnWidth(4, 1000);
		sheet.setColumnWidth(5, 1000);
		sheet.setColumnWidth(6, 1000);
		sheet.setColumnWidth(7, 1000);
		sheet.setColumnWidth(8, 1000);
		sheet.setColumnWidth(9, 1000);
		sheet.setColumnWidth(10, 1000);
		sheet.setColumnWidth(11, 1000);
		sheet.setColumnWidth(12, 1000);
		sheet.setColumnWidth(13, 1000);
		sheet.setColumnWidth(14, 1000);
		sheet.setColumnWidth(15, 1000);
		sheet.setColumnWidth(16, 1000);
		sheet.setColumnWidth(17, 1000);
		sheet.setColumnWidth(18, 1000);
		sheet.setColumnWidth(19, 1000);
		sheet.setColumnWidth(20, 1000);
		sheet.setColumnWidth(21, 1000);
		sheet.setColumnWidth(22, 1000);
		sheet.setColumnWidth(23, 1000);
		sheet.setColumnWidth(24, 1000);
		sheet.setColumnWidth(25, 1000);
		sheet.setColumnWidth(26, 1000);
		sheet.setColumnWidth(27, 1000);
		sheet.setColumnWidth(28, 1000);
		sheet.setColumnWidth(29, 1000);
		sheet.setColumnWidth(30, 1000);
		sheet.setColumnWidth(31, 1000);

		// Titre du tableau

		String title = "Planning mensuel des pilotes : ";
		String titleVacation = " Legende vacation ";
		int nbDayOfTheMonth = DateService.getNbDaysInMonth(dateSelected);

		StringBuffer buffer = new StringBuffer(title);

		// reconstitution de la date selectionnée

		Calendar c = Calendar.getInstance();
		c.setTime(dateSelected);
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setCalendar(c);
		sdf.applyPattern("MMMM");
		String libelleMois = sdf.format(c.getTime());
		String DateSelect = libelleMois + " "
				+ String.valueOf(c.get(Calendar.YEAR)).trim();

		buffer.append(DateSelect.trim());

		StringBuffer bufferVacation = new StringBuffer(titleVacation);

		// titre vacation
		text = new HSSFRichTextString(new String(bufferVacation));
		row = sheet.createRow((short) 0);
		row.setHeight((short) 400);
		cell = row.createCell(0);
		CellRangeAddress cellsLegend = new CellRangeAddress(row.getRowNum(),
				row.getRowNum(), 0, nbDayOfTheMonth);
		cell.setCellValue(text);
		sheet.addMergedRegion(cellsLegend);
		text.applyFont(fontWhiteBold);
		cell.setCellStyle(headerStyle);
		HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		rowIndex++;

		// couleurs de vacation

		row = sheet.createRow(rowIndex++);
		int k = 2;
		for (Planning_Vacation planning_vacation : listeCouleur) {

			text = new HSSFRichTextString(planning_vacation.getLibelle());
			String couleur = planning_vacation.getCodeCouleur();

			cell = row.createCell((k));
			row.getCell((k)).setCellValue(text);

			HSSFCellStyle couleurStyle = lExcelDoc.createCellStyle();
			couleurStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			couleurStyle.setFont(fontPetite);

			// on reconstitue le code couleur récupérée si il ne fait pas partie
			// de la palette
			HSSFColor hssfcolorCouleur = new HSSFColor();
			HSSFPalette paletteCouleur = lExcelDoc.getCustomPalette();

			byte red = ColorService.valueRedColor(couleur);
			byte green = ColorService.valueGreenColor(couleur);
			byte blue = ColorService.valueBlueColor(couleur);

			hssfcolorCouleur = paletteCouleur.findColor(red, green, blue);

			if (hssfcolorCouleur == null) {
				// paletteCouleur.setColorAtIndex(HSSFColor.DARK_YELLOW.index,
				// red, green, blue);
				// hssfcolorCouleur =
				// paletteCouleur.getColor(HSSFColor.DARK_YELLOW.index);

				// on definit un index nouveau pour chaque nouvelle couleur
				// utilisée
				Short nouvelIndex = ColorService
						.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
				// on ajoute l'index dans la liste
				listeIndexCouleurDejaUtilise.add(nouvelIndex);
				// on fige la couleur de l'index
				paletteCouleur.setColorAtIndex(nouvelIndex, red, green, blue);
				hssfcolorCouleur = paletteCouleur.getColor(nouvelIndex);
			}

			couleurStyle.setFillForegroundColor(hssfcolorCouleur.getIndex());

			couleurStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			// cell.setCellStyle(couleurStyle);
			row.getCell((k)).setCellStyle(couleurStyle);
			CellRangeAddress cellsColor = new CellRangeAddress(row.getRowNum(),
					row.getRowNum(), k, k + 1);
			sheet.addMergedRegion(cellsColor);
			HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN,
					cellsColor, sheet, lExcelDoc);
			HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN,
					cellsColor, sheet, lExcelDoc);
			HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellsColor,
					sheet, lExcelDoc);
			HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellsColor,
					sheet, lExcelDoc);

			k += 3;
		}

		// titre planning
		text = new HSSFRichTextString(new String(buffer));
		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		row.setHeight((short) 400);
		cell.setCellValue(text);
		CellRangeAddress cellsTitle = new CellRangeAddress(rowIndex,
				++rowIndex, 0, nbDayOfTheMonth);
		text.applyFont(fontTitleWhiteBold);
		cell.setCellStyle(headerStyle);
		sheet.addMergedRegion(cellsTitle);
		HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		rowIndex++;

		// Titre du document
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 300);
		text = new HSSFRichTextString(PILOTE);
		cell = row.createCell(0);
		row.getCell(0).setCellValue(text);
		row.getCell(0).setCellStyle(colomnTitleStyle);

		for (int i = 0; i < nbDayOfTheMonth; i++) {

			text = new HSSFRichTextString(String.valueOf(i + 1));
			cell = row.createCell((i + 1));
			row.getCell((i + 1)).setCellValue(text);
			row.getCell((i + 1)).setCellStyle(colomnTitleStyle);
		}

		HashMap<Integer, Date> mapJourDate = new HashMap<Integer, Date>();

		List<Integer> listJour = new ArrayList<Integer>();

		try {
			Date dateDebutMois = DateService.getFirstDayOfMonth(dateSelected);
			// Date dateFinMois = DateService.getLastDayOfMonth(dateSelected);

			dateSelected = dateDebutMois;

			for (int i = 1; i <= nbDayOfTheMonth; i++) {
				listJour.add(i);
				mapJourDate.put(i, dateSelected);
				dateSelected = DateService.addDays(dateSelected, 1);
			}
			for (Users u : listeUser) {
				row = sheet.createRow(rowIndex++);
				// cell
				String identite = u.getNom() + ' ' + u.getPrenom();
				text = new HSSFRichTextString(identite);
				row.createCell(0).setCellValue(text);
				row.getCell(0).setCellStyle(contentStyle);

				for (int i = 1; i <= nbDayOfTheMonth; i++) {
					// for(int i : listJour){
					// cell
					String Couleur = (String) listeVacationsMoisParPilote
							.get(u.getId()).get(i).get("couleur");

					// données à afficher
					// date du jour
					c.setTime(mapJourDate.get(i));
					// String DateJour = String.valueOf(c.get(Calendar.DATE));
					// DateJour += "/" + String.valueOf(c.get(Calendar.MONTH));
					// DateJour += "/" + String.valueOf(c.get(Calendar.YEAR));

					// String vacationId=(String)
					// listeVacationsMoisParPilote.get(u.getId()).get(i).get("vacationID");
					String vacationTexte = (String) listeVacationsMoisParPilote
							.get(u.getId()).get(i).get("vacationText");
					String texteCellule = vacationTexte.trim();

					text = new HSSFRichTextString(texteCellule);
					cell = row.createCell(i);
					// row.getCell(i).setCellValue(text);

					HSSFCellStyle jourStyle = lExcelDoc.createCellStyle();
					jourStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					jourStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					jourStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					jourStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					jourStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

					if (!Couleur.equals("")) {
						// on reconstitue le code couleur récupérée si il ne
						// fait pas partie de la palette
						HSSFColor hssfcolorJour = new HSSFColor();
						HSSFPalette paletteJour = lExcelDoc.getCustomPalette();

						byte red = ColorService.valueRedColor(Couleur);
						byte green = ColorService.valueGreenColor(Couleur);
						byte blue = ColorService.valueBlueColor(Couleur);

						hssfcolorJour = paletteJour.findColor(red, green, blue);

						if (hssfcolorJour == null) {
							// paletteJour.setColorAtIndex(HSSFColor.LAVENDER.index,
							// red, green, blue);
							// hssfcolorJour =
							// paletteJour.getColor(HSSFColor.LAVENDER.index);

							// on definit un index nouveau pour chaque nouvelle
							// couleur utilisée
							Short nouvelIndex = ColorService
									.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
							// on ajoute l'index dans la liste
							listeIndexCouleurDejaUtilise.add(nouvelIndex);
							// on fige la couleur de l'index
							paletteJour.setColorAtIndex(nouvelIndex, red,
									green, blue);
							hssfcolorJour = paletteJour.getColor(nouvelIndex);
						}

						// jourStyle.setFillForegroundColor(hssfcolorJour.getIndex());
						// int redtempo=Integer.parseInt(Couleur.substring(0,
						// 2),16);
						// int greentempo=Integer.parseInt(Couleur.substring(2,
						// 4),16);
						// int bluetempo=Integer.parseInt(Couleur.substring(4,
						// 6),16);
						// short red=(short)redtempo;
						// short green=(short)greentempo;
						// short blue=(short)bluetempo;

						// short[] tripletCouleur = {red ,green, blue};
						// HSSFColor.getTripletHash().put(HSSFColor.WHITE.index,
						// tripletCouleur);
						jourStyle.setFillForegroundColor(hssfcolorJour
								.getIndex());

					} else {
						jourStyle.setFillForegroundColor(CONTENT_COLOR);
					}

					jourStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					row.getCell(i).setCellStyle(jourStyle);
				}

			}

		} catch (Exception e) {
			throw new Exception("erreur survenue", e);
		}

		return lExcelDoc;
	}

	// methode à compléter pour l'export planning mois
	public static HSSFWorkbook exportPlanningMois(Date dateSelected,
			Map<Integer, Integer> mapJour, Planning_Nom_Equipe equip,
			Map<Integer, String> couleurliste,
			Map<Integer, String> listeVacation,
			Map<Integer, Integer> mapJourMois) throws Exception {
		int rowIndex = 0;
		HSSFRichTextString text;
		// HSSFRichTextString textvacation;
		HSSFRow row;
		HSSFCell cell;
		HSSFWorkbook lExcelDoc = new HSSFWorkbook();

		// on prepare une liste d'index de couleur temporaires
		List<Short> listeIndexCouleurDejaUtilise = new ArrayList<Short>();
		// short compteurListeIndexCouleur=0;

		// Style du font
		HSSFFont fontWhiteBold = lExcelDoc.createFont();
		fontWhiteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontWhiteBold.setColor(HSSFColor.WHITE.index);

		// Style centré
		HSSFCellStyle centerStyle = lExcelDoc.createCellStyle();
		centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// Style alignement à droite
		HSSFCellStyle rightStyle = lExcelDoc.createCellStyle();
		rightStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		// style des titres de colonnes
		HSSFCellStyle colomnTitleStyle = lExcelDoc.createCellStyle();
		colomnTitleStyle.setFont(fontWhiteBold);
		colomnTitleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		colomnTitleStyle.setFillForegroundColor(TH_COLOR);
		colomnTitleStyle.setBorderTop((short) 1);
		colomnTitleStyle.setBorderLeft((short) 1);
		colomnTitleStyle.setBorderRight((short) 1);
		colomnTitleStyle.setBorderBottom((short) 1);
		colomnTitleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFSheet sheet = lExcelDoc.createSheet();
		// sheet.setColumnWidth(0, (short)5800);
		// il y a 7 colonnes dans planningMois.jsp donc on les affiche dans le
		// document excel
		sheet.setColumnWidth(0, (short) 5300);
		sheet.setColumnWidth(1, (short) 5300);
		sheet.setColumnWidth(2, (short) 5300);
		sheet.setColumnWidth(3, (short) 5300);
		sheet.setColumnWidth(4, (short) 5300);
		sheet.setColumnWidth(5, (short) 5300);
		sheet.setColumnWidth(6, (short) 5300);

		// Style type
		HSSFCellStyle typeStyle = lExcelDoc.createCellStyle();
		typeStyle.setFont(fontWhiteBold);
		typeStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		typeStyle.setFillForegroundColor(SOUS_TH_COLOR);
		typeStyle.setBorderTop((short) 1);
		typeStyle.setBorderLeft((short) 1);
		typeStyle.setBorderRight((short) 1);
		typeStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		// Style contenu
		HSSFCellStyle contentStyle = lExcelDoc.createCellStyle();
		contentStyle.setFillForegroundColor(CONTENT_COLOR);
		contentStyle.setBorderTop((short) 1);
		contentStyle.setBorderLeft((short) 1);
		contentStyle.setBorderBottom((short) 1);
		contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

		// Titre du tableau
		// on a besoin de la date sélectionnée ainsi que l'equipe sélectionnée
		// pour compléter le titre
		String title = "Planning de : ";
		String titleVacation = " Légende vacation ";
		StringBuffer buffer = new StringBuffer(title);

		// reconstitution de la date selectionnée
		Calendar c = Calendar.getInstance();
		c.setTime(dateSelected);

		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setCalendar(c);
		sdf.applyPattern("MMMM");
		// String libelleMois=sdf.format(c.getTime());
		int libelleMois = c.get(Calendar.MONTH) + 1;
		if (libelleMois < 10) {
			String DateSelect = "0" + libelleMois + " / "
					+ String.valueOf(c.get(Calendar.YEAR)).trim();
			buffer.append(DateSelect.trim());
		} else {
			String DateSelect = libelleMois + " / "
					+ String.valueOf(c.get(Calendar.YEAR)).trim();
			buffer.append(DateSelect.trim());
		}

		HSSFCellStyle headerStyle = lExcelDoc.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		headerStyle.setFillForegroundColor(TH_COLOR);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		StringBuffer bufferVacation = new StringBuffer(titleVacation);

		// Style du font petite
		HSSFFont fontPetite = lExcelDoc.createFont();
		fontPetite.setFontHeightInPoints((short) 8);
		fontPetite.setColor(HSSFColor.BLACK.index);

		// titre vacation
		text = new HSSFRichTextString(new String(bufferVacation));
		row = sheet.createRow((short) 0);
		row.setHeight((short) 400);
		cell = row.createCell(0);
		CellRangeAddress cellsLegend = new CellRangeAddress(0, 0, 0, 9);
		cell.setCellValue(text);
		sheet.addMergedRegion(cellsLegend);
		text.applyFont(fontWhiteBold);
		// HSSFCellStyle headerStyle = lExcelDoc.createCellStyle();
		cell.setCellStyle(headerStyle);
		rowIndex++;
		HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);
		HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellsLegend,
				sheet, lExcelDoc);

		// couleurs de vacation
		row = sheet.createRow(rowIndex++);
		int k = 0;
		for (Integer vacationID : listeVacation.keySet()) {
			text = new HSSFRichTextString(listeVacation.get(vacationID));
			String couleur = couleurliste.get(vacationID);

			cell = row.createCell(k);
			row.getCell((k)).setCellValue(text);
			HSSFCellStyle couleurStyle = lExcelDoc.createCellStyle();
			couleurStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			couleurStyle.setFont(fontPetite);

			// on reconstitue le code couleur récupérée si il ne fait pas partie
			// de la palette
			HSSFColor hssfcolorCouleur = new HSSFColor();
			HSSFPalette paletteCouleur = lExcelDoc.getCustomPalette();
			byte red = ColorService.valueRedColor(couleur);
			byte green = ColorService.valueGreenColor(couleur);
			byte blue = ColorService.valueBlueColor(couleur);
			hssfcolorCouleur = paletteCouleur.findColor(red, green, blue);

			if (hssfcolorCouleur == null) {
				// on definit un index nouveau pour chaque nouvelle couleur
				// utilisée
				Short nouvelIndex = ColorService
						.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
				// on ajoute l'index dans la liste
				listeIndexCouleurDejaUtilise.add(nouvelIndex);
				// on fige la couleur de l'index
				paletteCouleur.setColorAtIndex(nouvelIndex, red, green, blue);
				hssfcolorCouleur = paletteCouleur.getColor(nouvelIndex);
			}

			couleurStyle.setFillForegroundColor(hssfcolorCouleur.getIndex());
			couleurStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			row.getCell((k)).setCellStyle(couleurStyle);
			CellRangeAddress cellsColor = new CellRangeAddress(row.getRowNum(),
					row.getRowNum(), k, k);
			HSSFRegionUtil.setBorderBottom(HSSFCellStyle.BORDER_THIN,
					cellsColor, sheet, lExcelDoc);
			HSSFRegionUtil.setBorderRight(HSSFCellStyle.BORDER_THIN,
					cellsColor, sheet, lExcelDoc);
			HSSFRegionUtil.setBorderLeft(HSSFCellStyle.BORDER_THIN, cellsColor,
					sheet, lExcelDoc);
			HSSFRegionUtil.setBorderTop(HSSFCellStyle.BORDER_THIN, cellsColor,
					sheet, lExcelDoc);

			k += 1;
		}

		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 400);

		text = new HSSFRichTextString(equip.getNomEquipe());
		cell = row.createCell(0);
		row.getCell(0).setCellValue(text);
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 9));
		text.applyFont(fontWhiteBold);
		row.getCell(0).setCellStyle(headerStyle);

		// La blanc de première ligne
		row = sheet.createRow(rowIndex++);
		cell = row.createCell(0);
		row.setHeight((short) 400);
		sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 9));
		row.getCell(0).setCellStyle(headerStyle);

		// titre planning
		text = new HSSFRichTextString(new String(buffer));
		row = sheet.createRow(rowIndex++);
		cell = row.createCell(0);
		row.setHeight((short) 400);
		row.getCell(0).setCellValue(text);
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 0, 9));
		text.applyFont(fontWhiteBold);
		row.getCell(0).setCellStyle(headerStyle);
		rowIndex++;

		// on affiche les titres des colonnes
		row = sheet.createRow(rowIndex++);
		row.setHeight((short) 300);
		text = new HSSFRichTextString(LUNDI);
		cell = row.createCell(0);
		row.getCell(0).setCellValue(text);
		row.getCell(0).setCellStyle(colomnTitleStyle);
		text = new HSSFRichTextString(MARDI);
		cell = row.createCell(1);
		row.getCell(1).setCellValue(text);
		row.getCell(1).setCellStyle(colomnTitleStyle);
		text = new HSSFRichTextString(MERCREDI);
		cell = row.createCell(2);
		row.getCell(2).setCellValue(text);
		row.getCell(2).setCellStyle(colomnTitleStyle);
		text = new HSSFRichTextString(JEUDI);
		cell = row.createCell(3);
		row.getCell(3).setCellValue(text);
		row.getCell(3).setCellStyle(colomnTitleStyle);
		text = new HSSFRichTextString(VENDREDI);
		cell = row.createCell(4);
		row.getCell(4).setCellValue(text);
		row.getCell(4).setCellStyle(colomnTitleStyle);
		text = new HSSFRichTextString(SAMEDI);
		cell = row.createCell(5);
		row.getCell(5).setCellValue(text);
		row.getCell(5).setCellStyle(colomnTitleStyle);
		text = new HSSFRichTextString(DIMANCHE);
		cell = row.createCell(6);
		row.getCell(6).setCellValue(text);
		row.getCell(6).setCellStyle(colomnTitleStyle);

		// il faut afficher ici le contenu du planning pour chaque jour de la
		// semaine à la date sélectionnée et pour l'equipe sélectionnée

		try {
			int compteurColonneCellule = 0;

			row = sheet.createRow(rowIndex++);
			row.setHeight((short) 300);
			for (int i = 0; i < 42; i++) {
				String Couleur = "FFFFFF";

				Integer vacationID = (Integer) mapJour.get(i);
				String vacationTexte = "";
				String texteCellule = "";

				if (i % 7 == 0) {
					compteurColonneCellule = 0;
					row = sheet.createRow(rowIndex++);
					row.setHeight((short) 300);

				} else {
					compteurColonneCellule++;
				}

				if (vacationID != null) {
					vacationTexte = (String) listeVacation.get(vacationID);
					texteCellule = mapJourMois.get(i) + "       "
							+ vacationTexte.trim();
					Couleur = (String) couleurliste.get(vacationID);
				} else {
					texteCellule = mapJourMois.get(i) + "";
				}
				if (texteCellule.equals("null"))
					texteCellule = "";

				text = new HSSFRichTextString(texteCellule);
				cell = row.createCell(compteurColonneCellule);
				row.getCell(compteurColonneCellule).setCellValue(text);

				HSSFCellStyle jourStyle = lExcelDoc.createCellStyle();
				jourStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				jourStyle.setBorderTop((short) 1);
				jourStyle.setBorderLeft((short) 1);
				jourStyle.setBorderBottom((short) 1);

				if (!Couleur.equals("")) {
					// on reconstitue le code couleur récupérée si il ne fait
					// pas partie de la palette
					HSSFColor hssfcolorJour = new HSSFColor();
					HSSFPalette paletteJour = lExcelDoc.getCustomPalette();

					byte red = ColorService.valueRedColor(Couleur);
					byte green = ColorService.valueGreenColor(Couleur);
					byte blue = ColorService.valueBlueColor(Couleur);

					hssfcolorJour = paletteJour.findColor(red, green, blue);

					if (hssfcolorJour == null) {

						// on definit un index nouveau pour chaque nouvelle
						// couleur utilisée
						Short nouvelIndex = ColorService
								.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
						// on ajoute l'index dans la liste
						listeIndexCouleurDejaUtilise.add(nouvelIndex);
						// on fige la couleur de l'index
						paletteJour.setColorAtIndex(nouvelIndex, red, green,
								blue);
						hssfcolorJour = paletteJour.getColor(nouvelIndex);
					}

					jourStyle.setFillForegroundColor(hssfcolorJour.getIndex());

				} else {
					jourStyle.setFillForegroundColor(CONTENT_COLOR);
				}

				jourStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				row.getCell(compteurColonneCellule).setCellStyle(jourStyle);
			}

		} catch (Exception e) {
			throw new Exception("erreur survenue", e);
		}

		return lExcelDoc;
	}

	public static HSSFWorkbook exportGroupeMeteo(String nomGroupe,
			String commentaireGroupe, String date, String horaire,
			Date heure_saisie, List<Meteo_Meteo> listMeteo,
			List<String> listCommentaire,
			List<List<Meteo_Service>> listService,
			List<List<Meteo_Environnement>> listEnvironnement, int nbEnvir,
			List<List<List<Map<String, String>>>> listIndicateur,
			List<Meteo_Environnement> envirList,
			List<List<Meteo_Caisse>> listCaisse,
			List<Meteo_EtatPossible> listEtat, String path_img,
			List<List<String>> listChampHeureDebut,
			List<List<String>> listChampHeureFin, String timezone)
			throws Exception {

		/*
		 * SimpleDateFormat simpleDateFormat = new
		 * SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		 * 
		 * Date dateParis = simpleDateFormat.parse(date+" "+horaire);
		 * System.out.println(dateParis);
		 * simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Pacific/Noumea"));
		 * String datestringfuseau = simpleDateFormat.format(dateParis);
		 * 
		 * SimpleDateFormat simpleDateFormat2 = new
		 * SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); Date dateFuseau =
		 * simpleDateFormat2.parse(datestringfuseau);
		 * System.out.println(datestringfuseau); System.out.println(dateFuseau);
		 * 
		 * long diff = dateFuseau.getTime()-dateParis.getTime(); long
		 * diffSeconds = diff / 1000 % 60; long diffMinutes = diff / (60 * 1000)
		 * % 60; long diffHours = diff / (60 * 60 * 1000);
		 * System.out.println(diffHours+":"+diffMinutes+":"+diffSeconds);
		 */

		int rowIndex = 7;
		HSSFRow row;
		HSSFCell cell;
		HSSFWorkbook lExcelDoc = new HSSFWorkbook();
		HSSFSheet sheet = lExcelDoc.createSheet(nomGroupe);
		HSSFPatriarch patr = sheet.createDrawingPatriarch();

		// on prepare une liste d'index de couleur temporaires
		List<Short> listeIndexCouleurDejaUtilise = new ArrayList<Short>();

		// add picture data to this workbook.
		InputStream is = new FileInputStream(path_img + "bandeau-mail-prod.jpg");
		byte[] bytes = IOUtils.toByteArray(is);
		int pictureIdx = lExcelDoc.addPicture(bytes,
				HSSFWorkbook.PICTURE_TYPE_JPEG);
		is.close();
		HSSFCreationHelper helper = lExcelDoc.getCreationHelper();

		// Style du font
		HSSFFont fontWhiteBold = lExcelDoc.createFont();
		fontWhiteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontWhiteBold.setColor(HSSFColor.WHITE.index);

		HSSFFont fontBlackBold = lExcelDoc.createFont();
		fontBlackBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontBlackBold.setColor(HSSFColor.BLACK.index);

		HSSFFont fontBlack = lExcelDoc.createFont();
		fontBlack.setColor(HSSFColor.BLACK.index);

		HSSFPalette palette = lExcelDoc.getCustomPalette();
		HSSFColor hssfcolor = new HSSFColor();

		byte red = ColorService.valueRedColor("a59694");
		byte green = ColorService.valueGreenColor("a59694");
		byte blue = ColorService.valueBlueColor("a59694");

		hssfcolor = palette.findColor(red, green, blue);
		short fonce = 0;
		if (hssfcolor == null) {
			Short nouvelIndex = ColorService
					.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
			listeIndexCouleurDejaUtilise.add(nouvelIndex);
			palette.setColorAtIndex(nouvelIndex, red, green, blue);
			hssfcolor = palette.getColor(nouvelIndex);
			fonce = hssfcolor.getIndex();
		}

		red = ColorService.valueRedColor("cdc4c3");
		green = ColorService.valueGreenColor("cdc4c3");
		blue = ColorService.valueBlueColor("cdc4c3");

		hssfcolor = palette.findColor(red, green, blue);
		short clair = 0;
		if (hssfcolor == null) {
			Short nouvelIndex = ColorService
					.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
			listeIndexCouleurDejaUtilise.add(nouvelIndex);
			palette.setColorAtIndex(nouvelIndex, red, green, blue);
			hssfcolor = palette.getColor(nouvelIndex);
			clair = hssfcolor.getIndex();
		}

		// Style centré
		HSSFCellStyle centerStyle = lExcelDoc.createCellStyle();
		centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		centerStyle.setFont(fontBlackBold);
		centerStyle.setFillForegroundColor(clair);
		centerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFCellStyle headerStyle = lExcelDoc.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFont(fontWhiteBold);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(fonce);

		HSSFCellStyle headerService = lExcelDoc.createCellStyle();
		headerService.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerService.setFont(fontBlackBold);
		headerService.setFillForegroundColor(clair);
		headerService.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerService.setBottomBorderColor(HSSFColor.WHITE.index);
		headerService.setBorderBottom((short) 2);
		headerService.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle headerComment = lExcelDoc.createCellStyle();
		headerComment.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerComment.setFont(fontBlackBold);
		headerComment.setFillForegroundColor(clair);
		headerComment.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerComment.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);
		headerComment.setWrapText(true);
		headerComment.setVerticalAlignment(HSSFCellStyle.VERTICAL_JUSTIFY);

		HSSFCellStyle headerEnvir = lExcelDoc.createCellStyle();
		headerEnvir.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerEnvir.setFont(fontBlackBold);
		headerEnvir.setFillForegroundColor(clair);
		headerEnvir.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerEnvir.setBorderLeft((short) 2);
		headerEnvir.setLeftBorderColor(HSSFColor.WHITE.index);

		HSSFCellStyle headerEnvirLast = lExcelDoc.createCellStyle();
		headerEnvirLast.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerEnvirLast.setFont(fontBlackBold);
		headerEnvirLast.setFillForegroundColor(clair);
		headerEnvirLast.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerEnvirLast.setBorderLeft((short) 2);
		headerEnvirLast.setLeftBorderColor(HSSFColor.WHITE.index);
		headerEnvirLast.setBorderRight((short) 2);
		headerEnvirLast.setRightBorderColor(HSSFColor.WHITE.index);

		HSSFCellStyle headerLegende = lExcelDoc.createCellStyle();
		headerLegende.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerLegende.setFont(fontBlackBold);
		headerLegende.setFillForegroundColor(clair);
		headerLegende.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerLegende.setBottomBorderColor(HSSFColor.WHITE.index);
		headerLegende.setLeftBorderColor(HSSFColor.WHITE.index);
		headerLegende.setRightBorderColor(HSSFColor.WHITE.index);
		headerLegende.setTopBorderColor(HSSFColor.WHITE.index);
		headerLegende.setBorderRight((short) 2);
		headerLegende.setBorderLeft((short) 2);
		headerLegende.setBorderTop((short) 2);
		headerLegende.setBorderBottom((short) 2);
		headerLegende.setWrapText(true);

		HSSFCellStyle headerLegendeEtat = lExcelDoc.createCellStyle();
		headerLegendeEtat.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerLegendeEtat.setFont(fontBlackBold);
		headerLegendeEtat.setFillForegroundColor(clair);
		headerLegendeEtat.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerLegendeEtat.setBottomBorderColor(HSSFColor.WHITE.index);
		headerLegendeEtat.setLeftBorderColor(HSSFColor.WHITE.index);
		headerLegendeEtat.setRightBorderColor(HSSFColor.WHITE.index);
		headerLegendeEtat.setTopBorderColor(HSSFColor.WHITE.index);
		headerLegendeEtat.setBorderRight((short) 2);
		headerLegendeEtat.setBorderLeft((short) 2);
		headerLegendeEtat.setBorderTop((short) 2);
		headerLegendeEtat.setBorderBottom((short) 2);

		HSSFCellStyle headerEtatBlue = lExcelDoc.createCellStyle();
		headerEtatBlue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerEtatBlue.setFont(fontBlackBold);
		headerEtatBlue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerEtatBlue.setBottomBorderColor(HSSFColor.WHITE.index);
		headerEtatBlue.setLeftBorderColor(HSSFColor.WHITE.index);
		headerEtatBlue.setRightBorderColor(HSSFColor.WHITE.index);
		headerEtatBlue.setTopBorderColor(HSSFColor.WHITE.index);
		headerEtatBlue.setBorderRight((short) 2);
		headerEtatBlue.setBorderLeft((short) 2);
		headerEtatBlue.setBorderTop((short) 2);
		headerEtatBlue.setBorderBottom((short) 2);
		headerEtatBlue
				.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);

		HSSFCellStyle headerSeparation = lExcelDoc.createCellStyle();
		headerSeparation.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerSeparation.setFont(fontBlackBold);
		headerSeparation.setFillForegroundColor(fonce);
		headerSeparation.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerSeparation.setBorderLeft((short) 2);
		headerSeparation.setLeftBorderColor(HSSFColor.WHITE.index);

		HSSFCellStyle headerHeure = lExcelDoc.createCellStyle();
		headerHeure.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerHeure.setFont(fontBlackBold);
		headerHeure.setFillForegroundColor(clair);
		headerHeure.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerHeure.setBottomBorderColor(HSSFColor.WHITE.index);
		headerHeure.setLeftBorderColor(HSSFColor.WHITE.index);
		headerHeure.setRightBorderColor(HSSFColor.WHITE.index);
		headerHeure.setTopBorderColor(HSSFColor.WHITE.index);
		headerHeure.setBorderRight((short) 2);
		headerHeure.setBorderLeft((short) 2);
		headerHeure.setBorderTop((short) 2);
		headerHeure.setBorderBottom((short) 2);

		boolean envirUnique = true;
		int indColonne = 1;
		for (int i = 0; i < listMeteo.size(); i++) {
			for (int j = 0; j < listEnvironnement.get(i).size(); j++) {
				if (listEnvironnement.get(i).size() != 1)
					envirUnique = false;
				sheet.setColumnWidth(indColonne, 7000);
				indColonne++;
				if (listChampHeureDebut.get(i).get(j).equals("1")) {
					nbEnvir++;
					sheet.setColumnWidth(indColonne, 7000);
					indColonne++;
					if (listChampHeureFin.get(i).get(j).equals("0")
							&& j + 1 != listEnvironnement.get(i).size()) {
						nbEnvir++;
						sheet.setColumnWidth(indColonne, 400);
						indColonne++;
					}
				}
				if (listChampHeureFin.get(i).get(j).equals("1")) {
					nbEnvir++;
					sheet.setColumnWidth(indColonne, 7000);
					indColonne++;
					if (j + 1 != listEnvironnement.get(i).size()) {
						nbEnvir++;
						sheet.setColumnWidth(indColonne, 400);
						indColonne++;
					}
				}
			}
		}

		if (nbEnvir < 4) {
			for (int i = nbEnvir + 1; i < 5; i++) {
				sheet.setColumnWidth(i, 7000);
			}
			nbEnvir = 4;
		}

		// Create the drawing patriarch. This is the top level container for all
		// shapes.
		Drawing drawing = sheet.createDrawingPatriarch();

		// add a picture shape
		ClientAnchor anchor = helper.createClientAnchor();
		anchor.setCol1(0);
		anchor.setRow1(0);
		anchor.setCol2(nbEnvir + 1);
		anchor.setRow2(rowIndex);
		drawing.createPicture(anchor, pictureIdx);

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbEnvir));
		rowIndex++;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		cell.setCellValue(nomGroupe);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex + 1, 0,
				nbEnvir));
		cell.setCellStyle(headerStyle);
		rowIndex = rowIndex + 2;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbEnvir));
		rowIndex++;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		cell.setCellValue(date + " " + horaire);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbEnvir));
		cell.setCellStyle(headerStyle);
		rowIndex++;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbEnvir));
		rowIndex++;

		if (!commentaireGroupe.equals("")) {
			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);
			cell.setCellValue(commentaireGroupe);
			cell.setCellStyle(headerComment);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbEnvir));
			int tailleTotalColumn = 7000 + (nbEnvir * 7000);

			float tailleLigneComment = tailleTotalColumn / 256;

			int nbLigneComment = 0;
			String[] lines = commentaireGroupe.split("\\n?\\r");
			for (int i = 0; i < lines.length; i++) {
				if (lines[i].length() < tailleLigneComment) {
					nbLigneComment++;
				} else {
					int nbRowComment = (int) Math.ceil((float) lines[i]
							.length() / tailleLigneComment);
					nbLigneComment = nbLigneComment + nbRowComment;
				}
			}

			int tailleRowComment = row.getHeight() * nbLigneComment;
			if (tailleRowComment != 0) {
				row.setHeight((short) tailleRowComment);
			}
			rowIndex++;
		}

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbEnvir));
		rowIndex++;

		for (int i = 0; i < listMeteo.size(); i++) {

			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);

			if (listCommentaire.get(i) != "") {
				cell.setCellValue(listMeteo.get(i).getNom_meteo()
						+ " (voir Commentaire Excel)");
				/*
				 * row = sheet.createRow(rowIndex); cell = row.createCell(0);
				 * sheet.addMergedRegion(new
				 * CellRangeAddress(rowIndex,rowIndex,0,nbEnvir)); rowIndex ++;
				 */

				String[] nbLigneString = listCommentaire.get(i).split("\n");
				int nbLigneComment = nbLigneString.length + 5;

				// row = sheet.createRow(rowIndex);
				// cell = row.createCell(0);
				// cell.setCellValue("Commentaire");
				Comment comment = patr.createComment(new HSSFClientAnchor(0, 0,
						0, 0, (short) 3, rowIndex + 1, (short) 7, rowIndex
								+ nbLigneComment));
				comment.setString(new HSSFRichTextString(listCommentaire.get(i)));
				// comment.setVisible(true);
				cell.setCellComment(comment);
				/*
				 * cell.setCellStyle(centerStyle); sheet.addMergedRegion(new
				 * CellRangeAddress(rowIndex,rowIndex,0,nbEnvir)); rowIndex ++;
				 */
			} else {
				cell.setCellValue(listMeteo.get(i).getNom_meteo());
			}

			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbEnvir));
			cell.setCellStyle(headerStyle);
			rowIndex++;

			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbEnvir));
			rowIndex++;

			row = sheet.createRow(rowIndex);
			int indexEnvir = 1;
			for (int j = 0; j < listEnvironnement.get(i).size(); j++) {
				cell = row.createCell(indexEnvir);
				cell.setCellValue(listEnvironnement.get(i).get(j)
						.getNom_envir());

				cell.setCellStyle(headerEnvir);
				if (j + 1 == listEnvironnement.get(i).size()
						&& listChampHeureDebut.get(i).get(j).equals("0")
						&& listChampHeureFin.get(i).get(j).equals("0")) {
					cell.setCellStyle(headerEnvirLast);
				}
				indexEnvir++;
				if (listEnvironnement.get(i).size() == 1)
					indexEnvir++;
				if (listChampHeureDebut.get(i).get(j).equals("1")) {
					cell = row.createCell(indexEnvir);
					cell.setCellValue("Heure Debut");
					cell.setCellStyle(headerEnvir);
					indexEnvir++;
					if (listChampHeureFin.get(i).get(j).equals("0")
							&& j + 1 != listEnvironnement.get(i).size()) {
						cell = row.createCell(indexEnvir);
						cell.setCellStyle(headerSeparation);
						indexEnvir++;
					}
					if (listChampHeureFin.get(i).get(j).equals("0")
							&& j + 1 == listEnvironnement.get(i).size()) {
						cell.setCellStyle(headerEnvirLast);
					}
				}
				if (listChampHeureFin.get(i).get(j).equals("1")) {
					cell = row.createCell(indexEnvir);
					cell.setCellValue("Heure Fin");
					cell.setCellStyle(headerEnvir);
					indexEnvir++;
					if (j + 1 != listEnvironnement.get(i).size()) {
						cell = row.createCell(indexEnvir);
						cell.setCellStyle(headerSeparation);
						indexEnvir++;
					}
					if (j + 1 == listEnvironnement.get(i).size()) {
						cell.setCellStyle(headerEnvirLast);
					}
				}

				if (envirUnique)
					sheet.addMergedRegion(new CellRangeAddress(rowIndex,
							rowIndex, 1, 2));
			}
			rowIndex++;

			List<String> listCommentaires = new ArrayList<String>();
			int nbComment = 1;
			row = sheet.createRow(rowIndex);
			for (int j = 0; j < listService.get(i).size(); j++) {
				row = sheet.createRow(rowIndex);
				int indexx = 0;
				cell = row.createCell(indexx);
				cell.setCellValue(listService.get(i).get(j).getService());
				sheet.autoSizeColumn(0);
				if (sheet.getColumnWidth(0) < 7000) {
					sheet.setColumnWidth(0, 7000);
				} else {
					sheet.setColumnWidth(0, sheet.getColumnWidth(0) + 700);
				}
				cell.setCellStyle(headerService);
				indexx++;

				for (int k = 0; k < listEnvironnement.get(i).size(); k++) {
					cell = row.createCell(indexx);
					if (listIndicateur.get(i).get(j).get(k)
							.get(PilotageConstants.COMMENTAIRE) != null) {
						cell.setCellValue(listIndicateur.get(i).get(j).get(k)
								.get(PilotageConstants.ETAT)
								+ " (" + nbComment + ")");
						listCommentaires.add(listIndicateur.get(i).get(j)
								.get(k).get(PilotageConstants.COMMENTAIRE));
						nbComment++;
					} else {
						cell.setCellValue(listIndicateur.get(i).get(j).get(k)
								.get(PilotageConstants.ETAT));
					}

					/*
					 * if(listIndicateur.get(i).get(j).get(k).get(PilotageConstants
					 * .COMMENTAIRE) != null){ String[] nbLigneString =
					 * listIndicateur
					 * .get(i).get(j).get(k).get(PilotageConstants.
					 * COMMENTAIRE).split("\n"); int nbLigneComment =
					 * nbLigneString.length+5; Comment comment =
					 * patr.createComment(new
					 * HSSFClientAnchor(0,0,0,0,(short)indexx
					 * ,rowIndex+1,(short)(indexx+2),rowIndex+nbLigneComment));
					 * comment.setString(new
					 * HSSFRichTextString(listIndicateur.get
					 * (i).get(j).get(k).get(PilotageConstants.COMMENTAIRE)));
					 * comment.setVisible(true); cell.setCellComment(comment); }
					 */

					HSSFCellStyle headertmp;
					if (!listIndicateur.get(i).get(j).get(k)
							.get(PilotageConstants.FORMAT).equals("datetime")
							&& !listIndicateur.get(i).get(j).get(k)
									.get(PilotageConstants.FORMAT)
									.equals("varchar")) {
						HSSFCellStyle headerIndicateur = lExcelDoc
								.createCellStyle();
						headerIndicateur
								.setAlignment(HSSFCellStyle.ALIGN_CENTER);
						headerIndicateur.setFont(fontBlackBold);
						headerIndicateur
								.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
						headerIndicateur
								.setBottomBorderColor(HSSFColor.WHITE.index);
						headerIndicateur
								.setLeftBorderColor(HSSFColor.WHITE.index);
						headerIndicateur
								.setRightBorderColor(HSSFColor.WHITE.index);
						headerIndicateur
								.setTopBorderColor(HSSFColor.WHITE.index);
						headerIndicateur.setBorderRight((short) 2);
						headerIndicateur.setBorderLeft((short) 2);
						headerIndicateur.setBorderTop((short) 2);
						headerIndicateur.setBorderBottom((short) 2);

						String couleur = listIndicateur.get(i).get(j).get(k)
								.get(PilotageConstants.COLOR).substring(1, 7);
						HSSFColor hssfcolorIndic = new HSSFColor();

						red = ColorService.valueRedColor(couleur);
						green = ColorService.valueGreenColor(couleur);
						blue = ColorService.valueBlueColor(couleur);

						hssfcolorIndic = palette.findColor(red, green, blue);
						if (hssfcolorIndic == null) {
							Short nouvelIndex = ColorService
									.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
							listeIndexCouleurDejaUtilise.add(nouvelIndex);
							palette.setColorAtIndex(nouvelIndex, red, green,
									blue);
							hssfcolorIndic = palette.getColor(nouvelIndex);
						}

						headerIndicateur.setFillForegroundColor(hssfcolorIndic
								.getIndex());

						cell.setCellStyle(headerIndicateur);
						headertmp = headerIndicateur;
					} else {
						cell.setCellStyle(headerHeure);
						headertmp = headerHeure;
					}

					if (envirUnique) {
						indexx++;
						cell = row.createCell(indexx);
						cell.setCellStyle(headertmp);
						sheet.addMergedRegion(new CellRangeAddress(rowIndex,
								rowIndex, 1, 2));
					}

					indexx++;

					if (listIndicateur.get(i).get(j).get(k)
							.get(PilotageConstants.FORMAT).equals("liste")) {
						if (listChampHeureDebut.get(i).get(k).equals("1")) {
							if (!listIndicateur.get(i).get(j).get(k)
									.get(PilotageConstants.HEURE_DEBUT)
									.equals("-1")) {
								cell = row.createCell(indexx);
								cell.setCellValue(listIndicateur.get(i).get(j)
										.get(k)
										.get(PilotageConstants.HEURE_DEBUT));
								cell.setCellStyle(headerHeure);
							} else {
								cell = row.createCell(indexx);
								cell.setCellStyle(headerHeure);
							}
							indexx++;
							if (listChampHeureFin.get(i).get(k).equals("0")
									&& k + 1 != listEnvironnement.get(i).size()) {
								cell = row.createCell(indexx);
								cell.setCellStyle(headerStyle);
								indexx++;
							}
						}
						if (listChampHeureFin.get(i).get(k).equals("1")) {
							if (!listIndicateur.get(i).get(j).get(k)
									.get(PilotageConstants.HEURE_FIN)
									.equals("-1")) {
								cell = row.createCell(indexx);
								cell.setCellValue(listIndicateur.get(i).get(j)
										.get(k)
										.get(PilotageConstants.HEURE_FIN));
								cell.setCellStyle(headerHeure);
							} else {
								cell = row.createCell(indexx);
								cell.setCellStyle(headerHeure);
							}
							indexx++;
							if (k + 1 != listEnvironnement.get(i).size()) {
								cell = row.createCell(indexx);
								cell.setCellStyle(headerStyle);
								indexx++;
							}
						}
					} else {
						if (listChampHeureDebut.get(i).get(k).equals("1")) {
							cell = row.createCell(indexx);
							cell.setCellStyle(headerHeure);
							indexx++;
							if (listChampHeureFin.get(i).get(k).equals("0")
									&& k + 1 != listEnvironnement.get(i).size()) {
								cell = row.createCell(indexx);
								cell.setCellStyle(headerStyle);
								indexx++;
							}
						}
						if (listChampHeureFin.get(i).get(k).equals("1")) {
							cell = row.createCell(indexx);
							cell.setCellStyle(headerHeure);
							indexx++;
							if (k + 1 != listEnvironnement.get(i).size()) {
								cell = row.createCell(indexx);
								cell.setCellStyle(headerStyle);
								indexx++;
							}
						}
					}
				}

				rowIndex++;
			}

			for (int n = 0; n < listCommentaires.size(); n++) {
				row = sheet.createRow(rowIndex);
				cell = row.createCell(0);
				cell.setCellValue(" (" + (n + 1) + ") "
						+ listCommentaires.get(n));
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex,
						0, nbEnvir));
				rowIndex++;
			}

			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbEnvir));
			rowIndex++;

			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbEnvir));
			rowIndex++;

		}

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		cell.setCellValue("Légende :");

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbEnvir));
		cell.setCellStyle(headerStyle);
		rowIndex++;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbEnvir));
		rowIndex++;

		int i = 0;
		int j = 0;
		while (i < envirList.size() || j < listEtat.size()) {
			row = sheet.createRow(rowIndex);

			if (i < listCaisse.size() && !listCaisse.get(i).isEmpty()) {
				String legende = envirList.get(i).getNom_envir() + " : ";

				cell = row.createCell(0);
				cell.setCellValue(legende);
				cell.setCellStyle(headerLegende);

				String stringCaisse = listCaisse.get(i).get(0).getNomCaisse();
				for (int k = 1; k < listCaisse.get(i).size(); k++) {
					stringCaisse += ", "
							+ listCaisse.get(i).get(k).getNomCaisse();
				}

				cell = row.createCell(1);
				cell.setCellValue(stringCaisse);

				cell.setCellStyle(headerLegende);

				cell = row.createCell(2);
				cell.setCellStyle(headerLegende);
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex,
						1, 2));

			}

			if (j < listEtat.size()) {
				cell = row.createCell(nbEnvir - 1);
				cell.setCellValue(listEtat.get(j).getLibelle_etat());

				HSSFCellStyle headerEtat = lExcelDoc.createCellStyle();
				headerEtat.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headerEtat.setFont(fontBlackBold);
				headerEtat.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headerEtat.setBottomBorderColor(HSSFColor.WHITE.index);
				headerEtat.setLeftBorderColor(HSSFColor.WHITE.index);
				headerEtat.setRightBorderColor(HSSFColor.WHITE.index);
				headerEtat.setTopBorderColor(HSSFColor.WHITE.index);
				headerEtat.setBorderRight((short) 2);
				headerEtat.setBorderLeft((short) 2);
				headerEtat.setBorderTop((short) 2);
				headerEtat.setBorderBottom((short) 2);

				String couleur = listEtat.get(j).getCouleur().substring(1, 7);
				HSSFColor hssfcolorlegende = new HSSFColor();

				red = ColorService.valueRedColor(couleur);
				green = ColorService.valueGreenColor(couleur);
				blue = ColorService.valueBlueColor(couleur);

				hssfcolorlegende = palette.findColor(red, green, blue);
				if (hssfcolorlegende == null) {
					Short nouvelIndex = ColorService
							.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
					listeIndexCouleurDejaUtilise.add(nouvelIndex);
					palette.setColorAtIndex(nouvelIndex, red, green, blue);
					hssfcolorlegende = palette.getColor(nouvelIndex);
				}

				headerEtat.setFillForegroundColor(hssfcolorlegende.getIndex());
				cell.setCellStyle(headerEtat);

				cell = row.createCell(nbEnvir);
				cell.setCellValue(listEtat.get(j).getDefinition());
				cell.setCellStyle(headerLegendeEtat);
			}

			if (i == listEtat.size()) {
				cell = row.createCell(nbEnvir - 1);
				cell.setCellValue("NA");
				cell.setCellStyle(headerEtatBlue);
				cell = row.createCell(nbEnvir);
				cell.setCellValue("Non Applicable");
				cell.setCellStyle(headerLegende);
			}

			i++;
			j++;
			rowIndex++;

		}

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbEnvir));
		rowIndex++;

		// add picture data to this workbook.
		InputStream isPiedPage = new FileInputStream(path_img
				+ "bandeau-pied-de-page.jpg");
		byte[] bytesPiedPage = IOUtils.toByteArray(isPiedPage);
		int pictureIdxPiedPage = lExcelDoc.addPicture(bytesPiedPage,
				HSSFWorkbook.PICTURE_TYPE_JPEG);
		is.close();

		CreationHelper helperPiedPage = lExcelDoc.getCreationHelper();

		// add a picture shape
		ClientAnchor anchorPiedPage = helperPiedPage.createClientAnchor();
		anchorPiedPage.setCol1(0);
		anchorPiedPage.setRow1(rowIndex);
		anchorPiedPage.setCol2(nbEnvir + 1);
		anchorPiedPage.setRow2(rowIndex + 2);
		drawing.createPicture(anchorPiedPage, pictureIdxPiedPage);

		return lExcelDoc;
	}

	public static HSSFWorkbook exportGroupeMeteoDetail(String nomGroupe,
			String commentaireGroupe, String date, String horaire,
			Date heure_saisie, List<Meteo_Meteo> listMeteo,
			List<String> listCommentaire,
			List<List<Meteo_Service>> listService,
			List<List<Meteo_Environnement>> listEnvironnement, int nbEnvir,
			List<List<List<Map<String, String>>>> listIndicateur,
			List<Meteo_Environnement> envirList,
			List<List<Meteo_Caisse>> listCaisse,
			List<Meteo_EtatPossible> listEtat, String path_img)
			throws Exception {

		int rowIndex = 7;
		HSSFRow row;
		HSSFCell cell;
		HSSFWorkbook lExcelDoc = new HSSFWorkbook();
		HSSFSheet sheet = lExcelDoc.createSheet(nomGroupe);
		HSSFPatriarch patr = sheet.createDrawingPatriarch();

		// on prepare une liste d'index de couleur temporaires
		List<Short> listeIndexCouleurDejaUtilise = new ArrayList<Short>();

		// add picture data to this workbook.
		InputStream is = new FileInputStream(path_img + "bandeau-mail-prod.jpg");
		byte[] bytes = IOUtils.toByteArray(is);
		int pictureIdx = lExcelDoc.addPicture(bytes,
				HSSFWorkbook.PICTURE_TYPE_JPEG);
		is.close();

		// Style du font
		HSSFFont fontWhiteBold = lExcelDoc.createFont();
		fontWhiteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontWhiteBold.setColor(HSSFColor.WHITE.index);

		HSSFFont fontBlackBold = lExcelDoc.createFont();
		fontBlackBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontBlackBold.setColor(HSSFColor.BLACK.index);

		HSSFFont fontBlack = lExcelDoc.createFont();
		fontBlack.setColor(HSSFColor.BLACK.index);

		HSSFPalette palette = lExcelDoc.getCustomPalette();
		HSSFColor hssfcolor = new HSSFColor();

		byte red = ColorService.valueRedColor("a59694");
		byte green = ColorService.valueGreenColor("a59694");
		byte blue = ColorService.valueBlueColor("a59694");

		hssfcolor = palette.findColor(red, green, blue);
		short fonce = 0;
		if (hssfcolor == null) {
			Short nouvelIndex = ColorService
					.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
			listeIndexCouleurDejaUtilise.add(nouvelIndex);
			palette.setColorAtIndex(nouvelIndex, red, green, blue);
			hssfcolor = palette.getColor(nouvelIndex);
			fonce = hssfcolor.getIndex();
		}

		red = ColorService.valueRedColor("cdc4c3");
		green = ColorService.valueGreenColor("cdc4c3");
		blue = ColorService.valueBlueColor("cdc4c3");

		hssfcolor = palette.findColor(red, green, blue);
		short clair = 0;
		if (hssfcolor == null) {
			Short nouvelIndex = ColorService
					.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
			listeIndexCouleurDejaUtilise.add(nouvelIndex);
			palette.setColorAtIndex(nouvelIndex, red, green, blue);
			hssfcolor = palette.getColor(nouvelIndex);
			clair = hssfcolor.getIndex();
		}

		red = ColorService.valueRedColor("e1dcdb");
		green = ColorService.valueGreenColor("e1dcdb");
		blue = ColorService.valueBlueColor("e1dcdb");

		hssfcolor = palette.findColor(red, green, blue);
		short lightclair = 0;
		if (hssfcolor == null) {
			Short nouvelIndex = ColorService
					.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
			listeIndexCouleurDejaUtilise.add(nouvelIndex);
			palette.setColorAtIndex(nouvelIndex, red, green, blue);
			hssfcolor = palette.getColor(nouvelIndex);
			lightclair = hssfcolor.getIndex();
		}

		HSSFCellStyle commentStyle = lExcelDoc.createCellStyle();
		commentStyle.setFillForegroundColor(lightclair);
		commentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		commentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		commentStyle.setBottomBorderColor(HSSFColor.WHITE.index);
		commentStyle.setLeftBorderColor(HSSFColor.WHITE.index);
		commentStyle.setRightBorderColor(HSSFColor.WHITE.index);
		commentStyle.setTopBorderColor(HSSFColor.WHITE.index);
		commentStyle.setBorderRight((short) 2);
		commentStyle.setBorderLeft((short) 2);
		commentStyle.setBorderTop((short) 2);
		commentStyle.setBorderBottom((short) 2);

		// Style centré
		HSSFCellStyle centerStyle = lExcelDoc.createCellStyle();
		centerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		centerStyle.setFont(fontBlackBold);
		centerStyle.setFillForegroundColor(SERVICE_COLOR);
		centerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFCellStyle headerStyle = lExcelDoc.createCellStyle();
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setFont(fontWhiteBold);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerStyle.setFillForegroundColor(fonce);

		HSSFCellStyle headerService = lExcelDoc.createCellStyle();
		// headerService.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerService.setFont(fontBlackBold);
		headerService.setFillForegroundColor(clair);
		headerService.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerService.setBottomBorderColor(HSSFColor.WHITE.index);
		headerService.setBorderBottom((short) 2);
		headerService.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFCellStyle headerCommentGroupe = lExcelDoc.createCellStyle();
		headerCommentGroupe.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerCommentGroupe.setFont(fontBlackBold);
		headerCommentGroupe.setFillForegroundColor(clair);
		headerCommentGroupe.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerCommentGroupe
				.setVerticalAlignment(HSSFCellStyle.VERTICAL_JUSTIFY);

		HSSFCellStyle headerEnvir = lExcelDoc.createCellStyle();
		headerEnvir.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerEnvir.setFont(fontBlackBold);
		headerEnvir.setFillForegroundColor(clair);
		headerEnvir.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerEnvir.setBorderLeft((short) 2);
		headerEnvir.setLeftBorderColor(HSSFColor.WHITE.index);

		HSSFCellStyle headerLegende = lExcelDoc.createCellStyle();
		headerLegende.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerLegende.setFont(fontBlackBold);
		headerLegende.setFillForegroundColor(clair);
		headerLegende.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerLegende.setBottomBorderColor(HSSFColor.WHITE.index);
		headerLegende.setLeftBorderColor(HSSFColor.WHITE.index);
		headerLegende.setRightBorderColor(HSSFColor.WHITE.index);
		headerLegende.setTopBorderColor(HSSFColor.WHITE.index);
		headerLegende.setBorderRight((short) 2);
		headerLegende.setBorderLeft((short) 2);
		headerLegende.setBorderTop((short) 2);
		headerLegende.setBorderBottom((short) 2);
		headerLegende.setWrapText(true);

		HSSFCellStyle headerLegendeEtat = lExcelDoc.createCellStyle();
		headerLegendeEtat.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerLegendeEtat.setFont(fontBlackBold);
		headerLegendeEtat.setFillForegroundColor(clair);
		headerLegendeEtat.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerLegendeEtat.setBottomBorderColor(HSSFColor.WHITE.index);
		headerLegendeEtat.setLeftBorderColor(HSSFColor.WHITE.index);
		headerLegendeEtat.setRightBorderColor(HSSFColor.WHITE.index);
		headerLegendeEtat.setTopBorderColor(HSSFColor.WHITE.index);
		headerLegendeEtat.setBorderRight((short) 2);
		headerLegendeEtat.setBorderLeft((short) 2);
		headerLegendeEtat.setBorderTop((short) 2);
		headerLegendeEtat.setBorderBottom((short) 2);

		HSSFCellStyle headerEtatBlue = lExcelDoc.createCellStyle();
		headerEtatBlue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerEtatBlue.setFont(fontBlackBold);
		headerEtatBlue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headerEtatBlue.setBottomBorderColor(HSSFColor.WHITE.index);
		headerEtatBlue.setLeftBorderColor(HSSFColor.WHITE.index);
		headerEtatBlue.setRightBorderColor(HSSFColor.WHITE.index);
		headerEtatBlue.setTopBorderColor(HSSFColor.WHITE.index);
		headerEtatBlue.setBorderRight((short) 2);
		headerEtatBlue.setBorderLeft((short) 2);
		headerEtatBlue.setBorderTop((short) 2);
		headerEtatBlue.setBorderBottom((short) 2);
		headerEtatBlue
				.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);

		int in = 1;
		for (int i = 0; i < nbEnvir; i++) {
			sheet.setColumnWidth(in, 7000);
			sheet.setColumnWidth(in + 1, 10000);
			in = in + 2;
		}

		int nbColumn = nbEnvir * 2;

		HSSFCreationHelper helper = lExcelDoc.getCreationHelper();

		// Create the drawing patriarch. This is the top level container for all
		// shapes.
		Drawing drawing = sheet.createDrawingPatriarch();

		// add a picture shape
		ClientAnchor anchor = helper.createClientAnchor();
		anchor.setCol1(0);
		anchor.setRow1(0);
		anchor.setCol2(nbColumn + 1);
		anchor.setRow2(rowIndex);
		drawing.createPicture(anchor, pictureIdx);

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbColumn));
		rowIndex++;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		cell.setCellValue(nomGroupe);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex + 1, 0,
				nbColumn));
		cell.setCellStyle(headerStyle);
		rowIndex = rowIndex + 2;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbColumn));
		rowIndex++;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		cell.setCellValue(date + " " + horaire);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbColumn));
		cell.setCellStyle(headerStyle);
		rowIndex++;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbColumn));
		rowIndex++;

		if (!commentaireGroupe.equals("")) {
			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);
			cell.setCellValue(commentaireGroupe);
			cell.setCellStyle(headerCommentGroupe);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbColumn));
			int tailleTotalColumn = 7000 + (nbEnvir * 7000) + (nbEnvir * 10000);
			float tailleLigneComment = tailleTotalColumn / 256;
			int nbLigneComment = 0;
			String[] lines = commentaireGroupe.split("\\n?\\r");

			for (int i = 0; i < lines.length; i++) {
				if (lines[i].length() < tailleLigneComment) {
					nbLigneComment++;
				} else {
					int nbRowComment = (int) Math.ceil((float) lines[i]
							.length() / tailleLigneComment);
					nbLigneComment = nbLigneComment + nbRowComment;
				}
			}

			int tailleRowComment = row.getHeight() * nbLigneComment;
			if (tailleRowComment != 0) {
				row.setHeight((short) tailleRowComment);
			}
			rowIndex++;
		}

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbColumn));
		rowIndex++;

		for (int i = 0; i < listMeteo.size(); i++) {

			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);

			if (listCommentaire.get(i) != "") {
				cell.setCellValue(listMeteo.get(i).getNom_meteo()
						+ " (voir Commentaire Excel)");

				String[] nbLigneString = listCommentaire.get(i).split("\n");
				int nbLigneComment = nbLigneString.length + 5;

				Comment comment = patr.createComment(new HSSFClientAnchor(0, 0,
						0, 0, (short) 3, rowIndex + 1, (short) 7, rowIndex
								+ nbLigneComment));
				comment.setString(new HSSFRichTextString(listCommentaire.get(i)));
				cell.setCellComment(comment);
			} else {
				cell.setCellValue(listMeteo.get(i).getNom_meteo());
			}

			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbColumn));
			cell.setCellStyle(headerStyle);
			rowIndex++;

			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbColumn));
			rowIndex++;

			row = sheet.createRow(rowIndex);
			int ind = 1;
			for (int j = 0; j < listEnvironnement.get(i).size(); j++) {
				cell = row.createCell(ind);
				cell.setCellValue(listEnvironnement.get(i).get(j)
						.getNom_envir());
				cell.setCellStyle(headerEnvir);
				ind++;
				cell = row.createCell(ind);
				cell.setCellValue("Commentaire "
						+ listEnvironnement.get(i).get(j).getNom_envir());
				cell.setCellStyle(headerEnvir);
				ind++;
			}
			rowIndex++;

			row = sheet.createRow(rowIndex);
			int hauteur = 0;
			int changeHeightRow = 0;
			for (int j = 0; j < listService.get(i).size(); j++) {
				row = sheet.createRow(rowIndex);
				int indexx = 0;
				cell = row.createCell(indexx);
				cell.setCellValue(listService.get(i).get(j).getService());
				sheet.autoSizeColumn(0);
				if (sheet.getColumnWidth(0) <= 7000) {
					sheet.setColumnWidth(0, 7000);
				} else {
					sheet.setColumnWidth(0, sheet.getColumnWidth(0) + 700);
				}
				cell.setCellStyle(headerService);
				indexx++;

				for (int k = 0; k < listEnvironnement.get(i).size(); k++) {
					cell = row.createCell(indexx);
					cell.setCellValue(listIndicateur.get(i).get(j).get(k)
							.get(PilotageConstants.ETAT));

					HSSFCellStyle headerIndicateur = lExcelDoc
							.createCellStyle();
					headerIndicateur.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					headerIndicateur.setFont(fontBlackBold);
					headerIndicateur
							.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					headerIndicateur
							.setBottomBorderColor(HSSFColor.WHITE.index);
					headerIndicateur.setLeftBorderColor(HSSFColor.WHITE.index);
					headerIndicateur.setRightBorderColor(HSSFColor.WHITE.index);
					headerIndicateur.setTopBorderColor(HSSFColor.WHITE.index);
					headerIndicateur.setBorderRight((short) 2);
					headerIndicateur.setBorderLeft((short) 2);
					headerIndicateur.setBorderTop((short) 2);
					headerIndicateur.setBorderBottom((short) 2);
					headerIndicateur
							.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

					String couleur = listIndicateur.get(i).get(j).get(k)
							.get(PilotageConstants.COLOR).substring(1, 7);
					HSSFColor hssfcolorIndic = new HSSFColor();

					red = ColorService.valueRedColor(couleur);
					green = ColorService.valueGreenColor(couleur);
					blue = ColorService.valueBlueColor(couleur);

					hssfcolorIndic = palette.findColor(red, green, blue);
					if (hssfcolorIndic == null) {
						Short nouvelIndex = ColorService
								.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
						listeIndexCouleurDejaUtilise.add(nouvelIndex);
						palette.setColorAtIndex(nouvelIndex, red, green, blue);
						hssfcolorIndic = palette.getColor(nouvelIndex);
					}

					headerIndicateur.setFillForegroundColor(hssfcolorIndic
							.getIndex());

					cell.setCellStyle(headerIndicateur);

					indexx++;

					cell = row.createCell(indexx);
					commentStyle.setWrapText(true);

					cell.setCellValue(listIndicateur.get(i).get(j).get(k)
							.get(PilotageConstants.COMMENTAIRE));
					cell.setCellStyle(commentStyle);
					if (listIndicateur.get(i).get(j).get(k)
							.get(PilotageConstants.COMMENTAIRE) != null) {
						changeHeightRow = 1;
						int nbLigneComment = 0;
						float tailleLigneComment = sheet.getColumnWidth(indexx) / 256;
						String[] lines = listIndicateur.get(i).get(j).get(k)
								.get(PilotageConstants.COMMENTAIRE)
								.split("\\r\\n|\\r|\\n");

						for (int l = 0; l < lines.length; l++) {
							if (lines[l].length() < tailleLigneComment) {
								nbLigneComment++;
							} else {
								int nbRowComment = (int) Math
										.ceil((float) lines[l].length()
												/ tailleLigneComment);
								nbLigneComment = nbLigneComment + nbRowComment;
							}
						}

						nbLigneComment = nbLigneComment + 3;
						int tailleRowComment = row.getHeight() * nbLigneComment;
						if (tailleRowComment > hauteur) {
							hauteur = tailleRowComment;
						}
					}

					indexx++;
				}
				if (changeHeightRow == 1) {
					row.setHeight((short) (hauteur));
					changeHeightRow = 0;
				}
				hauteur = 0;
				rowIndex++;
			}

			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbColumn));
			rowIndex++;

			row = sheet.createRow(rowIndex);
			cell = row.createCell(0);
			sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
					nbColumn));
			rowIndex++;

		}

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		cell.setCellValue("Légende :");

		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbColumn));
		cell.setCellStyle(headerStyle);
		rowIndex++;

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbColumn));
		rowIndex++;

		int i = 0;
		int j = 0;
		while (i < envirList.size() || j < listEtat.size()) {
			row = sheet.createRow(rowIndex);

			if (i < listCaisse.size() && !listCaisse.get(i).isEmpty()) {
				String legende = envirList.get(i).getNom_envir() + " : ";

				cell = row.createCell(0);
				cell.setCellValue(legende);
				cell.setCellStyle(headerLegende);

				String stringCaisse = listCaisse.get(i).get(0).getNomCaisse();
				for (int k = 1; k < listCaisse.get(i).size(); k++) {
					stringCaisse += ", "
							+ listCaisse.get(i).get(k).getNomCaisse();
				}

				cell = row.createCell(1);
				cell.setCellValue(stringCaisse);

				cell.setCellStyle(headerLegende);

				cell = row.createCell(2);
				cell.setCellStyle(headerLegende);
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex,
						1, 2));

			}

			if (j < listEtat.size()) {
				cell = row.createCell(nbColumn - 1);
				cell.setCellValue(listEtat.get(j).getLibelle_etat());

				HSSFCellStyle headerEtat = lExcelDoc.createCellStyle();
				headerEtat.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				headerEtat.setFont(fontBlackBold);
				headerEtat.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				headerEtat.setBottomBorderColor(HSSFColor.WHITE.index);
				headerEtat.setLeftBorderColor(HSSFColor.WHITE.index);
				headerEtat.setRightBorderColor(HSSFColor.WHITE.index);
				headerEtat.setTopBorderColor(HSSFColor.WHITE.index);
				headerEtat.setBorderRight((short) 2);
				headerEtat.setBorderLeft((short) 2);
				headerEtat.setBorderTop((short) 2);
				headerEtat.setBorderBottom((short) 2);

				String couleur = listEtat.get(j).getCouleur().substring(1, 7);
				// HSSFPalette palette = lExcelDoc.getCustomPalette();
				HSSFColor hssfcolorlegende = new HSSFColor();

				red = ColorService.valueRedColor(couleur);
				green = ColorService.valueGreenColor(couleur);
				blue = ColorService.valueBlueColor(couleur);

				hssfcolorlegende = palette.findColor(red, green, blue);
				if (hssfcolorlegende == null) {
					Short nouvelIndex = ColorService
							.getIndexCouleurNonUtilise(listeIndexCouleurDejaUtilise);
					listeIndexCouleurDejaUtilise.add(nouvelIndex);
					palette.setColorAtIndex(nouvelIndex, red, green, blue);
					hssfcolorlegende = palette.getColor(nouvelIndex);
				}

				headerEtat.setFillForegroundColor(hssfcolorlegende.getIndex());
				cell.setCellStyle(headerEtat);

				cell = row.createCell(nbColumn);
				cell.setCellValue(listEtat.get(j).getDefinition());
				cell.setCellStyle(headerLegendeEtat);
			}

			if (i == listEtat.size()) {
				cell = row.createCell(nbColumn - 1);
				cell.setCellValue("NA");
				cell.setCellStyle(headerEtatBlue);
				cell = row.createCell(nbColumn);
				cell.setCellValue("Non Applicable");
				cell.setCellStyle(headerLegende);
			}

			i++;
			j++;
			rowIndex++;

		}

		row = sheet.createRow(rowIndex);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0,
				nbColumn));
		rowIndex++;

		// add picture data to this workbook.
		InputStream isPiedPage = new FileInputStream(path_img
				+ "bandeau-pied-de-page.jpg");
		byte[] bytesPiedPage = IOUtils.toByteArray(isPiedPage);
		int pictureIdxPiedPage = lExcelDoc.addPicture(bytesPiedPage,
				HSSFWorkbook.PICTURE_TYPE_JPEG);
		is.close();

		CreationHelper helperPiedPage = lExcelDoc.getCreationHelper();

		// add a picture shape
		ClientAnchor anchorPiedPage = helperPiedPage.createClientAnchor();
		anchorPiedPage.setCol1(0);
		anchorPiedPage.setRow1(rowIndex);
		anchorPiedPage.setCol2(nbColumn + 1);
		anchorPiedPage.setRow2(rowIndex + 2);
		drawing.createPicture(anchorPiedPage, pictureIdxPiedPage);

		return lExcelDoc;
	}
}