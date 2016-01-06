package pilotage.charts;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import com.opensymphony.xwork2.ActionSupport;

public class BarChart extends ActionSupport {

	private static final long serialVersionUID = 8193480231812088396L;
	private static final String NAME = "n";
	private static final String VALUE = "v";
	private static final String CLASSNAME = "c";
	private static final String BAR = "bar";
	private static final String TEXT_NO_VALUE = "Aucune valeur disponible";
	
	private String type;
	private String titre;
	private int width;
	private int height;
	private boolean is3D;
	private boolean legende;
	private boolean tips;

	private String vertialLabel;
	private String horizontalLabel;
	private String colorString;
	
	
	/**
	 * @return the colorString
	 */
	public String getColorString() {
		return colorString;
	}

	/**
	 * @param colorString the colorString to set
	 */
	public void setColorString(String colorString) {
		this.colorString = colorString;
	}

	/**
	 * @return the vertialLabel
	 */
	public String getVertialLabel() {
		return vertialLabel;
	}

	/**
	 * @param vertialLabel the vertialLabel to set
	 */
	public void setVertialLabel(String vertialLabel) {
		this.vertialLabel = vertialLabel;
	}

	/**
	 * @return the horizontalLabel
	 */
	public String getHorizontalLabel() {
		return horizontalLabel;
	}

	/**
	 * @param horizontalLabel the horizontalLabel to set
	 */
	public void setHorizontalLabel(String horizontalLabel) {
		this.horizontalLabel = horizontalLabel;
	}

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

		if(BAR.equals(type)){
			BufferedImage buff = createBarChart(ServletActionContext.getRequest());
			bytes = ChartUtilities.encodeAsPNG(buff);
		}
		else
			bytes = new byte[0];
  
		//mise dans la response
        HttpServletResponse response = ServletActionContext.getResponse();  
        response.setContentType("image/png");  
        OutputStream out = response.getOutputStream();  
        out.write(bytes); 
        out.flush();
        out.close();
		
        return null;  
  
    }

	private BufferedImage createBarChart(HttpServletRequest request) {

		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		int i = 0;
		while(request.getParameter(NAME + i) != null){
			String nom = request.getParameter(NAME + i).toString();
			Double valeur = Double.parseDouble(request.getParameter(VALUE + i).toString());
			String className = request.getParameter(CLASSNAME + i).toString();
			categoryDataset.addValue(valeur, className , nom);
			++i;
		}
		
		//création du graph
		JFreeChart chart = is3D ? 
				ChartFactory.createBarChart3D(titre,horizontalLabel , vertialLabel, categoryDataset, PlotOrientation.VERTICAL, true, true, false): 
				ChartFactory.createBarChart(titre, horizontalLabel , vertialLabel , categoryDataset, PlotOrientation.VERTICAL, true, true, false);
		
		//couleurs
		Color color = new Color(Integer.parseInt(colorString, 16));
		CategoryPlot categoryplot = chart.getCategoryPlot();
		BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer(); 
		barrenderer.setSeriesPaint(0, color);
		
		
		//si aucune valeur
		categoryplot.setNoDataMessage(TEXT_NO_VALUE);
		barrenderer.setItemMargin(0);
		
		barrenderer.setMaximumBarWidth(0.1);
		barrenderer.setBaseItemLabelsVisible(true); 
		barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		barrenderer.setItemLabelAnchorOffset(10D);
		barrenderer.setIncludeBaseInRange(true);
		categoryplot.getRangeAxis().setLabelAngle(Math.PI/2);
		NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setUpperMargin(0.2);
		
		return chart.createBufferedImage(width, height);
	} 
	
	
}
