package dev.vengateshm.kotlin_practice.mockk

class CounterUseCase(private val repository: CounterRepository) {

  fun getCount(): Int {
    return repository.getCount()
  }

  fun increment() {
    repository.increment()
  }

  fun decrement() {
    repository.decrement()
  }

  suspend fun reset() {
    repository.reset()
  }

  fun computeWithHelper(): Int {
    val value = repository.getCount()
    val helper = CounterHelper()
    return helper.multiply(value)
  }

  fun complexComputationHelper(): Int {
    val helper = CounterHelper()
    val current = repository.getCount()
    val sub = helper.subtract(current)
    val added = helper.add(sub)
    return added
  }

  suspend fun conditionalReset() {
    if (AppConfig.shouldAutoReset) {
      repository.reset()
    }
  }

  suspend fun persist(count: Int) {
    repository.saveCountInDb(count)
  }

  suspend fun getCountFromNetworkAndPersist(onResult: (Int) -> Unit) {
    repository.loadFromNetwork { count -> onResult(count) }
  }
}