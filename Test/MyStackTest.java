import static org.junit.Assert.*;
import org.junit.*;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class MyStackTest {

    private MyStack stack;

    @Before
    public void setUp() {
        stack = new MyStack();
    }

    // Cause-effect tests

    @Test
    public void testPushAndPop() {
        // Cause: push a value onto the stack
        stack.push("Hello");

        // Effect: stack is not empty and peek returns the pushed value
        assertFalse(stack.empty());
        assertEquals("Hello", stack.peek());

        // Cause: pop the value from the stack
        Object item = stack.pop();

        // Effect: stack is empty and the popped value is returned
        assertTrue(stack.empty());
        assertEquals("Hello", item);
    }

    @Test
    public void testPopEmpty() {
        // Cause: try to pop from an empty stack
        try {
            stack.pop();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Effect: IllegalStateException is thrown
        }
    }
    @Test
    public void testPeekEmpty() {
        // Cause: try to peek from an empty stack
        try {
            stack.peek();
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Effect: IllegalStateException is thrown
        }

        // Cause: push a value onto the stack
        stack.push("Hello");

        // Effect: peek returns the pushed value without removing it
        assertFalse(stack.empty());
        assertEquals("Hello", stack.peek());
    }
    // Equivalence partitioning tests

    @Test
    public void testEmptyStack() {
        // Partition: empty stack
        assertTrue(stack.empty());
    }

    @Test
    public void testNonEmptyStack() {
        // Partition: non-empty stack
        stack.push("Hello");
        assertFalse(stack.empty());
    }

    @Test
    public void testPushToFullStack() {
        // Partition: full stack
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        try {
            stack.push(100);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
            // Effect: IllegalStateException is thrown
        }
    }

    // Boundary value tests

    @Test
    public void testPushToEmptyStack() {
        // Boundary: push to empty stack
        stack.push("Hello");
        assertFalse(stack.empty());
        assertEquals("Hello", stack.peek());
    }

    @Test
    public void testPopFromFullStack() {
        // Boundary: pop from full stack
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertFalse(stack.empty());
        assertEquals(99, stack.pop());
    }

    @Test
    public void testPeekFromFullStack() {
        // Boundary: peek from full stack
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertFalse(stack.empty());
        assertEquals(99, stack.peek());
    }

    @Test
    public void testPrint() throws IOException {
        // Partition: non-empty stack
        stack.push("Hello");
        stack.push("World");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream stream = new PrintStream(out)) {
            System.setOut(stream);

            stack.print();

            String output = out.toString();
            assertEquals("World\nHello\n", output);
        } finally {
            System.setOut(System.out);
        }
    }
    private StandardOutputStreamLog systemOut() {
        return new StandardOutputStreamLog();
    }

}