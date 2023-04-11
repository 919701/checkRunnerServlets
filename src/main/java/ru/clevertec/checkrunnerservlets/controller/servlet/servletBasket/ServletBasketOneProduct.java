package ru.clevertec.checkrunnerservlets.controller.servlet.servletBasket;

import ru.clevertec.checkrunnerservlets.model.Basket;
import ru.clevertec.checkrunnerservlets.repository.Repository;
import ru.clevertec.checkrunnerservlets.repository.impl.RepositoryBasketImpl;
import ru.clevertec.checkrunnerservlets.util.connection.ConnectionManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/basket_one_product")
public class ServletBasketOneProduct extends HttpServlet {

    private final Repository<Basket, Long> repositoryBasket = new RepositoryBasketImpl(ConnectionManager.open());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        request.setAttribute("product", repositoryBasket.find(id));
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/view/basket_one_product.jsp");
        dispatcher.forward(request, response);
    }
}
