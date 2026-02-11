package com.dardan.rent_tool.infrastructure.config;

import com.dardan.rent_tool.application.usecase.tool.SearchToolUseCase;
import com.dardan.rent_tool.domain.port.repository.ToolRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public SearchToolUseCase searchToolUseCase(ToolRepository toolRepository) {
        return new SearchToolUseCase(toolRepository);
    }
}
