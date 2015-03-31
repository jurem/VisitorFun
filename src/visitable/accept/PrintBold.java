package visitable.accept;

public class PrintBold extends Print {

    public PrintBold(String message) {
        super(message);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
