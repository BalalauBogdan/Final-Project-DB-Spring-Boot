package dev.bogdanbalalau.project.service;

import dev.bogdanbalalau.project.entity.Country;
import dev.bogdanbalalau.project.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService{

    private CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> getCountryById(Integer id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Country createCountry(Country country) {
        return this.countryRepository.save(country);
    }

    @Override
    public Country updateCountry(Country country) {
        return this.countryRepository.save(country);
    }

    @Override
    public void deleteCountryById(Integer id) {
        this.countryRepository.deleteById(id);
    }
}
