package com.b2.teste.controller;


import com.b2.teste.LeituraArquivo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iso")
public class IsoController {

    private final LeituraArquivo leituraArquivo;

    public IsoController(LeituraArquivo leituraArquivo) {
        this.leituraArquivo = leituraArquivo;
    }

    @PostMapping
    public ResponseEntity<?> processaIso8583(@RequestBody String message){
        return ResponseEntity.ok(leituraArquivo.processaIsoText(message));
    }
}
