package ru.clevertec.checkrunnerservlets.controller.servlet.servletBasket;

import ru.clevertec.checkrunnerservlets.model.Basket;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryBasketImpl;
import ru.clevertec.checkrunnerservlets.util.connection.ConnectionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/basket_all_products")
public class ServletBasketAllProducts extends HttpServlet {

    private final Repository<Basket, Long> repositoryBasket = new RepositoryBasketImpl(ConnectionManager.open());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("basketList", repositoryBasket.findAll());
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/basket_all_products.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
