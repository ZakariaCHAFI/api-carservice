package com.devoteam.carservice.controllers;

import com.devoteam.carservice.dto.CarResponseDto;
import com.devoteam.carservice.dto.ErrorResponseDto;
import com.devoteam.carservice.dto.LeasingDto;
import com.devoteam.carservice.dto.ReturnLeasedDto;
import com.devoteam.carservice.exceptions.ErrorCodes;
import com.devoteam.carservice.exceptions.InvalidRequestException;
import com.devoteam.carservice.services.CarService;
import com.devoteam.carservice.services.LeasingService;
import com.devoteam.carservice.validators.LeasingValidator;
import com.devoteam.carservice.validators.ReturnLeasedValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService carService;
    private final LeasingService leasingService;
    Logger logger = LoggerFactory.getLogger(CarController.class);

    public CarController(CarService carService, LeasingService leasingService) {
        this.carService = carService;
        this.leasingService = leasingService;
    }

    @GetMapping("/search")
    @Operation(summary = "Chercher une voiture de location")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Chercher une voitures de location", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))})
    public ResponseEntity<List<CarResponseDto>> search(@RequestParam(name = "startDate") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date startDate, @RequestParam(name = "endDate") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") Date endDate) {
        return ResponseEntity.ok().body(carService.findAvailableCars());
    }

    @GetMapping("/cars")
    @Operation(summary = "Afficher tous les voitures de location")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Afficher tous les voitures de location avec pagination", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))})
    public ResponseEntity<Page<CarResponseDto>> showAllCars(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok().body(carService.findAllCars(PageRequest.of(page, size, Sort.by(sortBy))));
    }

    @GetMapping("/cars-not-available")
    @Operation(summary = "Afficher tous les voitures de location reserver")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Afficher tous les voitures de location reserver", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))})
    public ResponseEntity<List<CarResponseDto>> showNotAvailableCars() {
        return ResponseEntity.ok().body(carService.findNotAvailableCars());
    }

    @PostMapping("/lease")
    @Operation(summary = "Reserver une voiture")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "reserver une voiture", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))})
    public ResponseEntity<LeasingDto> lease(@RequestBody LeasingDto leasingDto) {
        List<String> errors = LeasingValidator.validate(leasingDto);
        if (!errors.isEmpty()) {
            logger.error("Leasing is not valid {}", leasingDto);
            throw new InvalidRequestException("Leasing is not valid ", ErrorCodes.LEASING_NOT_VALID, errors);
        }
        return ResponseEntity.ok().body(leasingService.saveLeasing(leasingDto));
    }

    @GetMapping("/histo-leased")
    @Operation(summary = "Afficher tous les resevations")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Afficher tous les resevations", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))})
    public ResponseEntity<List<LeasingDto>> ShowHistoleasedCars() {
        return ResponseEntity.ok().body(leasingService.findAllLeasingHisto());
    }

    @PostMapping("/retuen-leased")
    @Operation(summary = "retourner une reservation de voiture")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "retourner une reservation de voiture", content = @Content(mediaType = "application/json")), @ApiResponse(responseCode = "500", description = "default error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDto.class)))})
    public ResponseEntity<LeasingDto> returnLeasedCar(@RequestBody ReturnLeasedDto returnLeasedDto) {
        List<String> errors = ReturnLeasedValidator.validate(returnLeasedDto);
        if (!errors.isEmpty()) {
            logger.error("Return leased car is not valid {}", returnLeasedDto);
            throw new InvalidRequestException("Return leased car is not valid ", ErrorCodes.RETURN_LEASED_NOT_VALID, errors);
        }
        return ResponseEntity.ok().body(leasingService.returnLeasedCar(returnLeasedDto));
    }
}
