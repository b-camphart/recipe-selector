package steps

import fixtures.TestContext
import io.cucumber.java.After
import io.cucumber.java.Before

private val _context = ThreadLocal.withInitial { TestContext() }
val context: TestContext
    get() = _context.get()

class `Setup Steps` {

    @Before
    fun setupContext() {
        _context.get()
    }

    @After
    fun destroyContext() {
        _context.remove()
    }

}