//CHECKSTYLE.OFF: LineLength - JavaDoc headers with hard links are too long by default
package nl.delftelectronics.spaceinvaders.core;

import junit.framework.Assert;
import junit.framework.TestCase;
import nl.delftelectronics.spaceinvaders.core.Logger.LogLevel;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Max
 *
 */
public class LoggerTest extends TestCase {

	/**
	 * Resets the global Logger to sane defaults before a test case
	 * @throws Exception  Super might throw exception
	 */
	protected void setUp() throws Exception {
		super.setUp();
		Logger.setLogLevel(Logger.LogLevel.Default);
		Logger.setOutputStream(System.out);
	}

	/**
	 * Resets the global Logger to sane defaults after a test case
	 * @throws Exception  Super might throw exception
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		Logger.setLogLevel(Logger.LogLevel.Default);
		Logger.setOutputStream(System.out);
	}

	/**
	 * Test method for
	 * {@link nl.delftelectronics.spaceinvaders.core.Logger#log(nl.delftelectronics.spaceinvaders.core.Logger.LogLevel, java.lang.String, java.lang.Object[])}
	 * .
	 */
	public void testLog() {
		OutputTest t = new OutputTest();
		Logger.setOutputStream(new PrintStream(t));

		String message = "Hello World! Test case testLog()";

		Logger.log(Logger.LogLevel.Error, message);
		Assert.assertTrue("Log output should contain message",
				t.buffer.contains(message));

		t.buffer = "";

		Logger.log(LogLevel.Debug, message);
		Assert.assertEquals("Logger should not log messages with a LogLevel that's too low",
				"", t.buffer);

		Logger.log(Logger.LogLevel.Warning, "Some subtext %s and some more", message);
		Assert.assertTrue("Log output should contain used parameters",
				t.buffer.contains(message));

		t.buffer = "";
		Logger.setLogLevel(Logger.LogLevel.None);
		Logger.log(LogLevel.Error, message);
		Assert.assertEquals("Logger should not log messages with a LogLevel that's too low",
				"", t.buffer);
	}

	/**
	 * Test method for
	 * {@link nl.delftelectronics.spaceinvaders.core.Logger#setOutputStream(java.io.PrintStream)}
	 * .
	 */
	public void testSetOutputStream() {
		PrintStream output = mock(PrintStream.class);
		Logger.setOutputStream(output);

		Logger.log(Logger.LogLevel.Error, "Test");
		verify(output).println(any(String.class));
	}

	/**
	 * Test method for
	 * {@link nl.delftelectronics.spaceinvaders.core.Logger#setLogLevel(nl.delftelectronics.spaceinvaders.core.Logger.LogLevel)}
	 * .
	 */
	public void testSetLogLevel() {
		Logger.setLogLevel(Logger.LogLevel.None);
		Assert.assertEquals(Logger.LogLevel.None, Logger.getLogLevel());
	}

	/**
	 * Tests whether the arguments are null checked
	 */
	public void testNullChecks() {
		//CHECKSTYLE.OFF: EmptyBlock - Exceptions are desired here.
		try {
			Logger.setLogLevel(null);
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			Logger.log(null, "hi");
			fail();
		} catch (IllegalArgumentException e) {
		}

		try {
			Logger.log(Logger.LogLevel.Default, null);
			fail();
		} catch (IllegalArgumentException e) {
		}
		//CHECKSTYLE.ON: EmptyBlock
	}

	/**
	 * Used to test the written output of the logger
	 * @author Max
	 *
	 */
	private static class OutputTest extends OutputStream {
		public String buffer = "";

		@Override
		public void write(int b) throws IOException {
			buffer += (char) b;
		}
	}
}
