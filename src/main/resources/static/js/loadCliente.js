/**
 * 
 */
window.addEventListener("load", onLoad);

//TODO GUSTAVO Limpar os comentários
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

				let dropDownEstado = document.querySelector("#dropDownEstado");
				let idEstado = 0;

				//TODO GUSTAVO Extrair para um método esse foreach, onde pegamos o id do estado a partir do nome que é passado no value do front
				let nomeEstado = dropDownEstado.value;
				this.estados.forEach(estado => {
					//Comparison nomeEstado == estado.nome may cause unexpected type coercion
					if(nomeEstado == estado.nome)
						idEstado = estado.id;
				});				
				let indexEstado = dropDownEstado.selectedIndex;
				axios
					.get('https://servicodados.ibge.gov.br/api/v1/localidades/estados/'+idEstado+'/municipios?orderBy=nome')					
					.then(response => {
						this.cidades = response.data;
						document.querySelector("#sigla").value = this.estados[indexEstado-1].sigla;						
					})
			}
		}
	})
	document.querySelector("#sigla").readOnly = true
}