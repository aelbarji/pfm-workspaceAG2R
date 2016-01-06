package pilotage.charts;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.opensymphony.xwork2.ActionSupport;

public class DrawChart extends ActionSupport {  

	private static final long serialVersionUID = 1L;
	private static final String NAME = "n";
	private static final String VALUE = "v";
	private static final String COULEUR_ROUGE = "r";
	private static final String COULEUR_VERTE = "g";
	private static final String COULEUR_BLEUE = "b";
	private static final String PIE = "pie";
	private static final String TEXT_NO_VALUE = "Aucune valeur disponible";
	
	private String type;
	private String titre;
	private int width;
	private int height;
	private boolean is3D;
	private boolean legende;
	private boolean tips;

	public boolean isLegende() {
		return legende;
	}

	public void setLegende(boolean legende) {
		this.legende = legende;
	}

	public boolean isTips() {
		return tips;
	}

	public void setTips(boolean tips) {
		this.tips = tips;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isIs3D() {
		return is3D;
	}

	public void setIs3D(boolean is3d) {
		is3D = is3d;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String execute() throws Exception{
		byte[] bytes;
		
		if(PIE.equals(type)){
			BufferedImage buff = createPieChart(ServletActionContext.getRequest());
			bytes = ChartUtilities.encodeAsPNG(buff);
		}
		else
			bytes = new byte[0];
  
		//mise dans la response
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setContentType("image/png");  
        OutputStream out = response.getOutputStream();  
        out.write(bytes);  
  
        return null;  
  
    }

	private BufferedImage createPieChart(HttpServletRequest request) {
		DefaultPieDataset datas = new DefaultPieDataset();
		
		//récupération des valeurs du graph
		int i = 0;
		Map<String, Color> couleurs = new HashMap<String, Color>();
		while(request.getParameter(NAME + i) != null){
			String nom = request.getParameter(NAME + i).toString();
			Double valeur = Double.parseDouble(request.getParameter(VALUE + i).toString());
			datas.setValue(nom,valeur);
			String rouge = (String)request.getParameter(COULEUR_ROUGE + i);
			String vert = (String)request.getParameter(COULEUR_VERTE + i);
			String bleu = (String)request.getParameter(COULEUR_BLEUE + i);
			if(rouge != null || vert != null || bleu != null){
				int comp_rouge = rouge == null ? 0 : Integer.parseInt(rouge);
				int comp_verte = vert == null ? 0 : Integer.parseInt(vert);
				int comp_bleu = bleu == null ? 0 : Integer.parseInt(bleu);
				
				couleurs.put(nom, new Color(comp_rouge, comp_verte, comp_bleu));
			}
			++i;
		}

		//création du graph
		JFreeChart chart = is3D ? 
				ChartFactory.createPieChart3D(titre, datas, legende, tips, false) : 
				ChartFactory.createPieChart(titre, datas, legende, tips, false);
		
		//couleurs
		PiePlot plot = (PiePlot)chart.getPlot();
		plot.setOutlineVisible(false);
		plot.setBackgroundAlpha(0);
		for(Entry<String, Color> entry : couleurs.entrySet())
			plot.setSectionPaint(entry.getKey(), entry.getValue());
		
		//si aucune valeur
		plot.setNoDataMessage(TEXT_NO_VALUE);
		
		//pourcentage
		//plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));//new StandardPieSectionLabelGenerator("1.0",new DecimalFormat("#0.00"),new DecimalFormat("#0.00%")));
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));
		
		return chart.createBufferedImage(width, height);
	} 
	
	
}
