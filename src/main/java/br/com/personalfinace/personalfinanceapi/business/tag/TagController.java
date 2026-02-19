package br.com.personalfinace.personalfinanceapi.business.tag;

import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagRequest;
import br.com.personalfinace.personalfinanceapi.business.tag.dto.TagResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.ApiResponse;
import br.com.personalfinace.personalfinanceapi.common.dto.Response;
import br.com.personalfinace.personalfinanceapi.common.exception.BusinessException;
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
    public ResponseEntity<ApiResponse<TagResponse>> save(@RequestBody TagRequest tagRequest) throws BusinessException {
        return ResponseEntity.ok(ApiResponse.success(tagService.save(tagRequest),"Tag saved"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Response>> delete(@PathVariable Long id) {
        Response response = tagService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(response,"Tag deleted"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TagResponse>>> findAll () throws BusinessException {
        return ResponseEntity.ok(ApiResponse.success(tagService.findAll(), "Tags found"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TagResponse>> findById (@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(tagService.findById(id), "Tag found"));
    }

}
