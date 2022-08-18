package ru.sberstudy.utils;

import ru.sberstudy.Main;
import ru.sberstudy.model.City;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Класс работы с данными о городах из указанного файла.
 */
public class CityCsvParser {
    private final String fileName;
    private final static int MAX_ELEMENTS = 5;
    private final List<City> cityList = new ArrayList<>();

    public CityCsvParser(String fileName) {
        this.fileName = fileName;
        parse();
    }

    /**
     * парсер файла в список.
     */
    private void parse() {
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

    /**
     * вывод списка на экран
     */
    public void print() {
        cityList.forEach(System.out::println);
    }

    /**
     * находит и отображает на экране индекс и количество населения самого крупного города
     */
    public void printCityIndexWithMaxPopulation() {
        City[] cityArray = cityList.toArray(new City[0]);
        int maxCityIndex = 0;
        long maxCityPopulation = cityArray[0].getPopulation();

        for (int index = 0; index < cityArray.length; index++) {
            if (maxCityPopulation < cityArray[index].getPopulation()) {
                maxCityPopulation = cityArray[index].getPopulation();
                maxCityIndex = index;
            }
        }

        System.out.printf("[%d] %,d", maxCityIndex, maxCityPopulation);
    }

    /**
     * сортировка по городам без учета регистра Lambda выражение
     */
    public void sortByCityLambdaVersion() {
        cityList.sort((city1, city2) -> city1.getName().compareToIgnoreCase(city2.getName()));
    }

    /**
     * сортировка по городам без учета регистра Comporator версия
     */
    public void sortByCityComparatorVersion() {
        cityList.sort(new Comparator<City>() {
            @Override
            public int compare(City city1, City city2) {
                return city1.getName().compareToIgnoreCase(city2.getName());
            }
        });
    }

    /**
     * сортировка по федеральному округу и по городам
     */
    public void sortByDistrict() {
        cityList.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    /**
     * парсинг объекта City из строки
     *
     * @param textLine - строка из файла с данными
     * @return {@link City}
     */
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
