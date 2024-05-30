package ar.edu.unsam.algo2

import ar.edu.unsam.algo2.ar.edu.unsam.algo2.Accion
import ar.edu.unsam.algo2.ar.edu.unsam.algo2.Observer
import ar.edu.unsam.algo2.ar.edu.unsam.algo2.Restriccion

class Grilla(
    val programas: MutableList<Programa>
) {
    //val restricciones = mutableMapOf<Restriccion, Accion>()
    val programasArevision = mutableSetOf<Programa>()
    val observers = mutableSetOf<Observer>()

//    fun agregarRestriccion(restriccion: Restriccion, accion: Accion) {
//        restricciones.put(restriccion, accion)
//    }
//    fun agregarARevision(programas: List<Programa>) {
//        programasArevision.addAll(programas)
//    }

    fun revisarProgramas() {
//        for (programa in programas) {
//            val restriccion = restricciones.keys.find { !it.programaCumple(programa) }
//            if (restriccion != null) {
//                restricciones[restriccion]!!.modificarPrograma(programas, programa)
//            }
//        }

        programas.forEach{ programa -> programa.revisar() }
    }

    fun crearPrograma(programa: Programa) {
        notificarObservers(programa)
        programas.add(programa)
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