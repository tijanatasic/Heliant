package heliant.app.controller;

import heliant.app.dto.request.FormularRequestDto;
import heliant.app.dto.response.FormularResponseDto;
import heliant.app.service.FormularService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formular")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Formular", description = "Controller in charge for managing formular")
@SecurityRequirement(name = "bearerAuth")
public class FormularController {

    private final FormularService formularService;

    @Operation(summary = "Get a formular by its id")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RADNIK')")
    @GetMapping("/{id}")
    public ResponseEntity<FormularResponseDto> getById(@PathVariable Integer id) {
        log.info("Received request for fetching formular with ID: {}", id);
        FormularResponseDto formularResponseDto = formularService.findFormularById(id);
        log.info("Returning fetched formular: {}", formularResponseDto);
        return ResponseEntity.ok(formularResponseDto);
    }

    @Operation(summary = "Get all formulars")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RADNIK')")
    @GetMapping("/all")
    public ResponseEntity<List<FormularResponseDto>> getByAll() {
        log.info("Received request for fetching all formulars");
        List<FormularResponseDto> formularResponseDtoList = formularService.findAllFormular();
        log.info("Returning fetched formulars: {}", formularResponseDtoList);
        return ResponseEntity.ok(formularResponseDtoList);
    }

    @Operation(summary = "Save formular")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RADNIK')")
    @PostMapping
    public ResponseEntity<FormularResponseDto> saveFormular(@Valid @RequestBody FormularRequestDto formularRequestDto) {
        log.info("Received request for saving formular: {}", formularRequestDto);
        FormularResponseDto formularResponseDto = formularService.saveFormular(formularRequestDto);
        log.info("Successfully saved formular: {}", formularResponseDto);
        return ResponseEntity.ok(formularResponseDto);
    }

    @Operation(summary = "Update formular")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RADNIK')")
    @PutMapping("/{id}")
    public ResponseEntity<FormularResponseDto> updateFormular(@Valid @RequestBody FormularRequestDto formularRequestDto, @PathVariable Integer id) {
        log.info("Received request for updating formular. Id - {}, body - {}", id, formularRequestDto);
        FormularResponseDto formularResponseDto = formularService.updateFormular(formularRequestDto, id);
        log.info("Successfully updated formular: {}", formularResponseDto);
        return ResponseEntity.ok(formularResponseDto);
    }

    @Operation(summary = "Delete formular")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'RADNIK')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormular(@PathVariable Integer id) {
        log.info("Received request for deleting formular with id: {}", id);
        formularService.deleteFormular(id);
        log.info("Successfully deleted formular with id: {}", id);
        return ResponseEntity.ok().build();
    }
}
