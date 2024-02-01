# Блок Kotlin и Окружение (семинары)
## Урок 1. Практика по основам Kotlin
Написать программу, которая обрабатывает введённые пользователем в консоль команды:
exit
help
add <Имя> phone <Номер телефона>
add <Имя> email <Адрес электронной почты>

После выполнения команды, кроме команды exit, программа ждёт следующую команду.

Имя – любое слово.
Если введена команда с номером телефона, нужно проверить, что указанный телефон может начинаться с +, затем идут только цифры. При соответствии введённого номера этому условию – выводим его на экран вместе с именем, используя строковый шаблон. В противном случае - выводим сообщение об ошибке.
Для команды с электронной почтой делаем то же самое, но с другим шаблоном – для простоты, адрес должен содержать три последовательности букв, разделённых символами @ и точкой.

Пример команд:
add Tom email tom@example.com
add Tom phone +7876743558