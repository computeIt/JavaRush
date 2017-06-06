package com.javarush.task.task37.task3711;

/**
 * Created by Igor on 06.06.2017.
 */
public class Computer {
    private CPU cpu;
    private HardDrive hardDrive;
    private Memory memory;

    public Computer(){
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }
//мы собираем все разрозненные классы под одним общим фасадом

//Паттерн Фасад позволяет скрыть сложность системы путём сведения всех возможных внешних вызовов к одному объекту,
//делегирующему их соответствующим объектам системы.

//класс Computer инкапсулирует создание объектов и предоставляет единую точку взаимодействия с ними.

    public void run(){
        cpu.calculate();
        memory.allocate();
        hardDrive.storeData();
    }
}
