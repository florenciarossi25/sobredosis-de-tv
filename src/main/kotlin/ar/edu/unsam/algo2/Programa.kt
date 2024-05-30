package ar.edu.unsam.algo2

import ar.edu.unsam.algo2.ar.edu.unsam.algo2.Restriccion

class Programa(
    val titulo: String,
    val conductores: MutableList<String>,
    val presupuesto: Int,
    val sponsors: MutableList<String>,
    var dia: Dia,
    val duracion: Int,
    val raitings: MutableList<Int>
){
    //val conductores: MutableList<Presentador> para tener el mail del presentador
    val restricciones = mutableListOf<Restriccion>()
    fun ultimosCincoRaitin() = raitings.subList(0, 5)
    fun promedioRaitin() = raitings.sum() / raitings.size

    fun revisar(){
        val primerRestriccion = restricciones.find { restriccion -> !restriccion.seCumple(this)  }
        primerRestriccion?.ejecutarAcciones(this)
    }

    fun conducidoPor(conductor: String) = conductores.contains(conductor)
    fun mitadPresentadores() = conductores.take(conductores.size/2).toMutableList()
}