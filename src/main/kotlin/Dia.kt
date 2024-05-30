package ar.edu.unsam.algo2

enum class Dia {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO;

    fun diaProximo(): Dia {
        val values = Dia.values()
        return values[(this.ordinal + 1) % values.size]
    }
}