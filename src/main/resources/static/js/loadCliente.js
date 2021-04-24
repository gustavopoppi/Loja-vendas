/**
 * 
 */
window.addEventListener("load", onLoad);

function onLoad() {
	console.log("entrou na load do cliente")
	var app = new Vue({
		el: '#endereco',
		data: {
			estados: [],
			cidades:[]
		},
		mounted() {
			axios
				.get('https://servicodados.ibge.gov.br/api/v1/localidades/estados?orderBy=nome')
				.then(response => {
					this.estados = response.data;
					console.log(this.estados)
					console.log("saiu na load do cliente")
				})
		},
		methods:{
			getCidades : function(){
				console.log("entrou no get cidades")
				let idEstado = document.querySelector("#dropDownEstado").value;
				let indexEstado = document.querySelector("#dropDownEstado").selectedIndex;
				axios
					.get('https://servicodados.ibge.gov.br/api/v1/localidades/estados/'+idEstado+'/municipios?orderBy=nome')
					.then(response => {
						this.cidades = response.data;
						document.querySelector("#sigla").value = this.estados[indexEstado-1].sigla;
						console.log(this.cidades);
						console.log('https://servicodados.ibge.gov.br/api/v1/localidades/estados/'+idEstado+'/municipios')
					})
			}
		}
	})
	document.querySelector("#sigla").readOnly = true
}