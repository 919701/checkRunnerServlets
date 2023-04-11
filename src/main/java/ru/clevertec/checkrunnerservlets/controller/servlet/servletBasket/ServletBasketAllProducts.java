package ru.clevertec.checkrunnerservlets.controller.servlet.servletBasket;

import ru.clevertec.checkrunnerservlets.model.Basket;
import ru.clevertec.checkrunnerservlets.model.DiscountCard;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryBasketImpl;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryDiscountCardImpl;
import ru.clevertec.checkrunnerservlets.util.connection.ConnectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/basket_all_products")
public class ServletBasketAllProducts extends HttpServlet {

    private final Repository<Basket, Long> repositoryBasket = new RepositoryBasketImpl(ConnectionManager.open());
    private final Repository<DiscountCard, Integer> repositoryCard = new RepositoryDiscountCardImpl(ConnectionManager.open());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Basket> list = repositoryBasket.findAll();

        int card = 1234;
        double percent = (100-repositoryCard.find(card).getDiscountPercent())/100;

        list.stream()
                .peek(basket -> basket.setTotalPrice(basket.getPriceProduct() * basket.getQuantityProduct()))
                .peek(basket -> basket.setTotalDiscount(basket.getTotalPrice() * percent))
                .toList();

        request.setAttribute("basketList", list);
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/basket_all_products.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
