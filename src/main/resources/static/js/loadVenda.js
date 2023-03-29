window.addEventListener("load", onLoad);

const idCliente = "idCliente";

function onLoad() {
	console.log("entrou na load do arquivo loadVenda")
	var app = new Vue({
		el: '#listaClientes',
		data: {
			clientes: [],
			cliente: '',
			row: 'row'
		},
		mounted() {
			axios
				.get('/api/clientes')
				.then(response => {
					this.clientes = response.data;
					document.getElementById("idCliente").readOnly = true;
				})
		}
	})
	document.getElementById(idCliente).value = 0; //inicializar o cód do front com 0
	console.log("saiu na load do arquivo loadVenda")
}

function obterCodCliente() {
	let valueCliente = document.getElementById("dropDownCliente").value;
	document.getElementById(idCliente).value = valueCliente
}