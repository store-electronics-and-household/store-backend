package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.NewSaleDto;
import ru.acceleration.store.dto.SaleDto;
import ru.acceleration.store.dto.UpdateSaleDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.SaleMapper;
import ru.acceleration.store.model.Product;
import ru.acceleration.store.model.Promotion;
import ru.acceleration.store.model.Sale;
import ru.acceleration.store.repository.SaleRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ProductService productService;
    private final PromotionService promotionService;

    @Override
    public SaleDto addSale(Long productId, NewSaleDto newSaleDto) {
        Product product = productService.getExistingProduct(productId);
        Sale sale = saleMapper.toSale(newSaleDto);
        sale.setProduct(product);

        if (newSaleDto.getPromotionId() != null) {
            Promotion promotion = promotionService.getPromotionById(newSaleDto.getPromotionId());
            sale.setPromotion(promotion);
        }

        Sale savedSale = saleRepository.save(sale);
        log.info("Added new sale for product with ID: {}, sale: {}", productId, savedSale);
        return saleMapper.toSaleDto(savedSale);
    }

    @Override
    public SaleDto editSale(Long productId, UpdateSaleDto updateSaleDto) {
        Sale existingSale = saleRepository.findSaleByProductId(productId);
        if (existingSale == null) {
            throw new DataNotFoundException(String.format("Product with id=%d was not found", productId));
        }
        if (updateSaleDto.getName() != null) {
            existingSale.setName(updateSaleDto.getName());
        }
        if (updateSaleDto.getQuantity() != null) {
            existingSale.setQuantity(updateSaleDto.getQuantity());
        }
        if (updateSaleDto.getPromotionId() != null) {
            Promotion promotion = promotionService.getPromotionById(updateSaleDto.getPromotionId());
            existingSale.setPromotion(promotion);
        }

        Sale updatedSale = saleRepository.save(existingSale);
        log.info("Updated sale with ID: {}, new data: {}", updatedSale.getId(), updatedSale);
        return saleMapper.toSaleDto(updatedSale);
    }

    @Override
    public void deleteSale(Long productId) {
        Sale saleToDelete = saleRepository.findSaleByProductId(productId);
        saleRepository.delete(saleToDelete);
        log.info("Deleted sale with ID: {}", saleToDelete.getId());
    }
}
