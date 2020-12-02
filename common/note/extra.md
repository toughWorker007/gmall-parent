1.动态绑定:父类的引用指向子类的实例时父类的声明调属性时调的是父类中的属性，调方法是调的子类方法。子类重写方法里的this.name指向的是子类实例的属性。因创建子类实例时，执行之类构造方法时默认invoke super.constructor,the first row is this.() or super.(),the 2nd row is field init;

2.public interface Collection<E> extends Iterable<E>，map没有继承这个接口。

3.accessors;