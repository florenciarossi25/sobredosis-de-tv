package ar.edu.unsam.algo2.ar.edu.unsam.algo2.ar.edu.unsam.algo2

interface MailSender {
    fun send(mail:Mail)
}

data class Mail(val para: MutableList<String>, val asunto: String, val cuerpo: String)

interface TextSender{
    fun send(text:Text)
}

data class Text(val para:String, val texto: String)