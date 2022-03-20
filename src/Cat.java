public class Cat {

    private String name;
    private Integer age;

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void speak(){
        System.out.println("냥");
    }

    public void accept(Visitor visitor){
        System.out.println("use implementation of visitor");
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
