package visitable.methods;

public class Print extends Statement {

    public final String message;

    public Print(String message) {
        this.message = message;
    }

    @Override
    public void print(int indent) {
        printIndent(indent, "print '" + message + "'");
    }

    @Override
    public void exec() {
        System.out.println(message);
    }

    @Override
    public int size() {
        return message.length();
    }

}
