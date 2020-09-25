package com.missnalgas.phr2.messages

enum class Messages {
    NEW_LEAF,
    MESSAGE,
    ERROR;

    companion object {
        fun decode(string: String) : Messages {
            return when(string) {
                "com.missnalgas.phr2.message.new_leaf" -> NEW_LEAF
                "com.missnalgas.phr2.message.message" -> MESSAGE
                else -> ERROR
            }
        }
    }
}