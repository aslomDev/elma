package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.CardOfElma;
import com.mycompany.myapp.domain.enumeration.credit;
import com.mycompany.myapp.repository.CardOfElmaRepository;
import com.mycompany.myapp.service.dto.entityDto.HumoCardDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.ZoneId;

@Service
public class CrudService {
    private final CardOfElmaRepository cardOfElmaRepository;

    public CrudService(CardOfElmaRepository cardOfElmaRepository) {
        this.cardOfElmaRepository = cardOfElmaRepository;
    }


    public Mono<?> save(HumoCardDTO cardDTO){

               CardOfElma card = new CardOfElma();
               card.setBalance(cardDTO.getBalance());
               if (cardDTO.getCredit()){
                   card.setCardTypeElma(credit.CREDIT);
               }else {
                   card.setCardTypeElma(credit.DEBIT);
               }
               card.setExpireDate(cardDTO.getExpireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
               card.setPan(cardDTO.getPan());
               card.setMaskedPan(card.getMaskedPan());
               card.setTypeCard(cardDTO.getCardType());
               return cardOfElmaRepository.save(card).flatMap(Mono::just);
    }
}
