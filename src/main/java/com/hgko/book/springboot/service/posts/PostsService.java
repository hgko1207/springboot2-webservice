package com.hgko.book.springboot.service.posts;

import com.hgko.book.springboot.domain.posts.Posts;
import com.hgko.book.springboot.domain.posts.PostsRepository;
import com.hgko.book.springboot.web.dto.PostsResponseDto;
import com.hgko.book.springboot.web.dto.PostsSaveRequestDto;
import com.hgko.book.springboot.web.dto.PostsUpdateRqquestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRqquestDto rqquestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        posts.update(rqquestDto.getTitle(), rqquestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
