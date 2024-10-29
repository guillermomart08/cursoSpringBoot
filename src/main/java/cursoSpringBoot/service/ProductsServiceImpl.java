package cursoSpringBoot.service;

import cursoSpringBoot.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service("listResourceService")
public class ProductsServiceImpl implements ProductService{

     List<Product> products = new ArrayList<>(Arrays.asList(
             new Product(1, "Ordenador", 799.99, 10),
             new Product(2, "Tablet", 300.00, 5),
             new Product(3, "Telefono", 650.99, 4),
             new Product(4, "Smartwatch", 299.99, 15)
     ));

     @Override
     public List<Product> getProducts(){
         return products;
     }

}
