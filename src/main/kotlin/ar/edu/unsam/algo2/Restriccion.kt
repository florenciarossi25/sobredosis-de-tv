package ar.edu.unsam.algo2.ar.edu.unsam.algo2

import ar.edu.unsam.algo2.Programa

abstract class Restriccion {
    abstract fun programaCumple(programa: Programa): Boolean
}

class PromedioRating(val valor: Int) : Restriccion() {

    override fun programaCumple(programa: Programa) = promedio(programa.ultimosCincoRaitin()) > valor

    private fun promedio(ultimosCincoRaitin: MutableList<Int>) = ultimosCincoRaitin.sum() / ultimosCincoRaitin.size
}

class MaximoConductores(val valor: Int) : Restriccion() {
    override fun programaCumple(programa: Programa) = programa.conductores.size <= valor
}

class ConConductor(val conductor: String) : Restriccion() {
    override fun programaCumple(programa: Programa) = programa.conductores.contains(conductor)
}

class PresupuestoLimite(val valor: Int) : Restriccion() {
    override fun programaCumple(programa: Programa) = programa.presupuesto <= valor
}

abstract class RestriccionCompuesta(val restriccion1: Restriccion, val restriccion2: Restriccion) : Restriccion() {
}

class RestriccionCompuestaOR(restriccion1: Restriccion, restriccion2: Restriccion) :
    RestriccionCompuesta(restriccion1, restriccion2) {
    override fun programaCumple(programa: Programa): Boolean {
        return restriccion1.programaCumple(programa) || restriccion2.programaCumple(programa)
    }
}

class RestriccionCompuestaAND(restriccion1: Restriccion, restriccion2: Restriccion) :
    RestriccionCompuesta(restriccion1, restriccion2) {
    override fun programaCumple(programa: Programa): Boolean {
        return restriccion1.programaCumple(programa) && restriccion2.programaCumple(programa)
    }
}