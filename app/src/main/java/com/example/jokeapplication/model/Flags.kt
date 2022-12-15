package com.example.jokeapplication.model

class Flags {
    var nsfw = false
    var religious = false
    var political = false
    var racist = false
    var sexist = false
    var explicit = false
    override fun toString(): String {
        return "Flags{" +
                "nsfw=" + nsfw +
                ", religious=" + religious +
                ", political=" + political +
                ", racist=" + racist +
                ", sexist=" + sexist +
                ", explicit=" + explicit +
                '}'
    }
}