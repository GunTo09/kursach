package ru.uvarov.ystu.kursach.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.uvarov.ystu.kursach.exceptions.ExceptionHandler;
import ru.uvarov.ystu.kursach.model.Seller;
import ru.uvarov.ystu.kursach.repository.SellerRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SellerServiceImpl implements SellerService{

    private final SellerRepository sellerRepository;

    private final ExceptionHandler exceptionHandler;

    @Override
    public void addSeller (Seller seller){
        sellerRepository.save(seller);
    }

    @Override
    public Page<Seller> getAllSeller(int page) {
        int size = 5;
        return sellerRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }

    @Override
    public Optional<Seller> putSellerById(Long id, Seller newSeller) {
        Optional<Seller> existingSeller =sellerRepository.findById(id);
        if (existingSeller.isPresent()){
            Seller sellerToUpdate = existingSeller.get();
            if(newSeller.getNameSeller() != null) {
                sellerToUpdate.setNameSeller(newSeller.getNameSeller());
            }
            if(newSeller.getSurnameSeller() != null) {
                sellerToUpdate.setSurnameSeller(newSeller.getSurnameSeller());
            }
            if (newSeller.getNumTelephoneSeller() != null){
                sellerToUpdate.setNumTelephoneSeller(newSeller.getNumTelephoneSeller());
            }
            sellerRepository.save(sellerToUpdate);
        }
        return existingSeller;
    }

    @Override
    public void deleteSellerById(Long id) {
        sellerRepository.deleteById(id);
    }


}
