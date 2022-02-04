function main() {
	let i
	let suma, calif, promedio
	console.log("Programa que calcula el promedio de 4 calificaciones")
	while(i<=4) {
		do{
			console.log("Teclea la calificacion", i)
		}while(calif<0 || calif>10)
		suma+=calif
		i +=1
	}
	promedio = suma / 4
	console.log("El promedio es:", promedio)
}