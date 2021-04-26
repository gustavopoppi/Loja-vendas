/**
 * 
 */

function teste(param){
	console.log(param)
	if(document.getElementById(param).classList.value == "fas fa-chevron-down")
	{
		console.log("entrou no if")
		document.getElementById(param).classList.remove("fa-chevron-down")
		document.getElementById(param).classList.add("fa-chevron-up")	
		return
	}
	
	console.log("saiu no if")
	document.getElementById(param).classList.remove("fa-chevron-up")
	document.getElementById(param).classList.add("fa-chevron-down")
		
}