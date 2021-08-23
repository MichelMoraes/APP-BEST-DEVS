function habilitaTabView(widgetVar,numGuia,enabled){
	
	if (enabled) {
		PF(widgetVar).enable(numGuia);
	} else {
		PF(widgetVar).disable(numGuia);
	}
	
}

function soMaiusculas(campo) {
	var str = campo.value;
	campo.value = str.toUpperCase();
}

function soMinusculas(campo) {
	var str = campo.value;
	campo.value = str.toUpperCase();
}

function soNumeros(e){  
    var tecla=(window.event)?event.keyCode:e.which;  
    if((tecla > 47 && tecla < 58)) return true;  
    else{  
        if ((tecla != 0)&&(tecla !=8)) return false;  
        else return true;   
    }    
} 

//Alterar o foco ao teclar Enter.
function setaFoco(campo)  {  
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;  
    if (keyCode == 13) {  
        var i;  
        for (i = 0; i < campo.form.elements.length; i++)  
            if (campo == campo.form.elements[i])  
                break;  
        i = (i + 1) % campo.form.elements.length;  
        campo.form.elements[i].focus();  
        event.preventDefault();  
        return false;  
    }  
    return false;  
}

//Alterar o foco ao teclar Enter.
function setaFocoNumber(campo)  {  
    var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;  
    if (keyCode == 13) {  
        var i;  
        for (i = 1; i < campo.form.elements.length; i++)  
            if (campo == campo.form.elements[i])  
                break;  
        i = (i + 2) % campo.form.elements.length;  
        campo.form.elements[i].focus();  
        event.preventDefault();  
        return false;  
    }  
    return false;  
}

//Ajustar a máscara de telefone com 9 Dígitos. 
function mascaraTelefone(campo) {	
	if(campo.value.length > 13) {  
	    $(campo).mask("(99)99999-999?9");  
	} else {  
	    $(campo).mask("(99)9999-9999?9");  
	}              	
}

function validarCNPJ(cnpj) {
	 
    cnpj = cnpj.replace(/[^\d]+/g,'');
 
    if(cnpj == '') return false;
     
    if (cnpj.length != 14)
        return false;
 
    // Elimina CNPJs invalidos conhecidos
    if (cnpj == "00000000000000" || 
        cnpj == "11111111111111" || 
        cnpj == "22222222222222" || 
        cnpj == "33333333333333" || 
        cnpj == "44444444444444" || 
        cnpj == "55555555555555" || 
        cnpj == "66666666666666" || 
        cnpj == "77777777777777" || 
        cnpj == "88888888888888" || 
        cnpj == "99999999999999")
        return false;
         
    // Valida DVs
    tamanho = cnpj.length - 2
    numeros = cnpj.substring(0,tamanho);
    digitos = cnpj.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0))
        return false;
         
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0,tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1))
          return false;
           
    return true;
    
}

function validarData(campo)
{

if ((campo.value==null) || (campo.value=="__/__/____") || (campo.value=="")) {
	return true;
}
else
	{
    var data = campo.value;
    var dia = data.substring(0,2)
    var mes = data.substring(3,5)
    var ano = data.substring(6,10)
 
    //Criando um objeto Date usando os valores ano, mes e dia.
    var novaData = new Date(ano,(mes-1),dia);
 
    var mesmoDia = parseInt(dia,10) == parseInt(novaData.getDate());
    var mesmoMes = parseInt(mes,10) == parseInt(novaData.getMonth())+1;
    var mesmoAno = parseInt(ano) == parseInt(novaData.getFullYear());
 
    if (!((mesmoDia) && (mesmoMes) && (mesmoAno)))
    {
        alert('Data inválida!');   
        campo.focus();    
        return false;
    }  
    return true;
}    
}

function valores(e){  
    var tecla=(window.event)?event.keyCode:e.which;  
    if((tecla > 47 && tecla < 58) || (tecla==44)) return true;  
    else{  
        if ((tecla != 0)&&(tecla !=8)) return false;  
        else return true;   
    }    
       
    
}


function removerAcentos( newStringComAcento ) {
	  var string = newStringComAcento;
		var mapaAcentosHex 	= {
			a : /[\xE0-\xE6]/g,
			e : /[\xE8-\xEB]/g,
			i : /[\xEC-\xEF]/g,
			o : /[\xF2-\xF6]/g,
			u : /[\xF9-\xFC]/g,
			c : /\xE7/g,
			n : /\xF1/g
		};

		for ( var letra in mapaAcentosHex ) {
			var expressaoRegular = mapaAcentosHex[letra];
			string = string.replace( expressaoRegular, letra );
		}

		return string;
	}

