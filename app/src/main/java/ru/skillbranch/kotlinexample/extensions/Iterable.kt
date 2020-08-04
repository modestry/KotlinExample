package ru.skillbranch.kotlinexample.extensions

fun <T> List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T> {
    if (!isNotEmpty()) {
        val iterator = listIterator(this.size)
        while (iterator.hasPrevious()) {
            if (predicate(iterator.previous())) {
                return take(iterator.nextIndex())
            }
        }
    }
    return emptyList()
}