package ru.sberstudy.model;

import java.util.Objects;

public class City {
    private final Long id;
    private final String name;
    private final String region;
    private final String district;
    private final Long population;
    private final String foundation;

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

        if (!Objects.equals(id, city.id)) return false;
        if (!Objects.equals(name, city.name)) return false;
        if (!Objects.equals(region, city.region)) return false;
        if (!Objects.equals(district, city.district)) return false;
        if (!Objects.equals(population, city.population)) return false;
        return Objects.equals(foundation, city.foundation);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (population != null ? population.hashCode() : 0);
        result = 31 * result + (foundation != null ? foundation.hashCode() : 0);
        return result;
    }
}
