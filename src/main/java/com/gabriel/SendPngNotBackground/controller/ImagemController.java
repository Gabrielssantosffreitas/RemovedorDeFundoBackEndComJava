package com.gabriel.SendPngNotBackground.controller;

import com.gabriel.SendPngNotBackground.model.models.ImagemModels;
import com.gabriel.SendPngNotBackground.services.Imagem.ImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class ImagemController {

    @Autowired
    ImagemService imagemService;

    @GetMapping("/test")
    public String getTest(){
        return "uma mensagem";
    }
    @PostMapping("/img_post")
    public ResponseEntity<ImagemModels> postImage(@RequestBody ImagemModels imagemModels) throws IOException, InterruptedException {
        return imagemService.EviarImagem(imagemModels);
    }
}



