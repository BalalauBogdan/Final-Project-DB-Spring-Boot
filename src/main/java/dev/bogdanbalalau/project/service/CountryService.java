package dev.bogdanbalalau.project.service;

import dev.bogdanbalalau.project.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> getAllCountries();
    Optional<Country> getCountryById(Integer id);
    Country createCountry(Country country);
    Country updateCountry(Country country);
    void deleteCountryById(Integer id);
}
