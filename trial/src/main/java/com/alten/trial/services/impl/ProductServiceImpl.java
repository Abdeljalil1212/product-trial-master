package com.alten.trial.services.impl;

import com.alten.trial.dtos.ProductRequest;
import com.alten.trial.dtos.ProductResponse;
import com.alten.trial.models.Product;
import com.alten.trial.repositories.ProductRepository;
import com.alten.trial.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    /**
     * Crée un nouveau produit.
     *
     * @param request Objet contenant les informations du produit à créer.
     * @return Le produit sauvegardé en base.
     * @throws Exception Si la requête est invalide.
     */
    public ProductResponse createProduct(ProductRequest request) throws Exception {
        try {
            if (request == null) {
                log.error("La requête du produit ne peut pas être null.");
                throw new Exception("La requête du produit ne peut pas être null.");
            }

            // Mapping automatique avec ObjectMapper
            Product product = objectMapper.convertValue(request, Product.class);

            product = productRepository.save(product);
            log.info("Produit créé avec succès");
            return objectMapper.convertValue(product, ProductResponse.class);

        } catch (Exception e) {
            log.error("Une erreur inattendue est survenue.", e);
            throw new Exception("Une erreur inattendue est survenue.", e);
        }
    }


    /**
     * Récupère la liste de tous les produits.
     *
     * @return Liste des produits sous forme de DTO.
     * @throws Exception En cas d'erreur.
     */
    public List<ProductResponse> getAllProducts() throws Exception {
        try {
            List<ProductResponse> productResponses = new ArrayList<>();
            List<Product> products = productRepository.findAll();
            if (!products.isEmpty()) {
                for (Product product : products) {
                    productResponses.add(objectMapper.convertValue(product, ProductResponse.class));
                }
            }
            return productResponses;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des produits.", e);
            throw new Exception("Erreur lors de la récupération des produits.", e);
        }
    }

    /**
     * Récupère un produit par son id.
     *
     * @return un produit sous forme de DTO.
     * @throws Exception En cas d'erreur.
     */
    public ProductResponse getProductById(Long idP) throws Exception {
        try {
            Product product = findProductById(idP);
            return objectMapper.convertValue(product, ProductResponse.class);
        } catch (Exception e) {
            log.error("Erreur lors de la récupération du produit avec l'ID : " + idP, e);
            throw new Exception("Erreur lors de la récupération du produit avec l'ID : " + idP, e);
        }
    }


    /**
     * Modifier un produit.
     *
     * @return un produit sous forme de DTO.
     * @throws Exception En cas d'erreur.
     */
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws Exception {
        try {
            Product product = findProductById(id);
            objectMapper.updateValue(product, productRequest);
            product = productRepository.save(product);
            return objectMapper.convertValue(product, ProductResponse.class);
        } catch (Exception e) {
            log.error("Erreur lors de la modification du produit avec : " + id, e);
            throw new Exception("Erreur lors de la modification du produit avec : " + id, e);
        }
    }

    /**
     * Supprimer un produit.
     *
     * @throws Exception En cas d'erreur.
     */
    public void deleteProduct(Long id) throws Exception {
        try {
            Product product = findProductById(id);
            productRepository.delete(product);
        } catch (Exception e) {
            log.error("Erreur lors de la supression du produit avec : " + id, e);
            throw new Exception("Erreur lors de la supression du produit avec : " + id, e);
        }
    }

    private Product findProductById(Long id) throws Exception {
        return productRepository.findById(id)
                .orElseThrow(() -> new Exception("Produit non trouvé avec l'ID : " + id));
    }
}
