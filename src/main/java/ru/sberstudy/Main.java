package ru.sberstudy;

import ru.sberstudy.model.City;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public final static int MAX_ELEMENTS = 5;

    public static void main(String[] args) throws IOException, URISyntaxException {
        List<City> cityList = new ArrayList<>();
        Scanner scanner = new Scanner(Paths.get(Main.class.getClassLoader().getResource("city_ru.csv").toURI()));

        while (scanner.hasNext()) {
            String textLine = scanner.nextLine();
            Optional<City> city = parseLine(textLine);
            city.ifPresent(cityList::add);
        }
        scanner.close();

        for (City city : cityList) {
            System.out.println(city);
        }
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
        } catch (Exception err) {
            err.getStackTrace();
        }
        return Optional.ofNullable(city);
    }
}