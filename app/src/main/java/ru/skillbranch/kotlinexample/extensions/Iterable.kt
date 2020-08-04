package ru.skillbranch.kotlinexample.extensions

fun <T> List<T>.dropLastUntil(predicate: (T) -> Boolean): List<T> {
    var list = this.toMutableList()
    var i = this.size
    while(--i >= 0)    {
        if(predicate(list[i])) {
            list.remove(list[i])
            break
        }
        list.remove(list[i])
    }
    return list
}