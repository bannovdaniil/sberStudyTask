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
     * находит и отображает на экране индекс и количество населения самого крупного города V1
     * через индекс массива
     * O(n)
     */
    public void printCityIndexWithMaxPopulationBruteForceV1() {
        City[] cityArray = cityList.toArray(new City[0]);
        int maxCityIndex = 0;
        long maxCityPopulation = cityArray[0].getPopulation();

        for (int index = 0; index < cityArray.length; index++) {
            if (maxCityPopulation < cityArray[index].getPopulation()) {
                maxCityPopulation = cityArray[index].getPopulation();
                maxCityIndex = index;
            }
        }

        System.out.printf("[%d] %,d%n", maxCityIndex, maxCityPopulation);
    }

    /**
     * находит и отображает на экране индекс и количество населения самого крупного города
     * выборкой объекта City
     * O(n)
     */
    public void printCityIndexWithMaxPopulationBruteforceV2() {
        City[] cityArray = cityList.toArray(new City[0]);
        City cityWithMaxPopulation = cityArray[0];

        for (City city : cityArray) {
            if (cityWithMaxPopulation.getPopulation() < city.getPopulation()) {
                cityWithMaxPopulation = city;
            }
        }
        System.out.printf("[%d] %,d%n", cityWithMaxPopulation.getId(), cityWithMaxPopulation.getPopulation());
    }

    /**
     * находит и отображает на экране индекс и количество населения самого крупного города
     * через сортировку городов по населению City
     * O(n log n)
     */
    public void printCityIndexWithMaxPopulationSortV3() {
        sortByCityPopulationReverse();
        City cityWithMaxPopulation = cityList.get(0);
        System.out.printf("[%d] %,d%n", cityWithMaxPopulation.getId(), cityWithMaxPopulation.getPopulation());
    }

    /**
     * находит и отображает на экране индекс и количество населения самого крупного города
     * через stream City
     * O(n)
     */
    public void printCityIndexWithMaxPopulationWithStreamV4() {
        City cityWithMaxPopulation = cityList.stream()
                .max(Comparator.comparing(City::getPopulation))
                .get();

        System.out.printf("[%d] %,d%n", cityWithMaxPopulation.getId(), cityWithMaxPopulation.getPopulation());
    }

    /**
     * находит и отображает на экране индекс и количество населения самого крупного города
     * Сортировка вставками City
     * O(n log n)
     */
    public void printCityIndexWithMaxPopulationInsertV5() {
        City[] cityArray = cityList.toArray(new City[0]);

        for (int i = 1; i < cityArray.length; i++) {
            City current = cityArray[i];
            int j = i - 1;
            while (j >= 0 && current.getPopulation() > cityArray[j].getPopulation()) {
                cityArray[j + 1] = cityArray[j];
                j--;
            }
            cityArray[j + 1] = current;
        }

        System.out.printf("[%d] %,d%n", cityArray[0].getId(), cityArray[0].getPopulation());
    }

    /**
     * сортировка по населению городов
     * O(n log n)
     */
    public void sortByCityPopulationReverse() {
        cityList.sort(Comparator.comparing(City::getPopulation).reversed());
    }

    /**
     * парсинг объекта City из строки
     *
     * @param textLine - строка из файла с данными
     * @return {@link City}
     */
    private Optional<City> parseLine(String textLine) {
        String[] element = textLine.split(";");
        City city = null;

        try {
            if (element.length >= MAX_ELEMENTS) {
                String year = element.length > MAX_ELEMENTS ? element[5] : "";
                city = new City(
                        (long) cityList.size(),
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
