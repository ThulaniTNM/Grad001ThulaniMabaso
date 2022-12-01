package com.eviro.assessment.grad001.thulaniMabaso.Grad001ThulaniMabaso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    @Autowired
    private AccountProfileService accountProfileService;

    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w}", produces = MediaType.IMAGE_JPEG_VALUE)
    public FileSystemResource gethttpImageLink(@PathVariable String name, @PathVariable String surname){
        accountProfileService.saveLoadAccountProfile();
        String filePath = accountProfileService.getResourcePath(name,surname);
        return new  FileSystemResource(filePath);
    }
}
