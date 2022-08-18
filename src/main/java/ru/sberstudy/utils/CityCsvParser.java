package ru.sberstudy.utils;

import ru.sberstudy.Main;
import ru.sberstudy.model.City;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CityCsvParser {
    private final static int MAX_ELEMENTS = 5;
    private final List<City> cityList = new ArrayList<>();

    public void parse(String fileName) {
        try {
            Scanner scanner = new Scanner(Paths.get(Main.class.getClassLoader().getResource(fileName).toURI()));

            while (scanner.hasNext()) {
                String textLine = scanner.nextLine();
                Optional<City> city = parseLine(textLine);
                city.ifPresent(cityList::add);
            }
            scanner.close();
        } catch (IOException | URISyntaxException err) {
            err.printStackTrace();
        }
    }

    public void print() {
        cityList.forEach(System.out::println);
    }

    private static Optional<City> parseLine(String textLine) {
        String[] element = textLine.split(";");
        City city = null;

        try {
            if (element.length >= MAX_ELEMENTS) {
                String year = element.length > MAX_ELEMENTS ? element[5] : "";
                city = new City(
                        Long.parseLong(element[0]),
                        element[1],
                        element[2],
                        element[3],
                        Long.parseLong(element[4]),
                        year
                );
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException err) {
            err.printStackTrace();
        }
        return Optional.ofNullable(city);
    }
}
