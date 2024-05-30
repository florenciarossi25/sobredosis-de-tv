package ar.edu.unsam.algo2

import ar.edu.unsam.algo2.ar.edu.unsam.algo2.Accion
import ar.edu.unsam.algo2.ar.edu.unsam.algo2.Observer
import ar.edu.unsam.algo2.ar.edu.unsam.algo2.Restriccion

class Encargado(
    val grilla: MutableList<Programa>
) {
    val restricciones = mutableMapOf<Restriccion, Accion>()
    val programasArevision = mutableSetOf<Programa>()
    val observers = mutableSetOf<Observer>()

    fun agregarRestriccion(restriccion: Restriccion, accion: Accion) {
        restricciones.put(restriccion, accion)
    }

    fun agregarARevision(programas: List<Programa>) {
        programasArevision.addAll(programas)
    }

    fun revisarProgramas() {
        for (programa in grilla) {
            val restriccion = restricciones.keys.find { !it.programaCumple(programa) }
            if (restriccion != null) {
                restricciones[restriccion]!!.modificarPrograma(grilla, programa)
            }
        }
    }

    fun crearPrograma(programa: Programa) {
        notificarObservers(programa)
        grilla.add(programa)
    }

    fun agregarObserver(observer: Observer) {
        observers.add(observer)
    }

    fun eliminarObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notificarObservers(programa: Programa) {
        for (observer in observers) {
            observer.notify(this, programa)
        }
    }

}