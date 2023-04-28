/**
 * 
 */

window.addEventListener("load", onLoad);

function onLoad() {
	console.log("entrou na load")
	var mesAtual = new Date().getMonth() + 1;
	console.log("int do mes selecionado " + mesAtual)
	var app = new Vue({
		el: '#homeDto',
		data: {
			vendasDto: [],
			row: 'row'
		},
		mounted() {
			axios
				.get('/api/home' + '?mes=' + mesAtual)
				.then(response => {
					this.vendasDto = response.data;
					console.log(this.vendasDto);
				})
		},
		methods: {
			getMes: function() {
				var novoValorMes = document.querySelector("#listaMeses").value
				console.log(novoValorMes)
				axios
					.get('/api/home' + '?mes=' + novoValorMes)
					.then(response => {
						this.vendasDto = response.data;
						console.log(this.vendasDto);
						console.log("entrou no teste")
					})
			},

			classObject: function(status) {
				var expr = status
				console.log("entrou na class object")
				//console.log(status)
				switch (expr) {
					case 'AGUARDANDO':
						return 'alert-danger ' + 'text-dark'
						break;
					case 'PARCIAL':
						return 'alert-warning ' + 'text-dark'
						break;
					case 'PAGA':
						return 'alert-success ' + 'text-dark'
						break;
					default:
						return 'alert-danger'
				}
			},
			getDadosModal: function(dados) {
				console.log("entrou na get dados modal")
				console.log(dados)
				document.getElementById("idParcela").value = dados.id
				document.getElementById("idVenda").value = dados.venda.id
				document.getElementById("nomeProduto").value = dados.venda.nomeProduto
				document.getElementById("numeroDaParcela").value = dados.numeroDaParcela
				document.getElementById("dataParcela").value = dados.dataParcela
				document.getElementById("valorParcela").value = dados.valorParcela

				document.getElementById("idParcela").readOnly = true
				document.getElementById("idVenda").readOnly = true
				document.getElementById("nomeProduto").readOnly = true
				document.getElementById("numeroDaParcela").readOnly = true
				document.getElementById("dataParcela").readOnly = true
				document.getElementById("valorParcela").readOnly = true
			}
		}
	})
	document.getElementById("listaMeses").value = mesAtual
}

function collapse(param) {
	console.log(param)
	let iconSeta = document.getElementById(param).classList;
	if (iconSeta.contains("fa-chevron-down")) {
		console.log("entrou no if")
		iconSeta.remove("fa-chevron-down")
		iconSeta.add("fa-chevron-up")
		return
	}

	console.log("saiu no if")
	iconSeta.remove("fa-chevron-up")
	iconSeta.add("fa-chevron-down")
}

function alertIfInvalidValuePaid() {
	var valorPago = document.getElementById("valorPago");
	if (valorPago != null && valorPago.value <= 0){
		alert("Valor pago inválido");
	}
}

