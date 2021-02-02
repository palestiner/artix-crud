package ru.palestiner.test.crud.converter;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;
import ru.palestiner.test.crud.entity.enums.Gender;


public class GenderConverter implements Converter<String, Gender> {

    @Override
    public Result<Gender> convertToModel(String code, ValueContext context) {
        return code == null ? null : Result.ok(Gender.getGender(code));
    }

    @Override
    public String convertToPresentation(Gender gender, ValueContext context) {
        return gender != null ? gender.getCode() : "";
    }
}
