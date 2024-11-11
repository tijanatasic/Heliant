package heliant.app.controller;

import heliant.app.dto.request.PoljeRequestDto;
import heliant.app.dto.response.PoljeResponseDto;
import heliant.app.service.PoljeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/polje")
@RequiredArgsConstructor
@Slf4j
public class PoljeController {

    private final PoljeService poljeService;

    @GetMapping("/{id}")
    public ResponseEntity<PoljeResponseDto> getById(@PathVariable Integer id) {
        log.info("Received request for fetching polje with ID: {}", id);
        PoljeResponseDto poljeResponseDto = poljeService.findPoljeById(id);
        log.info("Returning fetched polje: {}", poljeResponseDto);
        return ResponseEntity.ok(poljeResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PoljeResponseDto>> getByAll() {
        log.info("Received request for fetching all polja");
        List<PoljeResponseDto> poljeResponseDtos = poljeService.findAllPolja();
        log.info("Returning fetched polja: {}", poljeResponseDtos);
        return ResponseEntity.ok(poljeResponseDtos);
    }

    @PostMapping
    public ResponseEntity<PoljeResponseDto> savePolje(@Valid @RequestBody PoljeRequestDto poljeRequestDto) {
        log.info("Received request for saving polje: {}", poljeRequestDto);
        PoljeResponseDto poljeResponseDto = poljeService.savePolje(poljeRequestDto);
        log.info("Successfully saved polje: {}", poljeResponseDto);
        return ResponseEntity.ok(poljeResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoljeResponseDto> updatePolje(@Valid @RequestBody PoljeRequestDto poljeRequestDto, @PathVariable Integer id) {
        log.info("Received request for updating polje. Id - {}, body - {}", id, poljeRequestDto);
        PoljeResponseDto poljeResponseDto = poljeService.updatePolje(poljeRequestDto, id);
        log.info("Successfully updated polje: {}", poljeResponseDto);
        return ResponseEntity.ok(poljeResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolje(@PathVariable Integer id) {
        log.info("Received request for deleting polje with id: {}", id);
        poljeService.deletePolje(id);
        log.info("Successfully deleted polje with id: {}", id);
        return ResponseEntity.ok().build();
    }
}
