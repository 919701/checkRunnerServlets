package ru.clevertec.checkrunnerservlets.controller.servlet.servletBasket;

import ru.clevertec.checkrunnerservlets.model.Basket;
import ru.clevertec.checkrunnerservlets.model.Product;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryBasketImpl;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryProductImpl;
import ru.clevertec.checkrunnerservlets.util.connection.ConnectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/basket_single_product")
public class ServletBasketSingleProduct extends HttpServlet {

    private final Repository<Product, Long> repositoryProduct = new RepositoryProductImpl(ConnectionManager.open());
    private final Repository<Basket, Long> repositoryBasket = new RepositoryBasketImpl(ConnectionManager.open());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Product product = repositoryProduct.find(id);
        Basket basket = new Basket();
        basket.setNameProduct(product.getName());
        basket.setPriceProduct(product.getPrice());
        basket.setDiscountProduct(product.getDiscount());
        request.setAttribute("basket", basket);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/basket_single_product.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name_product");
        Double price = Double.valueOf(request.getParameter("price_product"));
        Double quantity = Double.valueOf(request.getParameter("quantity_product"));
        Boolean discount = Boolean.valueOf(request.getParameter("discount_product"));
        Double totalPrice = price * quantity;
        Double discountPercent = 0d;
//                Double.valueOf(request.getParameter("discount_card_percent"));
        Double totalDiscount = 0d;
//        Double.valueOf(request.getParameter("total_discount"));

        Basket basket = new Basket(name, price, quantity, discount, totalPrice, discountPercent, totalDiscount);
        repositoryBasket.insert(basket);

        response.sendRedirect(request.getContextPath()+"/all_products");
    }
}
