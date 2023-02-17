package com.vladimirpandurov.springdatarelationship.controller;

import com.vladimirpandurov.springdatarelationship.dto.requestDto.CategoryRequestDto;
import com.vladimirpandurov.springdatarelationship.dto.responseDto.CategoryResponseDto;
import com.vladimirpandurov.springdatarelationship.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> addCategory(@RequestBody final CategoryRequestDto categoryRequestDto){
        CategoryResponseDto categoryResponseDto = this.categoryService.addCategory(categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable("id") final Long id){
        CategoryResponseDto categoryResponseDto = this.categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> getCategories(){
        List<CategoryResponseDto> categoryResponseDtos = this.categoryService.getCategories();
        return new ResponseEntity<>(categoryResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> deleteCategory(@PathVariable("id") final Long id){
        CategoryResponseDto categoryResponseDto = this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> editCategory(@RequestBody final CategoryRequestDto categoryRequestDto,
                                                            @PathVariable("id") final Long id){
        CategoryResponseDto categoryResponseDto = this.categoryService.editCategory(id, categoryRequestDto);
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
    }


}
