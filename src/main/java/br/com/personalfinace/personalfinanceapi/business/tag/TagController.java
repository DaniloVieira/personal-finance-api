package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagRequest;
import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tag")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<TagResponse> save(@RequestBody TagRequest tagRequest) {
        return ResponseEntity.ok(tagService.save(tagRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.delete(id));
    }

    @GetMapping
    public ResponseEntity<List<TagResponse>> findAll () {
        return ResponseEntity.ok(tagService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponse> findById (@PathVariable Long id) {
        return ResponseEntity.ok(tagService.findById(id));
    }

}
