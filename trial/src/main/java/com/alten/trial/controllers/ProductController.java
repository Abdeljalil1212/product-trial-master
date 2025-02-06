package com.alten.trial.controllers;

import com.alten.trial.dtos.ProductRequest;
import com.alten.trial.dtos.ProductResponse;
import com.alten.trial.models.Product;
import com.alten.trial.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * Crée un nouveau produit.
     *
     * @param request Données du produit à créer.
     * @return Le produit créé.
     */
    @Operation(summary = "Sauvgarder une nouveau produit.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Product.class))
            })
    })
    @PostMapping("/createNewProduct")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) throws Exception {
        ProductResponse product = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    /**
     * Récupère tous les produits.
     *
     * @return Liste de produits sous forme de DTO.
     */
    @Operation(summary = "Récupère tous les produits.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit supprimé avec succès", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé avec l'ID fourni", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content)
    })
    @GetMapping("/retrieveAllProducts")
    public ResponseEntity<List<ProductResponse>> getAllProducts() throws Exception {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Récupère un produit par son id.
     *
     * @return produit sous forme de DTO.
     */
    @Operation(summary = "Récupère un produit par son id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit supprimé avec succès", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé avec l'ID fourni", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content)
    })
    @GetMapping("/retrieveProductById/{id}")
    public ResponseEntity<ProductResponse> getProductByID(@PathVariable Long id) throws Exception {
        ProductResponse product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    /**
     * Modifier un produit.
     *
     * @return produit modifié sous forme de DTO.
     */
    @Operation(summary = "Modifier un produit.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit supprimé avec succès", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé avec l'ID fourni", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content)
    })
    @GetMapping("/updateProduct/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) throws Exception {
        ProductResponse product = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(product);
    }

    /**
     * Supprimer un produit par son id.
     */
    @Operation(summary = "Supprimer un produit par son id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit supprimé avec succès", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé avec l'ID fourni", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur interne du serveur", content = @Content)
    })
    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<String> deleteProductByID(@PathVariable Long id) throws Exception {
        productService.deleteProduct(id);
        String message = "Produit avec ID : " + id + " supprimé avec succès.";
        return ResponseEntity.ok(message);

    }
}
