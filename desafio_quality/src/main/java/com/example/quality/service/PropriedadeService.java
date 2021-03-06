package com.example.quality.service;

import com.example.quality.dto.ComodoDTO;
import com.example.quality.dto.M2PorComodoDTO;
import com.example.quality.dto.PropriedadeDTO;
import com.example.quality.entity.Comodo;
import com.example.quality.entity.Propriedade;
import com.example.quality.exception.BairroNotFoundException;
import com.example.quality.exception.PropriedadeNotFoundException;
import com.example.quality.mapper.ComodoMapper;
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

    public void salvaPropriedade(PropriedadeDTO propriedadeDTO) throws BairroNotFoundException {
        verifyIfBairroExists(propriedadeDTO.getBairro());
        Propriedade propriedade = new Propriedade(propriedadeDTO.getNome(), propriedadeDTO.getBairro(), ComodoMapper.postsDTOtoComodosList(propriedadeDTO.getComodos()));
        propriedades.add(propriedade);
    }

    public Double getTotalM2Propriedade(String propriedadeNome) throws PropriedadeNotFoundException {
        Propriedade propriedade = verifyIfPropriedadeExists(propriedadeNome);
        return Math.round(propriedade.totalMetrosQuadradosDaPropriedade()*100.0)/100.0;
    }

    public Double getValorPropriedade(String propriedadeNome) throws PropriedadeNotFoundException {
        Propriedade propriedade = verifyIfPropriedadeExists(propriedadeNome);
        String bairro = deAccent(propriedade.getBairro());
        Double valorM2 = bairroValorPorMetroQuadradoMap.get(bairro);
        return Math.round((propriedade.totalMetrosQuadradosDaPropriedade()*valorM2)*100.0)/100.0;
    }

    public ComodoDTO getMaiorComodo(String propriedadeNome) throws PropriedadeNotFoundException {
        Propriedade propriedade = verifyIfPropriedadeExists(propriedadeNome);
        return ComodoMapper.comodoToComodoDTO(propriedade.maiorComodo());
    }

    public List<M2PorComodoDTO> getM2PorComodo(String propriedadeNome) throws PropriedadeNotFoundException {
        Propriedade propriedade = verifyIfPropriedadeExists(propriedadeNome);
        Map<String, Double> map =  propriedade.metrosQuadradosDeCadaComodo();
        List<M2PorComodoDTO> comodos = new ArrayList<>();
        for(Map.Entry<String, Double> pair: map.entrySet()){
            comodos.add(new M2PorComodoDTO(pair.getKey(), pair.getValue()));
        }
        return comodos;
    }

    public boolean verifyIfBairroExists(String nomeBairro) throws BairroNotFoundException {
         String bairro = deAccent(nomeBairro);
         for(Map.Entry<String, Double> pair: bairroValorPorMetroQuadradoMap.entrySet()){
             if(pair.getKey().equalsIgnoreCase(bairro)){
                 return true;
             }
         }
         throw new BairroNotFoundException("O bairro de nome " + nomeBairro + " n??o existe na base de Bairros");
    }

    public Propriedade verifyIfPropriedadeExists(String propriedadeNome) throws PropriedadeNotFoundException {
        for(Propriedade p: propriedades){
            if(p.getNome().equalsIgnoreCase(propriedadeNome)){
                return p;
            }
        }
        throw new PropriedadeNotFoundException("A propriedade de nome " + propriedadeNome + " n??o foi encontrada");
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
         this.propriedades.add(new Propriedade("Mans??o", "Fonseca", comodos));
    }

    private static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }
}
