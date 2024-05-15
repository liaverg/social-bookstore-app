package com.myy803.social_bookstore.services;

import com.myy803.social_bookstore.domain.views.AuthorView;
import com.myy803.social_bookstore.mappers.AuthorMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ViewAuthorsService implements ViewAuthorsUseCase {
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorView> viewAuthors() {
        return authorMapper.findAll().stream()
                .map(author -> new AuthorView(author.getId(), author.getName()))
                .toList();
    }
}
