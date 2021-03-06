package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;

/**
 * Created by Igor on 06.06.2017.
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    private void setBorderColor(Shape shape){
        System.out.println("Setting border color for " + shape.getClass().getSimpleName() + " to red.");
    }
//паттерн декоратор
//класс RedShapeDecorator в пакете Decorators расширяет функциональность объектов типа Shape не меняя их структуру
    @Override
    public void draw() {
        setBorderColor(this.decoratedShape);
        decoratedShape.draw();
    }
}
