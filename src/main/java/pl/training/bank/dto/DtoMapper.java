package pl.training.bank.dto;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class DtoMapper {

    private MessageSource messageSource;

    public DtoMapper(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public <Source, DestinationType> DestinationType map(Source source, Class<DestinationType> type) {
        return new ModelMapper().map(source, type);
    }

    public <SourceElement, DestinationType> List<DestinationType> map(List<SourceElement> source, Class<DestinationType> type) {
        ModelMapper modelMapper = new ModelMapper();
        return source.stream()
                .map(element -> modelMapper.map(element, type))
                .collect(Collectors.toList());
    }

    public ExceptionDto map(Exception ex, Locale locale) {
        String description = messageSource.getMessage(ex.getClass().getSimpleName(), null, locale);
        return new ExceptionDto(description);
    }

}
