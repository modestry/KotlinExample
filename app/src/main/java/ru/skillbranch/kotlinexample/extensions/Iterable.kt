package ru.skillbranch.kotlinexample.extensions

fun String.simplifyPhone() = replace("[^+\\d]".toRegex(), "")

fun String.validatePhone() = simplifyPhone().matches("[+]\\d{11}".toRegex())

fun String.simplifyLogin() = if (contains('@')) trim() else simplifyPhone()

fun String.nullIfEmpty() = if (isEmpty()) null else this
