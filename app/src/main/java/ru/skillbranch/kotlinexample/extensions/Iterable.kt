package ru.skillbranch.kotlinexample.extensions

fun <T> List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T> =
    run {
        toMutableList().apply {
            listIterator(size).apply {
                while (hasPrevious()) {
                    val element = previous()
                    if (predicate(element)) {
                        remove()
                        break
                    } else {
                        remove()
                    }
                }
            }
        }.toList()
    }