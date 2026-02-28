package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagRequest;
import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.ApiResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
import br.com.personalfinace.personalfinanceapi.common.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tag")
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<ApiResponse<TagResponse>> save(@Valid @RequestBody TagRequest request) throws BusinessException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(tagService.save(request), "Tag created successfully"));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<TagResponse>> update(@Valid @RequestBody TagRequest request) throws BusinessException {
        return ResponseEntity.ok(ApiResponse.success(tagService.save(request), "Tag updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Response>> delete(@PathVariable Long id) throws BusinessException {
        Response response = tagService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null,"Tag deleted successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TagResponse>>> findAll () throws BusinessException {
        return ResponseEntity.ok(ApiResponse.success(tagService.findAll(), "Tags retrieved"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TagResponse>> findById (@PathVariable Long id) throws BusinessException {
        return ResponseEntity.ok(ApiResponse.success(tagService.findById(id), "Tag found"));
    }

}
