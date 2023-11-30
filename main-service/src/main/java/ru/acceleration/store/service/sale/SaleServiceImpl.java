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
import ru.acceleration.store.model.Model;
import ru.acceleration.store.model.Sale;
import ru.acceleration.store.repository.SaleRepository;
import ru.acceleration.store.service.model.ModelService;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final ModelService modelService;

    /**
     * Метод создает скидку к товару.
     * Нельзя добавить скидку повторно к одному и тому же товару.
     *
     * @param newSaleDto Данные добавляемой скидки
     * @return SaleDto - созданная скидка
     */
    @Override
    public SaleDto addSale(NewSaleDto newSaleDto) {
        Model model = modelService.getExistingModel(newSaleDto.getModelId());

        // Проверка на наличие скидки у товара
        Sale checkExistingSale = saleRepository.findSaleByModelId(model.getId());
        if (checkExistingSale != null) {
            throw new ConflictException(String.format("Sale for model with id=%d already exists. Cannot add another sale.", model.getId()));
        }

        Sale sale = saleMapper.toSale(newSaleDto);
        sale.setModel(model);

        Sale savedSale = saleRepository.save(sale);
        log.info("Added new sale for model with ID: {}, sale: {}", model.getId(), savedSale);
        return saleMapper.toSaleDto(savedSale);
    }

    /**
     * Метод изменяет поля скидки, прикрепленной к товару.
     * Можно изменить name, quantity или переместить скидку в другой существующий баннер.
     *
     * @param saleId Id товара
     * @param updateSaleDto данные для обновления
     * @return обновленный объект скидки
     */
    @Override
    public SaleDto editSale(Long saleId, UpdateSaleDto updateSaleDto) {
        Sale existingSale = getExistingSale(saleId);
        existingSale.setPercent(updateSaleDto.getPercent());

        Sale updatedSale = saleRepository.save(existingSale);
        log.info("Updated sale with ID: {}, new data: {}", saleId, updatedSale);
        return saleMapper.toSaleDto(updatedSale);
    }

    @Override
    public void deleteSale(Long saleId) {
        Sale saleToDelete = getExistingSale(saleId);
        saleRepository.delete(saleToDelete);
        log.info("Deleted sale with ID: {}", saleToDelete.getId());
    }

    @Override
    public Sale getExistingSale(Long saleId) {
        return saleRepository.findById(saleId).orElseThrow(() -> {
            throw new DataNotFoundException(String.format("Sale with id=%d was not found", saleId));
        });
    }
}
