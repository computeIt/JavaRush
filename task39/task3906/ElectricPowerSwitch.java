package com.javarush.task.task39.task3906;

public class ElectricPowerSwitch {    //то есть объединяем схожие по функционалу классы через интерфейс
    private Switchable switchable;    //и подставляем интерфейс вместо них
                                      //похоже на полиморфизм при наследовании, но у интерфейса нет свойств-полей и нельзя создавать их объекты
    public ElectricPowerSwitch(Switchable switchable) {
        this.switchable = switchable;
    }

    public void press() {
        System.out.println("Pressed the power switch.");
        if (switchable.isOn()) {
            switchable.turnOff();
        } else {
            switchable.turnOn();
        }
    }
}
