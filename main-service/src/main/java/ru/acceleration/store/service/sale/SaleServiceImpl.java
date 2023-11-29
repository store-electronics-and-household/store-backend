package ru.acceleration.store.service.sale;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.acceleration.store.dto.sale.NewSaleDto;
import ru.acceleration.store.dto.sale.SaleDto;
import ru.acceleration.store.dto.sale.UpdateSaleDto;
import ru.acceleration.store.exceptions.ConflictException;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.mapper.SaleMapper;
import ru.acceleration.store.model.Product;
import ru.acceleration.store.model.Promotion;
import ru.acceleration.store.model.Sale;
import ru.acceleration.store.repository.SaleRepository;
import ru.acceleration.store.service.product.ProductService;
import ru.acceleration.store.service.promotion.PromotionService;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ProductService productService;
    private final PromotionService promotionService;

    /**
     * Метод создает скидку к товару.
     * Скидку можно добавить в баннер, если указан promotionId и он создан заранее.
     * Нельзя добавить скидку повторно к одному и тому же товару.
     *
     * @param productId Id товара
     * @param newSaleDto Данные добавляемой скидки
     * @return SaleDto - созданная скидка
     */
    @Override
    public SaleDto addSale(Long productId, NewSaleDto newSaleDto) {
        Product product = productService.getExistingProduct(productId);

        // Проверка на наличие скидки у товара
        Sale checkExistingSale = saleRepository.findSaleByProductId(productId);
        if (checkExistingSale != null) {
            throw new ConflictException(String.format("Sale for product with id=%d already exists. Cannot add another sale.", productId));
        }

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

    /**
     * Метод изменяет поля скидки, прикрепленной к товару.
     * Можно изменить name, quantity или переместить скидку в другой существующий баннер.
     *
     * @param productId Id товара
     * @param updateSaleDto данные для обновления
     * @return обновленный объект скидки
     */
    @Override
    public SaleDto editSale(Long productId, UpdateSaleDto updateSaleDto) {
        Sale existingSale = saleRepository.findSaleByProductId(productId);
        if (existingSale == null) {
            throw new DataNotFoundException(String.format("Sale not found for product with id=%d", productId));
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
