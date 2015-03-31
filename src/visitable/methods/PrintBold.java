package visitable.methods;

public class PrintBold extends Print {

    public PrintBold(String message) {
        super(message);
    }

    @Override
    public void print(int indent) {
        printIndent(indent, "printBold '" + message + "'");
    }

    @Override
    public void exec() {
        System.out.println("**" + message + "**");
    }

    @Override
    public Node compile() {
        return new Block(new Print("**"), new Block(new Print(message), new Print("**")));
    }

}