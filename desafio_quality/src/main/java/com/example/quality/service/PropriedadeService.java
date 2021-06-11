package com.example.quality.service;

import com.example.quality.dto.ComodoDTO;
import com.example.quality.dto.PropriedadeDTO;
import com.example.quality.entity.Comodo;
import com.example.quality.entity.Propriedade;
import com.example.quality.exception.BairroNotFoundException;
import com.example.quality.exception.PropriedadeNotFoundException;
import com.example.quality.mapper.ComodoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class PropriedadeService {

    private final Map<String, Double> bairroValorPorMetroQuadradoMap = new HashMap<>();
    private final List<Propriedade> propriedades = new ArrayList<>();

     public PropriedadeService(){
        populaListaDeBairros();
        populaListaDePropriedades();
     }

    public ResponseEntity<?> salvaPropriedade(PropriedadeDTO propriedadeDTO) throws BairroNotFoundException {
        verifyIfBairroExists(propriedadeDTO.getBairro());
        Propriedade propriedade = new Propriedade(propriedadeDTO.getNome().toLowerCase(), propriedadeDTO.getBairro(), ComodoMapper.postsDTOtoComodosList(propriedadeDTO.getComodos()));
        propriedades.add(propriedade);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> getTotalM2Propriedade(String propriedadeNome) throws PropriedadeNotFoundException {
        Propriedade propriedade = verifyIfPropriedadeExists(propriedadeNome);
        return new ResponseEntity<>(propriedade.totalMetrosQuadradosDaPropriedade(), HttpStatus.OK);
    }

    public ResponseEntity<?> getValorPropriedade(String propriedadeNome) throws PropriedadeNotFoundException {
        Propriedade propriedade = verifyIfPropriedadeExists(propriedadeNome);
        Double valorM2 = bairroValorPorMetroQuadradoMap.get(propriedade.getBairro());
        return new ResponseEntity<>((propriedade.totalMetrosQuadradosDaPropriedade()*valorM2), HttpStatus.OK);
    }

    public ComodoDTO getMaiorComodo(String propriedadeNome) throws PropriedadeNotFoundException {
        Propriedade propriedade = verifyIfPropriedadeExists(propriedadeNome);
        return ComodoMapper.comodoToComodoDTO(propriedade.maiorComodo());
    }

    public ResponseEntity<?> getM2PorComodo(String propriedadeNome) throws PropriedadeNotFoundException {
        Propriedade propriedade = verifyIfPropriedadeExists(propriedadeNome);
        return new ResponseEntity<>(propriedade.metrosQuadradosDeCadaComodo(), HttpStatus.OK);
    }

    public void verifyIfBairroExists(String nomeBairro) throws BairroNotFoundException {
         String bairro = deAccent(nomeBairro);
         boolean existe = false;
         for(Map.Entry<String, Double> pair: bairroValorPorMetroQuadradoMap.entrySet()){
             if(pair.getKey().equalsIgnoreCase(bairro)){
                 existe = true;
                 break;
             }
         }

         if (!existe){
             throw new BairroNotFoundException("O bairro de nome " + nomeBairro + " não existe na base de Bairros");
         }
    }

    private Propriedade verifyIfPropriedadeExists(String propriedadeNome) throws PropriedadeNotFoundException {
        for(Propriedade p: propriedades){
            if(p.getNome().equalsIgnoreCase(propriedadeNome)){
                return p;
            }
        }

        throw new PropriedadeNotFoundException("A propriedade de nome " + propriedadeNome + " não foi encontrada");
    }

    private void populaListaDeBairros(){
        this.bairroValorPorMetroQuadradoMap.put("Fonseca", 1000.0);
        this.bairroValorPorMetroQuadradoMap.put("Icarai", 4000.0 );
        this.bairroValorPorMetroQuadradoMap.put("Santa Rosa", 2500.0);
        this.bairroValorPorMetroQuadradoMap.put("Mutondo", 700.0);
        this.bairroValorPorMetroQuadradoMap.put("Vila Madalena", 12130.0);
        this.bairroValorPorMetroQuadradoMap.put("Vila Olimpia", 11650.0);
        this.bairroValorPorMetroQuadradoMap.put("Pinheiros", 12610.0);
    }

    private void populaListaDePropriedades(){
         List<Comodo> comodos = new ArrayList<>();
         comodos.add(new Comodo("Sala", 12.5, 15.5));
         comodos.add(new Comodo("Quarto", 10.5, 14.5));
         this.propriedades.add(new Propriedade("Mansão", "Fonseca", comodos));
    }

    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
