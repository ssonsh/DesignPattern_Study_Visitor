public class NameVisitor implements Visitor{
    @Override
    public void visit(Cat cat) {
        System.out.println(String.format("Name is [%s]", cat.getName()));
    }
}
