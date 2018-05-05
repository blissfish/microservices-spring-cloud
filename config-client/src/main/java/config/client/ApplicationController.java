package config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ApplicationController
{
    @Value("${message}")
    String message;
    @GetMapping("/message")
    public String name()
    {
        return message;
    }
}