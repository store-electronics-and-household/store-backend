package ru.acceleration.store.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import ru.acceleration.store.dto.category.CategoryIncomeDto;
import ru.acceleration.store.exceptions.BadRequestException;
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

    CategoryIncomeDto dto1, dto2, dto3, dto4;

    @BeforeEach
    public void createEntities() throws Exception {
        dto1 = new CategoryIncomeDto("category1", null, null);
        dto2 = new CategoryIncomeDto("category2", null, null);
        dto3 = new CategoryIncomeDto("category3", null, null);
        dto4 = new CategoryIncomeDto("category4", null, null);
    }

    @Test
    public void createCategory_validCategory_succeed() {
        assertThat(service.createCategory(dto1))
                .hasFieldOrPropertyWithValue("name", dto1.getName())
                .hasFieldOrProperty("id");
    }

    @Test
    public void findById_validId_succeed() {
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
        service.createCategory(new CategoryIncomeDto("category2", parentId, null));
        service.createCategory(new CategoryIncomeDto("category3", parentId, null));
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
    public void updateCategory_updateName_succeed() {
        Long parentId = service.createCategory(dto1).getId();
        assertThat(service.findCategoryById(parentId)).hasFieldOrPropertyWithValue("name", "category1");


        String updatedName = "updatedName";
        dto1.setName(updatedName);
        assertThat(service.updateCategory(dto1, parentId, false, false))
                .hasFieldOrPropertyWithValue("name", "updatedName");
    }

    @Test
    public void updateCategory_removeParentId_succeed() {
        Long parentId = service.createCategory(dto1).getId();
        dto2.setParentCategoryId(parentId);
        Long childId = service.createCategory(dto2).getId();

        assertThat(service.findChildCategoriesByParentId(parentId)).hasSize(1);
        dto2.setParentCategoryId(null);
        service.updateCategory(dto2, childId, true, false);

        assertThat(service.findChildCategoriesByParentId(parentId)).hasSize(0);
    }

    @Test
    public void updateCategory_removeImageLink_succeed() {
        dto1.setImageLink("image link");
        Long id = service.createCategory(dto1).getId();

        assertThat(service.findCategoryById(id))
                .hasFieldOrPropertyWithValue("imageLink", "image link");
        dto1.setImageLink(null);
        assertThat(service.updateCategory(dto1, id, false, true))
                .hasFieldOrPropertyWithValue("imageLink", null);
    }

    @Test
    public void updateCategory_changeParentId_succeed() {
        Long parentId1 = service.createCategory(dto1).getId();
        Long parentId2 = service.createCategory(dto2).getId();
        dto3.setParentCategoryId(parentId1);
        Long childId = service.createCategory(dto3).getId();

        assertThat(service.findChildCategoriesByParentId(parentId1)).hasSize(1);
        assertThat(service.findChildCategoriesByParentId(parentId2)).hasSize(0);
        dto3.setParentCategoryId(parentId2);
        service.updateCategory(dto3, childId, false, false);

        assertThat(service.findChildCategoriesByParentId(parentId1)).hasSize(0);
        assertThat(service.findChildCategoriesByParentId(parentId2)).hasSize(1);
    }

    @Test
    public void updateCategory_changeImage_succeed() {
        Long catId = service.createCategory(dto1).getId();

        dto1.setImageLink("new image link");

        assertThat(service.updateCategory(dto1, catId, false, false))
                .hasFieldOrPropertyWithValue("imageLink", "new image link");
    }

    @Test
    public void deleteCategory_byValidId_succeed() {
        Long categoryId = service.createCategory(dto1).getId();

        service.deleteCategoryById(categoryId);

        assertThatThrownBy(() -> service.findCategoryById(categoryId)).isInstanceOf(DataNotFoundException.class);
    }

    @Test
    public void deleteCategory_withSubCategories_exceptionThrown() {
        Long parentId = service.createCategory(dto1).getId();
        dto2.setParentCategoryId(parentId);
        Long childId = service.createCategory(dto2).getId();

        assertThatThrownBy(() -> service.deleteCategoryById(parentId)).isInstanceOf(BadRequestException.class);
    }
}
