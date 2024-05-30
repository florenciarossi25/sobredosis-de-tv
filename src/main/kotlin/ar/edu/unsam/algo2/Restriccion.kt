package ar.edu.unsam.algo2.ar.edu.unsam.algo2

import ar.edu.unsam.algo2.Programa

abstract class Restriccion {
    val acciones = mutableListOf<Accion>()
    abstract fun seCumple(programa: Programa): Boolean
    fun ejecutarAcciones(programa: Programa) {
        acciones.forEach{accion  -> accion.ejecurar(programa) }
    }

}

class PromedioRating(val valor: Int) : Restriccion() {
    override fun seCumple(programa: Programa) = programa.promedioRaitin() > valor

}

class MaximoConductores(val valor: Int) : Restriccion() {
    override fun seCumple(programa: Programa) = programa.conductores.size <= valor
}

class ConConductor(val conductor: String) : Restriccion() {
    override fun seCumple(programa: Programa) = programa.conducidoPor(conductor)
}

class PresupuestoLimite(val valor: Int) : Restriccion() {
    override fun seCumple(programa: Programa) = programa.presupuesto <= valor
}

class RestriccionCompuestaOR(val restricciones:List<Restriccion>) :
    Restriccion() {
    override fun seCumple(programa: Programa): Boolean {
        return restricciones.any{ it.seCumple(programa)}
    }
}

class RestriccionCompuestaAND(val restricciones:List<Restriccion>) :
    Restriccion() {
    override fun seCumple(programa: Programa): Boolean {
        return restricciones.all{ it.seCumple(programa)}
    }
}