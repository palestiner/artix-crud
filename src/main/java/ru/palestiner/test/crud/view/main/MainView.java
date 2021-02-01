package ru.palestiner.test.crud.view.main;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import ru.palestiner.test.crud.entity.Customer;
import ru.palestiner.test.crud.service.CustomerService;


@Route(value = "")
public class MainView extends VerticalLayout {

    private final CustomerService customerService;

    private Grid<Customer> customerGrid = new Grid<>(Customer.class);

    public MainView(CustomerService customerService) {
        this.customerService = customerService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        add(customerGrid);
        updateList();
    }

    private void configureGrid() {
        customerGrid.addClassName("customer-grid");
        customerGrid.setSizeFull();
        customerGrid.setColumns("name", "gender", "dayOfBirth");
    }

    private void updateList() {
        customerGrid.setItems(customerService.findAll());
    }
}
