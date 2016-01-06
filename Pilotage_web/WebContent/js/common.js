function changeData(name, data){
	var txtToChange = document.getElementById(name);
	txtToChange.value = data;
}

function submitData(urlToGo){
	var formToSub = document.getElementById("mainForm");
	formToSub.action = urlToGo;
	formToSub.submit();	
}
//permets d'ajouter un nb de jour à une date
function AddDays(date, amount) 
{ 
    var tzOff = date.getTimezoneOffset() * 60 * 1000; 
    var t = date.getTime(); 
    t += (1000 * 60 * 60 * 24) * amount; 
    var d = new Date(); 
    d.setTime(t); 
    var tzOff2 = d.getTimezoneOffset() * 60 * 1000; 
    if (tzOff != tzOff2) 
    { 
        var diff = tzOff2 - tzOff; 
        t += diff; 
        d.setTime(t); 
    } 
    return d; 
}
//Méthodes pour ne pas entrer de date à la main dans les datePickers
function disableControls(id){
	document.getElementById(id).childNodes[1].disable='true'; 
	document.getElementById(id).childNodes[1].readOnly='readonly';
	document.getElementById(id).childNodes[1].disable='';
}

function checkEmailAddress(mail){    
	var emailRegEx = new RegExp('^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$', 'i');
	return emailRegEx.test(mail);
}

//check pourcentage
function checkRate(value) {
	return /^[0-9]+(\.{0,1}|,{0,1})[0-9]{0,2}$/.test(value);
}

//check pourcentage
function checkNombreEntier(value) {
	return /^[0-9]+$/.test(value);
}

//Pagination
function changePage(nb) {
	var exp = new RegExp("^[0-9]+$","g");
	if(! exp.test(nb)){
		alert("Veuillez entrer un nombre entier en page");
		return;
	}
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	changeData("page", nb);
	document.getElementById("mainForm").submit();
}

function changeNrPerPage(nb) {
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	changeData("page", '1');
	changeData("nrPerPage", nb);
	document.getElementById("mainForm").submit();
}

function changePageIncident(nb) {
	var exp = new RegExp("^[0-9]+$","g");
	if(! exp.test(nb)){
		alert("Veuillez entrer un nombre entier en page");
		return;
	}
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	changeData("pageIncident", nb);
	document.getElementById("mainForm").submit();
}

function changeNrPerPageIncident(nb) {
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	changeData("pageIncident", '1');
	changeData("nrPerPageIncident", nb);
	document.getElementById("mainForm").submit();
}

function changePageInterEquipe(nb) {
	var exp = new RegExp("^[0-9]+$","g");
	if(! exp.test(nb)){
		alert("Veuillez entrer un nombre entier en page");
		return;
	}
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	changeData("pageInterEquipe", nb);
	document.getElementById("mainForm").submit();
}

function changeNrPerPageInterEquipe(nb) {
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	changeData("pageInterEquipe", '1');
	changeData("nrPerPageInterEquipe", nb);
	document.getElementById("mainForm").submit();
}

function changePageQuotidienne(nb) {
	var exp = new RegExp("^[0-9]+$","g");
	if(! exp.test(nb)){
		alert("Veuillez entrer un nombre entier en page");
		return;
	}
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	changeData("pageQuotidienne", nb);
	document.getElementById("mainForm").submit();
}

function changeNrPerPageQuotidienne(nb) {
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	changeData("pageQuotidienne", '1');
	changeData("nrPerPageQuotidienne", nb);
	document.getElementById("mainForm").submit();
}

function changeChecklistNrPerPage(nb){
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	changeData("page", '1');
	changeData("nrPerPage", nb);
	submitData("saveNrPerPageAction.action");
}

//tri
function changeSort(champ) {
	if('function' == typeof(reinitFilterValues)){
		reinitFilterValues();
	}
	var sort = document.getElementById("mainForm").sort.value;
	var sens = document.getElementById("mainForm").sens.value;
	if (sort == champ && sens == "asc")
		sens = "desc";
	else
		sens = "asc";
	document.getElementById("mainForm").sort.value = champ;
	document.getElementById("mainForm").sens.value = sens;
	document.getElementById("mainForm").submit();
}

function submitOnKeyPress(e){  
    var charCode=(navigator.appName=="Netscape")?e.which:e.keyCode;  
    if(charCode==13){  
        valider();  
    }  
}

function ajouterOnKeyPress(e){  
    var charCode=(navigator.appName=="Netscape")?e.which:e.keyCode;  
    if(charCode==13){  
        ajouter();  
    }  
}

function lancerRechercherOnKeyPress(e){  
    var charCode=(navigator.appName=="Netscape")?e.which:e.keyCode;  
    if(charCode==13){  
    	lancerRecherche();  
    }  
}
