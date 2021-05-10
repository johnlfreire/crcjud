function limparComboCidade() {
	document.getElementById("comboCidade").options.length = 0;
	option = new Option("Selecione a cidade", 0);
	comboCidade.options[comboCidade.options.length] = option;
	document.getElementById("comboCidade").disabled = false;
	if(document.getElementById("comboVara")){
	document.getElementById("comboVara").disabled = true;
	limparComboVara();
	}
	
}
function limparComboVara() {
	document.getElementById("comboVara").options.length = 0;
	option = new Option("Selecione a Vara", 0);
	comboVara.options[comboVara.options.length] = option;
}

function getComboCidade(cidade) {
	var data = cidade;
	console.log(data);

	fetch('http://localhost:8080/cidades?id=' + data.value)
		.then(response => response.json())
		.then(json => {
			valuedd = json
			console.log(valuedd);	
			limparComboCidade();		
			valuedd.forEach((language) => {
				option = new Option(language.nome_municipio, language.id);
				console.log(option);
				comboCidade.options[comboCidade.options.length] = option;
				
			});

		}
		)
}
function getVara(cidade){
	var data = cidade;
	console.log(data);
		fetch('http://localhost:8080/varas/bycidade?id=' + data.value)
		.then(response => response.json())
		.then(json => {
			valuedd = json
			console.log(valuedd);
			limparComboVara();
			if(document.getElementById("comboVara")){
			document.getElementById("comboVara").disabled = false;
			}
			valuedd.forEach((language) => {
				option = new Option(language.nome, language.id);
				console.log(option);
				comboVara.options[comboVara.options.length] = option;				
			});		

		}
		)
}
