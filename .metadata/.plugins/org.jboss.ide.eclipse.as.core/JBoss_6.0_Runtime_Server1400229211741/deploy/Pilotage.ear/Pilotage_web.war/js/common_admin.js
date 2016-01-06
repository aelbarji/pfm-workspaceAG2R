/**
 * This file concludes all the common functions of the admin part.
 */

function submitData(urlToGo){
	var formToSub = document.getElementById("mainForm");
	formToSub.action = urlToGo;
	formToSub.submit();	
}

function changeData(name, dt){
	var txtToChange = document.getElementById(name);
	txtToChange.value = dt;
}


function checkChiffres(chaine){
	var entier = /^\d+$/;
	if(entier.test(chaine))
		return true;
	return false;
}

function checkLettresChiffres(chaine){
	var lettresChiffres = /^[0-9a-zA-Z_]+$/;
	if(lettresChiffres.test(chaine))
		return true;
	return false;
}