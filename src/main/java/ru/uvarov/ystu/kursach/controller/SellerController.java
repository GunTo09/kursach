package ru.uvarov.ystu.kursach.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.uvarov.ystu.kursach.model.Seller;
import ru.uvarov.ystu.kursach.repository.SellerRepository;
import ru.uvarov.ystu.kursach.service.SellerService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/seller")
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    ResponseEntity<Void> addSeller(@RequestBody @Valid Seller seller,
                                   BindingResult bindingResult){
        sellerService.addSeller(seller);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<Page<Seller>> getAllEmployees(@RequestParam int page){
        return ResponseEntity.ok(sellerService.getAllSeller(page));
    }

    @GetMapping("/{id}")
    ResponseEntity<Seller> getSellerById(@PathVariable Long id){
        Optional<Seller> sellerOptional = sellerService.getSellerById(id);
        return sellerOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    ResponseEntity<Seller> updateSellerById(@PathVariable Long id, @RequestBody Seller newSeller) {
        Optional<Seller> newSellerOptional = sellerService.putSellerById(id, newSeller);
        return newSellerOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSellerById(@PathVariable Long id){
        sellerService.deleteSellerById(id);
        return ResponseEntity.ok().build();
    }


}
