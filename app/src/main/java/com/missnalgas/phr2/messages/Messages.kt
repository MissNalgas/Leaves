package com.missnalgas.phr2.messages

import com.missnalgas.phr2.api.Leaves

enum class Messages {
    NEW_LEAF,
    MESSAGE,
    ERROR;

    companion object {
        fun decode(string: String) : Messages {
            return when(string) {
                Leaves.MESSAGE_NEW_LEAF -> NEW_LEAF
                Leaves.MESSAGE_MESSAGE -> MESSAGE
                else -> ERROR
            }
        }
    }
}