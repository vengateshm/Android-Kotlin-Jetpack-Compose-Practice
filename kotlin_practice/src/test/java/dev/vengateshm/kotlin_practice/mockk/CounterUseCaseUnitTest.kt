package dev.vengateshm.kotlin_practice.mockk

import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.slot
import io.mockk.spyk
import io.mockk.unmockkConstructor
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class CounterUseCaseUnitTest {
  @Test
  fun `computeWithHelper should return multiplied value using constructor mocked helper`() {
    val repository = mockk<CounterRepository>()
    val useCase = CounterUseCase(repository)

    mockkConstructor(CounterHelper::class)

    val count = 4
    every { repository.getCount() } returns count

    every { anyConstructed<CounterHelper>().multiply(count) } returns 40

    val result = useCase.computeWithHelper()

    assertEquals(40, result)

    verify(exactly = 1) {
      repository.getCount()
      anyConstructed<CounterHelper>().multiply(count)
    }

    confirmVerified(repository)

    unmockkConstructor(CounterHelper::class)
    clearMocks(repository)
  }

  @Test
  fun `getCountFromNetworkAndPersist should forward result from repository`() = runTest {
    val repository = mockk<CounterRepository>()
    val useCase = CounterUseCase(repository)

    val callbackSlot = slot<suspend (Int) -> Unit>()

    coEvery {
      repository.loadFromNetwork(capture(callbackSlot))
    } coAnswers {
      callbackSlot.captured.invoke(200)
    }

    var result: Int = -1

    useCase.getCountFromNetworkAndPersist {
      result = it
    }

    assertEquals(200, result)

    coVerify(exactly = 1) {
      repository.loadFromNetwork(any())
    }

    confirmVerified(repository)
    clearMocks(repository)
  }

  @Test
  fun `getCountFromNetworkAndPersist should forward result from repository 1`() = runTest {
    val repository = mockk<CounterRepository>()
    val useCase = CounterUseCase(repository)

    coEvery {
      repository.loadFromNetwork(any())
    } coAnswers {
      val callback = arg<suspend (Int) -> Unit>(0)
      callback(200)
    }

    var result: Int = -1

    useCase.getCountFromNetworkAndPersist {
      result = it
    }

    assertEquals(200, result)

    coVerify(exactly = 1) {
      repository.loadFromNetwork(any())
    }

    confirmVerified(repository)
    clearMocks(repository)
  }

  @Test
  fun `complexComputationHelper should use helper operations correctly`() {
    val repository = mockk<CounterRepository>()
    val useCase = CounterUseCase(repository)

    mockkConstructor(CounterHelper::class)
    val spy = spyk<CounterHelper>()

    every { repository.getCount() } returns 10

    every { anyConstructed<CounterHelper>().subtract(any()) } answers {
      spy.subtract(firstArg())
    }
    every { anyConstructed<CounterHelper>().add(any()) } answers {
      spy.add(firstArg())
    }

    val result = useCase.complexComputationHelper()

    verify { spy.subtract(10) }
    verify { spy.add(0) }

    assertEquals(10, result)

    unmockkConstructor(CounterHelper::class)
    clearMocks(repository)
  }
}