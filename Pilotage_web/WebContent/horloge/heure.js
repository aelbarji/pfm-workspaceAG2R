// PLF - http://www.jejavascript.net/

heure=12;
minute=0;
var dos="horloge/";


function changer_jjs(heure, pays) {
		im1 = "im1"+pays;
		im2 = "im2"+pays;
		im3 = "im3"+pays;
		im4 = "im4"+pays;
		
		heur1=heure.charAt(0);
		heur2=heure.charAt(1);
		min1=heure.charAt(3);
		min2=heure.charAt(4);
       document.getElementById(im1).src = dos+heur1+".gif";
       document.getElementById(im2).src = dos+heur2+".gif";
       document.getElementById(im3).src = dos+min1+".gif";
	   document.getElementById(im4).src = dos+min2+".gif";	
}
