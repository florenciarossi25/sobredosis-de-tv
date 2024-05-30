package ar.edu.unsam.algo2.ar.edu.unsam.algo2

import ar.edu.unsam.algo2.Grilla
import ar.edu.unsam.algo2.Programa
import ar.edu.unsam.algo2.ar.edu.unsam.algo2.ar.edu.unsam.algo2.Mail
import ar.edu.unsam.algo2.ar.edu.unsam.algo2.ar.edu.unsam.algo2.MailSender
import ar.edu.unsam.algo2.ar.edu.unsam.algo2.ar.edu.unsam.algo2.Text
import ar.edu.unsam.algo2.ar.edu.unsam.algo2.ar.edu.unsam.algo2.TextSender

abstract class Observer {
    abstract fun notify(encargado: Grilla, programa: Programa)
}

class NotificarConductores(val mailSender: MailSender) : Observer() {
    override fun notify(encargado: Grilla, programa: Programa) {
        mailSender.send(
            Mail(
                para = programa.conductores,
                asunto = "Oportunidad!!",
                cuerpo = "Fuiste seleccionado para conducir ${programa.titulo}!\nPonete en contacto con la gerencia. "
            )
        )
    }

}

class NotificarAgencia(val textSender: TextSender) : Observer() {
    override fun notify(encargado: Grilla, programa: Programa) {
        if (programa.presupuesto > 100000) {
            textSender.send(
                Text(
                    para = "Cliowin",
                    texto = "${programa.presupuesto} -- ${programa.titulo} - CONSEGUIR SPONSOR UTGENTE!"
                )
            )
        }
    }

}

class LimpiarRevision() : Observer() {
    override fun notify(encargado: Grilla, programa: Programa) {
        encargado.programasArevision.removeAll { !encargado.programas.contains(it) }
    }

}
