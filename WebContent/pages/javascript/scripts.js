function getSelectedRadio(src, dest) {
	var newValue = document.getElementById(dest).Value;
	newValue = src.Value;
}

function getSelectedCombo(form, src, dst) {
	var i = document.getElementById(form + ':' + src).selectedIndex;
	var source = document.getElementById(form + ':' + src).options[i].value;
	var destiny = document.getElementById(form + ':' + dst).value;
	destiny = source;
}

function showElements(divId) {
	var elements = document.getElementsByTagName('div');
	var idDiv = divId.split('_');
	var idDivTam = idDiv[0].length;
	var divs = new Array(); var cont = 0;
	for (var i=0; i<elements.length; i++) {
		if (elements[i].id.length >= idDivTam) {
			var idElem = elements[i].id.split('_');
			if (idElem.length >= 2 && idElem[0] == idDiv[0]) {
				divs[cont] = elements[i].id; cont++;
			}
		}
	}
	for (var j=0; j<divs.length; j++) {
		var divAtual = document.getElementById(divs[j]);
		if (divs[j] == divId) {
			divAtual.style.display = 'block';
		} else {
			divAtual.style.display = 'none';
		}
	}
}

function updateHiddenValue(value, dest) {
	var hidden = document.getElementById(dest);
	hidden.value = value;
}

function popularListaAlimentos(alimento) {
}

function checkSession() {
	var autenticado = document.getElementById('authForm:authUser');
	if (autenticado != null && autenticado.value == null) {
		window.location.href='/NutriBem/pages/index.jsf';
	}
}