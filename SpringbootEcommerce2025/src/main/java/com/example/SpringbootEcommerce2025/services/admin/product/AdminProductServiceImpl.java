package com.example.SpringbootEcommerce2025.services.admin.product;

import com.example.SpringbootEcommerce2025.dto.ProductDto;
import com.example.SpringbootEcommerce2025.entity.Category;
import com.example.SpringbootEcommerce2025.entity.Product;
import com.example.SpringbootEcommerce2025.repository.CategoryRepository;
import com.example.SpringbootEcommerce2025.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminProductServiceImpl implements AdminProductService{

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;


    public AdminProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product= new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

        Category category=categoryRepository.findById(productDto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepository.save(product).getDto();

    }

    public List<ProductDto> getAllProducts(){
        List<Product> products= productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());

    }

    public List<ProductDto> getAllProductsByName(String name){
        List<Product> products= productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());

    }

    public boolean deleteProduct(Long id){
        Optional<Product> optionalProduct=productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public ProductDto getProductById(Long productId){
        Optional<Product> optionalProduct=productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            return optionalProduct.get().getDto();
        }else{
            return null;
        }
    }

    public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct=productRepository.findById(productId);
        Optional<Category> optionalCategory=categoryRepository.findById(productDto.getCategoryId());
        if(optionalProduct.isPresent() && optionalCategory.isPresent()){
            Product product= optionalProduct.get();

            product.setName((productDto.getName()));
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(optionalCategory.get());

            if(productDto.getImg() !=null){
                product.setImg(productDto.getImg().getBytes());
            }

            return productRepository.save(product).getDto();
        }else{
            return null;
        }
    }
}
