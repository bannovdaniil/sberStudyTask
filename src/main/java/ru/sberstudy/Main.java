package ru.sberstudy;

import ru.sberstudy.utils.CityCsvParser;

public class Main {
    public static void main(String[] args) {
        CityCsvParser cityCsvParser = new CityCsvParser("city_ru.csv");

        System.out.println("Lambda: Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра:");
        cityCsvParser.sortByCityLambdaVersion();
        cityCsvParser.print();

        System.out.println("Comparator: Сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра:");
        cityCsvParser.sortByCityComparatorVersion();
        cityCsvParser.print();

        System.out.println("Сортировка списка городов по федеральному округу и наименованию города внутри каждого " +
                "федерального округа в алфавитном порядке по убыванию с учетом регистра:");
        cityCsvParser.sortByDistrict();
        cityCsvParser.print();
    }
}