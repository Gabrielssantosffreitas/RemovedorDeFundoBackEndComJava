package com.gabriel.SendPngNotBackground.services.Imagem;

import com.gabriel.SendPngNotBackground.model.models.ImagemModels;
import com.gabriel.SendPngNotBackground.services.Imagem.httpRequestApi.RequestApiBackgroundBg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

@Service
public class ImagemService {
    @Value(value = "${api.key}")
    private String API_KEY;
    private String detectImageType(byte[] imageBytes) {
        if (imageBytes.length > 8 && imageBytes[0] == (byte)0x89 && imageBytes[1] == (byte)0x50 && imageBytes[2] == (byte)0x4E && imageBytes[3] == (byte)0x47) {
            return "png";
        }
        // JPEG
        if (imageBytes.length > 3 && imageBytes[0] == (byte)0xFF && imageBytes[1] == (byte)0xD8 && imageBytes[2] == (byte)0xFF) {
            return "jpg";
        }
        return "unknown";
    }

    public ResponseEntity<ImagemModels> EviarImagem (ImagemModels imagemModels) throws IOException {
        String imgBase64 = imagemModels.getImagemBase64();
        byte[] imgDecoder = Base64.getDecoder().decode(imgBase64);
        String imageType = detectImageType(imgDecoder);
        if (imageType.equals("unknown")) {
            System.out.println("Imagem invalida so jpg ou png");
            return ResponseEntity.internalServerError().build();
        }
        String extension = imageType.equals("png") ? ".png" : ".jpg";
        Path arquivoTemporario = Files.createTempFile("upload-", extension);
        Files.write(arquivoTemporario, imgDecoder);

        ResponseEntity<byte[]> response = RequestApiBackgroundBg.Request(API_KEY, arquivoTemporario, imageType);
        HttpHeaders respHeaders = response.getHeaders();
        MediaType respType = respHeaders.getContentType();

        if (respType != null && (respType.equals(MediaType.IMAGE_PNG) || respType.equals(MediaType.IMAGE_JPEG))) {
            byte[] imageBytes = response.getBody();
            String imgBase64Respose = Base64.getEncoder().encodeToString(imageBytes);
            ImagemModels imagemModelsResponse =  new ImagemModels();
            imagemModelsResponse.setImagemBase64(imgBase64Respose);
            imagemModelsResponse.setMensagem("Aqui esta sua foto");
            return  ResponseEntity.ok().body(imagemModelsResponse);
        } else if (respType != null && respType.equals(MediaType.APPLICATION_JSON)) {
            System.out.println("erro " + new String(response.getBody()));
             return  ResponseEntity.internalServerError().build();
        } else {
            System.out.println(  respType);
            System.out.println( new String(response.getBody()));
            return  ResponseEntity.internalServerError().build();
        }
    }

}
