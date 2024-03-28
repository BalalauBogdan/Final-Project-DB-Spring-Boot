package dev.bogdanbalalau.project.controller;

import dev.bogdanbalalau.project.dto.CountryDTO;
import dev.bogdanbalalau.project.entity.Country;
import dev.bogdanbalalau.project.service.CountryService;
import dev.bogdanbalalau.project.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
@CrossOrigin(origins = "http://localhost:4200")
public class CountryController {

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllCountries() {
        List<Country> countryList = this.countryService.getAllCountries();

        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("List with all countries")
                .data(countryList)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCountryById(@PathVariable Integer id) {
        Optional<Country> optionalCountry = this.countryService.getCountryById(id);

        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();

            ApiResponse response = ApiResponse.builder()
                    .status(200)
                    .message("County found with id: " + country.getId())
                    .data(country)
                    .build();

            return ResponseEntity.ok(response);
        }

        ApiResponse response = ApiResponse.builder()
                .status(404)
                .message("Country not found with id: " + id)
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createCountry(@RequestBody CountryDTO countryDTO) {
        Country country = new Country();
        country.setName(countryDTO.getName());
        country.setCapital(countryDTO.getCapital());
        country.setOfficialLanguage(countryDTO.getOfficialLanguage());
        country.setPopulation(countryDTO.getPopulation());

        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Country created successfully")
                .data(this.countryService.createCountry(country))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateCountry(@RequestBody Country country) {
        Optional<Country> optionalCountry = this.countryService.getCountryById(country.getId());

        if (optionalCountry.isPresent()) {
            Country DBCountry = optionalCountry.get();
            DBCountry.setName(country.getName());
            DBCountry.setCapital(country.getCapital());
            DBCountry.setOfficialLanguage(country.getOfficialLanguage());
            DBCountry.setPopulation(country.getPopulation());

            ApiResponse response = ApiResponse.builder()
                    .status(200)
                    .message("Country updated successfully")
                    .data(this.countryService.updateCountry(DBCountry))
                    .build();

            return ResponseEntity.ok(response);
        }

        ApiResponse response = ApiResponse.builder()
                .status(404)
                .message("Country not found")
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCountryById(@PathVariable Integer id) {
        Optional<Country> optionalCountry = this.countryService.getCountryById(id);

        if (optionalCountry.isPresent()) {
            Country DBCountry = optionalCountry.get();
            this.countryService.deleteCountryById(DBCountry.getId());

            ApiResponse response = ApiResponse.builder()
                    .status(200)
                    .message("Country with id: " + id + " deleted successfully")
                    .data(null)
                    .build();

            return ResponseEntity.ok(response);
        }

        ApiResponse response = ApiResponse.builder()
                .status(404)
                .message("Country not found with id: " + id)
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
