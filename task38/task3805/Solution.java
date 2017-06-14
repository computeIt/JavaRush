package com.javarush.task.task38.task3805;

/* 
Улучшения в Java 7 (multiple exceptions)
Перепиши реализации методов класса Solution.
Используй нововведения (multiple exceptions), касающиеся обработки исключений, которые были добавлены в Java 7.

Требования:
1. В конструкторе класса Solution в одном catch блоке должны быть обработаны исключения WrongDataException и ConnectionException.
2. В методе write в одном catch блоке должны быть обработаны исключения WrongDataException и ConnectionException.
3. В методе read в одном catch блоке должны быть обработаны исключения WrongDataException и ConnectionException.
4. В методе disconnect в одном catch блоке должны быть обработаны исключения WrongDataException и ConnectionException.*/

public class Solution {
    private final Connection connection = new ConnectionMock();

    public Solution() throws SolutionException {
        try {
            connection.connect();
        }
        catch (WrongDataException | ConnectionException e) {
            if (e instanceof WrongDataException)
                throw new SolutionException("WrongDataException: " + e.getMessage());
            else if (e instanceof ConnectionException)
                throw new SolutionException("ConnectionException: " + e.getMessage());
        }
    }

    public void write(Object data) throws SolutionException {
        try {
            connection.write(data);
        }
        catch (WrongDataException | ConnectionException e) {
            if (e instanceof WrongDataException)
                throw new SolutionException("WrongDataException: " + e.getMessage());
            else if (e instanceof ConnectionException)
                throw new SolutionException("ConnectionException: " + e.getMessage());
        }
    }

    public Object read() throws SolutionException {
        try {
            return connection.read();
        }
        catch (WrongDataException | ConnectionException e) {
            if (e instanceof WrongDataException)
                throw new SolutionException("WrongDataException: " + e.getMessage());
            else if (e instanceof ConnectionException)
                throw new SolutionException("ConnectionException: " + e.getMessage());
        }
        return null;//???? я в недоумении
    }

    public void disconnect() throws SolutionException {
        try {
            connection.disconnect();
        }
        catch (WrongDataException | ConnectionException e) {
            if (e instanceof WrongDataException)
                throw new SolutionException("WrongDataException: " + e.getMessage());
            else if (e instanceof ConnectionException)
                throw new SolutionException("ConnectionException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

    }
}
