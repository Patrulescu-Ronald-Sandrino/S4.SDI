package ro.lab10.domain.convertors;

import ro.lab10.domain.Agency;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class AgencyConvertor implements Convertor<Agency> {
    private final String FIELD_SEPARATOR = "#";

    @Override
    public String toMessage(Agency agency) {
        return Stream.of(agency.getId().toString(), agency.getName(), agency.getAddress())
                .reduce("", (s, s2) -> s + FIELD_SEPARATOR + s2);
    }

    @Override
    public Agency fromMessage(String message) {
        var tokens = Convertor.getTokens(message, FIELD_SEPARATOR);
        var id = Long.parseLong(tokens.get(0));
        var name = tokens.get(1);
        var address = tokens.get(2);

        return new Agency(id, name, address);
    }
}
