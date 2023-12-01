package ru.acceleration.store.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.exceptions.DataNotFoundException;
import ru.acceleration.store.service.category.CategoryServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(
        properties = "db.name=store-electronics",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class CategoryServiceIntegrationTest {
    private final CategoryServiceImpl service;
    private final ObjectMapper mapper;
    private final JdbcTemplate jdbcTemplate;


    CategoryIncomeDto dto1, dto2, dto3, dto4;

    @BeforeEach
    public void createEntities() throws Exception {
        dto1 = new CategoryIncomeDto("category1", null);
        dto2 = new CategoryIncomeDto("category2", null);
        dto3 = new CategoryIncomeDto("category3", null);
        dto4 = new CategoryIncomeDto("category4", null);
    }

    @Test
    public void createCategory_validCategory_succeed() {
        assertThat(service.createCategory(dto1))
                .hasFieldOrPropertyWithValue("name", "category1")
                .hasFieldOrProperty("id");
    }

    @Test
    public void findById_forVolodya_succeed() {
        Long categoryId = service.createCategory(dto1).getId();
        assertThat(service.findCategoryById(categoryId))
                .hasFieldOrPropertyWithValue("name", dto1.getName())
                .hasFieldOrPropertyWithValue("id", categoryId);
    }

    @Test
    public void createCategory_notUniqueName_exceptionThrown() {
        dto2.setName(dto1.getName());
        service.createCategory(dto1);
        assertThatThrownBy(() -> service.createCategory(dto2)).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    public void createCategory_wrongParentCategory_exception() {
        dto3.setParentCategoryId(1L);
        assertThatThrownBy(() -> service.createCategory(dto3))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    public void findChildCategoriesByParentId_childsExist_validListSize() {
        Long parentId = service.createCategory(dto1).getId();
        service.createCategory(new CategoryIncomeDto("category2", parentId));
        service.createCategory(new CategoryIncomeDto("category3", parentId));
        assertThat(service.findChildCategoriesByParentId(parentId)).hasSize(2);
    }

    @Test
    public void findChildCategoriesByParentId_noChilds_emptyList() {
        Long parentId = service.createCategory(dto1).getId();
        assertThat(service.findChildCategoriesByParentId(parentId)).hasSize(0);
    }

    @Test
    public void findRoots_someRoots_validListSize() {
        Long root1Id = service.createCategory(dto1).getId();
        Long root2Id = service.createCategory(dto2).getId();
        dto3.setParentCategoryId(root1Id);
        dto4.setParentCategoryId(root1Id);
        service.createCategory(dto3);
        service.createCategory(dto4);
        assertThat(service.findRoots()).hasSize(2);
    }

    @Test
    public void updateCategory_updateName_validReturn() {
        Long parentId = service.createCategory(dto1).getId();
        assertThat(service.findCategoryById(parentId)).hasFieldOrPropertyWithValue("name", "category1");

        String updatedName = "updated_category1";
        assertThat(service.updateCategory(new CategoryIncomeDto("updatedName", null), parentId))
                .hasFieldOrPropertyWithValue("name", "updatedName");
    }

    @Test
    public void deleteCategory_byValidId_succeed() {
        Long categoryId = service.createCategory(dto1).getId();

        service.deleteCategoryById(categoryId);

        assertThatThrownBy(() -> service.findCategoryById(categoryId)).isInstanceOf(DataNotFoundException.class);
    }
}
