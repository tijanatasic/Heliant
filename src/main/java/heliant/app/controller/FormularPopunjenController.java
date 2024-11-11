package heliant.app.controller;

import heliant.app.dto.request.FormularPopunjenRequestDto;
import heliant.app.dto.response.FormularPopunjenResponseDto;
import heliant.app.service.FormularPopunjenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formular-popunjen")
@RequiredArgsConstructor
@Slf4j
public class FormularPopunjenController {

    private final FormularPopunjenService formularPopunjenService;

    @GetMapping("/{id}")
    public ResponseEntity<FormularPopunjenResponseDto> getById(@PathVariable Integer id) {
        log.info("Received request for fetching popunjen formular with ID: {}", id);
        FormularPopunjenResponseDto formularPopunjenResponseDto = formularPopunjenService.findPopunjenFormularById(id);
        log.info("Returning fetched popunjen formular: {}", formularPopunjenResponseDto);
        return ResponseEntity.ok(formularPopunjenResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FormularPopunjenResponseDto>> getByAll() {
        log.info("Received request for fetching all popunjeni formulari");
        List<FormularPopunjenResponseDto> formularPopunjenResponseDtos = formularPopunjenService.findAllPopunjeniFormulari();
        log.info("Returning fetched formulari popunjeni: {}", formularPopunjenResponseDtos);
        return ResponseEntity.ok(formularPopunjenResponseDtos);
    }

    @PostMapping
    public ResponseEntity<FormularPopunjenResponseDto> savePopunjenFormular(@Valid @RequestBody @Validated FormularPopunjenRequestDto formularPopunjenRequestDto) {
        log.info("Received request for saving popunjen formular: {}", formularPopunjenRequestDto);
        FormularPopunjenResponseDto formularPopunjenResponseDto = formularPopunjenService.savePopunjenFormular(formularPopunjenRequestDto);
        log.info("Successfully saved popunjen formular: {}", formularPopunjenResponseDto);
        return ResponseEntity.ok(formularPopunjenResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormularPopunjenResponseDto> updatePopunjenFormular(@Valid @RequestBody @Validated FormularPopunjenRequestDto formularPopunjenRequestDto, @PathVariable Integer id) {
        log.info("Received request for updating popunjen formular. Id - {}, body - {}", id, formularPopunjenRequestDto);
        FormularPopunjenResponseDto formularPopunjenResponseDto = formularPopunjenService.updatePopunjenFormular(formularPopunjenRequestDto, id);
        log.info("Successfully updated formular popunjen: {}", formularPopunjenResponseDto);
        return ResponseEntity.ok(formularPopunjenResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePopunjenFormular(@PathVariable Integer id) {
        log.info("Received request for deleting popunjen formular with id: {}", id);
        formularPopunjenService.deletePopunjenFormular(id);
        log.info("Successfully deleted popunjen formular with id: {}", id);
        return ResponseEntity.ok().build();
    }
}
