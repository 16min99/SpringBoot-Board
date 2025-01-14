package com.toy.board.springbootboard.controller;

import com.toy.board.springbootboard.ApiResponse;
import com.toy.board.springbootboard.model.dto.PostDto;
import com.toy.board.springbootboard.model.service.PostService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @ExceptionHandler
    private ApiResponse<String> exceptionHandle(Exception exception) {
        log.info("Exception : {} , {}", HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return ApiResponse.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    private ApiResponse<String> notFoundHandle(NotFoundException exception) {
        log.info("Exception : {} , {}", HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ApiResponse.fail(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @PostMapping("")
    public ApiResponse<Long> save(@RequestBody PostDto postDto) throws NotFoundException {
        return ApiResponse.ok(postService.save(postDto));
    }

    @GetMapping("")
    public ApiResponse<Page<PostDto>> getAll(Pageable pageable) {
        Page<PostDto> page = postService.findPosts(pageable);
        return ApiResponse.ok(page);
    }

    @GetMapping("/{id}")
    public ApiResponse<PostDto> get(@PathVariable long id) throws NotFoundException {
        PostDto postDto = postService.findById(id);
        return ApiResponse.ok(postDto);
    }

    @PostMapping("/{id}")
    public ApiResponse<Long> update(
            @PathVariable long id,
            @RequestBody PostDto postDto
    ) {
        return ApiResponse.ok(postService.update(id, postDto));
    }
}
