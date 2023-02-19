package com.veridic.hackathon.evstations.controller;

import com.veridic.hackathon.evstations.model.Station;
import com.veridic.hackathon.evstations.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stations")
@CrossOrigin
public class StationController {

    @Autowired
    StationRepository stationRepository;

    @GetMapping
    public List<Station> getStations(@RequestParam(defaultValue = "100") Integer limit,@RequestParam(defaultValue = "price") String sort) {
        Pageable paging =  PageRequest.of(0,limit,Sort.by(sort));
        Page<Station> pagedResult=  stationRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Station>();
        }

    }



    @GetMapping("/{id}")
    public Station getStation(@PathVariable Long id) {
        return stationRepository.findById(id).orElseThrow(RuntimeException::new);
    }



    @PostMapping
    public ResponseEntity createStation(@RequestBody Station station) throws URISyntaxException {
        Station savedStation = stationRepository.save(station);
        return ResponseEntity.created(new URI("/stations/" + savedStation.getId())).body(savedStation);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStation(@PathVariable Long id, @RequestBody Station station) {
        Station currentStation = stationRepository.findById(id).orElseThrow(RuntimeException::new);
        currentStation.setName(station.getName());
        currentStation.setAddress(station.getAddress());
        currentStation.setPrice(station.getPrice());
        currentStation = stationRepository.save(station);
        return ResponseEntity.ok(currentStation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStation(@PathVariable Long id) {
        stationRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}


