<%@ page import="java.io.File,java.io.FilenameFilter,java.util.Arrays"%>
<script text="text/javascript">
	function couleur(lien) {
	  var liens = document.getElementsByTagName("a");
	  for(var i=0;i<liens.length;i++) {
	    liens[i].style.color = "black";
	  }
	  lien.style.color = "red";
	}
	
</script>
<%
/**
  * jQuery File Tree JSP Connector
  * Version 1.0
  * Copyright 2008 Joshua Gould
  * 21 April 2008
*/	
    String dir = request.getParameter("dir");
    if (dir == null) {
    	return;
    }
	
	if (dir.charAt(dir.length()-1) == '\\') {
    	dir = dir.substring(0, dir.length()-1) + "/";
	} else if (dir.charAt(dir.length()-1) != '/') {
	    dir += "/";
	}
	
	dir = java.net.URLDecoder.decode(dir, "UTF-8");	
	
    if (new File(dir).exists()) {
		String[] files = new File(dir).list(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
				return name.charAt(0) != '.';
		    }
		});
		Arrays.sort(files, String.CASE_INSENSITIVE_ORDER);
		out.print("<ul class=\"jqueryFileTree\" style=\"display: none;\">");
		// All dirs
		for (String file : files) {
		    if (new File(dir, file).isDirectory()) {
				out.print("<li class=\"directory collapsed\"><a href=\"#\" rel=\"" + dir + file + "/\">"
					+ file + "</a></li>");
		    }
		}
		// All files
		out.print("<table border='2' rules='rows' cellpadding='0' cellspacing='0' width='500px' class='tabCenter'>");
		out.print("<thead><tr><th class='colTitle' width=''>Document(s)</th>");
		out.print("<th class='colTitle' width='20%'>Action</th></tr></thead><tbody>");
		
		if(files.length == 0)
			out.print("<tr>Le répertoire sélectionné ne contient aucun document</tr>");
		
		for (String file : files) {
		    if (!new File(dir, file).isDirectory()) {
				int dotIndex = file.lastIndexOf('.');
				String ext = dotIndex > 0 ? file.substring(dotIndex + 1) : "";
					out.print("<tr><td>");
					out.print("<li class='file ext_" + ext + "'><a href='#' rel='" + dir + file + " ' onclick='javascript:couleur(this)' >"
					+ file + "</a></td><td align='center'><a href='#' onclick='javascript:supprimer(\""+ file +"\")'><img class='icone' alt='Supprimer' title='Supprimer' src='GCE/img/supprimer-16.png\'/></a>");
					out.print("<a href='#' onclick='javascript:downloadFile(\"" + dir + file + "\")'><img class='icone' alt='Télécharger' title='Télécharger' src='GCE/img/download.jpeg\'/></a></li></td></tr>");
		    	}
		}
		
		out.print("</tbody></table></ul>");
    }
%>