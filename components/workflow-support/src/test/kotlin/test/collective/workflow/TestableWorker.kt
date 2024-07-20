package test.collective.workflow

import io.collective.workflow.Worker
import io.collective.workflow.NoopTask
import java.util.concurrent.CountDownLatch

class TestableWorker(private val latch: CountDownLatch) : Worker<NoopTask> {
    override val name: String = "testable-worker"

    override fun execute(task: NoopTask) {
        latch.countDown() // Signal that execute was called
    }
}
