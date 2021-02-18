// This function uses simple sieve of eratosthenes to
// find primes upto sqrt(high)
fun simpleSieve(limit:Int, prime:MutableList<Int>)
{
  // bound is square root of "high"
  val bound :Int = Math.sqrt(limit.toDouble()).toInt()
  val mark = MutableList<Boolean>(bound + 1) {true}

  var i = 2
  while (i * i <= bound) {
    if (mark[i]) {
      var j = i * i
      while (j <= bound)
      {
        mark[i] = false
        j += i
      }
    }
    i += 1
  }

  i = 2
  // adding primes to vector
  while (i <= bound) {
    if (mark[i]) {prime.add(i)}
    i += 1
  }
}

// Finds all prime numbers in range low to high
// using the primes we obtained from
// simple sieve
fun segmentedSieve(low:Int, high:Int)
{
  val prime = mutableListOf<Int>()
  simpleSieve(high, prime) // stores primes upto sqrt(high) in prime

  val mark = BooleanArray(high - low + 1) {true}
  var i = 0
  while (i < prime.count()) {
    // find minimum number in [low...high]
    // that is multiple of prime[i]
    var loLim = (low / prime.get(i)) * prime.get(i);
    // loLim += prime.get(i);
    if (loLim < low) {loLim += prime.get(i)}
    if (loLim == prime.get(i)) {loLim += prime.get(i)}

    var j = loLim
    while (j <= high) {
      if (j != prime.get(i)) {mark[j - low] = false}
      j += prime.get(i)
    }
    i += 1
  }

  print ("[OUTPUT] ")
  i = low
  // print all primes in [low...high]
  while (i <= high) {
    if (mark[i - low]) {print("$i ")}
    i += 1
  }
}

fun main() {
  val low: Int = 10
  val high: Int = 20
  println("[INPUT] low: $low, high: $high")
	segmentedSieve(low, high)
}
