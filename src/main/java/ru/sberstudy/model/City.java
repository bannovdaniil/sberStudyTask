package ru.sberstudy.model;

import java.util.Objects;

public class City {
    private final Long id;
    private final String name;
    private final String region;
    private final String district;
    private final Long population;
    private final String foundation;

    public Long getId() {
        return id;
    }

    public Long getPopulation() {
        return population;
    }

    public City(Long id, String name, String region, String district, Long population, String foundation) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", foundation='" + foundation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        return Objects.equals(population, city.population);
    }

    @Override
    public int hashCode() {
        return population != null ? population.hashCode() : 0;
    }
}
