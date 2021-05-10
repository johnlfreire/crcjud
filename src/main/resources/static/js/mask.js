const cpf = document.getElementById('cpf') // Seletor do campo de cpf

cpf.addEventListener('keypress', (e) => mascaraCpf(e.target.value)) // Dispara quando digitado no campo
cpf.addEventListener('change', (e) => mascaraCpf(e.target.value)) // Dispara quando autocompletado o campo

const tel = document.getElementById('telefone') // Seletor do campo de telefone
tel.addEventListener('keypress', (e) => mascaraTelefone(e.target.value)) // Dispara quando digitado no campo
tel.addEventListener('change', (e) => mascaraTelefone(e.target.value)) // Dispara quando autocompletado o campo

const mascaraCpf = (valor) => {
    valor = valor.replace(/\D/g,"")
    valor = valor.replace(/(\d{3})(\d)/,"$1.$2")
    valor = valor.replace(/(\d{3})(\d)/,"$1.$2")
    valor = valor.replace(/(\d{3})(\d{1,2})$/,"$1-$2")
    cpf.value = valor // Insere o(s) valor(es) no campo

}
const mascaraTelefone = (tel) =>  {
				tel=tel.replace(/\D/g,"")
				tel=tel.replace(/^(\d)/,"($1")
				tel=tel.replace(/(.{3})(\d)/,"$1)$2")
				if(tel.length == 9) {
					tel=tel.replace(/(.{1})$/,"-$1")
				} else if (tel.length == 10) {
					tel=tel.replace(/(.{2})$/,"-$1")
				} else if (tel.length == 11) {
					tel=tel.replace(/(.{3})$/,"-$1")
				} else if (tel.length == 12) {
					tel=tel.replace(/(.{4})$/,"-$1")
				} else if (tel.length > 12) {
					tel=tel.replace(/(.{4})$/,"-$1")
				}
   telefone.value = tel // Insere o(s) valor(es) no campo

}