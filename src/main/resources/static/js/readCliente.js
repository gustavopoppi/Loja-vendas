window.addEventListener("load", onLoad);

function onLoad() {
	
    //TODO GUSTAVO ver se da p inicializar o vue.js e chamar um método que fosse configurações readonly e setasse todos true;
	document.querySelector("#nomeCliente").readOnly = true;
    document.querySelector("#sigla").readOnly = true;
    document.querySelector("#estado").readOnly = true;
    document.querySelector("#cidade").readOnly = true;
    document.querySelector("#comprasAtivas").readOnly = true;
    document.querySelector("#comprasTotais").readOnly = true;
    document.querySelector("#comprasFinalizadas").readOnly = true;
}