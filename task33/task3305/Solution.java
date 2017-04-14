//        Расставь JSON аннотации так, чтобы результат выполнения метода main был следующим:
//        {
//        "className" : ".Parking",
//        "name" : "Super Parking",
//        "city" : "Kyiv",
//        "autos" : [ {
//        "className" : "com.javarush.task.task33.task3305.RaceBike",
//        "name" : "Simba",
//        "owner" : "Peter",
//        "age" : 2
//        }, {
//        "className" : "com.javarush.task.task33.task3305.Motorbike",
//        "name" : "Manny",
//        "owner" : null
//        }, {
//        "className" : "com.javarush.task.task33.task3305.Car"
//        } ]
//        }
//
//        Подсказка: это всего два класса.
//
//
//        Требования:
//        1. Вывод на экран должен соответствовать условию задачи.
//        2. Класс Parking должен быть отмечен аннотацией @JsonTypeInfo с подходящим набором параметров.
//        3. Класс Auto должен быть отмечен аннотацией @JsonTypeInfo с подходящим набором параметров.
//        4. Класс Auto должен быть отмечен аннотацией @JsonSubTypes с подходящим набором параметров.

public class Solution {
    public static void main(String[] args) throws IOException {
        Parking parking = new Parking("Super Parking", "Kyiv");
        RaceBike raceBike = new RaceBike("Simba", "Peter", 2);
        Motorbike motorbike = new Motorbike("Manny");
        Car car = new Car();
        List<Auto> autos = new ArrayList<>();
        autos.add(raceBike);
        autos.add(motorbike);
        autos.add(car);
        parking.setAutos(autos);
        convertToJson(parking);
    }
    public static void convertToJson(Parking parking) throws IOException {
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, parking);
        /*writerWithDefaultPrettyPrinter позволяет сохранить форматирование строки "лесенкой", вместо страндартной плоской строки для json
        что облегчает читаемость кода*/
        System.out.println(writer.toString());
    }
}
public class Car extends Auto {
}
public class Motorbike extends Auto {
    private String owner;
    public Motorbike(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    @Override
    public String toString() {
        return "Motorbike{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
public class RaceBike extends Motorbike {
    private String owner;
    private int age;
    public RaceBike(String name, String owner, int age) {
        super(name);
        this.owner = owner;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public String getOwner() {
        return owner;
    }
    public int getAge() {
        return age;
    }
    @Override
    public String toString() {
        return "RaceBike{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", age=" + age +
                '}';
    }
}
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property="className")//MINIMAL_CLASS - распечатает непосредственно имя класса, как нам требуется
public class Parking {
    public String name;
    public String city;
    public List<Auto> autos;
    public Parking(String name, String city) {
        this.name = name;
        this.city = city;
    }
    public void setAutos(List<Auto> autos) {
        this.autos = autos;
    }
    @Override
    public String toString() {
        return "Parking{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", autos=" + autos +
                '}';
    }
}
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property="className")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Car.class, name = "car"),//задаем, как будет распечатываться имя класса после десериализации
        @JsonSubTypes.Type(value=Motorbike.class, name = "motorbike"),
        @JsonSubTypes.Type(value=RaceBike.class, name = "race-bike")
})
public abstract class Auto {
    protected String name;
    protected String owner;
    protected int age;
}
