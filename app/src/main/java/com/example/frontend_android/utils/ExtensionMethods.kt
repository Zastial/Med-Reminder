package com.example.frontend_android.utils



// Prend un entier et le retourne un string dans le format "XX"
//  2   ->   "02"
fun Int.FormatToStringHour() : String {
    if ( this < 0 || this > 24 )
        throw IllegalArgumentException("L'heure n'est pas valide")

    if (this < 10)
        return "0$this"

    return this.toString()
}


// Prend un entier et le retourne un string dans le format "XX"
//  2   ->   "02"
fun Int.FormatToStringMinutes() : String {
    if (this < 0 || this > 59)
        throw  IllegalArgumentException("Les minutes ne sont pas valides")

    if (this < 10)
        return "0$this"

    return this.toString()
}
