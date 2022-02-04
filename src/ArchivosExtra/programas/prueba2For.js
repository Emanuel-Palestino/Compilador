// el main se puede omitir, pero en este caso lo pondré como una funcion
function main() {
	let pot, suma
	let i, j, n
	console.log("Programa que calcula la sumatoria de la serie 1+1/2+1/4+...")
	console.log("Teclea el limite superior de n")
	pot=1
	for (i = 0; i <= n; i++) {
		pot += 1
		for(j = 0; j< i; j++) {
			pot *= 2
		}
		suma += suma
	}
	console.log("la sumatoria es:", suma)
}