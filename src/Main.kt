fun main() {
    val list = Catalog()
    list.createList(arrayOf())
}

class Catalog {
    private var list = Array(10) {"Позиция пуста"}
        private set(value) {
            if (value.size < 11 && value.isNotEmpty()) {
                for (i in value.indices) {
                    field[i] = value[i]
                }
            }
        }

    fun getPerson(positionInCatalog: Int): String {
        if (positionInCatalog in list.indices) {
            return list[positionInCatalog - 1]
        } else {
            println("Такой позиции нет в каталоге")
            return ""
        }
    }

    fun getPerson(name: String): String {
        if (list.contains(name)) {
            return list[list.indexOf(name)]
        } else {
            println("Такой персоны нет в каталоге")
            return ""
        }
    }

    fun createList(list: Array<String>): Array<String> {
        this.list = list
        while (true) {
            println("Выберите действие: 1 - Добавить/заменить персону, 2 - Выбрать персону, 3 - Выйти")
            val choice = readln()
            try {
                choice.toInt()
            } catch (e: Exception) {
                println("Введено неверное значение")
                continue
            }
            when (choice) {
                "1" -> println("Заполните данные персоны\nВведите имя:")
                "2" -> {
                    println("Выберите персону из каталога:")
                    this.list.forEachIndexed { index, s ->
                        println("${index + 1}: $s")
                    }
                    val input = readln()
                    try {
                        val person = getPerson(input.toInt())
                        if (person == "Позиция пуста") {
                            println("Позиция пуста")
                            continue
                        }
                        println(person.greeting())
                        continue
                    } catch (e: Exception) {
                        val person = getPerson(input)
                        if (person == "Позиция пуста") {
                            println("Позиция пуста")
                            continue
                        }
                        println(person.greeting())
                        continue
                    }
                }
                else -> return this.list
            }
            val name = readln()
            try {
                if (name.isEmpty()) CatalogException().throwEmptyNameException()
            } catch (e: CatalogException) {
                println(e.message)
                continue
            }
            println("Введите фамилию:")
            val surname = readln()
            try {
                if (surname.isEmpty()) CatalogException().throwEmptyNameException()
            } catch (e: CatalogException) {
                println(e.message)
                continue
            }
            println("Введите позицию в каталоге (необязательно):")
            val position = readln()
            try {
                if (position.toInt() !in this.list.indices)
                    CatalogException().throwOutOfCatalogBoundException()
            } catch (e: Exception) {
                println(e.message)
                continue
            }
            this.list[position.toInt() - 1] = "$name $surname"
        }
    }
}

fun String.greeting() = "Привет! Меня зовут $this."

class CatalogException : Exception() {
    fun throwEmptyNameException() {
        throw Exception("Поле ввода имени/фамилии пустое!")
    }

    fun throwOutOfCatalogBoundException() {
        throw Exception("Такой позиции в каталоге не существует!")
    }
}