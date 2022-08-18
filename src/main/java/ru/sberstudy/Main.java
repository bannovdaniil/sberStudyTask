package ru.sberstudy;

import ru.sberstudy.utils.CityCsvParser;

public class Main {
    public static void main(String[] args) {
        CityCsvParser cityCsvParser = new CityCsvParser("city_ru.csv");

        cityCsvParser.printCityIndexWithMaxPopulation();
    }
}