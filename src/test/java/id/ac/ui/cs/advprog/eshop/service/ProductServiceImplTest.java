package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product();
        sampleProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        sampleProduct.setProductName("Sampo Cap Bambang");
        sampleProduct.setProductQuantity(100);
    }

    @Test
    void testCreate() {
        when(productRepository.create(sampleProduct)).thenReturn(sampleProduct);

        Product result = productService.create(sampleProduct);

        assertNotNull(result);
        assertEquals(sampleProduct.getProductId(), result.getProductId());
        assertEquals(sampleProduct.getProductName(), result.getProductName());
        verify(productRepository, times(1)).create(sampleProduct);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(sampleProduct);
        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(sampleProduct.getProductId(), result.get(0).getProductId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindAllEmpty() {
        List<Product> emptyList = new ArrayList<>();
        Iterator<Product> iterator = emptyList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdFound() {
        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .thenReturn(Optional.of(sampleProduct));

        Product result = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(result);
        assertEquals(sampleProduct.getProductId(), result.getProductId());
        verify(productRepository, times(1)).findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById("nonexistent-id"))
                .thenReturn(Optional.empty());

        Product result = productService.findById("nonexistent-id");

        assertNull(result);
        verify(productRepository, times(1)).findById("nonexistent-id");
    }

    @Test
    void testUpdate() {
        when(productRepository.update(sampleProduct)).thenReturn(sampleProduct);

        Product result = productService.update(sampleProduct);

        assertNotNull(result);
        assertEquals(sampleProduct.getProductId(), result.getProductId());
        verify(productRepository, times(1)).update(sampleProduct);
    }

    @Test
    void testDeleteFound() {
        when(productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6"))
                .thenReturn(true);

        boolean result = productService.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertTrue(result);
        verify(productRepository, times(1)).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

    @Test
    void testDeleteNotFound() {
        when(productRepository.delete("nonexistent-id"))
                .thenReturn(false);

        boolean result = productService.delete("nonexistent-id");

        assertFalse(result);
        verify(productRepository, times(1)).delete("nonexistent-id");
    }
}
