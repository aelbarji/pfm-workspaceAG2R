(function($){
    $.fn.validationEngineLanguage = function(){
    };
    $.validationEngineLanguage = {
        newLang: function(){
            $.validationEngineLanguage.allRules = {
                "required": {
                    "regex": "none",
                    "alertText": "* Ce champ est requis",
                    "alertTextCheckboxMultiple": "* Choisir une option",
                    "alertTextCheckboxe": "* Cette option est requise"
                },
                "requiredInFunction": { 
                    "func": function(field, rules, i, options){
                        return (field.val() == "test") ? true : false;
                    },
                    "alertText": "* Field must equal test"
                },
                "DateTimeFR": { 
                    "func": function(field, rules, i, options){
            			var check = false;
            			var re = /^(0?[1-9]|[12][0-9]|3[01])[\/\/](0?[1-9]|1[012])[\/\/](\d{4}) ([\d:]+)$/;
            			if( re.test(field.val())) {
            				var adata = re.exec(field.val());
            				var gg = parseInt(adata[1],10);
            				var mm = parseInt(adata[2],10);
            				var aaaa = parseInt(adata[3],10);
            				var xdata = new Date(aaaa,mm-1,gg);
            				if ( ( xdata.getFullYear() === aaaa ) && ( xdata.getMonth() === mm - 1 ) && ( xdata.getDate() === gg ) ){
            					check = /^([01]\d|2[0-3])(:[0-5]\d){1,2}$/.test(adata[4]);
            				} else {
            					check = false;
            				}
            			} else {
            				check = false;
            			}
            			return check;
                    },
                    "alertText": "Date incorrecte ('dd/mm/yyyy hh:mm')"
                },
                "combobox": { 
                    "func": function(field, rules, i, options){
            			var check = false;
            			
            			if(field.value() == -1) {
            				check = false;
            			}
            			else{
            				check = true; 
            			}
            			return check;
                    },
                    "alertText": "Champ a renseigner"
                },
                "timeWithoutSec": { 
            		"regex": /^(0?[0-9]|[1][0-9]|[2][0-3])(:[0-5][0-9])$/,
                    "alertText": "Heure incorrecte ('hh:mm')"
                },
                "timeWithSec": { 
            		"regex": /^(0?[0-9]|[1][0-9]|[2][0-3])(:[0-5][0-9])(:[0-5][0-9])$/,
                    "alertText": "Heure incorrecte ('hh:mm:ss')"
                },
               "minSize": {
                    "regex": "none",
                    "alertText": "* Minimum ",
                    "alertText2": " caractères requis"
                },
				"groupRequired": {
                    "regex": "none",
                    "alertText": "* Vous devez remplir un des champs suivant"
                },
                "maxSize": {
                    "regex": "none",
                    "alertText": "* Maximum ",
                    "alertText2": " caractères requis"
                },
		        "min": {
                    "regex": "none",
                    "alertText": "* Valeur minimum requise "
                },
                "max": {
                    "regex": "none",
                    "alertText": "* Valeur maximum requise "
                },
		        "past": {
                    "regex": "none",
                    "alertText": "* Date avant le "
                },
                "future": {
                    "regex": "none",
                    "alertText": "* Date aprés le "
                },
                "maxCheckbox": {
                    "regex": "none",
                    "alertText": "* Nombre max de choix excédé"
                },
                "minCheckbox": {
                    "regex": "none",
                    "alertText": "* Veuillez choisir ",
                    "alertText2": " options"
                },
                "equals": {
                    "regex": "none",
                    "alertText": "* Votre champ n'est pas identique"
                },
                "creditCard": {
                    "regex": "none",
                    "alertText": "* Numéro de carte bancaire valide"
                },
                "phone": {
                    // credit: jquery.h5validate.js / orefalo
                    "regex": /(^(0|\+33|\(33\))[1-9](?:[ _.-]?(\d{2}))((?:[ _.-]?(\d{2})){3}|(?:[ _.-]?(\d{3})){2})$)|(^[1-9](\d{4})$)/,
                    "alertText": "* Numéro de téléphone invalide"
                },
                "email": {
                    // Shamelessly lifted from Scott Gonzalez via the Bassistance Validation plugin http://projects.scottsplayground.com/email_address_validation/
                    "regex": /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
                    "alertText": "* Adresse email invalide"
                },
                "integer": {
                    "regex": /^[\-\+]?\d+$/,
                    "alertText": "* Nombre entier invalide"
                },
                "number": {
                    // Number, including positive, negative, and floating decimal. credit: orefalo
                    "regex": /^[\-\+]?((([0-9]{1,3})([,][0-9]{3})*)|([0-9]+))?([\.]([0-9]+))?$/,
                    "alertText": "* Nombre flottant invalide"
                },
                "date": {
                    "regex": /^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/,
                    "alertText": "* Date invalide, format YYYY-MM-DD requis"
                },
                "dateFR": {
                    "func": function(field, rules, i, options){
            			var check = false;
            			var re = /^(0?[1-9]|[12][0-9]|3[01])[\/\/](0?[1-9]|1[012])[\/\/](\d{4})$/;
            			if( re.test(field.val())) {
            				var adata = re.exec(field.val());
            				var gg = parseInt(adata[1],10);
            				var mm = parseInt(adata[2],10);
            				var aaaa = parseInt(adata[3],10);
            				var xdata = new Date(aaaa,mm-1,gg);
            				if ( ( xdata.getFullYear() === aaaa ) && ( xdata.getMonth() === mm - 1 ) && ( xdata.getDate() === gg ) ){
            					check = true;
            				} else {
            					check = false;
            				}
            			} else {
            				check = false;
            			}
            			return check;
                    },
                    "alertText": "* Date invalide, format dd/mm/aaaa requis"
                },
                "ipv4": {
                	"regex": /^((([01]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))[.]){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))$/,
                    "alertText": "* Adresse IP invalide"
                },
                "url": {
                    "regex": /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
                    "alertText": "* URL invalide"
                },
                "onlyNumberSp": {
                    "regex": /^[0-9\ ]+$/,
                    "alertText": "* Seuls les chiffres sont acceptés"
                },
                "onlyLetterSp": {
                    "regex": /^[a-zA-Z\u00C0-\u00D6\u00D9-\u00F6\u00F9-\u00FD\ \']+$/,
                    "alertText": "* Seules les lettres sont acceptées"
                },
                "onlyLetterNumber": {
                    "regex": /^[0-9a-zA-Z\u00C0-\u00D6\u00D9-\u00F6\u00F9-\u00FD]+$/,
                    "alertText": "* Aucun caractère spécial n'est accepté"
                },
				// --- CUSTOM RULES -- Those are specific to the demos, they can be removed or changed to your likings
                "ajaxUserCall": {
                    "url": "ajaxValidateFieldUser",
                    "extraData": "name=eric",
                    "alertTextLoad": "* Chargement, veuillez attendre",
                    "alertText": "* Ce nom est déjà pris"
                },
                "ajaxNameCall": {
                    "url": "ajaxValidateFieldName",
                    "alertText": "* Ce nom est déjà pris",
                    "alertTextOk": "*Ce nom est disponible",
                    "alertTextLoad": "* Chargement, veuillez attendre"
                },
                "validate2fields": {
                    "alertText": "Veuillez taper le mot HELLO"
                }
            };
        }
    };
    $.validationEngineLanguage.newLang();
})(jQuery);