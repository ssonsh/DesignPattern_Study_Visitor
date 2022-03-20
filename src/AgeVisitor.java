public class AgeVisitor implements Visitor{
    @Override
    public void visit(Cat cat) {
        System.out.println(String.format("Age is [%s]", cat.getAge()));
    }
}
