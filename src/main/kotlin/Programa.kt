package ar.edu.unsam.algo2

class Programa(
    val titulo: String,
    val conductores: MutableList<String>,
    val presupuesto: Int,
    val sponsors: MutableList<String>,
    var dia: Dia,
    val duracion: Int,
    val raitings: MutableList<Int>
){
    fun ultimosCincoRaitin() = raitings.subList(0, 5)
}