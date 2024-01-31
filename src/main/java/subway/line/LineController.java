package subway.line;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import subway.line.section.CannotAddSectionException;
import subway.line.section.SectionRequest;
import subway.station.StationNotFoundException;

import java.net.URI;
import java.util.List;

@RestController
public class LineController {
    private final LineService lineService;

    public LineController(LineService lineService) {
        this.lineService = lineService;
    }

    @PostMapping("/lines")
    public ResponseEntity<LineResponse> createLine(@RequestBody LineRequest lineRequest) throws StationNotFoundException, CannotAddSectionException {
        LineResponse lineResponse = lineService.createLine(lineRequest);
        return ResponseEntity.created(URI.create("/lines/" + lineResponse.getId())).body(lineResponse);
    }

    @GetMapping("/lines")
    public ResponseEntity<List<LineResponse>> showLines() {
        return ResponseEntity.ok().body(lineService.showLines());
    }

    @GetMapping("/lines/{id}")
    public ResponseEntity<LineResponse> showLine(@PathVariable Long id) throws LineNotFoundException {
        return ResponseEntity.ok().body(lineService.showLine(id));
    }

    @PutMapping("/lines/{id}")
    public ResponseEntity<Void> updateLine(@PathVariable Long id, @RequestBody UpdateLineRequest updateLineRequest) throws LineNotFoundException {
        lineService.updateLine(id, updateLineRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/lines/{id}")
    public ResponseEntity<Void> deleteLine(@PathVariable Long id) {
        lineService.deleteLine(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/lines/{id}/sections")
    public ResponseEntity<LineSectionResponse> showLineSections(@PathVariable Long id) throws LineNotFoundException {
        return ResponseEntity.ok().body(lineService.showLineSections(id));
    }

    @PostMapping("/lines/{id}/sections")
    public ResponseEntity<LineSectionResponse> addLineSection(@PathVariable Long id, @RequestBody SectionRequest sectionRequest) {
        try {
            return ResponseEntity.ok().body(lineService.addLineSection(id, sectionRequest));
        } catch (CannotAddSectionException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
