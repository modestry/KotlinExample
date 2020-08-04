package ru.skillbranch.kotlinexample

import androidx.annotation.VisibleForTesting
import ru.skillbranch.kotlinexample.extensions.simplifyLogin
import ru.skillbranch.kotlinexample.extensions.simplifyPhone
import ru.skillbranch.kotlinexample.extensions.validatePhone

object UserHolder {

    private val map = mutableMapOf<String, User>()

    fun registerUser(fullName: String, email: String, password: String): User {
        if (map.containsKey(email.toLowerCase()))
            throw IllegalArgumentException("A user with this email already exists")

        return User.makeUser(fullName, email = email, password = password)
            .also { user -> map[user.login] = user }
    }

    fun registerUserByPhone(fullName: String, phone: String): User {
        if (!phone.validatePhone())
            throw IllegalArgumentException("Enter a valid phone number starting with a + and containing 11 digits")

        if (map.containsKey(phone.simplifyPhone()))
            throw IllegalArgumentException("A user with this phone already exists")

        return User.makeUser(fullName, phone = phone)
            .also { user -> map[user.login] = user }
    }

    fun loginUser(login: String, password: String): String? {
        return map[login.simplifyLogin()]?.run {
            if (checkPassword(password)) this.userInfo
            else null
        }
    }

    fun requestAccessCode(phone: String) {
        map[phone.simplifyPhone()]?.run {
            changeAccessCode(phone)
        }
    }

    fun importUsers(usersInfo: List<String>) =
        usersInfo.map {
            val user = User.importUser(it)
            map[user.login] = user
            user
        }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }
}
