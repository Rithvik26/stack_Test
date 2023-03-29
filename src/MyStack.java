public class MyStack {

    private Object[] stack;
    private int top;

    public MyStack() {
        stack = new Object[100];
        top = -1;
    }

    public boolean empty() {
        return top == -1;
    }

    public Object peek() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

    public Object pop() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Object item = stack[top];
        stack[top] = null;
        top--;
        return item;
    }

    public void push(Object item) {
        if (top == 99) {
            throw new IllegalStateException("Stack is full");
        }
        top++;
        stack[top] = item;
    }

    public void print() {
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

}