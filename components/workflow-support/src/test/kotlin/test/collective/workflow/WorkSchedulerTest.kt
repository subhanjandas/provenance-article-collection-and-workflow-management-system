package test.collective.workflow

import io.collective.workflow.NoopTask
import io.collective.workflow.NoopWorkFinder
import io.collective.workflow.WorkScheduler
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class WorkSchedulerTest {
    @Test
    fun testScheduler() {
        val latch = CountDownLatch(1)
        val finder = NoopWorkFinder()
        val worker = TestableWorker(latch) // Pass the latch to TestableWorker

        val scheduler = WorkScheduler(finder, listOf(worker), delay = 1) // Reduced delay for quick testing

        scheduler.start()

        latch.await(5, TimeUnit.SECONDS) // Wait for the execute method to be called
        assertEquals(0, latch.count) // Verify that the latch was counted down

        scheduler.shutdown()
    }
}
