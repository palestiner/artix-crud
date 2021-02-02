package ru.palestiner.test.crud.view.editor;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import ru.palestiner.test.crud.entity.Customer;
import ru.palestiner.test.crud.entity.enums.Gender;
import ru.palestiner.test.crud.converter.GenderConverter;
import ru.palestiner.test.crud.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UIScope
@SpringComponent
public class CustomerEditor extends VerticalLayout implements KeyNotifier {
    private final CustomerService customerService;

    private Customer customer;
    TextField name;
    Select<String> gender;
    DatePicker dayOfBirth;

    Button save;
    Button cancel;
    Button delete;
    HorizontalLayout actions;

    Binder<Customer> binder = new Binder<>(Customer.class);

    private ChangeHandler changeHandler;

    @Autowired
    public CustomerEditor(CustomerService customerService) {
        this.customerService = customerService;
        name = new TextField("Name");
        name.setWidth("350px");
        gender = new Select<>();
        gender.setLabel("Gender");
        final List<String> gendersList = Stream
                .of(Gender.values())
                .map(Gender::getCode)
                .filter(s -> !s.equals("NONE"))
                .collect(Collectors.toList());
        gender.setItems(gendersList);
        dayOfBirth = new DatePicker("Day of Birth");
        save = new Button("Save", VaadinIcon.CHECK.create());
        cancel = new Button("Cancel");
        delete = new Button("Delete", VaadinIcon.TRASH.create());
        actions = new HorizontalLayout(save, delete, cancel);

        add(name, gender, dayOfBirth, actions);

        binder.forField(gender)
                .withConverter(new GenderConverter())
                .bind(Customer::getGender, Customer::setGender);
        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());
        addKeyPressListener(Key.ESCAPE, e -> cancel());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> cancel());
        setVisible(false);
        cancel.setVisible(true);
    }

    private boolean isExistCustomer(Customer customer) {
        if (customer == null) {
            setVisible(false);
            return true;
        }
        return false;
    }

    private void cancel() {
        setVisible(false);
    }

    public void editCustomer(Customer customer) {
        if (isExistCustomer(customer)) return;
        final boolean persisted = customer.getId() != null;
        if (persisted) {
            this.customer = customerService.findById(customer.getId());
        } else {
            this.customer = customer;
        }
        binder.setBean(this.customer);
        dayOfBirth.setValue(this.customer.getDayOfBirth());
        setVisible(true);
        name.focus();
    }

    private void save() {
        customerService.save(customer);
        changeHandler.onChange();
    }

    private void delete() {
        customerService.delete(customer);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public void setChangeHandler(ChangeHandler changeHandler) {
        this.changeHandler = changeHandler;
    }

}
