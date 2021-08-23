package br.com.dataagil.web.converte.data;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {

	@Override
    public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
        return (entityValue==null) ? null : java.sql.Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
        return (databaseValue==null) ? null : databaseValue.toLocalDate();
    }
    
    
    
}
