import com.diogonunes.jcolor.Ansi
import com.diogonunes.jcolor.Attribute
import kotlin.system.exitProcess


/*
KOTLIN - HW1
------------
Написать программу, которая обрабатывает введённые пользователем в консоль команды:
exit
help
add <Имя> phone <Номер телефона>
add <Имя> email <Адрес электронной почты>
*/

val HELP_MESSAGE: String = """
        Перечень команд:
        
              exit
                - прекращение работы
                
              help
                - справка
                
              add <Имя> phone <Номер телефона>
                - сохранение записи с введенными именем и номером телефона
                 
              add <Имя> email <Адрес электронной почты>
                - сохранение записи с введенными именем и адрес электронной почты
              
    """.trimIndent()

val COMMON_ERROR_MESSAGE: String = Ansi.colorize("Ошибка! Команда введена неверно", Attribute.BRIGHT_RED_TEXT())

fun main() {

    println("Введите команду или \"help\" для вывода списка команд ")

    while (true) {
        print("> ")
        val userInput: String = readln().lowercase()
        if (userInput.startsWith("add")) {
            println(getEntry(userInput))
        } else if (userInput.startsWith("help")) {
            println(HELP_MESSAGE)
        } else if (userInput.startsWith("exit")) {
            exitProcess(0)
        } else {
            println(COMMON_ERROR_MESSAGE)
        }
    }
}

fun getEntry(txt: String): String {
    lateinit var entryName: String
    lateinit var entryPhone: String
    lateinit var entryEmail: String

    var entryData = txt.split(' ')
    if (entryData.size > 3) {
        entryData = entryData.subList(1, entryData.size)
        entryName = entryData[0]
    } else {
        return COMMON_ERROR_MESSAGE
    }

    if ("phone" in entryData && "email" !in entryData) {
        entryPhone = getPhone(entryData)

        return if (entryPhone == "null") {
            Ansi.colorize("Ошибка! Неверно указан номер телефоны", Attribute.BRIGHT_RED_TEXT())
        } else {
            Ansi.colorize("$entryName: $entryPhone", Attribute.GREEN_TEXT())
        }

    }

    if ("email" in entryData && "phone" !in entryData) {
        entryEmail = getEmail(entryData)

        return if (entryEmail == "null") {
            Ansi.colorize("Ошибка! Неверно указан адрес электронной почты", Attribute.BRIGHT_RED_TEXT())
        } else {
            Ansi.colorize("$entryName: $entryEmail", Attribute.GREEN_TEXT())
        }
    }

    return COMMON_ERROR_MESSAGE

}

fun getPhone(entryData: List<String>): String {
    val phonePattern = Regex("[+]+\\d+")
    val entryPhone = entryData[entryData.indexOf("phone") + 1]
    return if (entryPhone.matches(phonePattern) && entryData.size <= 3) {
        entryPhone
    } else {
        "null"
    }
}

fun getEmail(entryData: List<String>): String {
    val emailPattern = Regex("[a-zA-z0-9]+@[a-zA-z0-9]+[.]([a-zA-z0-9]{2,4})")
    val entryEmail = entryData[entryData.indexOf("email") + 1]
    return if (entryEmail.matches(emailPattern) && entryData.size <= 3) {
        entryEmail
    } else {
        "null"
    }
}
