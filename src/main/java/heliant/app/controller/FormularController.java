package heliant.app.controller;

import heliant.app.dto.request.FormularRequestDto;
import heliant.app.dto.response.FormularResponseDto;
import heliant.app.service.FormularService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formular")
@RequiredArgsConstructor
@Slf4j
public class FormularController {

    private final FormularService formularService;

    @GetMapping("/{id}")
    public ResponseEntity<FormularResponseDto> getById(@PathVariable Integer id) {
        log.info("Received request for fetching formular with ID: {}", id);
        FormularResponseDto formularResponseDto = formularService.findFormularById(id);
        log.info("Returning fetched formular: {}", formularResponseDto);
        return ResponseEntity.ok(formularResponseDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FormularResponseDto>> getByAll() {
        log.info("Received request for fetching all formulars");
        List<FormularResponseDto> formularResponseDtoList = formularService.findAllFormular();
        log.info("Returning fetched formulars: {}", formularResponseDtoList);
        return ResponseEntity.ok(formularResponseDtoList);
    }

    @PostMapping
    public ResponseEntity<FormularResponseDto> saveFormular(@Valid @RequestBody FormularRequestDto formularRequestDto) {
        log.info("Received request for saving formular: {}", formularRequestDto);
        FormularResponseDto formularResponseDto = formularService.saveFormular(formularRequestDto);
        log.info("Successfully saved formular: {}", formularResponseDto);
        return ResponseEntity.ok(formularResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormularResponseDto> updateFormular(@Valid @RequestBody FormularRequestDto formularRequestDto, @PathVariable Integer id) {
        log.info("Received request for updating formular. Id - {}, body - {}", id, formularRequestDto);
        FormularResponseDto formularResponseDto = formularService.updateFormular(formularRequestDto, id);
        log.info("Successfully updated formular: {}", formularResponseDto);
        return ResponseEntity.ok(formularResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormular(@PathVariable Integer id) {
        log.info("Received request for deleting formular with id: {}", id);
        formularService.deleteFormular(id);
        log.info("Successfully deleted formular with id: {}", id);
        return ResponseEntity.ok().build();
    }
}
