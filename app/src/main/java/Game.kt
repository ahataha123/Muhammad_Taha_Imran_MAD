
/* The main function uses a do-while loop to repeatedly ask the user for their guess and check
it against the secret number until the user correctly guesses the number.
The loop also checks that the user's guess is a valid 4-digit number.*/
fun main() {
    val randomNumber = generateRandomNumber()
    var guess: String?
    var guessCount = 0

    do {
        guessCount++
        guess = readLine()?.trim()
        if (guess == null || guess.length != 4) {
            println("Invalid guess. Please enter a 4-digit number.")
            continue
        }
        val (n, m) = checkGuess(randomNumber, guess)
        println("$n:$m")
    } while (guess != randomNumber)

    println("Congratulations! You guessed the number in $guessCount tries.")
}
/* The generateRandomNumber function generates a random 4-digit number without repeating digits
by shuffling a list of digits 0-9 and taking the first 4 elements.*/
fun generateRandomNumber(): String {
    val digits = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
    digits.shuffle()
    return digits.take(4).joinToString("")
}
/* The checkGuess function takes the secret number and user's guess, and returns a pair of the
number of digits guessed correctly regardless of their position (n) and the number of digits
guessed correctly at their correct position (m). */
fun checkGuess(secretNumber: String, guess: String): Pair<Int, Int> {
    var n = 0
    var m = 0
    val usedDigits = mutableSetOf<Int>()
    for (i in 0..3) {
        val secretDigit = secretNumber[i].code - '0'.code
        val guessDigit = guess[i].code - '0'.code
        if (secretDigit == guessDigit) {
            m++
            usedDigits.add(secretDigit)
        }
    }
    for (i in 0..3) {
        val secretDigit = secretNumber[i].code - '0'.code
        if (guess.contains(secretDigit.toString()) && secretDigit !in usedDigits) {
            n++
            usedDigits.add(secretDigit)
        }
    }
    return Pair(n, m)
}