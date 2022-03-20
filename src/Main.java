public class Main {
    public static void main(String[] args) {

        Cat cat = new Cat("titi", 3);
        cat.speak();


        NameVisitor nameVisitor = new NameVisitor();
        AgeVisitor ageVisitor = new AgeVisitor();

        cat.accept(nameVisitor);
        cat.accept(ageVisitor);


        cat.accept(new Visitor() {
            @Override
            public void visit(Cat cat) {
                System.out.println(String.format("익명 내부 클래스 - 이름 : %s", cat.getName()));
            }
        });

        cat.accept(new Visitor() {
            @Override
            public void visit(Cat cat) {
                System.out.println(String.format("익명 내부 클래스 - 나이 : %s", cat.getAge()));
            }
        });


        cat.accept(cat1 -> System.out.println(String.format("Lambda - 이름 : %s", cat1.getName())));

        cat.accept(cat12 -> System.out.println(String.format("Lambda - 나이 : %s", cat12.getAge())));

    }
}
