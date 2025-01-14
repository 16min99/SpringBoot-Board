package com.toy.board.springbootboard.model.converter;

import com.toy.board.springbootboard.model.domain.Post;
import com.toy.board.springbootboard.model.domain.User;
import com.toy.board.springbootboard.model.dto.PostDto;
import com.toy.board.springbootboard.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostConverter {

    public Post convertPost(PostDto postDto, User user){
        Post post = new Post(
                postDto.getTitle(),
                postDto.getContent(),
                user
        );
        post.setCreatedAt(LocalDateTime.now());
        post.setCreatedBy(user.getName());

        return post;
    }

    public User convertUser(UserDto userDto){
        User user = new User(
                userDto.getName(),
                userDto.getAge(),
                userDto.getHobby()
        );
        user.setCreatedAt(userDto.getCreatedAt());
        user.setCreatedBy(userDto.getCreatedBy());

        return user;
    }

    public PostDto convertPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public UserDto converUserDto (User user){
        return UserDto.builder()
                .id(user.getId())
                .age(user.getAge())
                .name(user.getName())
                .hobby(user.getHobby())
                .createdBy(user.getCreatedBy())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
