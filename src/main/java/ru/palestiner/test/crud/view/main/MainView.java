package ru.palestiner.test.crud.view.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import ru.palestiner.test.crud.entity.Customer;
import ru.palestiner.test.crud.service.CustomerService;
import ru.palestiner.test.crud.view.editor.CustomerEditor;


@Route(value = "")
public class MainView extends VerticalLayout {

    private final CustomerService customerService;
    private final Grid<Customer> customerGrid;
    private final CustomerEditor customerEditor;
    private final Button addBtn;


    public MainView(CustomerService customerService, CustomerEditor customerEditor) {
        this.customerEditor = customerEditor;
        customerGrid = new Grid<>(Customer.class);
        this.customerService = customerService;
        addBtn = new Button("New customer");
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        HorizontalLayout addLayout = new HorizontalLayout(addBtn);
        add(addLayout, customerGrid, customerEditor);
        updateList();
        customerEditor.setChangeHandler(() -> {
            customerEditor.setVisible(false);
            updateList();
        });
        customerGrid.asSingleSelect().addValueChangeListener(e -> {
            customerEditor.editCustomer(e.getValue());
        });
        addBtn.addClickListener(e -> customerEditor.editCustomer(new Customer()));
    }

    private void configureGrid() {
        customerGrid.addClassName("customer-grid");
        customerGrid.setSizeFull();
        customerGrid.setColumns("id", "name", "gender", "dayOfBirth");
        customerGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateList() {
        customerGrid.setItems(customerService.findAll());
    }
}
