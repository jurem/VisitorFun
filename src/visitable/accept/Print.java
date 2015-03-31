package visitable.accept;

public class Print extends Statement {

    public final String message;

    public Print(String message) {
        this.message = message;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
