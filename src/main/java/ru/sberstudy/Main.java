package ru.sberstudy;

import ru.sberstudy.utils.CityCsvParser;

public class Main {
    public static void main(String[] args) {
        CityCsvParser cityCsvParser = new CityCsvParser("city_ru.csv");

        cityCsvParser.printCityIndexWithMaxPopulationBruteForceV1();
        cityCsvParser.printCityIndexWithMaxPopulationBruteforceV2();
        cityCsvParser.printCityIndexWithMaxPopulationSortV3();
        cityCsvParser.printCityIndexWithMaxPopulationWithStreamV4();
        cityCsvParser.printCityIndexWithMaxPopulationInsertV5();
    }
}