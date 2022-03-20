# DesignPattern_Study_Visitor

# Notion Link
https://five-cosmos-fb9.notion.site/Visitor-c942fe109c6e4f1f91e4afa0c5dabcb3


# 방문자 (Visitor)

### 의도

객체 구조에 대한 알고리즘을 외부에서 정의한다.

방문자 패턴을 통해 객체 구조를 수정하지 않고 새로운 알고리즘을 추가할 수 있다.

<aside>
🎈 그리 중요한 패턴은 아니라 한다 😂

</aside>

- 일반적으로 객체에 대한 알고리즘은 객체 안에 구현되어 객체의 구조와 같이 정의될 수 있다.
- 하지만, 객체 구조와 큰 관련 없는 알고리즘을 객체 내에서 구현한다면 ?
    - 객체 지향 프로그래밍에서 **“단일 책임 원칙”**과, **“개방-폐쇄 원칙”**을 위배할 수 있다.
- 이에 대해 동일한 알고리즘군을 객체 구조 밖으로 빼내어 캡슐화 할 수 있도록 해당 패턴을 사용할 수 있다.

### 해석

- 객체에 대한 알고리즘을 객체의 구조로부터 분리하여 방문자 객체에 구현하는 패턴
- 방문자 패턴에서 객체는 방문자 객체에 **“방문”하여 객체 구조에 대한 알고리즘을 수행**
- 객체가 자신에 대한 방문자 객체의 약속된 메소드를 호출하면 원하는 구체적 비즈니스 로직을 수행하는 것이다.

- **하지만, 객체 구조를 방문자 객체에 제공해야 함으로, 객체 구조의 데이터에 대한 은닉성은 다소 약해질 수 있다.**
    - 또한, 객체 구조가 변경될 경우 방문자 객체도 따라서 수정되어야 함으로
    - 객체 구조와의 결합이 생길 수 있다.
    - 방문자 패턴은 자주 변하지 않는 객체 구조에 대한 사용을 권장한다.

### 구조

![image](https://user-images.githubusercontent.com/18654358/159145291-d294b21b-790c-43a5-9389-d0609cb4b1c8.png)

- visit
- accept

---

---

![image](https://user-images.githubusercontent.com/18654358/159145336-6c2aeaba-ada8-4d20-84ab-cd75291d88c0.png)

![image](https://user-images.githubusercontent.com/18654358/159145344-855f418f-0af5-450f-8b3c-a2c3c1c0de21.png)

Cat.java

```java
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
```

Visitor.java

```java
public interface Visitor {
    void visit(Cat cat);
}
```

NameVisitor.java

```java
public class NameVisitor implements Visitor{
    @Override
    public void visit(Cat cat) {
        System.out.println(String.format("Name is [%s]", cat.getName()));
    }
}
```

AgeVisitor.java

```java
public class AgeVisitor implements Visitor{
    @Override
    public void visit(Cat cat) {
        System.out.println(String.format("Age is [%s]", cat.getAge()));
    }
}
```

Test.java

```java
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
```

```java
냥
use implementation of visitor
Name is [titi]
use implementation of visitor
Age is [3]
use implementation of visitor
익명 내부 클래스 - 이름 : titi
use implementation of visitor
익명 내부 클래스 - 나이 : 3
use implementation of visitor
Lambda - 이름 : titi
use implementation of visitor
Lambda - 나이 : 3
```

중요하지 않다? 

- 비슷한 구조로 코드를 만들겠다면
- accept 함수, visitor 클래스를 만드는 것이 아니라
- 프로퍼티만 존재하는 Object를 만들면 된다.
    - 구조체, namedTuple, Interface 를 통해 Object를 생성할 수 있다.
    - 그 Object를 다룰 함수를 만들기만 하면 굳이 Visitor 패턴을 활용하지 않을 수 있다.
- 기존 클래스가 이미 존재한다면?
    - 고양이를 받는 클래스를 새로 정의하면 된다.
    - 고양이 클래스를 건드리지 않고 새로 만든 클래스에서 원하는 처리를 진행할 수 있다.
