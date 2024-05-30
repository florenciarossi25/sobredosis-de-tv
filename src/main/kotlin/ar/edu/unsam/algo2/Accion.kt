package ar.edu.unsam.algo2.ar.edu.unsam.algo2

import ar.edu.unsam.algo2.Dia
import ar.edu.unsam.algo2.Programa

abstract class Accion {

    fun modificarPrograma(grilla: MutableList<Programa>, programa: Programa) {
        grilla.remove(programa)
        grilla.addAll(programaModificado(grilla, programa))
    }

    abstract fun programaModificado(grilla: MutableList<Programa>, programa: Programa): List<Programa>
}

class DivisorDePrograma() : Accion() {
    override fun programaModificado(grilla: MutableList<Programa>, programa: Programa): List<Programa> {
        val programa1 = Programa(
            titulo = "${titulo(programa, 0)} en el aire!",
            conductores = conductores(programa, 0, (programa.conductores.size + 1) / 2),
            presupuesto = programa.presupuesto / 2,
            sponsors = programa.sponsors,
            dia = programa.dia,
            duracion = programa.duracion / 2,
            raitings = programa.raitings
        )
        val programa2 = Programa(
            titulo = titulo(programa, 1),
            conductores = conductores(programa, (programa.conductores.size + 1) / 2, programa.conductores.size),
            presupuesto = programa.presupuesto / 2,
            sponsors = programa.sponsors,
            dia = programa.dia,
            duracion = programa.duracion / 2,
            raitings = programa.raitings
        )
        return mutableListOf(programa1, programa2)
    }

    private fun conductores(programa: Programa, inicio: Int, fin: Int): MutableList<String> =
        programa.conductores.subList(inicio, fin)

    private fun titulo(programa: Programa, idx: Int): String = programa.titulo.split(" ")[idx]
}

class Reemplazador() : Accion() {
    override fun programaModificado(grilla: MutableList<Programa>, programa: Programa): List<Programa> {
        val programaDefault = Programa(
            titulo = "Los Simpsons",
            conductores = mutableListOf("Juan Perez"),
            presupuesto = 250000,
            sponsors = mutableListOf("Speed"),
            dia = Dia.DOMINGO,
            duracion = 20,
            raitings = mutableListOf(15, 4, 15, 10, 9)
        )
        return mutableListOf(programaDefault)
    }
}

class Fusionador() : Accion() {
    val titulos = listOf("Impacto total", "Un buen día")
    override fun programaModificado(grilla: MutableList<Programa>, programa: Programa): List<Programa> {
        val progProximo = programaProximo(grilla, programa)
        val programaDefault = Programa(
            titulo = titulos.random(),
            conductores = mutableListOf(programa.conductores[0], progProximo.conductores[0]),
            presupuesto = minOf(programa.presupuesto, progProximo.presupuesto),
            sponsors = listOf(programa.sponsors, programa.sponsors).random(),
            dia = programa.dia,
            duracion = programa.duracion + progProximo.duracion,
            raitings = mutableListOf(15, 4, 15, 10, 9)
        )
        return mutableListOf(programaDefault)
    }

    private fun programaProximo(grilla: MutableList<Programa>, programa: Programa): Programa {
        return if (grilla.indexOf(programa) == grilla.size - 1) {
            grilla[0]
        } else {
            grilla[grilla.indexOf((programa)) + 1]
        }
    }
}

class Reprogramador() : Accion() {
    override fun programaModificado(grilla: MutableList<Programa>, programa: Programa): List<Programa> {
        programa.dia = programa.dia.diaProximo()
        return mutableListOf(programa)
    }
}