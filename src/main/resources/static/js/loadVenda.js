window.addEventListener("load", onLoad);

function onLoad() {
	console.log("entrou na load")
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
					document.getElementById("id").readOnly = true;							
					console.log(this.clientes);
				})
		}
	})
	document.getElementById("id").value = 0; //inicializar o cód do front com 0
}

function obterCodCliente() {
	let valueCliente = document.getElementById("dropDownCliente").value;
	document.getElementById("id").value = valueCliente
}